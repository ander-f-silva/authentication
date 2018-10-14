package br.com.authentication.respository.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.authentication.json.customer.LocalDateTimeSerializer;

/**
 * Entidade que respresenta os dados do Usuario
 *
 * @author anderson
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

    private static final long serialVersionUID = -7449015679025828479L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @NotEmpty
    @Column(name = "NAME", length = 150)
    private String name;

    @NotEmpty
    @Column(name = "EMAIL", length = 100)
    private String email;

    @NotEmpty
    @Column(name = "PASSWORD", length = 100)
    private String password;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "CREATED")
    private LocalDateTime created;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "MODIFIED")
    private LocalDateTime modified;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "LAST_LOGIN")
    private LocalDateTime lastLogin;

    @Column(name = "TOKEN")
    private String token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Phone> phones = new HashSet<Phone>();

    @Deprecated
    public User() { }

    public User(String name, String email, String password, Set<Phone> phones, LocalDateTime start, String token) {
        this.name = name;
        this.email = email;
        this.password = password;

        if (phones != null && !phones.isEmpty()) {
            phones.stream().forEach(phone -> phone.setUser(this));
        }

        this.phones = phones;
        this.created = start;
        this.lastLogin = start;
        this.token = token;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("phones")
    public Set<Phone> getPhones() {
        return phones;
    }

    @JsonProperty("create")
    public LocalDateTime getCreated() {
        return created;
    }

    @JsonProperty("last_login")
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    @JsonProperty("modified")
    public LocalDateTime getModified() {
        return modified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(created, user.created) &&
                Objects.equals(modified, user.modified) &&
                Objects.equals(lastLogin, user.lastLogin) &&
                Objects.equals(token, user.token) &&
                Objects.equals(phones, user.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, created, modified, lastLogin, token, phones);
    }
}
