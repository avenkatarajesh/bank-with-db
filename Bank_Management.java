package model;

import java.util.ArrayList;
import bank.Customer;

public class Bank_Management {
	private Customer customer;
	public static ArrayList<Customer> CustomerList = new ArrayList<>();
	private int CusListIndex = 0;

	public Bank_Management(Customer cus) {
		this.customer = cus;
	}

	public Bank_Management(String cusName, String accType, double accBalance, String phoneNo, String passWord,
			String userName) {
		customer = new Customer(cusName, accType, accBalance, phoneNo, userName, passWord);
		BankDAO bDao = BankDAO.getInstance();
		bDao.setCustomer(this.customer);
		CustomerList.add(this.customer);
		CusListIndex++;
	}

	public void deposit(double amount) {
		customer.setAccBalance(amount);
//		cus.setTransaction(amount + " Added to your Account");
		getDatabaseObject().putTransaction("Credited",customer.getAccNum(),amount);
		balanceEnquiry();
	}

	public void withdraw(double amount) {
//		balanceEnquiry();
//		String debited = CustomerList.get(CusListIndex).withDraw(amount);
//		System.out.println(debited);
		customer.withDraw(amount);
		getDatabaseObject().putTransaction("Debited",customer.getAccNum(),amount);
		balanceEnquiry();
	}
	
	public BankDAO getDatabaseObject() {
		return BankDAO.getInstance();
	}

	public void accountDetails() {
		customer.displaydetails();
	}

	public void balanceEnquiry() {
		System.out.println(" Available Balance " + CustomerList.get(CusListIndex).getAccBalance());
	}

	public void miniStatement() {
////		ArrayList<String> transaction = CustomerList.get(CusListIndex).getTransaction();
//		if (transaction.size() < 0) {
//			System.out.println("No Transaction Available.");
//			return;
//		}
//		for (int index = transaction.size() - 1; index >= 0; index--) {
//			System.out.println((transaction.size() - index + 1) + " " + transaction.get(index));
//		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public static void getCustomers() {
		System.out.println(CustomerList);
	}

}
