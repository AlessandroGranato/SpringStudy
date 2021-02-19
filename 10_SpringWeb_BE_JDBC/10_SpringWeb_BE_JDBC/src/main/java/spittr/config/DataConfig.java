package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

@Configuration
public class DataConfig {

    @Bean
    public JndiObjectFactoryBean dataSource() {
        JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
        jndiObjectFB.setJndiName("java:jboss/datasources/jdbc/SpitterDS");

        // if the application is running in a Java application server, you’ll want to set the resource-ref property to true so that the value given in jndi-name will be prepended with java:comp/env/. Since I'm using JBoss, I'll use only the name defined by the JNDI in Jboss.
        //jndiObjectFB.setResourceRef(true);
        jndiObjectFB.setProxyInterface(javax.sql.DataSource.class);
        return jndiObjectFB;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
