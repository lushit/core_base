package lush.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

/**
 * Transaction 설정파일
 */
@Service
@Scope("prototype")
public class MyBatisTransactionManager implements TransactionDefinition {
	protected Logger logger = LoggerFactory.getLogger(MyBatisTransactionManager.class);
	

	@Autowired
	@Qualifier("transactionManager")
	protected PlatformTransactionManager transactionManager;

	TransactionStatus status;

	/**
	 * 현재의 Transaction 반환하거나 새로운 Transaction을 생성.
	 * @throws TransactionException
	 */
	public void start() throws TransactionException {
		status = transactionManager.getTransaction(this);
	}

	/**
	 * 현재 Transaction 상태를 확인 후 commit .
	 * @throws TransactionException
	 */
	public void commit() throws TransactionException {
		if(!status.isCompleted()) {
			transactionManager.commit(status);
		}
	}

	/**
	 * 현재의 Transaction 상태를 확인 후 rollback.
	 * @throws TransactionException
	 */
	public void rollback() throws TransactionException {
		if(!status.isCompleted()) {
			transactionManager.rollback(status);
		}
	}

	/**
	 * Transaction 종료 후 상태를 확인하여 rollback.
	 * @throws TransactionException
	 */
	public void end() throws TransactionException {
		rollback();
	}

	/**
	 * Transaction 격리 레벨을 지정.
	 * 현재 Transaction 내에서 변경된 데이터가 다른 Transaction에 반영될지에 대한 레벨을 지정한다.
	 * @return 0 (Read Uncommited Isolation Level)
	 */
	@Override
	public int getIsolationLevel() {
		// TODO Auto-generated method stub
		// select 문장을 수행하는 경우 해당 데이터에 shared lock이 걸리지 않는다.
		// Transaction에 처리 중인 혹은 아직 commit 되지 않은 데이터를 다른 Transaction이 읽는 것을 허용한다.
		// A라는 데이터를 B로 변경하는 동안 다른 사용자는 B라는 아직 완료되지 않은 데이터를 B를 읽을 수 있다.
		return 0;
	}

	/**
	 * Transaction 이름 설정.
	 * @return null
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 호출되는 메소드와 클라이언트에 대하여 Transaction의 경계를 설정.
	 * 0 (PROPAGATION_REQUIRED) : 이미 하나의 Transaction이 존재하면 해당 Transaction을 지원하고, 없다면 새 Transaction을 생성한다.
	 * @return Default 0
	 */
	@Override
	public int getPropagationBehavior() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 지정한 시간 내에 해당 메소드 수행이 되지 않는 경우 rollback 수행.
	 *  -1 일 경우 no tiimeout 으로 설정된다.
	 * @return Default 0
	 */
	@Override
	public int getTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Transaction이 읽기 전용일 경우 사용
	 * (기본 설정은 false 이며, true로 설정 시 한 트렌젝션 안에서  insert, update, delete 실행 시 예외가 발생한다.)
	 * @return false
	 */
	@Override
	public boolean isReadOnly() {
		// TODO Auto-generated method stub
		return false;
	}
}
