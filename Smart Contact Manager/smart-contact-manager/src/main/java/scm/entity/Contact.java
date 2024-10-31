
package scm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "contact")
@Table(name = "contact")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contactId;

    @Column(nullable=false)
    private String name;

    private String primaryContact;
   
private String email;
@Column(length=1000)
    private String about;
    private String contactImage;
    private String github;
    private String linkedin;
    private String cloudinaryImagePublicId;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private User user;

    // @JsonIgnore for it will goes Recursivve
    //  IT will break the recursive call of user and contact
   

    
}
