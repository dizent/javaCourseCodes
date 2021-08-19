package cn.dizent.week08xa.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.RuleConfiguration;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @Auther: 布谷
 * @Date: 2021/8/15 10:17
 * @Description:
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "cn.dizent.week08xa.mapper.xadb")
public class MySQLConfig {

    private final MybatisProperties mybatisProperties;

    @Autowired
    public MySQLConfig(MybatisProperties mybatisProperties) {
        this.mybatisProperties = mybatisProperties;
    }

    @Bean("datasource0")
    @ConfigurationProperties(prefix = "spring.datasource.datasource0")
    public DataSource datasource0() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setConnectionTestQuery("SELECT 1");
        dataSource.setIdleTimeout(300000);
        dataSource.setConnectionTimeout(600000);
        dataSource.setValidationTimeout(30000);
        dataSource.setMaxLifetime(600000);
        dataSource.setPoolName("ds0");
        return dataSource;
    }

    @Bean("datasource1")
    @ConfigurationProperties(prefix = "spring.datasource.datasource1")
    public DataSource datasource1() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setConnectionTestQuery("SELECT 1");
        dataSource.setIdleTimeout(300000);
        dataSource.setConnectionTimeout(600000);
        dataSource.setValidationTimeout(30000);
        dataSource.setMaxLifetime(600000);
        dataSource.setPoolName("ds1");
        return dataSource;
    }

    @Bean
    public ShardingTableRuleConfiguration tOrderConfig() {
        ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration("t_order");
        orderTableRuleConfig.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("order_id", "snowflake"));
        return orderTableRuleConfig;
    }

    @Bean
    public ShardingTableRuleConfiguration tUserConfig() {
        ShardingTableRuleConfiguration userTableRuleConfig = new ShardingTableRuleConfiguration("t_user_info");
        userTableRuleConfig.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("user_id", "snowflake"));
        return userTableRuleConfig;
    }

    @Bean
    public ShardingRuleConfiguration orderShardingRuleConfig() {
        ShardingRuleConfiguration result = new ShardingRuleConfiguration();
        result.getTables().add(tOrderConfig());
        result.getTables().add(tUserConfig());
        result.setDefaultDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("user_id", "standard_order_db"));
        // 配置分库算法
        Properties orderProp = new Properties();
        orderProp.setProperty("algorithm-expression", "ds$->{user_id % 2}");
        result.getShardingAlgorithms().put("standard_order_db", new ShardingSphereAlgorithmConfiguration("INLINE", orderProp));

        result.getKeyGenerators().put("snowflake", new ShardingSphereAlgorithmConfiguration("SNOWFLAKE", getProperties()));

        return result;
    }

    private static Properties getProperties() {
        Properties result = new Properties();
        result.setProperty("worker-id", "123");
        return result;
    }

    @Bean
    @Primary
    public DataSource shardingDataSource(@Qualifier("datasource0") DataSource dataSource0, @Qualifier("datasource1") DataSource dataSource1) throws SQLException {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds0", dataSource0);
        dataSourceMap.put("ds1", dataSource1);
        List<RuleConfiguration> list = Collections.singletonList(orderShardingRuleConfig());
        return ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, list, new Properties());
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource shardingDataSource) throws Exception {
        // 配置数据源
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(shardingDataSource);
        // *Mapper.xml文件扫描
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources("classpath:cn/dizent/week08xa/mapper/xadb/**/*.xml"));
        factoryBean.setConfigurationProperties(mybatisProperties.getConfigurationProperties());
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate xadbBatchSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
    }

    @Bean
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager(DataSource shardingDataSource) {
        return new DataSourceTransactionManager(shardingDataSource);
    }
}
