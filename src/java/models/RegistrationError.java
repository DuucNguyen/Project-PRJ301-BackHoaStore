/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author admin
 */
public class RegistrationError {
    String usernameLengthErr;
    String passwordLengthErr;
    String confirmNotMatchErr;
    String fullNameLengthErr;
    String usernameExistedErr;
    String emailInvalid;
    public RegistrationError() {
    }

    public RegistrationError(String usernameLengthErr, String passwordLengthErr, String confirmNotMatchErr, String fullNameLengthErr, String usernameExistedErr, String emailInvalid) {
        this.usernameLengthErr = usernameLengthErr;
        this.passwordLengthErr = passwordLengthErr;
        this.confirmNotMatchErr = confirmNotMatchErr;
        this.fullNameLengthErr = fullNameLengthErr;
        this.usernameExistedErr = usernameExistedErr;
        this.emailInvalid = emailInvalid;
    }

    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getConfirmNotMatchErr() {
        return confirmNotMatchErr;
    }

    public void setConfirmNotMatchErr(String confirmNotMatchErr) {
        this.confirmNotMatchErr = confirmNotMatchErr;
    }

    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    public String getUsernameExistedErr() {
        return usernameExistedErr;
    }

    public void setUsernameExistedErr(String usernameExistedErr) {
        this.usernameExistedErr = usernameExistedErr;
    }

    public String getEmailInvalid() {
        return emailInvalid;
    }

    public void setEmailInvalid(String emailInvalid) {
        this.emailInvalid = emailInvalid;
    }

   
            
}
