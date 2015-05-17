package configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import play.Play;

import java.util.HashMap;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"models"})
@EnableTransactionManagement
public class DataConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPackagesToScan("models", "services");
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPersistenceUnitName("default");
        entityManagerFactory.setJpaPropertyMap(new HashMap<String, String>() {
            {
                put("hibernate.hbm2ddl.auto", "update");
                put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            }
        });
        entityManagerFactory.afterPropertiesSet();
        return entityManagerFactory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory());

        return transactionManager;
    }

    @Bean(name = "mysql_datasource")
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Play.application().configuration().getString("db.default.driver"));
        dataSource.setUrl(Play.application().configuration().getString("db.default.url"));
        dataSource.setUsername(Play.application().configuration().getString("db.default.user"));
        dataSource.setPassword(Play.application().configuration().getString("db.default.pass"));

        return dataSource;
    }

    @Bean
    public play.mvc.Security.AuthenticatedAction security$AuthenticatedAction() {
        return new play.mvc.Security.AuthenticatedAction();
    }

}
