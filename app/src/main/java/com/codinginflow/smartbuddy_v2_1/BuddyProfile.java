package com.codinginflow.smartbuddy_v2_1;

public class BuddyProfile {
    private String Name,Email,Subject,Reference;

    public BuddyProfile(){
    }

    public BuddyProfile(String name, String email, String subject, String reference) {
        this.Email = email;
        this.Name = name;
        this.Subject=subject;
        this.Reference=reference;
    }

    public String getBuddyEmail() {
        return Email;
    }

    public void setBuddyEmail(String email) {
        this.Email = email;
    }

    public String getBuddyName() {
        return Name;
    }

    public void setBuddyName(String name) {
        this.Name = name;
    }

    public String getBuddySubject() {
        return Subject;
    }

    public void setBuddySubject(String subject) {
        this.Subject = subject;
    }

    public String getBuddyReference() {
        return Reference;
    }

    public void setBuddyreference(String reference) {
        this.Reference = reference;
    }
}
