package valerio.BingeBookBE.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import valerio.BingeBookBE.config.StringConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;

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

    ///test
    public MultipartFile convertStringToMultipartFile(String fileString, String fileName) throws IOException {
        // Convert string to byte array
        byte[] bytes = fileString.getBytes(StandardCharsets.UTF_8);
        
        // Create a ByteArrayInputStream from the byte array
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        
        // Create a MultipartFile object
        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return fileName;
            }

            @Override
            public String getContentType() {
                return "application/octet-stream"; // You may need to set the appropriate content type
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return bytes.length;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return bytes;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return inputStream;
            }

            @Override
            public void transferTo(File file) throws IOException, IllegalStateException {
                StreamUtils.copy(inputStream, Files.newOutputStream(file.toPath()));
            }
        };

        return multipartFile;
    }

    // public static Base64 encodeStringToBase64(String inputString) {
    //     // Encode the string to Base64
    //     byte[] encodedBytes = Base64.getEncoder().encode(inputString.getBytes());
    //     Base64 a = new Base64()

    //     return Base64.getEncoder().wrap(encodedBytes);
    // }

    public static String decodeBase64ToString(String base64String) {
        // Decode Base64 to string
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        return new String(decodedBytes);
    }

}

