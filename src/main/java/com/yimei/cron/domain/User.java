package com.yimei.cron.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class User {

    private Long id;

    private String name;

    private String phone;

    private String loginname;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String passwordsalt;

    private String email;

    private Integer activated;

    private String creator;

    private LocalDateTime createtime;

    private String role;

    private String plainpassword ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPasswordsalt() {
        return passwordsalt;
    }

    public void setPasswordsalt(String passwordsalt) {
        this.passwordsalt = passwordsalt == null ? null : passwordsalt.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getPlainpassword() {
        return plainpassword;
    }

    public void setPlainpassword(String plainpassword) {
        this.plainpassword = plainpassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", loginname='" + loginname + '\'' +
                ", password='" + password + '\'' +
                ", passwordsalt='" + passwordsalt + '\'' +
                ", email='" + email + '\'' +
                ", activated=" + activated +
                ", creator='" + creator + '\'' +
                ", createtime=" + createtime +
                ", role='" + role + '\'' +
                ", plainpassword='" + plainpassword + '\'' +
                '}';
    }
}