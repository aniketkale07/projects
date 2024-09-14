package scm.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AddContactForm {

    private Long contactId;
    private String name;
    private String primaryContact;
    private String email;
    private String about;

    // This will not be stored in the database, only used for upload processing
    private MultipartFile contactImage; 
    
    // Store the URL of the image in the database
    private String contactImageUrl;

    private String github;
    private String linkedin;
}
