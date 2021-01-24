package com.ibtardis.billing.api.dao;

import com.ibtardis.billing.api.Constants;
import com.ibtardis.billing.api.model.Billing;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class BillingDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Billing getBillById(int id){
        Billing responseBilling = null;

        String queryTotal = "select * from pb_billing where bill_id = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(queryTotal, id);

        for (Map row : rows) {
            try {
                responseBilling = (new Billing(Integer.valueOf(row.get("bill_id").toString()), Integer.valueOf(row.get("bill_id").toString()), Integer.valueOf(row.get("bill_id").toString()), Constants.dbDateFormat.parse(row.get("bill_datetime").toString())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return  responseBilling;
    }

    public List<Billing> getAllBills(){
        Billing responseBilling = null;
        List<Billing> allBills = new ArrayList<>();
        String queryTotal = "select * from pb_billing";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(queryTotal);

        for (Map row : rows) {
            try {
                allBills.add(new Billing(Integer.valueOf(row.get("bill_id").toString()), Integer.valueOf(row.get("bill_id").toString()), Integer.valueOf(row.get("bill_id").toString()), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(row.get("bill_datetime").toString())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return  allBills;
    }

    public Billing create(Billing bill){
        Billing responseBilling = null;

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        try{
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement statement = con.prepareStatement("INSERT INTO pb_billing (`bill_id`, `patient_id`, `physician_id`) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
                    statement.setInt(1, bill.getId());
                    statement.setInt(2, bill.getPatientId());
                    statement.setInt(3, bill.getPhysicianId());
                    return statement;
                }
            }, holder);

            long result = holder.getKey().longValue();

            if(result>= 1){
                responseBilling = this.getBillById((int)result);
            } else {
                responseBilling = null;
            }
        } catch (DuplicateKeyException dke){
            throw new DuplicateKeyException("Record already exist.");
        }

        return  responseBilling;
    }

    public Billing update(Billing billing){
        Billing responseBilling = null;

        String queryTotal = "update pb_billing SET patient_id=?, physician_id=? where patient_id = ?";
        int updateResult = jdbcTemplate.update(queryTotal, billing.getPatientId(),  billing.getPhysicianId(), billing.getId());

        if(updateResult>= 1){
            responseBilling = getBillById(billing.getId());
        } else if(updateResult == 0){
            responseBilling = new Billing(0);
        }

        return responseBilling;
    }

    public Billing delete(int billId){
        Billing responseBilling = getBillById(billId);

        if(responseBilling != null && responseBilling.getPatientId() > 0){
            String queryTotal = "delete from pb_billing where patient_id = ?";
            int updateResult = jdbcTemplate.update(queryTotal, billId);
            if(updateResult >= 1){
                // skip
            } else {
                responseBilling = null;
            }
        }

        return responseBilling;
    }

}
