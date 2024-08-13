package scm.entity;

import java.util.ArrayList;
import java.util.List;

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

/**
 * Represents a user entity in the system.
 */
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

    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    /**
     * First name of the user.
     * Example: "John"
     */
    @Column(nullable=false)
    private String firstName;

    /**
     * Middle name of the user.
     * Example: "Michael"
     */
    private String middleName;

    /**
     * Last name of the user.
     * Example: "Doe"
     */
    private String lastName;

    /**
     * Email address of the user.
     * Must be unique and not null.
     * Example: "johndoe@example.com"
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Primary contact number of the user.
     * Example: "1234567890"
     */
    private String contact1;

    /**
     * Secondary contact number of the user.
     * Example: "0987654321"
     */
    private String contact2;

    /**
     * Password for the user.
     * Must not be null.
     * Example: "password123"
     */
    @NotNull
    private String password;

    /**
     * About me description of the user.
     * Maximum length of 1000 characters.
     * Example: "I am a software engineer"
     */
    @Column(length = 1000)
    private String about;

    /**
     * Profile photo of the user.
     */
    private byte[] profilePhoto;

    /**
     * Indicates whether the user's Gmail account is verified.
     * Default value: false
     */
    @Builder.Default
    private boolean gmailVerified=false;

    /**
     * Indicates whether the user's account is enabled.
     * Default value: false
     */
    @Builder.Default
    private boolean isEnabled=false;

    /**
     * Indicates whether the user is currently logged in.
     * Default value: false
     */
    @Builder.Default
    private boolean isLogin=false;

    /**
     * Indicates whether the user's phone number is verified.
     * Default value: false
     */
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
}