package com.ibtardis.patient;

import com.ibtardis.patient.multitenantconfig.TenantDataSources.DataSourceMap;
import com.ibtardis.patient.multitenantconfig.TenantInterceptorRoutingDataSource.CustomRoutingDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PatientbillingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientbillingApplication.class, args);
    }

    @Bean
    public DataSource dataSource(){
        CustomRoutingDataSource customDataSource=new CustomRoutingDataSource();
        customDataSource.setTargetDataSources(DataSourceMap.getDataSourceHashMap());
        return customDataSource;
    }
}
