package lush.service;

import java.sql.Connection;
import lush.config.MyBatisTransactionManager;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Service 에서 공통으로 또는 지속적으로 사용되는 요소들을 관리
 */
public class BaseService {
	// File Log
	protected Logger logger = LoggerFactory.getLogger(BaseService.class);

	// Qualifier annotation을 사용하여 transactionManager(Bean 객체) 의존성을 주입.
	@Autowired
	@Qualifier("transactionManager")
	protected PlatformTransactionManager transactionManager;

	// Qualifier annotation을 사용하여 sqlSessionFactory(Bean 객체) 의존성을 주입.
	@Autowired
	@Qualifier("sqlSessionFactory")
	protected SqlSessionFactory sqlSessionFactory;

	// Qualifier annotation을 사용하여 sqlSession(Bean 객체) 의존성을 주입.
	@Autowired
	@Qualifier("sqlSession")
	protected SqlSession sqlSession;
	
	@Autowired
	protected ApplicationContext applicationContext;
	
	
	/**
	 * 한번 사용한 SQL문을 재 사용하는 SqlSession을 설정하여 반환
	 * @return SqlSession(SQL 재사용)
	 */
	protected SqlSession getSqlSessionSimpleOutBound() {
		SqlSession session	= null;
		Connection conn 	= null;
		
		try {
			// PreparedStatements(한번 사용한 SQL문을 저장해서 반복하여 사용)를 재사용하며 AutoCommit은 사용하지 않음으로 Session을 Open한다.
			session = sqlSessionFactory.openSession(ExecutorType.REUSE, false);
			conn = session.getConnection();
			conn.setAutoCommit(false);
		}
		catch (Exception ex) {
			new Throwable("getSqlSessionSimpleOutBound 생성오류 발생!");
		}
		return session;
	}

	/**
	 * 한번 사용한 SQL문을 재 사용하지 않는 SqlSession을 설정하여 반환
	 * @return SqlSession(SQL 재사용 안함)
	 */
	protected SqlSession getSqlSessionAutoCommit() {
		SqlSession session	= null;
		Connection conn 	= null;
		try {
			// 구문 실행 마다 새로운 PreparedStatements를 생성, AutoCommit은 사용하지 않음으로 Session을 Open 한다.
			session = sqlSessionFactory.openSession(ExecutorType.SIMPLE, false);
			conn = session.getConnection();
			conn.setAutoCommit(false);
		} catch (Exception ex) {
			new Throwable("getSqlSessionAutoCommit 생성오류 발생!");
		}
		return session;
	}
	
	/**
	 *  Bean에 등록된 MyBatisTransactionManager를 반환.
	 * @return MyBatisTransactionManager
	 */
	public MyBatisTransactionManager getTransactionManager() {
		 return applicationContext.getBean(MyBatisTransactionManager.class);
	 } 
}
