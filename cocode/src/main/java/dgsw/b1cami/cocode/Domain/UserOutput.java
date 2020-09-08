package dgsw.b1cami.cocode.Domain;

public class UserOutput {

    Long id;

    String email;

    String password;

    String name;

    String schoolNumber;

    String image;

    String certifyCode;

    public UserOutput(User user) {
        this.setId(user.getId());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setName(user.getName());
        this.setSchoolNumber(user.getSchoolNumber());
        this.setImage(user.getImage());
        this.setCertifyCode(user.getCertifyCode());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCertifyCode() {
        return certifyCode;
    }

    public void setCertifyCode(String certifyCode) {
        this.certifyCode = certifyCode;
    }

}