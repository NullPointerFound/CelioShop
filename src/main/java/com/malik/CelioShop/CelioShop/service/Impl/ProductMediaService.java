package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.product.ProductMedia;
import com.malik.CelioShop.CelioShop.repository.ProductMediaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
@Service
public class ProductMediaService {

    private ProductMediaRepository productMediaRepository;

    private final String FOLDER_PATH = "/upload_dir/";

    public String uploadMediaToUploadDir(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH+file.getOriginalFilename();


        ProductMedia productMedia = productMediaRepository
                        .save(ProductMedia.builder()
                                .name(file.getOriginalFilename())
                                .type(file.getContentType())
                                //.filePath("")
                                .build() );

        file.transferTo(new File(filePath));

        if (productMedia != null){
            return "File uploaded successfully";
        }
        return null;
    }

//    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
//        Optional<ProductMedia> dbMedia = productMediaRepository.findByName(fileName);
//        Path filePath = dbMedia.get().getFilePath();
//        byte[] images = Files.readAllBytes(new File(filePath).toPath());
//        return images;
//    }

}
