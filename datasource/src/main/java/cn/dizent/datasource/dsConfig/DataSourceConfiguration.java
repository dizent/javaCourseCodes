package cn.dizent.datasource.dsConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Auther: 布谷
 * @Date: 2021/8/10 14:12
 * @Description:
 */
@Configuration
public class DataSourceConfiguration {

    @Autowired
    private JpaProperties jpaProperties;

    @Bean
    @ConfigurationProperties(prefix = "spring.jpa")
    public Properties jpaProperties(){
        return new Properties();
    }

//    @Autowired
//    private HibernateProperties hibernateProperties;

    @Bean(name = "writeDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.first")
    public DataSource writeDatasource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "readDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource readDatasource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource datasource(DataSource writeDatasource,DataSource readDatasource){
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        targetDataSources.put("writeDatasource", writeDatasource);
        targetDataSources.put("readDatasource", readDatasource);
        return new DynamicDataSource(writeDatasource, targetDataSources);
    }

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().getObject().createEntityManager();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory () {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(datasource(writeDatasource(),readDatasource()));
        entityManagerFactory.setPackagesToScan("cn.dizent.datasource.entity");
        entityManagerFactory.setJpaProperties(jpaProperties());
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

}
