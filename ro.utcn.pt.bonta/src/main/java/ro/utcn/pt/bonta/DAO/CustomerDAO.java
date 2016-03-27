package ro.utcn.pt.bonta.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ro.utcn.pt.bonta.model.Customer;

public class CustomerDAO {
	private static Connection c;
	private static Statement stmt;

	public CustomerDAO(Connection c, Statement stmt) {
		CustomerDAO.c = c;
		CustomerDAO.stmt = stmt;
	}

	public static ArrayList<Customer> getCustomers() {
		ArrayList<Customer> customersList = new ArrayList<Customer>();
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Customer;");
			while (rs.next()) {
				String cnp = rs.getString("cnp");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				customersList.add(new Customer(cnp, name, age));
				System.out.println("cnp = " + cnp);
				System.out.println("NAME = " + name);
				System.out.println("AGE = " + age);
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			e.printStackTrace();
		}

		return customersList;
	}

	public static int insertNewCustomer(ArrayList<Customer> customersList, Customer customer) {
		try {
			stmt = c.createStatement();
			String sql = "INSERT INTO Customer VALUES ('" + customer.getCNP() + "', '" + customer.getName() + "', "
					+ customer.getAge() + ");";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public static int updateCustomer(ArrayList<Customer> customersList, Customer oldCustomer, Customer editedCustomer) {
		try {
			stmt = c.createStatement();
			String sql = "UPDATE Customer set cnp = '" + editedCustomer.getCNP() + "', name = '"
					+ editedCustomer.getName() + "', age = " + editedCustomer.getAge() + " where cnp='"
					+ oldCustomer.getCNP() + "';";
			System.out.println(sql);

			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public static int deleteCustomer(ArrayList<Customer> customersList, Customer deleteCustomer) {
		try {
			stmt = c.createStatement();
			String sql = "DELETE FROM Customer where cnp='" + deleteCustomer.getCNP() + "';";
			System.out.println(sql);

			stmt.executeUpdate(sql);	
			c.commit();
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
}
