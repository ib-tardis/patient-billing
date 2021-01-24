package com.ibtardis.patient.api.model;

import java.util.Date;

public class Patient {
    int id;
    String name;
    int age;
    String gender;
    Date createdTime;
    Date modifiedTime;
    int createdBy;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return this.modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public int getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Patient(int id, String name, int age, String gender, Date createdTime, Date modifiedTime, int createdBy) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.createdBy = createdBy;
    }

    public Patient(int id){
        this.id = id;
    }
}
