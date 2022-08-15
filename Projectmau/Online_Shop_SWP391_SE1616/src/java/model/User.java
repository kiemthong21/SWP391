/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class User {
    private int userID;
    private String phone;
    private String name;
    private boolean gender;
    private String password;
    private String email;
    private String address;
    private Setting role;
    private Date registeredAt;
    private Date lastLogin;
    private String avatar;
    private Date dob;
    private Setting status;

    public User() {
    }

    public User(int userID, String phone, String name, boolean gender, String password, String email, String address, Setting role, Date registeredAt, Date lastLogin, String avatar, Date dob, Setting status) {
        this.userID = userID;
        this.phone = phone;
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.email = email;
        this.address = address;
        this.role = role;
        this.registeredAt = registeredAt;
        this.lastLogin = lastLogin;
        this.avatar = avatar;
        this.dob = dob;
        this.status = status;
    }
    
    

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Setting getRole() {
        return role;
    }

    public void setRole(Setting role) {
        this.role = role;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Setting getStatus() {
        return status;
    }

    public void setStatus(Setting status) {
        this.status = status;
    }

  
    
    
}
