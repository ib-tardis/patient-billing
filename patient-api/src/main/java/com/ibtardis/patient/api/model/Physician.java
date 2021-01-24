package com.ibtardis.patient.api.model;

import java.util.Date;

public class Physician {
    public int id;
    public String name;
    public Date createdDateTime;
    public Date modifiedDateTime;
    public int createdBy;
    public int modifiedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Date getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(Date modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Physician(int id, String name, Date createdDateTime, Date modifiedDateTime, int createdBy, int modifiedBy) {
        this.id = id;
        this.name = name;
        this.createdDateTime = createdDateTime;
        this.modifiedDateTime = modifiedDateTime;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public Physician(int id){
        this.id = id;
    }
}
