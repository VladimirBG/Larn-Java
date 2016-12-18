package ru.techcoll.swords.web.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class PlayerRegisterForm {

    @NotBlank
    @Length(min = 4, max = 80)
    private String name;

    @NotBlank
    @Length(min = 4, max = 20)
    private String nickname;

    @NotBlank
    @Length(min = 4, max = 80)
    @Email
    private String email;

    @NotBlank
    @Length(min = 6, max = 20)
    private String password;

    @NotBlank
    @Length(min = 6, max = 20)
    private String passwordConfirmation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

}
