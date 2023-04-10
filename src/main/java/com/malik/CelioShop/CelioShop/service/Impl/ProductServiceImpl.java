package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.product.ProductCategory;
import com.malik.CelioShop.CelioShop.entity.product.ProductMedia;
import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.exception.ResourceAlreadyExist;
import com.malik.CelioShop.CelioShop.exception.ResourceNotFound;
import com.malik.CelioShop.CelioShop.playload.ProductDto;
import com.malik.CelioShop.CelioShop.playload.ProductDtoResponse;
import com.malik.CelioShop.CelioShop.repository.ProductCategoryRepository;
import com.malik.CelioShop.CelioShop.repository.ProductRepository;
import com.malik.CelioShop.CelioShop.repository.UserRepository;
import com.malik.CelioShop.CelioShop.service.ProductService;
import com.malik.CelioShop.CelioShop.service.ServiceHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;
    private ModelMapper modelMapper;
    private ServiceHelper serviceHelper;

    @Override
    public ProductDtoResponse createProduct(ProductDto productDto, String categoryName) {

        // find the authenticated user
        User user = serviceHelper.getAuthenticatedUser();

        // Convert from DTO to Entity
        Product newProduct = modelMapper.map(productDto,Product.class);

        // Check if the category exist
        if (categoryName != null) {

            ProductCategory category = productCategoryRepository
                    .findByName(categoryName)
                    .orElseThrow(
                            () -> new ResourceNotFound("Category","name",categoryName)
                    );

            // If the category exist we set the product to that category
            newProduct.setProductCategory(category);
        }


        checkSkuExist(newProduct.getSku());

        newProduct.setUser(user);

        // Save the new product to DB
        Product savedProduct = productRepository.save(newProduct);

        // Return the entity after we convert to DTO
        return modelMapper.map(savedProduct, ProductDtoResponse.class);
    }

    private void checkSkuExist(String skuNumber) {
        //  Check if there is a product in DB with the same SKU
        Optional<Product> productExist = productRepository.findBySku(skuNumber);

        if ( productExist.isPresent()){
            throw new ResourceAlreadyExist("Product","SKU", skuNumber);
        }
    }

    @Override
    public ProductDtoResponse getProductById(Long productId) {
        // Retrieve the product by ID and if it doesn't exist we throw a Resource not found exception
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFound("Product","ID",productId)
        );

        // we return the product found after we convert it to DTO
        return modelMapper.map(product,ProductDtoResponse.class);
    }

    @Override
    public List<ProductDtoResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();


        // Convert the entities found to DTOs
        List<ProductDtoResponse> productDtoList = products.stream().map(
                product -> modelMapper.map(product,ProductDtoResponse.class)
                ).collect(Collectors.toList());

        return productDtoList;
    }

    @Override
    public void deleteProductById(Long productId) {
        // Retrieve the product by ID and if it doesn't exist we throw a Resource not found exception
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFound("Product","ID",productId)
        );

        // Delete the product
        productRepository.delete(product);
    }

    @Override
    public ProductDtoResponse updateProductById(Long productId, ProductDto productDto) {

        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFound("Product","ID",productId)
        );

        if (productDto.getName() != null){
            product.setName(productDto.getName());
        }

        if (productDto.getDescription() != null){
            product.setDescription(productDto.getDescription());
        }

        if (productDto.getSku() != null){
            checkSkuExist(productDto.getSku());
            product.setSku(productDto.getSku());
        }

        if (productDto.getPrice() != null){
            product.setPrice(productDto.getPrice());
        }

        if (productDto.getQuantity() != null){
            product.setQuantity(productDto.getQuantity());
        }
        return modelMapper.map(productRepository.save(product), ProductDtoResponse.class);
    }

    @Override
    public List<ProductDtoResponse> getProductsByCategoryId(Long categoryId) {

        ProductCategory productCategory = productCategoryRepository.findById(categoryId).orElseThrow(
                ()->  new ResourceNotFound("Category","ID",categoryId)
        );
            List<Product> productList = productRepository.findByProductCategory(productCategory);

            return productList.stream().map(
                        product -> modelMapper.map(product,ProductDtoResponse.class)
                        ).collect(Collectors.toList());

        }

        @Override
        public  List<ProductDtoResponse> searchProduct(String query){


            List<Product> productsFound = productRepository.searchProduct(query);

            List<ProductDtoResponse> productFoundDto = productsFound.stream().map(
                product -> modelMapper.map(product,ProductDtoResponse.class)
                ).collect(Collectors.toList());

        return productFoundDto;
        }




}
