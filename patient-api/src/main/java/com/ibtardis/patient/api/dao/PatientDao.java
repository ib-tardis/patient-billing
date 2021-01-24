package com.ibtardis.patient.api.dao;

import com.ibtardis.patient.api.Constants;
import com.ibtardis.patient.api.exceptions.RecordAlreadyExistsException;
import com.ibtardis.patient.api.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class PatientDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Patient> getAllPatients(){
        List<Patient> allPatients = new ArrayList<>();
        String queryTotal = "select * from pb_patient";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(queryTotal);

        for (Map row : rows) {
            try {
                allPatients.add(new Patient(Integer.valueOf(row.get("patient_id").toString()),row.get("p_name").toString(),Integer.valueOf(row.get("p_age").toString()),
                        row.get("p_gender").toString(), Constants.dbDateFormat.parse(row.get("p_createdtime").toString()),Constants.dbDateFormat.parse(row.get("p_modifiedtime").toString()), Integer.valueOf(row.get("p_createdby").toString())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return allPatients;
    }

    public Patient insert(Patient patient) {
        Patient responsePatient = null;

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        try{
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement statement = con.prepareStatement("INSERT INTO pb_patient (`patient_id`, `p_name`, `p_age`, `p_gender`,`p_createdby`) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                    statement.setInt(1, patient.getId());
                    statement.setString(2, patient.getName());
                    statement.setInt(3, patient.getAge());
                    statement.setString(4, patient.getGender());
                    statement.setInt(5, patient.getCreatedBy());
                    return statement;
                }
            }, holder);

            long result = holder.getKey().longValue();

            if(result>= 1){
                responsePatient = this.getPatientById((int)result);
            } else {
                responsePatient = null;
            }
        } catch (DuplicateKeyException dke){
            throw new RecordAlreadyExistsException("Record already exist.");
        }

        return responsePatient;
    }

    public Patient update(Patient patient){
        Patient responsePatient = null;

        String queryTotal = "update pb_patient SET p_name=?, p_age=?, p_gender=?, p_modifiedtime=? where patient_id = ?";
        int updateResult = jdbcTemplate.update(queryTotal, patient.getName(), patient.getAge(), patient.getGender(), Constants.dbDateFormat.format(new Date()), patient.getId());

        if(updateResult>= 1){
            responsePatient = getPatientById(patient.getId());
        } else if(updateResult == 0){
            responsePatient = new Patient(0);
        }

        return responsePatient;
    }

    public Patient delete(int patientId){
        Patient responsePatient = getPatientById(patientId);

        if(responsePatient != null && !responsePatient.getName().equals(null)){
            String queryTotal = "delete from pb_patient where patient_id = ?";
            int updateResult = jdbcTemplate.update(queryTotal, patientId);
            if(updateResult >= 1){
                // skip
            } else {
                responsePatient = null;
            }
        }

        return responsePatient;
    }

    public Patient getPatientById(int patientId){
        Patient responsePatient = null;

        String queryTotal = "select * from pb_patient where patient_id = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(queryTotal, patientId);

        for (Map row : rows) {
            try {
                responsePatient = (new Patient(Integer.valueOf(row.get("patient_id").toString()),row.get("p_name").toString(),Integer.valueOf(row.get("p_age").toString()),
                        row.get("p_gender").toString(), Constants.dbDateFormat.parse(row.get("p_createdtime").toString()),Constants.dbDateFormat.parse(row.get("p_modifiedtime").toString()), Integer.valueOf(row.get("p_createdby").toString())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return  responsePatient;
    }

}
