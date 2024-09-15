package scm.entity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated; 
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name="user")
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    
    private String firstName;
    
    private String middleName;

    
    private String lastName;


    // @Getter(value = AccessLevel.NONE)
    @Column(name="email")
    @NotBlank
    private String email;


    private String contact1;

    
    private String contact2;

    
    private String password;

    
    private String about;

    private MultipartFile profilePhoto;

    @Builder.Default
    private boolean emailVerified=false;

    @Builder.Default
    @Getter(value = AccessLevel.NONE)
    private boolean isEnabled=false;

    @Builder.Default
    private boolean isLogin=false;


    @Builder.Default
    private boolean phoneVerified=false;

    /**
     * Provider type for the user's account.
     * Can be SELF, GOOGLE, FACEBOOK, TWITTER, or LINKEDIN.
     * Default value: SELF
     */
    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private Providers providers = Providers.SELF;

    /**
     * Unique identifier for the user's provider account.
     * Example: "1234567890" for Google provider
     */

    private String providerUserId;

    /**
     * List of contacts associated with the user.
     */
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contact = new ArrayList<>();


    @ElementCollection(fetch =FetchType.EAGER)
private List<String> roleList=new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        Collection<SimpleGrantedAuthority> roles = roleList.stream()
        .map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());

        return roles;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.email;
    }

    @Override
    public boolean isEnabled(){
        return this.isEnabled; 
    }

    @Override
    public String getPassword(){
        return this.password;
    }
}