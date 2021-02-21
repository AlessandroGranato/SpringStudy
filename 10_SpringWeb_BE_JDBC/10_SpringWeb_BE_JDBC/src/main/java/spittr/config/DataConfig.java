package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

@Configuration
public class DataConfig {

//     // Working, but returning a JndiObjectFactoryBean
//    @Bean
//    public JndiObjectFactoryBean dataSource() {
//        JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
//        jndiObjectFB.setJndiName("java:jboss/datasources/jdbc/SpitterDS");
//
//        // if the application is running in a Java application server, you’ll want to set the resource-ref property to true so that the value given in jndi-name will be prepended with java:comp/env/. Since I'm using JBoss, I'll use only the name defined by the JNDI in Jboss.
//        //jndiObjectFB.setResourceRef(true);
//        jndiObjectFB.setProxyInterface(javax.sql.DataSource.class);
//        return jndiObjectFB;
//    }

//    //Not working
//    @Bean
//    public DataSource dataSource() {
//        JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
//        jndiObjectFB.setJndiName("java:jboss/datasources/jdbc/SpitterDS");
//        jndiObjectFB.setProxyInterface(javax.sql.DataSource.class);
//        return (DataSource) jndiObjectFB.getObject();
//    }

    @Bean
    public DataSource dataSource(){
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        DataSource dataSource = dsLookup.getDataSource("java:jboss/datasources/jdbc/SpitterDS");
        return dataSource;
    }



    @Bean
    public JdbcOperations jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
