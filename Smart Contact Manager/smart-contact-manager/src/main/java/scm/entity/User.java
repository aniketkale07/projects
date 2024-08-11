 package scm.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.authenticator.jaspic.PersistentProviderRegistrations.Providers;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="user")
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
 public class User {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long userId;

@Column(nullable=false)
private String fName;
private String mName;
private String lName;

@Column(nullable = false, unique = true)
private String email;

private String contact1;
private String contact2;

@NotNull
private String password;


@Column(length = 1000)
private String about;

private byte[] profilePhoto;


// Verification of gmail
private boolean gmailVerified=false;
private boolean isEnabled=false;
private boolean isLogin=false;
private boolean phoneVerified=false;


// @Enumerated(value = EnumType.STRING)
// SELF, GOOGLE , FACEBOOK, TWITTER Linkden
// private Providers providers = Providers.class;
// private String providerUserId;

@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
private ArrayList<Contact> contact;
    

}