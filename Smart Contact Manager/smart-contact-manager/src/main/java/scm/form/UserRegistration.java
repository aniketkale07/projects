/*
 * THe FORM PACKAGE IS THE DTO PACKAGE 
 * DTO - Data Transfer Object which is works between controller and view i.e 
 * model and view .. ---> it is use to retrive data form the forms and other 
 * model belog to the frontend
 */

package scm.form;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegistration {
    
    @NotNull
    private String fName;
    @NotNull
    private String mName;
    @NotNull
    private String lName;
  
    @NotNull
    private String email;

    @NotNull
    private String password;
@NotNull
    private String about;

    private byte[] profilePhoto;
}
