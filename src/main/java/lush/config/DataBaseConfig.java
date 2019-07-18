package lush.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 *	DataBase 설정 파일.
 */
@Configuration
public class DataBaseConfig {
	
	/**
	 * sqlSessionFactory 을 정의하여 Bean에 등록한다.
	 * @param dataSource
	 * @return sessionFactory.getObject()
	 * @throws Exception
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
		return sessionFactory.getObject();
	}
	
	/**
	 * transactionManager 이름의 DataSourceTransactionManager를 Bean에 등록한다.
	 * @param dataSource
	 * @return DataSourceTransactionManager
	 */
	@Bean(name="transactionManager")
	public DataSourceTransactionManager mssqlTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	/**
	 * sqlSession 이름의 SqlSessionTemplate을 Bean에 등록한다.
	 * @param sqlSessionFactory
	 * @return SqlSessionTemplate
	 */
	@Bean(name="sqlSession")
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory){
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
