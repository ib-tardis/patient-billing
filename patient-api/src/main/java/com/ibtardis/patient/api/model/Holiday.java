package com.ibtardis.patient.api.model;

import java.util.Date;

public class Holiday {
    public String holidayName;
    public Date holidayDate;
    public Date createdDate;
    public Date modificationDate;
    public int createdBy;
    public int modifiedBy;

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
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

    public Holiday(String holidayName, Date holidayDate, Date createdDate, Date modificationDate, int createdBy, int modifiedBy) {
        this.holidayName = holidayName;
        this.holidayDate = holidayDate;
        this.createdDate = createdDate;
        this.modificationDate = modificationDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }
}
