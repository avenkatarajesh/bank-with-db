package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import bank.Customer;

public class BankDAO {
	private static BankDAO bankdaoObject;
	Connection connect = null;
	PreparedStatement prepared_statement = null;
	ResultSet result_set = null;

	private BankDAO() {
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank_Application", "root", "rajesh");
//			System.out.println("BankDAO connected");
		} catch (SQLException e) {
			System.out.println("Connection is not established");
			e.printStackTrace();
		}
	}
	
	public static BankDAO getInstance() {
		if(bankdaoObject == null) {
			bankdaoObject = new BankDAO();
		}
	return bankdaoObject;
	}

	public Customer getCustomer(String userName, String password) {
		Customer customer = new Customer();
		try {
			prepared_statement = connect.prepareStatement("select * from customer where username =? and password =?");
			prepared_statement.setString(1, userName);
			prepared_statement.setString(2, password);
			result_set = prepared_statement.executeQuery();
			while (result_set.next()) {
//				System.out.println("success");
				customer.setCusName(result_set.getString(1));
				customer.setAccNum(result_set.getLong(2));
				customer.setAccType(result_set.getString(3));
				customer.setAccBalance(result_set.getDouble(4));
				customer.setPhoneNo(result_set.getString(5));
				customer.setPassWord(result_set.getString(6));
				customer.setUserName(result_set.getString(7));
				return customer;
			}
		} catch (SQLException e) {
			System.out.println("Not Found.");
		} finally {
			if (prepared_statement != null) {
				try {
					prepared_statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (result_set != null) {
				try {
					result_set.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return customer;
	}

	public Customer setCustomer(Customer customer) {
		try {
//			prepared_statement = connect.prepareStatement("insert into customer (cusName , accType , balance ,PhoneNo , password , username)values(?,?,?,?,?,?)");
			prepared_statement = connect.prepareStatement(
					"insert into customer (cusName , accType , balance ,PhoneNo , password , username)values(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			prepared_statement.setString(1, customer.getCusName());
			prepared_statement.setString(2, customer.getAccType());
			prepared_statement.setDouble(3, customer.getAccBalance());
			prepared_statement.setString(4, customer.getPhoneNo());
			prepared_statement.setString(5, customer.getPassWord());
			prepared_statement.setString(6, customer.getUserName());
			prepared_statement.executeUpdate();
			result_set = prepared_statement.getGeneratedKeys();
			if (result_set.next()) {
				customer.setAccNum(result_set.getLong(1));
			}
			System.out.println("Account Created SuccessFully");
		} catch (SQLException e) {
			System.out.println("user not inserted");
			e.printStackTrace();
		} finally {
			if (prepared_statement != null) {
				try {
					prepared_statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (result_set != null) {
				try {
					result_set.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}

	public boolean checkUserName(String userName) {
		try {
			prepared_statement = connect.prepareStatement("select * from customer where username = ?");
			prepared_statement.setString(1, userName);
			result_set = prepared_statement.executeQuery();
			if (result_set.getRow() > 0) {
				System.out.println("success");
				prepared_statement.close();
				result_set.close();
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (prepared_statement != null) {
				try {
					prepared_statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (result_set != null) {
				try {
					result_set.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public Customer putTransaction(String process , long accNum , double amount){
		try {
			prepared_statement = connect.prepareStatement("INSERT INTO TRANSACTION (TIME_DATE ,PROCESS, ACCNUM , AMOUNT) VALUES (?,?,?,?)");
			prepared_statement.setTimestamp(1,new Timestamp(System.currentTimeMillis()));
			prepared_statement.setString(2,process);
			prepared_statement.setLong(3, accNum);
			prepared_statement.setDouble(4, amount);
			prepared_statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(prepared_statement != null) {
				try {
					prepared_statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public ArrayList<Transaction> getTransaction(Long accNum) {
		ArrayList<Transaction> transactionList = new ArrayList<>();
		
		try {
			prepared_statement = connect.prepareStatement("SELECT * FROM TRANSACTION WHERE ACCNUM = ?");
			prepared_statement.setLong(1, accNum);
			result_set = prepared_statement.executeQuery();
			while(result_set.next()) {
				Transaction object = new Transaction();
				object.setTransactionTime(result_set.getTimestamp(2));
				object.setProcess(result_set.getString(3));
				object.setAmount(result_set.getDouble(4));
				transactionList.add(object);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (prepared_statement != null) {
				try {
					prepared_statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (result_set != null) {
				try {
					result_set.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return transactionList;
	}
	
}
