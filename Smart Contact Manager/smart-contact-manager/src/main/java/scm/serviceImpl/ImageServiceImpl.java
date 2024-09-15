package scm.serviceImpl;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

import scm.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {


    private Cloudinary cloudinary;
    
    public ImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
    String filename = UUID.randomUUID().toString();
    @Override
    public String uploadImage(MultipartFile contactImage) {
        
       
        try {
            byte[] data = new byte[contactImage.getInputStream().available()];
            contactImage.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                "public_id", filename
            ));
            return this.getUrlFromPublicId(filename);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        
        return cloudinary
        .url()
        .transformation(
            new Transformation<>()
            .width("CONTACT_IMAGE_WIDTH")
            .height("CONTACT_IMAGE_HEIGHT")
            .crop("CONTACT_IMAGE_CROP")
        )
        .generate(publicId);
        
    }
    
}
