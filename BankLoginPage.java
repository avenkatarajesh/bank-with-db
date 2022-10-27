package view;

import java.util.Scanner;
import bank.Customer;
import model.BankDAO;
import model.Bank_Management;

public class BankLoginPage {
	Scanner scan = new Scanner(System.in);
	Customer customer;
	String userName, password;
	double amount = 0;
	BankDAO bDao =BankDAO.getInstance();

	public void existingUser() {
		System.out.println("Enter your UserName :");
		String userName = scan.next();
		System.out.println("Enter PassWord : ");
		password = scan.next();
		customer = bDao.getCustomer(userName, password);
		if (customer != null) {
			inner: while (true) {
				System.out.print("Loading.");
				for (int index = 0; index < 5; index++) {
					System.out.print(".");
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println();
				System.out.println("WELCOME " + customer.getCusName());
				System.out.println("1. Money WithDraw");
				System.out.println("2. Money Deposit");
				System.out.println("3. Balance Enquiry.");
				System.out.println("4. Mini statement.");
				System.out.println("5. Account Details");
				System.out.println("6. Logout");
				System.out.println(" Enter your Choice :");
				int choice = scan.nextInt();
//				Bank_Management.CustomerList.add(customer);
				Bank_Management bankCustomer = new Bank_Management(customer);
				scan.nextLine();
				switch (choice) {
				case 1:
					System.out.println("Enter the amount :");
					amount = scan.nextDouble();
					bankCustomer.withdraw(amount);
					break;
				case 2:
					System.out.println("Enter the amount :");
					amount = scan.nextDouble();
					bankCustomer.deposit(amount);
					break;
				case 3:
					bankCustomer.balanceEnquiry();
					break;
				case 4:
					bankCustomer.miniStatement();
					break;
				case 5:
					bankCustomer.accountDetails();
					break;
				case 6:
					break inner;
				default:
					System.out.println("Invalid Choice , Retry.");
				}
			}
		} else {
			System.out.println("UserName not Found.");
			scan.nextLine();
		}
	}

	public void newUser() {
		System.out.println("Willing to Open an Account :");
		System.out.println("1. yes");
		System.out.println("2. No");
		int choice = scan.nextInt();
		if (choice == 1) {
			System.out.println("Enter Your Name :");
			String name = scan.next();
			System.out.println("Enter Your Phone No. :");
			String phoneNo = scan.next();
			System.out.println("Enter Account Type(Saving / current) : ");
			String accType = scan.next();
			System.out.println("Enter the Amount to intial deposit : ");
			amount = scan.nextDouble();
			System.out.println("Set UserName :");
			userName = scan.next();
			boolean checkUserName = bDao.checkUserName(userName);
			while (checkUserName) {
				System.out.println("username already exist . Try another");
				System.out.print("Set UserName :");
				userName = scan.next();
				checkUserName = bDao.checkUserName(userName);
			}
			System.out.println(
					"Set the password (minimum 8 chars; minimum 1 digit, 1 lowercase, 1 uppercase, 1 special character[!@#$%^&*_]) :");
			password = scan.next();
			while (!password.matches((("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}")))) {
				System.out.println("Password Not Satisfied the Condtion. Try Again.");
				password = scan.next();
			}
			Bank_Management newCustomer = new Bank_Management(name, accType, amount, phoneNo, userName, password);
			newCustomer.accountDetails();
			customer = newCustomer.getCustomer();
		} else {
			System.out.println("Thank You.");
		}
	}

	public void display() {
		outer: while (true) {
			System.out.print("Loading.");
			for (int index = 0; index < 5; index++) {
				System.out.print(".");
				try {
					Thread.sleep(750);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println();
			System.out.println("<<< ! Welcome to R Bank ! >>>");
			System.out.println(" 1. Existing Customer.");
			System.out.println(" 2. New Customer.");
			System.out.println(" 3. About us.");
			System.out.println(" 4. Exit.");
			System.out.println("-------------------------");
			System.out.println("Select the choice : ");
			int choice = scan.nextInt();
			switch (choice) {
			case 1:
				existingUser();
				break;
			case 2:
				newUser();
				break;
			case 3:
				System.out.println("R Bank ");
				System.out.println("Since 2022");
				System.out.println("Founder Rajesh.");
				break;
			case 4:
				System.out.println("Thank you.");
				break outer;
			default:
				System.out.println("Invalid Try Again.");
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		BankLoginPage bLP = new BankLoginPage();
		bLP.display();
	}
}
