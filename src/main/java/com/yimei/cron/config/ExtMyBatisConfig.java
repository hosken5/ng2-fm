package com.yimei.cron.config;

import com.github.pagehelper.PageHelper;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.flywaydb.core.Flyway;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by hary on 16/3/15.
 */
@Configuration
@MapperScan(basePackages = {"com.yimei.cron.mapper"})
public class ExtMyBatisConfig {
    Logger logger  = LoggerFactory.getLogger(ExtMyBatisConfig.class);

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.maxpoolsize}")
    private int maxpoolsize;

    @Bean
    public DataSource dataSource() {
        logger.info("jdbcurl:["+url+"]");
        final HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(maxpoolsize);
        ds.setDriverClassName(driverClassName);
        ds.setJdbcUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        logger.info("starting flyway...");
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setDataSource(dataSource);
//        flyway.setLocations("db/migration/" + databaseVendor);
//        flyway.setTable("auth_server_schema_version");
//        MigrationVersion version = MigrationVersion.fromVersion("0");
//        flyway.setBaselineVersion(version);
//        flyway.migrate();
        logger.info("ending flyway...");
        return flyway;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeHandlers(new TypeHandler<?>[]{new LocalDateTypeHandler(), new LocalDateTimeTypeHandler()});
        //分页插件
      PageHelper pageHelper = new PageHelper();
      Properties properties = new Properties();
      properties.setProperty("reasonable", "true");
      properties.setProperty("supportMethodsArguments", "true");
      properties.setProperty("returnPageInfo", "check");
      properties.setProperty("params", "count=countSql");
      pageHelper.setProperties(properties);

      bean.setPlugins(new Interceptor[]{pageHelper});
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:mappers/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}