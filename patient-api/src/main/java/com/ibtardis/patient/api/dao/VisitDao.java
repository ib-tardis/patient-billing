package com.ibtardis.patient.api.dao;

import com.ibtardis.patient.api.Constants;
import com.ibtardis.patient.api.exceptions.RecordAlreadyExistsException;
import com.ibtardis.patient.api.model.Visit;
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
public class VisitDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Visit create(Visit visit) {
        Visit responseVisitor = null;

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement statement = con.prepareStatement("INSERT INTO `pb_visit` (`visit_id`, `physician_id`, `v_reason`, `v_createdby`, `v_modifiedby`) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                    statement.setInt(1, visit.getId());
                    statement.setInt(2, visit.getPhysicianId());
                    statement.setString(3, visit.getReason());
                    statement.setInt(4, visit.getCreatedBy());
                    statement.setInt(5, visit.getModifiedBy());
                    return statement;
                }
            }, holder);

            long result = holder.getKey().longValue();

            if (result >= 1) {
                responseVisitor = this.getById((int) result);
            } else {
                responseVisitor = null;
            }
            return responseVisitor;
        } catch (DuplicateKeyException dke){
            throw new RecordAlreadyExistsException("Record already exist.");
        }
    }

    public Visit getById(int visitorId){
        Visit responseVisitor = null;


        String queryTotal = "select * from pb_visit where visit_id = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(queryTotal, visitorId);

        for (Map row : rows) {
            try {
                responseVisitor = (new Visit(Integer.valueOf(row.get("visit_id").toString()), Constants.dbDateFormat.parse(row.get("v_datetime").toString()), Integer.valueOf(row.get("physician_id").toString()),
                        row.get("v_reason").toString(),Constants.dbDateFormat.parse(row.get("v_createdtime").toString()), Constants.dbDateFormat.parse(row.get("v_modifiedtime").toString()), Integer.valueOf(row.get("v_createdby").toString()),Integer.valueOf(row.get("v_modifiedby").toString())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return responseVisitor;
    }

    public List<Visit> getAllVisits(){
        List<Visit> allVisits = new ArrayList<>();
        String queryTotal = "select * from pb_visit";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(queryTotal);

        for (Map row : rows) {
            try {
                allVisits.add(new Visit(Integer.valueOf(row.get("visit_id").toString()), Constants.dbDateFormat.parse(row.get("v_datetime").toString()), Integer.valueOf(row.get("physician_id").toString()), row.get("v_reason").toString(),
                        Constants.dbDateFormat.parse(row.get("v_createdtime").toString()), Constants.dbDateFormat.parse(row.get("v_modifiedtime").toString()), Integer.valueOf(row.get("v_createdby").toString()), Integer.valueOf(row.get("v_modifiedby").toString())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return allVisits;
    }

    public Visit update(Visit visit){
        Visit responsePatient = null;

        String queryTotal = "update pb_visit SET v_reason=?, physician_id=?, v_modifiedtime=? where visit_id = ?";
        int updateResult = jdbcTemplate.update(queryTotal, visit.getReason(), visit.getPhysicianId(), Constants.dbDateFormat.format(new Date()), visit.getId());

        if(updateResult>= 1){
            responsePatient = getById(visit.getId());
        } else if(updateResult == 0){
            responsePatient = new Visit(0);
        }

        return responsePatient;
    }

    public Visit delete(int visitId){
        Visit responsePatient = getById(visitId);

        String queryTotal = "delete from pb_visit where visit_id = ?";
        int updateResult = jdbcTemplate.update(queryTotal, visitId);

        if(updateResult>= 1){
            // skip
        } else if(updateResult == 0){
            responsePatient = new Visit(0);
        }

        return responsePatient;
    }
}
