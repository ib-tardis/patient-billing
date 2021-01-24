package com.ibtardis.patient.api.controller;

import com.ibtardis.patient.api.dao.HolidayDao;
import com.ibtardis.patient.api.dao.PhysicianDao;
import com.ibtardis.patient.api.exceptions.HolidayException;
import com.ibtardis.patient.api.model.Physician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("physician")
public class PhysicianController {

    @Autowired
    private HolidayDao holidayDao;

    @Autowired
    private PhysicianDao physicianDao;

    @PostMapping("/")
    public ResponseEntity<Physician> createPhysician(@RequestBody Physician postPhysician){
        Physician responsePhysician = null;
        HttpStatus responseCode = null;

        if(!holidayDao.isTodayHoliday()){
            Physician physician = physicianDao.create(postPhysician);
            if(physician != null){
                responsePhysician = physician;
                responseCode = HttpStatus.OK;
            } else {
                responsePhysician = null;
                responseCode = HttpStatus.BAD_REQUEST;
            }
        } else {
            throw new HolidayException("Today is holiday! No data manipulation.");
        }

        return new ResponseEntity<Physician>(responsePhysician, responseCode);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Physician> updatePhysician(@RequestBody Physician postPhysician, @PathVariable int id){
        Physician responsePhysician = null;
        HttpStatus responseCode = null;

        if(!holidayDao.isTodayHoliday()){
            Physician physician = physicianDao.update(postPhysician);
            if(physician.getId() == 0){
                responsePhysician = null;
                responseCode = HttpStatus.NOT_FOUND;
            }
            else if(physician != null && physician.getId() != 0){
                responsePhysician = physician;
                responseCode = HttpStatus.OK;
            } else {
                responsePhysician = null;
                responseCode = HttpStatus.BAD_REQUEST;
            }
        } else {
            throw new HolidayException("Today is holiday! No data manipulation.");
        }

        return new ResponseEntity<Physician>(responsePhysician, responseCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Physician> deletePhysician(@PathVariable int id){
        Physician responsePhysician = null;
        HttpStatus responseCode = null;

        if(!holidayDao.isTodayHoliday()){
            Physician physician = physicianDao.delete(id);
            if(physician != null){
                responsePhysician = physician;
                responseCode = HttpStatus.OK;
            } else {
                responsePhysician = null;
                responseCode = HttpStatus.NOT_FOUND;
            }
        } else {
            throw new HolidayException("Today is holiday! No data manipulation.");
        }

        return new ResponseEntity<Physician>(responsePhysician, responseCode);
    }

    @GetMapping("/")
    public ResponseEntity<List<Physician>> getAll() {
        List<Physician> user = physicianDao.getAll();
        return new ResponseEntity<List<Physician>>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Physician> getPatientById(@PathVariable int id) {
        Physician user = physicianDao.getById(id);
        return new ResponseEntity<Physician>(user, HttpStatus.OK);
    }
}
