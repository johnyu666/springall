package cn.johnyu.springjpahibernateconfig;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan //副作用：创建PersistenceAnnotationBeanPostProcessor对象
@EnableTransactionManagement(proxyTargetClass = false)
public class AppConf {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource("jdbc:mysql://johnyu.cn:3306/test","john","123");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    /**通用的属性尽量在VenderAdapter中进行**/
    @Bean
    public AbstractJpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        adapter.setShowSql(true);
        adapter.setDatabase(Database.MYSQL);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");

        return adapter;
    }

    @Bean
    public AbstractEntityManagerFactoryBean entityManagerFactoryBean(){
        Properties properties=new Properties();
        properties.setProperty("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
/**通用的属性尽量在VenderAdapter中进行**/
 //        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
//        properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
//        properties.setProperty("hibernate.show_sql","true");
//        properties.setProperty("hibernate.hbm2ddl.auto","create");
        LocalContainerEntityManagerFactoryBean factoryBean=new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setPackagesToScan("cn.johnyu.springjpahibernateconfig.pojo");
//        指定Persistenc unit (实例化LocalEntityManagerFactoryBean时)
//        factoryBean.setPersistenceUnitName("entityManager");
        factoryBean.setJpaProperties(properties);
        return factoryBean;
    }
    @Bean
    public JpaTransactionManager getTransactionManager() {
        return new JpaTransactionManager(entityManagerFactoryBean().getObject());
    }
    @Bean
    public BeanPostProcessor persistenceTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
