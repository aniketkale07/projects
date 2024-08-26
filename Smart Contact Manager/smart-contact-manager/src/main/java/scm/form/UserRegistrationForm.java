package scm.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class UserRegistrationForm {

    @NotBlank(message = "First name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First name must contain only letters and spaces")
    private String fName;

    @NotBlank(message = "Last name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Middle name must contain only letters and spaces")
    private String mName;

    @NotBlank(message = "Last name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Last name must contain only letters and spaces")
    private String lName;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "About section cannot be blank")
    private String about;

    @NotBlank(message = "Contact number cannot be blank")
    private String contact1;

    private String contact2;

    private String profilePhoto;
}
