package aditya.SpringSecurity.JWT_Security6.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Setter
@Getter
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String userName;
    String passwrod;

    public User() {
    }

    public User(Integer id, String userName, String passwrod) {
        this.id = id;
        this.userName = userName;
        this.passwrod = passwrod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswrod() {
        return passwrod;
    }

    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + userName + '\'' +
                ", password='" + passwrod + '\'' +
                '}';
    }
}
