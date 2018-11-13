package com.bank.service;


//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.model.Account;
import com.bank.repository.AccountRepository;

@Service("txrService")
public class TxrServiceImpl implements TxrService {

//	private static Logger logger = Logger.getLogger("bank");

	@Autowired
	@Qualifier("jpa")
	private AccountRepository accountRepository;

	@Transactional(transactionManager="jpaTxnManager")
	public boolean txr(double amount, String fromAccNum, String toAccNum) {
//		logger.info("Txr initiated..");

		Account fromAccount = accountRepository.loadAccount(fromAccNum);
		Account toAccount = accountRepository.loadAccount(toAccNum);

		// debit & credit
		fromAccount.setBalance(fromAccount.getBalance()-amount);
		toAccount.setBalance(toAccount.getBalance()+amount);
		
		accountRepository.updateAccount(fromAccount);
		accountRepository.updateAccount(toAccount);
//		logger.info("Txr finished..");
		return true;
	}

	

}
