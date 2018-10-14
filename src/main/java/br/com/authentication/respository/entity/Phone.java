package br.com.authentication.respository.entity;

import java.io.Serializable;
import java.util.Objects;

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

    @Deprecated
    public Phone() {

    }

    public Long getId() {
        return id;
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

    @JsonProperty("ddd")
    public Short getDdd() {
        return ddd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(id, phone.id) &&
                Objects.equals(user, phone.user) &&
                Objects.equals(number, phone.number) &&
                Objects.equals(ddd, phone.ddd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, number, ddd);
    }
}
