package com.thewangzl.sh.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
@EnableJpaRepositories
public class DaoConfig {
	
	@Bean("dataSource")
	@ConfigurationProperties(prefix="spring.datasource.druid")
	public DataSource dataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	@Primary
	@Bean
	public EntityManagerFactory entityManagerFactory(JpaProperties jpaProperties, @Qualifier("dataSource") DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.thewangzl.sh.domain");
		factory.setDataSource(dataSource);// 数据源
		factory.setJpaPropertyMap(jpaProperties.getProperties());
		factory.afterPropertiesSet();// 在完成了其它所有相关的配置加载以及属性设置后,才初始化
		EntityManagerFactory bean = factory.getObject();
		return bean;
	}

	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory entityManagerFactory) {
		HibernateJpaSessionFactoryBean bean = new HibernateJpaSessionFactoryBean();
		bean.setEntityManagerFactory(entityManagerFactory);
		return bean;
	}

	@Bean
	public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}

	@Primary
	@Bean
	public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}

	private static final int TX_METHOD_TIMEOUT = 50000;
	private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.thewangzl.sh.service.impl..*+.*(..))";

	@Bean
	public TransactionInterceptor txAdvice(PlatformTransactionManager m) {
		NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
		RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
		readOnlyTx.setReadOnly(true);
		readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
		RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
		requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		requiredTx.setTimeout(TX_METHOD_TIMEOUT);
		Map<String, TransactionAttribute> txMap = new HashMap<>();
		txMap.put("add*", requiredTx);
		txMap.put("save*", requiredTx);
		txMap.put("insert*", requiredTx);
		txMap.put("update*", requiredTx);
		txMap.put("delete*", requiredTx);
		txMap.put("get*", readOnlyTx);
		txMap.put("query*", readOnlyTx);
		source.setNameMap(txMap);
		TransactionInterceptor txAdvice = new TransactionInterceptor(m, source);
		return txAdvice;
	}

	// 切面的定义,pointcut及advice
	@Bean
	public Advisor txAdviceAdvisor( TransactionInterceptor txAdvice) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
		return new DefaultPointcutAdvisor(pointcut, txAdvice);
	}
	
	@Primary
	@Bean(name="jdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
