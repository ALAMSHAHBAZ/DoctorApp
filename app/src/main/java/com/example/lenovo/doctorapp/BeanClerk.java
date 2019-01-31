package com.example.lenovo.doctorapp;

/**
 * Created by lenovo on 7/23/2017.
 */

public class BeanClerk {
    String name;
    String id;
    byte [] ClerkPic;
    String password;
    String email;
    String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getClerkPic() {
        return ClerkPic;
    }

    public void setClerkPic(byte[] clerkPic) {
        ClerkPic = clerkPic;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
