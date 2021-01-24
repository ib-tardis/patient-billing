package com.ibtardis.patient.api.model;

import java.util.Date;

public class Visit {
    public int id;
    public Date dateTime;
    public int physicianId;
    public String reason;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public Visit(int id, Date dateTime, int physicianId, String reason, Date createdDateTime, Date modifiedDateTime, int createdBy, int modifiedBy) {
        this.id = id;
        this.dateTime = dateTime;
        this.physicianId = physicianId;
        this.reason = reason;
        this.createdDateTime = createdDateTime;
        this.modifiedDateTime = modifiedDateTime;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public Visit (int id){
        this.id = id;
    }
}
