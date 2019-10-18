package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.InvalidInsuranceAmountException;
import com.example.demo.test.TestBasicTxnMgment;
import com.example.demo.test.TestPropagationTxnMgment;
import com.example.demo.test.TestTransactionRollback;
import com.example.demo.test.TestTxnIsolation;

@SpringBootApplication
public class SpringbootTxnManagementApplication {

	public static void main(String[] args) throws InvalidInsuranceAmountException {

		final ApplicationContext context = SpringApplication.run(SpringbootTxnManagementApplication.class, args);
		
		final TestBasicTxnMgment testBasicTxnMgment = context.getBean(TestBasicTxnMgment.class);
		// getTestBasicTxn(testBasicTxnMgment);
		
		final TestPropagationTxnMgment testPropagationTxnMgment = context.getBean(TestPropagationTxnMgment.class);
		//getTestPropagationTxnMgment(testPropagationTxnMgment);
		
		final TestTransactionRollback testTransactionRollback = context.getBean(TestTransactionRollback.class);
		//getTestTransactionRollback(testTransactionRollback);
		
		final TestTxnIsolation testTxnIsolation = context.getBean(TestTxnIsolation.class);
		testTxnIsolation.joinOrg_Isolation_Txn_Serializable();
		
	}
	
	static void getTestTransactionRollback(final TestTxnIsolation testTxnIsolation) throws InvalidInsuranceAmountException
	{
		/*
		 * 
		 * Dirty Reads =>	Suppose two transactions - Transaction A and Transaction B are running concurrently. 
		 * 					If Transaction A modifies a record but not commits it. Transaction B reads this record but 
		 * 					then Transaction A again rollbacks the changes for the record and commits it. 
		 * 					So Transaction B has a wrong value.
		 * Non-Repeatable Reads =>	Suppose two transactions - Transaction A and Transaction B are running concurrently. 
		 * 							If Transaction A reads some records. Transaction B modifies these records before transaction A has been committed. 
		 * 							So if Transaction A again reads these records they will be different. 
		 * 							So same select statements result in different existing records.
		 * Phantom Reads =>	Suppose two transactions - Transaction A and Transaction B are running concurrently. 
		 * 					If Transaction A reads some records. Transaction B adds more such records before transaction A has been committed. 
		 * 					So if Transaction A again reads there will be more records than the previous select statement. 
		 * 					So same select statements result in different number records to be displayed as new records also get added.
		 * 
		 * 
		 * The following are the types of Transaction Isolation Levels-
		 * 
		 * SERIALIZABLE =>	If two transactions are executing concurrently then it is as if the transactions get executed serially i.e 
		 * 					the first transaction gets committed only then the second transaction gets executed. 
		 * 					This is total isolation. So a running transaction is never affected by other transactions. 
		 * 					However this may cause issues as performance will be low and deadlock might occur.
		 * 
		 * REPEATABLE_READ =>	If two transactions are executing concurrently - till the first transaction is committed the existing records cannot be changed 
		 * 						by second transaction but new records can be added. After the second transaction is committed, 
		 * 						the new added records get reflected in first transaction which is still not committed. 
		 * 						For MySQL the default isolation level is REPEATABLE_READ.
		 *						However the REPEATABLE READ isolation level behaves differently when using mysql. 
		 *						When using MYSQL we are not able to see the newly added records that are committed by the second transaction.
		 * 
		 * READ_COMMITTED => 	If two transactions are executing concurrently - before the first transaction is committed the existing records can be changed 
		 * 						as well as new records can be changed by second transaction. After the second transaction is committed, 
		 * 						the newly added and also updated records get reflected in first transaction which is still not committed.
		 * 
		 * READ_UNCOMMITTED =>	If two transactions are executing concurrently - before the first transaction is committed the existing records can be 
		 * 						changed as well as new records can be changed by second transaction. Even if the second transaction is not committed 
		 * 						the newly added and also updated records get reflected in first transaction which is still not committed.
		 */
		testTxnIsolation.joinOrg_Isolation_Txn_Serializable();
	}
	static void getTestTransactionRollback(final TestTransactionRollback testTransactionRollback) throws InvalidInsuranceAmountException
	{
		testTransactionRollback.positive_JoinOrgRollbackTxn_ExceptionRaised();
		
		/*
		 * If the child throwing any custom or any other exception than DB Exception then 
		 * this exception should be handle at parent level.
		 * for example : @Transactional(rollbackFor = InvalidInsuranceAmountException.class)
		 * 
		 * At parent level rollbackFor exception not handle then any exception occur at child level then child will failed but rest of other txn will execute.
		 * It may produces data inconsistency.
		 * 
		 */
		testTransactionRollback.negative_JoinOrgRollbackTxn_ExceptionRaised();
	}

	static void getTestBasicTxn(final TestBasicTxnMgment testBasicTxnMgment) {
		testBasicTxnMgment.pTestScenario1();
		testBasicTxnMgment.nTestScenario1();
	}

	static void getTestPropagationTxnMgment(final TestPropagationTxnMgment testPropagationTxnMgment) {
		
		/*
		 * P-WITHOUT_TXN ==> C-TXN-PROPAGATION-REQUIRED : Child create own txn.If failed inside the child then it  will rollback.
		 * testPropagationTxnMgment.joinOrg_ParentWithOutTxn_ChildPropagationRequired();
		 */
		
		/*
		 * P-TXN-PROPAGATION-REQUIRED ==> C-TXN-PROPAGATION-REQUIRED : Parent will create txn.Parent and child will be share the same txn boundaries.If child fails then whole txn will be rollback.
		 * testPropagationTxnMgment.joinOrg_ParentPropagationRequired_ChildPropagationRequired();
		 */
	
		
		/*
		 * P-WITHOUT_TXN ==> C-TXN-PROPAGATION-SUPPORTS : Child Doesn't create own txn.If failed inside the child then rollback will not work.
		 * testPropagationTxnMgment.joinOrg_ParentWithOutTxn_ChildPropagationSupports();
		 */
	
		
		/*
		 * P-TXN-PROPAGATION-REQUIRED ==> C-TXN-PROPAGATION-SUPPORTS : Parent and child will be share the same txn boundaries.If child fails then whole txn will be rollback.
		 * testPropagationTxnMgment.joinOrg_ParentPropagationRequired_ChildPropagationSupports();
		 */
		
		/*
		 * P-WITHOUT_TXN ==> C-TXN-PROPAGATION-NOTSUPPORTS : Child Doesn't create own txn.If failed inside the child then rollback will not work.
		 * testPropagationTxnMgment.joinOrg_ParentWithOutTxn_ChildPropagationNotSupports();
		 */
		
		/*
		 * P-TXN-PROPAGATION-REQUIRED ==> C-TXN-PROPAGATION-NOTSUPPORTS : In this case child neither used a parent transaction nor create new child transaction.If failed inside the child then rollback will not work.
		 * testPropagationTxnMgment.joinOrg_ParentPropagationRequired_ChildPropagationNotSupports();
		 */
		
		/*
		 * P-WITHOUT_TXN ==> C-TXN-PROPAGATION-REQUIRES_NEW : Child create own txn.If failed inside the child then rollback child txn.
		 * testPropagationTxnMgment.joinOrg_ParentWithOutTxn_ChildPropagationRequiresNew();
		 */
		
		/*
		 *P-TXN-PROPAGATION-REQUIRED ==> C-TXN-PROPAGATION-REQUIRES_NEW : First parent will create txn then parent txn suspend and child will create own txn.Child will execute queries inside the child txn and close the child txn.After completion of child txn, again parent txn will resume and complete parent txn.
		 * if child and parent txn failed then rollback the own txn respectively 
		 * testPropagationTxnMgment.joinOrg_ParentPropagationRequired_ChildPropagationRequiresNew();
		 */
		
		
		/*
		 * P-WITHOUT_TXN ==> C-TXN-PROPAGATION-NEVER : Child doesn't create own txn.If failed inside the child then rollback will not work in side the child txn
		 * testPropagationTxnMgment.joinOrg_ParentWithOutTxn_ChildPropagationNever();
		 */
		
		/*
		 * P-TXN-PROPAGATION-REQUIRED ==> C-TXN-PROPAGATION-NEVER : if parent having the txn then child txn will failed to execute because child txn defined as never.
		 * If parent calling child without txn and child having its own txn then child will execute successfully. 
		 * testPropagationTxnMgment.joinOrg_ParentPropagationRequired_ChildPropagationNever();
		 */
		
		/*
		 * P-WITHOUT_TXN ==> C-TXN-PROPAGATION-MANDATORY : Child execution failed because parent doesnt have txn boundries.
		 * testPropagationTxnMgment.joinOrg_ParentWithOutTxn_ChildPropagationMandatory();
		 */
		
		/*
		 * P-TXN-PROPAGATION-REQUIRED ==> C-TXN-PROPAGATION-MANDATORY : If the child having mandatory propagation then parent should have own txn then only child txn will work.parent and child share the txn.
		 *  
		 * testPropagationTxnMgment.joinOrg_ParentPropagationRequired_ChildPropagationMandatory();
		 */
		
		
		
	}

}
