package com.ibtardis.billing.api.controller;

import com.ibtardis.billing.api.dao.BillingDao;
import com.ibtardis.billing.api.model.Billing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("billing")
public class BillingController {

    @Autowired
    BillingDao billingDao;

    @GetMapping("/")
    public ResponseEntity<List<Billing>> getAll() {
        List<Billing> user = billingDao.getAllBills();
        return new ResponseEntity<List<Billing>>(user, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Billing> createBill(@RequestBody Billing billing) {
        Billing bill = billingDao.create(billing);
        Billing responseBill = null;
        HttpStatus responseStatus = null;
        if(bill != null){
            responseBill = bill;
            responseStatus = HttpStatus.OK;
        } else {
            responseBill = null;
            responseStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Billing>(responseBill, responseStatus);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Billing> updateBill(@RequestBody Billing billing, @PathVariable int id) {
        Billing bill = billingDao.update(billing);
        Billing responseBill = null;
        HttpStatus responseStatus = null;
        if(bill.getId() == 0){
            responseBill = null;
            responseStatus = HttpStatus.NOT_FOUND;
        } else if(bill != null && bill.getId() > 0){
            responseBill = bill;
            responseStatus = HttpStatus.OK;
        } else {
            responseBill = null;
            responseStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Billing>(responseBill, responseStatus);
    }
}
