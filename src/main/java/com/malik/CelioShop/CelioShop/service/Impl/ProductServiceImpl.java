package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.product.ProductCategory;
import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.exception.ResourceAlreadyExist;
import com.malik.CelioShop.CelioShop.exception.ResourceNotFound;
import com.malik.CelioShop.CelioShop.playload.product.PageProductDtoResponse;
import com.malik.CelioShop.CelioShop.playload.product.ProductDto;
import com.malik.CelioShop.CelioShop.playload.product.ProductDtoResponse;
import com.malik.CelioShop.CelioShop.repository.ProductCategoryRepository;
import com.malik.CelioShop.CelioShop.repository.ProductRepository;
import com.malik.CelioShop.CelioShop.service.ProductService;
import com.malik.CelioShop.CelioShop.service.ServiceHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.support.PageableUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Product newProduct = modelMapper.map(productDto, Product.class);

        // Check if the category exist
        if (categoryName != null) {

            ProductCategory category = productCategoryRepository
                    .findByName(categoryName)
                    .orElseThrow(
                            () -> new ResourceNotFound("Category", "name", categoryName)
                    );

            // If the category exist we set the product to that category
            newProduct.setProductCategory(category);
        }

        // checking if the sku is unique or not
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

        if (productExist.isPresent()) {
            throw new ResourceAlreadyExist("Product", "SKU", skuNumber);
        }
    }

    @Override
    public ProductDtoResponse getProductById(Long productId) {
        // Retrieve the product by ID and if it doesn't exist we throw a Resource not found exception
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFound("Product", "ID", productId)
        );

        // we return the product found after we convert it to DTO
        return modelMapper.map(product, ProductDtoResponse.class);
    }

    @Override
    public PageProductDtoResponse getAllProducts(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();


        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        // Retrieve the products
        Page<Product> productsPage = productRepository.findAll(pageable);


        PageProductDtoResponse pageProductDtoResponse = getPageProductDtoResponse(productsPage);

        return pageProductDtoResponse;
    }

    // extract from Page<Product> products and convert them to ProductDto and assign the result to PageProductDtoResponse
    private PageProductDtoResponse getPageProductDtoResponse(Page<Product> productsPage) {

        List<Product> productList = productsPage.getContent();

        // Convert the products found to DTOs
        List<ProductDtoResponse> productDtoList = productList.stream().map(
                product -> modelMapper.map(product, ProductDtoResponse.class)
        ).collect(Collectors.toList());

        // Create PageProductDtoResponse instance
        PageProductDtoResponse pageProductDtoResponse = new PageProductDtoResponse();
        pageProductDtoResponse.setPageNumber(productsPage.getNumber());
        pageProductDtoResponse.setPageSize(productsPage.getSize());
        pageProductDtoResponse.setTotalElements(productsPage.getTotalElements());
        pageProductDtoResponse.setTotalPages(productsPage.getTotalPages());
        pageProductDtoResponse.setLast(productsPage.isLast());
        pageProductDtoResponse.setContent(productDtoList);
        return pageProductDtoResponse;
    }

    @Override
    public void deleteProductById(Long productId) {
        // Retrieve the product by ID and if it doesn't exist we throw a Resource not found exception
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFound("Product", "ID", productId)
        );

        // Delete the product
        productRepository.delete(product);
    }

    @Override
    public ProductDtoResponse updateProductById(Long productId, ProductDto productDto) {

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFound("Product", "ID", productId)
        );

        if (productDto.getName() != null) {
            product.setName(productDto.getName());
        }

        if (productDto.getDescription() != null) {
            product.setDescription(productDto.getDescription());
        }

        if (productDto.getSku() != null) {
            checkSkuExist(productDto.getSku());
            product.setSku(productDto.getSku());
        }

        if (productDto.getPrice() != null) {
            product.setPrice(productDto.getPrice());
        }

        if (productDto.getQuantity() != null) {
            product.setQuantity(productDto.getQuantity());
        }
        return modelMapper.map(productRepository.save(product), ProductDtoResponse.class);
    }

    @Override
    public List<ProductDtoResponse> getProductsByCategoryId(Long categoryId, int pageNumber, int pageSize, String sortBy, String sortDir) {

        ProductCategory productCategory = productCategoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFound("Category", "ID", categoryId)
        );

        List<Product> productList = productRepository.findByProductCategory(productCategory);

        return productList.stream().map(
                product -> modelMapper.map(product, ProductDtoResponse.class)
        ).collect(Collectors.toList());

    }

    @Override
    public PageProductDtoResponse searchProduct(String query, int pageNumber, int pageSize, String sortBy, String sortDir) {


        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageRequest = createPageRequestUsing(pageNumber, pageSize);

        List<Product> productsFound = productRepository.searchProduct(query);

        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), productsFound.size());

        List<Product> pageContent = productsFound.subList(start, end);
        Page<Product> pageableProducts =  new PageImpl<>(pageContent, pageRequest, productsFound.size());

        PageProductDtoResponse pageProductDtoResponse = getPageProductDtoResponse(pageableProducts);


//        List<ProductDtoResponse> productFoundDto = productsFound.stream().map(
//                product -> modelMapper.map(product, ProductDtoResponse.class)
//        ).collect(Collectors.toList());
//
        return pageProductDtoResponse;
    }


    private Pageable createPageRequestUsing(int page, int size) {
        return PageRequest.of(page, size);
    }
}
