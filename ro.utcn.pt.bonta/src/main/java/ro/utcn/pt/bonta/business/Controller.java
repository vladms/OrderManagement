package ro.utcn.pt.bonta.business;

import java.util.ArrayList;
import java.sql.*;

import ro.utcn.pt.bonta.DAO.CustomerDAO;
import ro.utcn.pt.bonta.DAO.OrderDAO;
import ro.utcn.pt.bonta.DAO.ProductDAO;
import ro.utcn.pt.bonta.model.Customer;
import ro.utcn.pt.bonta.model.Order;
import ro.utcn.pt.bonta.model.Product;
import ro.utcn.pt.bonta.presentation.MainScreen;
/**   
* Controller.java - Controller : brain of the app. It makes the connection to the database and manages the interface.
* @author  Vlad Bonta 
*/

public class Controller {
	private MainScreen mainScreen;

	private ArrayList<Customer> customersList;
	private ArrayList<Product> productsList;
	private ArrayList<Order> ordersList;

	private Connection c;
	private Statement stmt;
	
	private CustomerDAO customerDAO;
	private ProductDAO productDAO;
	private OrderDAO orderDAO;

	public Controller() {
		customersList = new ArrayList<Customer>();
		productsList = new ArrayList<Product>();
		ordersList = new ArrayList<Order>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:pt.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		customerDAO = new CustomerDAO(c, stmt);
		productDAO = new ProductDAO(c, stmt);
		orderDAO = new OrderDAO(c, stmt);
		customersList = CustomerDAO.getCustomers();
		productsList = ProductDAO.getProducts();
		ordersList = OrderDAO.getOrders();

		mainScreen = new MainScreen(customersList, productsList, ordersList);
	}
}