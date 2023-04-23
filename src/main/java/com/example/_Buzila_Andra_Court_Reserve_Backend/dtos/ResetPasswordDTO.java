package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ResetPasswordDTO
{
    //Fields:
    @NotNull
    private String email;

    //Criptat sau Decriptat:
    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;


    //Constructor without id:
    public ResetPasswordDTO(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    //Empty constructor:
    public ResetPasswordDTO()
    {
    }

    //Getters and Setters:
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
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    //Equals:
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResetPasswordDTO that = (ResetPasswordDTO) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(confirmPassword, that.confirmPassword);
    }

    //Hash:
    @Override
    public int hashCode() {
        return Objects.hash(email, password, confirmPassword);
    }
}
