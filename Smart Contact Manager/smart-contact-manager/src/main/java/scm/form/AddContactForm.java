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

    private MultipartFile contactImage;

    private String github;
    private String linkedin;

}
