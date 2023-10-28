package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.entity.product.ProductMedia;
import com.malik.CelioShop.CelioShop.exception.ResourceNotFound;
import com.malik.CelioShop.CelioShop.repository.ProductMediaRepository;
import com.malik.CelioShop.CelioShop.repository.ProductRepository;
import com.malik.CelioShop.CelioShop.service.MediaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.malik.CelioShop.CelioShop.utils.AppConstants.FOLDER_MEDIA_PATH;

@AllArgsConstructor
@Service
public class MediaServiceImpl implements MediaService {

    private ProductMediaRepository productMediaRepository;
    private ProductRepository productRepository;
    private ServiceHelper serviceHelper;

    @Override
    public String uploadMedia(MultipartFile media, long productId) throws IOException{

        Product product = serviceHelper.getProductByIdOrThrowNotFoundException(productId);

        String relativeMediaPath = FOLDER_MEDIA_PATH + media.getOriginalFilename();

        ProductMedia mediaSaved = productMediaRepository.save(ProductMedia.builder()
                .name(media.getOriginalFilename()).type(media.getContentType()).path(relativeMediaPath).product(product).build());

        // Get the absolute path of the current working directory.
        String projectDirectory = System.getProperty("user.dir");

        // Combine the project directory and the relative path to get the absolute media path.
        String absoluteMediaPath = projectDirectory + "/" + relativeMediaPath;


        media.transferTo(new File(absoluteMediaPath));


        return mediaSaved != null ? "file uploaded" : null ;
    }

    @Override
    public byte[] downloadMedia(String mediaName) throws IOException {

        ProductMedia mediaObj = productMediaRepository.findByName(mediaName).orElseThrow(

                () -> new ResourceNotFound("Media", "Name", mediaName)
        );

        String mediaPath = mediaObj.getPath();

        // Get the absolute path of the current working directory.
        String projectDirectory = System.getProperty("user.dir");

        // Combine the project directory and the relative path to get the absolute media path.
        String absoluteMediaPath = projectDirectory + "/" + mediaPath;

        byte[] media = Files.readAllBytes(new File(absoluteMediaPath).toPath());
        return media;

    }
}
