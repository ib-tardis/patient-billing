package com.ibtardis.patient.multitenantconfig.TenantDataSources;

import com.ibtardis.patient.tenantconfig.model.Tenant;
import com.ibtardis.patient.tenantconfig.util.TenantUtil;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataSourceMap {

    public static Map<Object, Object> getDataSourceHashMap() {

        HashMap hashMap = new HashMap();
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/db1?serverTimezone=UTC");
//        dataSource.setUsername("root");
//        dataSource.setPassword("");
//
//
//        DriverManagerDataSource dataSource1 = new DriverManagerDataSource();
//        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource1.setUrl("jdbc:mysql://localhost:3306/db2?serverTimezone=UTC");
//        dataSource1.setUsername("root");
//        dataSource1.setPassword("");
//        hashMap.put("tenantId1", dataSource);
//        hashMap.put("tenantId2", dataSource1);

        TenantUtil tenantUtil = new TenantUtil();
        File[] files = tenantUtil.getFiles();
        for (File propertyFile : files) {
            try {
                Tenant tenant = new ObjectMapper().readValue(propertyFile, Tenant.class);
                tenant.getDatabaseConfig().setUrl();
                DriverManagerDataSource dataSource = new DriverManagerDataSource();
                dataSource.setDriverClassName(tenant.getDatabaseConfig().getDriver());
                dataSource.setUrl(tenant.getDatabaseConfig().getUrl());
                dataSource.setUsername(tenant.getDatabaseConfig().getUsername());
                dataSource.setPassword(tenant.getDatabaseConfig().getPassword());
                hashMap.put(tenant.getName(), dataSource);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return hashMap;
    }
}
