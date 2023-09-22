package com.malik.CelioShop.CelioShop.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MediaService {

    public String uploadMedia(MultipartFile media, long productId) throws IOException;

    public byte[] downloadMedia(String mediaName) throws IOException;

}
