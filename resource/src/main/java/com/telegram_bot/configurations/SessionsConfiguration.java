package com.telegram_bot.configurations;

import com.telegram_bot.entity.Sessions;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

/**
 * Created by Greg on 9/14/16.
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "sessionsEntityManager",
        transactionManagerRef = "sessionsTransactionManager",
        basePackageClasses = { com.telegram_bot.repository.SessionsRepository.class })
public class SessionsConfiguration {

    @Autowired(required = false)
    private PersistenceUnitManager persistenceUnitManager;

    @Bean
    @ConfigurationProperties("spring.sessions")
    public JpaProperties sessionsJpaProperties() {
        return new JpaProperties();
    }

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix="spring.sessions")
    public DataSource sessionsDataSource() {
        return (DataSource) DataSourceBuilder.create().type(DataSource.class).build();
    }

    @Bean(name = "sessionsEntityManager")
    public LocalContainerEntityManagerFactoryBean sessionsEntityManager(JpaProperties sessionsJpaProperties) {
        EntityManagerFactoryBuilder builder = createEntityManagerFactoryBuilder(sessionsJpaProperties);
        return builder
                .dataSource(sessionsDataSource())
                .persistenceUnit("sessions")
                .packages(Sessions.class)
                .build();
    }

    @Bean
    public JpaTransactionManager sessionsTransactionManager(EntityManagerFactory sessionsEntityManager) {
        return new JpaTransactionManager(sessionsEntityManager);
    }

    private EntityManagerFactoryBuilder createEntityManagerFactoryBuilder(JpaProperties sessionsJpaProperties) {
        JpaVendorAdapter jpaVendorAdapter = createJpaVendorAdapter(sessionsJpaProperties);
        return new EntityManagerFactoryBuilder(jpaVendorAdapter,
                sessionsJpaProperties.getProperties(), this.persistenceUnitManager);
    }

    private JpaVendorAdapter createJpaVendorAdapter(JpaProperties jpaProperties) {
        AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(jpaProperties.isShowSql());
        adapter.setDatabase(jpaProperties.getDatabase());
        adapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
        adapter.setGenerateDdl(jpaProperties.isGenerateDdl());
        return adapter;
    }

}
