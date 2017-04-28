package web.product.models;

import java.sql.Date;

public class User {

    private int id;
    private String login;
    private String password;
    private Date age;
    private String email;

    public User() {}

    public User(int id, String login) {
        this.id = id;
        this.login = login;
    } // TODO: Guest

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
