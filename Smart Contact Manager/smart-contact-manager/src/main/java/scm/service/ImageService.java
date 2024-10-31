package scm.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uploadImage(MultipartFile contactImage, String filename);

    // work is progress
    String getUrlFromPublicId(String publicId);
}
