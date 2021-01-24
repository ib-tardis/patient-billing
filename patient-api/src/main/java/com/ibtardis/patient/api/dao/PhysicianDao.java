package com.ibtardis.patient.api.dao;

import com.ibtardis.patient.api.Constants;
import com.ibtardis.patient.api.exceptions.RecordAlreadyExistsException;
import com.ibtardis.patient.api.model.Physician;
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
public class PhysicianDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Physician create(Physician physician) {
        Physician responsePhysician = null;

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement statement = con.prepareStatement("INSERT INTO pb_physician (`physician_id`, `ph_name`,`ph_createdby`, `ph_modifiedby`) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                    statement.setInt(1, physician.getId());
                    statement.setString(2, physician.getName());
                    statement.setInt(3, physician.getCreatedBy());
                    statement.setInt(4, physician.getModifiedBy());
                    return statement;
                }
            }, holder);

            long result = holder.getKey().longValue();

            if (result >= 1) {
                responsePhysician = this.getById((int) result);
            } else {
                responsePhysician = null;
            }
            return responsePhysician;
        } catch (DuplicateKeyException dke){
            throw new RecordAlreadyExistsException("Record already exist.");
        }
    }

    public Physician update(Physician physician){
        Physician responsePhysician = null;

        String queryTotal = "update pb_physician SET ph_name=?, ph_modifiedtime=?, ph_modifiedby=?  where physician_id = ?";
        int updateResult = jdbcTemplate.update(queryTotal, physician.getName(), Constants.dbDateFormat.format(new Date()), physician.getModifiedBy(), physician.getId());

        if(updateResult>= 1){
            responsePhysician = getById(physician.getId());
        } else if(updateResult == 0) {
            responsePhysician = new Physician(0);
        }

        return responsePhysician;
    }

    public Physician delete(int physicianId){
        Physician responsePhysician = getById(physicianId);

        if(!responsePhysician.getName().equals(null)){
            String queryTotal = "delete from pb_physician where physician_id = ?";
            int updateResult = jdbcTemplate.update(queryTotal, physicianId);
            if(updateResult >= 1){
                // skip
            } else if(updateResult == 0) {
                responsePhysician = new Physician(0);
            }
        } else {
            responsePhysician = null;
        }

        return responsePhysician;
    }

    public List<Physician> getAll(){

        List<Physician> allPhysicians = new ArrayList<>();
        String queryTotal = "select * from pb_physician";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(queryTotal);

        for (Map row : rows) {
            try {
                allPhysicians.add(new Physician(Integer.valueOf(row.get("physician_id").toString()), row.get("ph_name").toString(), Constants.dbDateFormat.parse(row.get("ph_createdtime").toString()),
                        Constants.dbDateFormat.parse(row.get("ph_modifiedtime").toString()), Integer.valueOf(row.get("ph_createdby").toString()), Integer.valueOf(row.get("ph_modifiedby").toString())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return allPhysicians;
    }

    public Physician getById(int physicianId){
        Physician responsePhysician = null;


        String queryTotal = "select * from pb_physician where physician_id = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(queryTotal, physicianId);

        for (Map row : rows) {
            try {
                responsePhysician = (new Physician(Integer.valueOf(row.get("physician_id").toString()), row.get("ph_name").toString(), Constants.dbDateFormat.parse(row.get("ph_createdtime").toString()),
                        Constants.dbDateFormat.parse(row.get("ph_modifiedtime").toString()), Integer.valueOf(row.get("ph_createdby").toString()), Integer.valueOf(row.get("ph_modifiedby").toString())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        return responsePhysician;
    }
}
