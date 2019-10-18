package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.InvalidInsuranceAmountException;
import com.example.demo.test.TestBasicTxnMgment;
import com.example.demo.test.TestPropagationTxnMgment;
import com.example.demo.test.TestTransactionRollback;

@SpringBootApplication
public class SpringbootTxnManagementApplication {

	public static void main(String[] args) throws InvalidInsuranceAmountException {

		final ApplicationContext context = SpringApplication.run(SpringbootTxnManagementApplication.class, args);
		
		final TestBasicTxnMgment testBasicTxnMgment = context.getBean(TestBasicTxnMgment.class);
		// getTestBasicTxn(testBasicTxnMgment);
		
		final TestPropagationTxnMgment testPropagationTxnMgment = context.getBean(TestPropagationTxnMgment.class);
		//getTestPropagationTxnMgment(testPropagationTxnMgment);
		
		final TestTransactionRollback testTransactionRollback = context.getBean(TestTransactionRollback.class);
		getTestTransactionRollback(testTransactionRollback);
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
