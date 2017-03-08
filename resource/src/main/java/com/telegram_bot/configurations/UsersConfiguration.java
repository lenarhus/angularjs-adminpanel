package com.telegram_bot.configurations;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.telegram_bot.entity.Users;

import javax.persistence.EntityManagerFactory;

/**
 * Created by Greg on 9/14/16.
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "usersEntityManager",
        transactionManagerRef = "usersTransactionManager",
        basePackageClasses = { com.telegram_bot.repository.UserRepository.class })
public class UsersConfiguration {

    @Autowired(required = false)
    private PersistenceUnitManager persistenceUnitManager;

    @Bean
    @ConfigurationProperties("spring.users")
    public JpaProperties usersJpaProperties() {
        return new JpaProperties();
    }

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix="spring.users")
    public DataSource usersDataSource() {
        return (DataSource) DataSourceBuilder.create().type(DataSource.class).build();
    }

    @Bean(name = "usersEntityManager")
    public LocalContainerEntityManagerFactoryBean usersEntityManager(JpaProperties usersJpaProperties) {
        EntityManagerFactoryBuilder builder = createEntityManagerFactoryBuilder(usersJpaProperties);
        return builder
                .dataSource(usersDataSource())
                .persistenceUnit("users")
                .packages(Users.class)
                .build();
    }

    @Bean
    @Primary
    public JpaTransactionManager usersTransactionManager(EntityManagerFactory usersEntityManager) {
        return new JpaTransactionManager(usersEntityManager);
    }

    private EntityManagerFactoryBuilder createEntityManagerFactoryBuilder(JpaProperties usersJpaProperties) {
        JpaVendorAdapter jpaVendorAdapter = createJpaVendorAdapter(usersJpaProperties);
        return new EntityManagerFactoryBuilder(jpaVendorAdapter,
                usersJpaProperties.getProperties(), this.persistenceUnitManager);
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
