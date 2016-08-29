package br.com.authentication.respository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entidade que respresenta os dados do Telefone
 * 
 * @author anderson
 */
@Entity
@Table(name = "PHONE")
public class Phone implements Serializable {

    private static final long serialVersionUID = 4655244518822017432L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @JsonIgnore
    private Long id;

    @JsonIgnore
    @ManyToOne
    private User user;

    @Column(name = "NUMBER", length = 9)
    private Long number;

    @Column(name = "DDD", length = 3)
    private Short ddd;

    public Phone() {

    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    @JsonProperty("number")
    public Long getNumber() {
	return number;
    }

    public void setNumber(Long number) {
	this.number = number;
    }

    @JsonProperty("ddd")
    public Short getDdd() {
	return ddd;
    }

    public void setDdd(Short ddd) {
	this.ddd = ddd;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((ddd == null) ? 0 : ddd.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((number == null) ? 0 : number.hashCode());
	result = prime * result + ((user == null) ? 0 : user.hashCode());
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
	Phone other = (Phone) obj;
	if (ddd == null) {
	    if (other.ddd != null)
		return false;
	} else if (!ddd.equals(other.ddd))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (number == null) {
	    if (other.number != null)
		return false;
	} else if (!number.equals(other.number))
	    return false;
	if (user == null) {
	    if (other.user != null)
		return false;
	} else if (!user.equals(other.user))
	    return false;
	return true;
    }
}
