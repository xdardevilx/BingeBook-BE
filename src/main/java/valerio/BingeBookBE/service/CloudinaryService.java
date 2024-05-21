package valerio.BingeBookBE.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import valerio.BingeBookBE.config.StringConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(byte[] imageBytes) {
        try {
            String url = (String) cloudinary.uploader()
                    .upload(imageBytes, ObjectUtils.emptyMap())
                    .get("url");
            return url;
        } catch (IOException e) {
            throw new IllegalArgumentException(StringConfig.errorUploadImage, e);
        }
    }

    public byte[] getBytesFromMultipartFile(MultipartFile multipartFile) {
        try {
            return multipartFile.getBytes();
        } catch (IOException e) {
            throw new IllegalArgumentException(StringConfig.errorUploadImage, e);
        }
    }

    public String uploadImageFromMultipartFile(MultipartFile multipartFile) {
        if (multipartFile.isEmpty())
            throw new IllegalArgumentException(StringConfig.errorUploadImage);
        byte[] imageBytes = getBytesFromMultipartFile(multipartFile);
        return uploadImage(imageBytes);
    }
}

