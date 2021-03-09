package spittr.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataConfig {

    @Bean
    public DataSource dataSource(){
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        DataSource dataSource = dsLookup.getDataSource("java:jboss/datasources/XASpitterDS");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setDataSource(dataSource);
        sfb.setPackagesToScan(new String[] { "spittr.domain" });
        Properties props = new Properties();
        //props.setProperty("hibernate.connection.datasource","java:jboss/datasources/jdbc/SpitterDS");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle8iDialect");
        props.setProperty("hibernate.current_session_context_class", "thread");
        props.setProperty("hibernate.transaction.jta.platform", "org.hibernate.service.jta.platform.internal.JBossStandAloneJtaPlatform");
        sfb.setHibernateProperties(props);
        return sfb;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
