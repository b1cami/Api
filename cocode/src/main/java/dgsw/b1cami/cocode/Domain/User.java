package dgsw.b1cami.cocode.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;

    @Column(nullable = false, unique = true, length = 320)
    String userEmail;

    @Column(nullable = false)
    String userPassword;

    @Column(nullable = false, length = 100)
    String userName;

    @Column(nullable = true, unique = true, length = 4)
    String userSchoolNumber;

    @Column(nullable = true)
    String userImage;

    @Column(nullable = true)
    String userCertifyCode;

    public User(String email) {
        this.setEmail(email);
    }

    public Integer getId() {
        return userId;
    }

    public void setId(Integer id) {
        this.userId = id;
    }

    public String getEmail() {
        return userEmail;
    }

    public void setEmail(String email) {
        this.userEmail = email;
    }

    public String getPassword() {
        return userPassword;
    }

    public void setPassword(String password) {
        this.userPassword = password;
    }

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public String getSchoolNumber() {
        return userSchoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.userSchoolNumber = schoolNumber;
    }

    public String getImage() {
        return userImage;
    }

    public void setImage(String image) {
        this.userImage = image;
    }

    public String getCertifyCode() {
        return userCertifyCode;
    }

    public void setCertifyCode(String certifyCode) {
        this.userCertifyCode = certifyCode;
    }

}