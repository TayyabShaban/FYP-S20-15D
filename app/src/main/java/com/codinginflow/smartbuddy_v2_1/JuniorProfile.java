package com.codinginflow.smartbuddy_v2_1;

public class JuniorProfile {
    private String Name,Email;
    private Boolean isJunior;

    public JuniorProfile(){
    }

    public JuniorProfile(String name, String email, Boolean isjunior) {
        this.Email = email;
        this.Name = name;
    }

    public String getJuniorEmail() {
        return Email;
    }

    public void setJuniorEmail(String email) {
        this.Email = email;
    }

    public String getJuniorName() {
        return Name;
    }

    public void setJuniorName(String name) {
        this.Name = name;
    }

    public Boolean getJuniorStatus() {
        return isJunior;
    }

    public void setJuniorStatus(Boolean isjunior) {
        this.isJunior = isjunior;
    }
}
