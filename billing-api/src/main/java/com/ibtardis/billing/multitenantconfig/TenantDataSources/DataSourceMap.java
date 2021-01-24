package com.ibtardis.billing.multitenantconfig.TenantDataSources;

import com.ibtardis.billing.tenantconfig.model.Tenant;
import com.ibtardis.billing.tenantconfig.util.TenantUtil;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataSourceMap {

    public static Map<Object, Object> getDataSourceHashMap() {

        HashMap hashMap = new HashMap();

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
