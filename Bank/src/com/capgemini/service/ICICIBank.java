package com.capgemini.service;
import java.util.*;
import com.capgemini.beans.Account;
import com.capgemini.exceptions.*;


public class ICICIBank implements Bank {
	
	private LinkedList<Account> accounts=new LinkedList<>();
	
	@Override
	public String createAccount(int accountNumber,int amount) throws InsuffientOpeningAmountException
	{	
		if(amount>=500) {
		Account account = new Account(accountNumber,amount);
		
		accounts.add(account);
		return "account created successfully";
		}
		
		else throw new InsuffientOpeningAmountException();
		
		
	}
	
	private Account searchAccount(int accountNumber)throws InvalidAccountNumberException
	{
		
		for(Account account : accounts)
		{
			if(account.getAccountNumber()==accountNumber)
			{
				return account;
			}
		}
		throw new InvalidAccountNumberException();
		
	}
	
	@Override
	public int withdrawAmount(int accountNumber,int amount) throws InvalidAccountNumberException, InsufficientBalanceException
	{
		Account account=searchAccount(accountNumber);
		
		synchronized(account) {
			if((account.getAmount()-amount)>=0)
			{
				account.setAmount(account.getAmount()-amount);
				return account.getAmount();
			}

			throw new InsufficientBalanceException();
		}
	}

	@Override
	public int depositAmount(int accountNumber,int amount) throws InvalidAccountNumberException {
		Account account=searchAccount(accountNumber);
		
		account.setAmount(account.getAmount()+amount);
		return account.getAmount();
	}

	@Override
	public int[] fundTransfer(int accountNumber1,int accountNumber2,int amount) throws InvalidAccountNumberException,InsufficientBalanceException {
		
		Account account1 = searchAccount(accountNumber1);
		Account account2 = searchAccount(accountNumber2);
		
		if((account1.getAmount()-amount)>=0)
		{
			account1.setAmount(account1.getAmount()-amount);
			account2.setAmount(account2.getAmount()+amount);
			
			int[] amounts = {account1.getAmount(),account2.getAmount()};
			return amounts;
		}
		
		else throw new InsufficientBalanceException();
		
	}

}
