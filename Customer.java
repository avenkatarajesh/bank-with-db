package bank;

import java.util.ArrayList;
import model.Transaction;

public class Customer {

	private static String bankName = "R bank";
	private String cusName;
	private long accNum;
	private String accType;
	private double accBalance = 0;
	private String phoneNo;
	public String userName;
	public String passWord;
	ArrayList<Transaction> transaction = new ArrayList<>();

	public Customer() {

	}

	public Customer(String cusName, String accType, double accBalance, String phoneNo, String userName,
			String passWord) {
		this.cusName = cusName;
		this.accType = accType;
		this.accBalance = accBalance;
		this.phoneNo = phoneNo;
		this.userName = userName;
		this.passWord = passWord;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public long getAccNum() {
		return accNum;
	}

	public void setAccNum(long accNum) {
		this.accNum = accNum;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public double getAccBalance() {
		return accBalance;
	}

	public void setAccBalance(double amount) {
		this.accBalance += amount;
//		System.out.println("Money Credited");
	}

	public String withDraw(double amount) {
		if (amount > accBalance) {
			return "Insufficient Balance";
		}
		accBalance -= amount;

		return "collect the Money";
	}

	public static String getBankNme() {
		return bankName;
	}

	public void displaydetails() {
		System.out.println("Account Holder Name :" + cusName);
		System.out.println("Account Number :" + accNum);
		System.out.println("Account Type :" + accType);
		System.out.println("Available Balance : " + accBalance);
		System.out.println("Phone no. " + phoneNo);
	}

	public ArrayList<Transaction> getTransaction() {
		
		return transaction;
	}

//	public void setTransaction(String t) {
//		transaction.add(t);
//	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	public String toString() {
		return "Customer [cusName=" + cusName + ", accNum=" + accNum + "]";
	}

}
