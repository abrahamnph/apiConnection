package com.connection.apihub;

import com.connection.Entity.RequestConnection;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Connectiondb {

    RequestConnection params = new RequestConnection();

   public Connectiondb(String reanOnly,String read_write ,String user ,String pass){
       params.setRead_only(reanOnly);
       params.setRead_write(read_write);
       params.setUser(user);
       params.setPass(pass);
   }

   public DataSource mastertransaction(){
       TransactionRoutingDataSource masterSlaveRoutingDataSource = new TransactionRoutingDataSource();
       Map<Object, Object> targetDataSources = new HashMap<>();
       targetDataSources.put(UtilsTransactiion.DataSourceType.READ_WRITE, WriteDataSource() );
       targetDataSources.put(UtilsTransactiion.DataSourceType.READ_ONLY, ReadDataSource());
       masterSlaveRoutingDataSource.setTargetDataSources(targetDataSources);
       log.info("dataSource"+targetDataSources);
       // Set as all transaction point to master
       masterSlaveRoutingDataSource.setDefaultTargetDataSource(ReadDataSource());
       return masterSlaveRoutingDataSource;
   }

   public DataSource WriteDataSource(){
       log.info("WRITE dataSource >>>>>"+params.getRead_write());
       HikariDataSource hikariDataSource = new HikariDataSource();
       hikariDataSource.setJdbcUrl(params.getRead_write());
       hikariDataSource.setUsername(params.getUser());
       hikariDataSource.setPassword(params.getPass());
       return hikariDataSource;
   }


    public DataSource ReadDataSource(){
        log.info("READ dataSource >>>>>"+params.getRead_only());
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(params.getRead_only());
        hikariDataSource.setUsername(params.getUser());
        hikariDataSource.setPassword(params.getPass());
        return hikariDataSource;
    }
}
