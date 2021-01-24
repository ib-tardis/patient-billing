package com.ibtardis.billing.api.model;

import java.util.Date;

public class Billing {
    int id;
    int patientId;
    int physicianId;
    Date billDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(int physicianId) {
        this.physicianId = physicianId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Billing(int id, int patientId, int physicianId, Date billDate) {
        this.id = id;
        this.patientId = patientId;
        this.physicianId = physicianId;
        this.billDate = billDate;
    }

    public Billing(int id){
        this.id = id;
    }
}
