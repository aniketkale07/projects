package scm.form;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AddContactForm {
    
    @NotNull
    private String name;
    private String contact1;
    private String contact2;
    private String gmail;
    private String about;
    private byte[] profilePhoto;

}
