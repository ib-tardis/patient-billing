package com.ibtardis.patient.api.controller;

import com.ibtardis.patient.api.dao.HolidayDao;
import com.ibtardis.patient.api.dao.PatientDao;
import com.ibtardis.patient.api.exceptions.HolidayException;
import com.ibtardis.patient.api.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private HolidayDao holidayDao;

    @GetMapping("/")
    public ResponseEntity<List<Patient>> getAll() {
        List<Patient> user = patientDao.getAllPatients();
        return new ResponseEntity<List<Patient>>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int id) {
        Patient user = patientDao.getPatientById(id);
        return new ResponseEntity<Patient>(user, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient postPatient ) {

        Patient responsePatient = null;
        HttpStatus responseCode = null;

        if(!holidayDao.isTodayHoliday()){
            Patient patient = patientDao.insert(postPatient);
            if(patient != null ){
                responsePatient = patient;
                responseCode = HttpStatus.OK;
            } else {
                responsePatient = null;
                responseCode = HttpStatus.BAD_REQUEST;
            }
        } else {
            throw new HolidayException("Today is holiday! No data manipulation.");
        }

        return new ResponseEntity<Patient>(responsePatient, responseCode);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient postPatient, @PathVariable int id ){

        Patient responsePatient = null;
        HttpStatus responseCode = null;

        if(!holidayDao.isTodayHoliday()){
            Patient patient = patientDao.update(postPatient);
            if(patient.getId() == 0 ){
                responsePatient = null;
                responseCode = HttpStatus.NOT_FOUND;
            } else if(patient != null && patient.getId() != 0){
                responsePatient = patient;
                responseCode = HttpStatus.OK;
            } else {
                responsePatient = null;
                responseCode = HttpStatus.BAD_REQUEST;
            }
        } else {
            throw new HolidayException("Today is holiday! No data manipulation.");
        }

        return new ResponseEntity<Patient>(responsePatient, responseCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatient( @PathVariable int id ){

        Patient responsePatient = null;
        HttpStatus responseCode = null;

        if(!holidayDao.isTodayHoliday()){
            Patient patient = patientDao.delete(id);
            if(patient != null ){
                responsePatient = patient;
                responseCode = HttpStatus.OK;
            } else {
                responsePatient = null;
                responseCode = HttpStatus.NOT_FOUND;
            }
        } else {
            throw new HolidayException("Today is holiday! No data manipulation.");
        }

        return new ResponseEntity<Patient>(responsePatient, responseCode);
    }

}
