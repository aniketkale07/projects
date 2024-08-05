package scm.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AddContact {
    
    private String name;
    private String contact1;
    private String contact2;
    private String gmail;
    private String about;
    private byte[] profilePhoto;

}
