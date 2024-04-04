package fr.univamu.iut.apiuserplate.model.user;

public class User {
    protected int id;
    protected String username;
    protected String password;
    protected String email;
    protected String deliveryAddress;

    public User() {}

    public User(int id, String username, String password, String email, String deliveryAddress) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
    }

    public User(String username, String password, String email, String deliveryAddress) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
