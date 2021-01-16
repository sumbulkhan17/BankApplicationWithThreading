package com.capgemini.service;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;

public class BankRunner implements Runnable {

	Bank bank;
	
	public BankRunner(Bank bank)
	{
		this.bank=bank;
	}
	@Override
	public void run() {
		
		try
		{
			if(Thread.currentThread().getName().equals("first"))
			{
			System.out.println("Balance in account 101 = "+bank.withdrawAmount(101, 2000));
			}
			else
			{
				System.out.println("Balance in account 101 = "+bank.withdrawAmount(101, 2000));
			}
		}catch(InvalidAccountNumberException ian)
		{
			System.out.println("Invalid account number");
		}catch(InsufficientBalanceException ibe)
		{
			System.out.println("Insufficient balance");
		}

	}

}
