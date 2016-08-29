package br.com.authentication.respository.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
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

import org.hibernate.validator.constraints.NotEmpty;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private Set<Phone> phones = new HashSet<Phone>();

    public User() {

    }

    public User(String name, String email, String password, Set<Phone> phones, LocalDateTime start, String token) {
	this.name = name;
	this.email = email;
	this.password = password;
	
	if (phones != null && !phones.isEmpty()) {
	    for (Phone phone : phones) {
		phone.setUser(this);
	    }
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

    public void setId(Long id) {
	this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @JsonProperty("email")
    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @JsonProperty("password")
    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @JsonProperty("phones")
    public Set<Phone> getPhones() {
	return phones;
    }

    public void setPhones(Set<Phone> phones) {
	this.phones = phones;
    }

    @JsonProperty("create")
    public LocalDateTime getCreated() {
	return created;
    }

    public void setCreated(LocalDateTime created) {
	this.created = created;
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

    public void setToken(String token) {
	this.token = token;
    }

    @JsonProperty("modified")
    public LocalDateTime getModified() {
	return modified;
    }

    public void setModified(LocalDateTime modified) {
	this.modified = modified;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	User other = (User) obj;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
