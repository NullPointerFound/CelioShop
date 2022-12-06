package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.product.ProductCategory;
import com.malik.CelioShop.CelioShop.entity.product.ProductMedia;
import com.malik.CelioShop.CelioShop.entity.user.User;
import com.malik.CelioShop.CelioShop.exception.ResourceAlreadyExist;
import com.malik.CelioShop.CelioShop.exception.ResourceNotFound;
import com.malik.CelioShop.CelioShop.playload.ProductDto;
import com.malik.CelioShop.CelioShop.repository.ProductCategoryRepository;
import com.malik.CelioShop.CelioShop.repository.ProductRepository;
import com.malik.CelioShop.CelioShop.repository.UserRepository;
import com.malik.CelioShop.CelioShop.service.ProductService;
import com.malik.CelioShop.CelioShop.service.ServiceHelper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;
    private ModelMapper modelMapper;
    private ServiceHelper serviceHelper;

    @Override
    public ProductDto createProduct(ProductDto productDto, String categoryName, MultipartFile media) throws IOException {

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


        //  Check if there is a product in DB with the same SKU
        Optional<Product> productExist = productRepository.findBySku(newProduct.getSku());

        if ( productExist.isPresent()){
            throw new ResourceAlreadyExist("Product","SKU",newProduct.getSku());
        }

        if(media != null){
            Set<ProductMedia> medias = uploadMedia(media,newProduct);
            newProduct.setProductMedia(medias);
        }

        newProduct.setUser(user);

        // Save the new product to DB
        Product savedProduct = productRepository.save(newProduct);

        // Return the entity after we convert to DTO
        return modelMapper.map(savedProduct,ProductDto.class);
    }


    public Set<ProductMedia> uploadMedia(MultipartFile multipartFiles, Product product) throws IOException {

        Set<ProductMedia> productMediaSet = new HashSet<>();

        String FOLDER_PATH = "/upload_dir/";
        byte[] bytes = multipartFiles.getBytes();

        Path path = Paths.get(FOLDER_PATH, multipartFiles.getOriginalFilename());

        ProductMedia productMedia = new ProductMedia();
        productMedia.setName(multipartFiles.getOriginalFilename());
        productMedia.setType(multipartFiles.getContentType());
        productMedia.setFilePath(String.valueOf(path));
        productMedia.setProductId(product);

        productMediaSet.add(productMedia);

//      file.transferTo(path);
        Files.write(path, bytes);

        return productMediaSet;
    }

    public Set<ProductMedia> uploadMedia(MultipartFile[] multipartFiles) throws IOException {
        Set<ProductMedia> productMediaSet = new HashSet<>();

        String FOLDER_PATH = "/upload_dir/";

        for ( MultipartFile file : multipartFiles  ){

            byte[] bytes = file.getBytes();
            Path path = Paths.get(FOLDER_PATH, file.getOriginalFilename());

            ProductMedia productMedia = new ProductMedia(
                    file.getOriginalFilename(), file.getContentType(), String.valueOf(path)
            );
            productMediaSet.add(productMedia);
//            file.transferTo(path);
            Files.write(path, bytes);
        }

        return productMediaSet;
    }






    @Override
    public ProductDto getProductById(Long productId) {
        // Retrieve the product by ID and if it doesn't exist we throw a Resource not found exception
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFound("Product","ID",productId)
        );

        // we return the product found after we convert it to DTO
        return modelMapper.map(product,ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {

        List<Product> products = productRepository.findAll();

        // Convert the entities found to DTOs
        List<ProductDto> productDtoList = products.stream().map(
                product -> modelMapper.map(product,ProductDto.class)
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
    public void updateProductById(Long productId) {

    }

    @Override
    public List<ProductDto> getProductsByCategoryId(Long categoryId) {

        ProductCategory productCategory = productCategoryRepository.findById(categoryId).orElseThrow(
                ()->  new ResourceNotFound("Category","ID",categoryId)
        );
            List<Product> productList = productRepository.findByProductCategory(productCategory);

            return productList.stream().map(
                        product -> modelMapper.map(product,ProductDto.class)
                        ).collect(Collectors.toList());

        }

        @Override
        public  List<ProductDto> searchProduct(String query){

            System.out.println("Malik: "+query);
            List<Product> productsFound = productRepository.searchProduct(query);
            System.out.println(productsFound);
            List<ProductDto> productFoundDto = productsFound.stream().map(
                product -> modelMapper.map(product,ProductDto.class)
                ).collect(Collectors.toList());

        return productFoundDto;
        }




}
