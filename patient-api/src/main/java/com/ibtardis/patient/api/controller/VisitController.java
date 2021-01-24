package com.ibtardis.patient.api.controller;

import com.ibtardis.patient.api.dao.HolidayDao;
import com.ibtardis.patient.api.dao.VisitDao;
import com.ibtardis.patient.api.exceptions.HolidayException;
import com.ibtardis.patient.api.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("visitor")
public class VisitController {

    @Autowired
    private HolidayDao holidayDao;

    @Autowired
    private VisitDao visitDao;

    @GetMapping("/")
    public ResponseEntity<List<Visit>> getAll() {
        List<Visit> visits = visitDao.getAllVisits();
        return new ResponseEntity<List<Visit>>(visits, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visit> getById(@PathVariable int id) {
        Visit visit = visitDao.getById(id);
        HttpStatus responseStatus = null;
        if(visit != null){
            responseStatus = HttpStatus.OK;
        } else {
            responseStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<Visit>(visit, responseStatus);
    }

    @PostMapping("/")
    public ResponseEntity<Visit> createVisit(@RequestBody Visit postVisit){
        Visit responsePhysician = null;
        HttpStatus responseCode = null;

        if(!holidayDao.isTodayHoliday()){
            Visit visit = visitDao.create(postVisit);
            if(visit != null){
                responsePhysician = visit;
                responseCode = HttpStatus.OK;
            } else {
                responsePhysician = null;
                responseCode = HttpStatus.BAD_REQUEST;
            }
        } else {
            throw new HolidayException("Today is holiday! No data manipulation.");
        }

        return new ResponseEntity<Visit>(responsePhysician, responseCode);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Visit> createVisit(@RequestBody Visit postVisit, @PathVariable int id){
        Visit responsePhysician = null;
        HttpStatus responseCode = null;

        if(!holidayDao.isTodayHoliday()){
            Visit visit = visitDao.update(postVisit);
            if(visit.getId() == 0){
                responsePhysician = null;
                responseCode = HttpStatus.NOT_FOUND;
            }  else if(visit != null && visit.getId() != 0) {
                responsePhysician = visit;
                responseCode = HttpStatus.OK;
            } else {
                responsePhysician = null;
                responseCode = HttpStatus.BAD_REQUEST;
            }
        } else {
            throw new HolidayException("Today is holiday! No data manipulation.");
        }

        return new ResponseEntity<Visit>(responsePhysician, responseCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Visit> deleteVisit(@PathVariable int id){
        Visit responseVisit = null;
        HttpStatus responseCode = null;

        if(!holidayDao.isTodayHoliday()){
            Visit visit = visitDao.delete(id);
            if(visit != null && visit.getId() != 0){
                responseVisit = visit;
                responseCode = HttpStatus.OK;
            } else {
                responseVisit = null;
                responseCode = HttpStatus.NOT_FOUND;
            }
        } else {
            throw new HolidayException("Today is holiday! No data manipulation.");
        }

        return new ResponseEntity<Visit>(responseVisit, responseCode);
    }

}
