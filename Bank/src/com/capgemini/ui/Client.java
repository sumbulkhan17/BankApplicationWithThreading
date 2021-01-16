package com.capgemini.ui;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsuffientOpeningAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.service.*;

public class Client {
	
	public static void main( String[] args) {
		
		Bank bank = new ICICIBank();
		BankRunner bankRunner = new BankRunner(bank);
		
		try {
			System.out.println("Account creation");
			System.out.println(bank.createAccount(101,10000));
			System.out.println(bank.createAccount(102,800));
			
			Thread firstThread=new Thread(bankRunner,"first");				//creating threads
			firstThread.start();
			
			Thread secondThread=new Thread(bankRunner,"second");
			secondThread.start();
			
			System.out.println("After depositing amount 3000 in account no 101 is : "+bank.depositAmount(101,3000));
			
			int[] transfer=bank.fundTransfer(101, 102, 3000);
			
			System.out.println("After fund transfer of 3000, new balance of sender: "+transfer[0]
					+"  New balance of receiver: "+transfer[1]);
		}catch(InsuffientOpeningAmountException ins) {
			System.out.println("Insufficient Amount For Opening Account!! Minimum balance required is 500");
		}catch(InsufficientBalanceException inb) {
			System.out.println("insufficient balance");
		}catch(InvalidAccountNumberException ina) {
			System.out.println("invalid account number");
		}
	}

}
