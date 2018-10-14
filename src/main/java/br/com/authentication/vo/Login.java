package br.com.authentication.vo;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

/**
 * Classe Representa o objeto para realizar o login
 * 
 * @author anderson
 *
 */
public class Login implements Serializable {

    private static final long serialVersionUID = -6618805411652286886L;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @Deprecated
    public Login() {

    }

    public Login(String email, String password) {
	    this.email = email;
	    this.password = password;
    }

    @JsonProperty("email")
    public String getEmail() {
	return email;
    }

    @JsonProperty("password")
    public String getPassword() {
	return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return Objects.equals(email, login.email) &&
                Objects.equals(password, login.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
