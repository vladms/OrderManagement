package ro.utcn.pt.bonta.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ro.utcn.pt.bonta.model.Customer;
import ro.utcn.pt.bonta.model.Product;

public class ProductDAO {
	private static Connection c;
	private static Statement stmt;

	public ProductDAO(Connection c, Statement stmt) {
		ProductDAO.c = c;
		ProductDAO.stmt = stmt;
	}

	public static ArrayList<Product> getProducts() {
		ArrayList<Product> productsLists = new ArrayList<Product>();
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Product;");
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				productsLists.add(new Product(id, name, price, quantity));
				System.out.println("id = " + id);
				System.out.println("NAME = " + name);
				System.out.println("price = " + price);
				System.out.println("quantity = " + quantity);

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

		return productsLists;
	}

	public static int insertNewProduct(ArrayList<Product> productsList, Product product) {

		try {
			stmt = c.createStatement();
			String sql = "INSERT INTO Product VALUES ('" + product.getId() + "', '" + product.getName() + "', "
					+ product.getPrice() + ", " + product.getQuantity() + ");";
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

	public static int updateProduct(ArrayList<Product> productsList, Product oldProduct, Product editedProduct) {

		try {
			stmt = c.createStatement();
			String sql = "UPDATE Product set id = '" + editedProduct.getId() + "', name = '" + editedProduct.getName()
					+ "', price = " + editedProduct.getPrice() + ", quantity = " + editedProduct.getQuantity() + " WHERE id='"
					+ oldProduct.getId() + "';";
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

	public static int deleteProduct(ArrayList<Product> productsList, Product deleteProduct) {

		try {
			stmt = c.createStatement();
			String sql = "DELETE FROM Product where id='" + deleteProduct.getId() + "';";
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
