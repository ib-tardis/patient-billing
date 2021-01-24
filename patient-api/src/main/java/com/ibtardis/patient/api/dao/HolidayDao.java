package com.ibtardis.patient.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class HolidayDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean isTodayHoliday(){
        boolean isHoliday = false;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());

        String queryTotal = "select * from pb_holiday where hl_date = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(queryTotal,date);

        if(rows.size() >= 1){
            isHoliday = true;
        } else {
            isHoliday = false;
        }

        return  isHoliday;
    }
}
