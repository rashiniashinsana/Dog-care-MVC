package lk.ijse.dogCareClinic.dto;

public class UserDto {
    private String username;
    private String password;
    private String confirm_Password;
    private String email;

    public UserDto(String username, String password, String confirm_Password, String email) {
        this.username = username;
        this.password = password;
        this.confirm_Password = confirm_Password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getConfirm_Password() {
        return confirm_Password;
    }

    public void setConfirm_Password(String confirm_Password) {
        this.confirm_Password = confirm_Password;
    }
}
