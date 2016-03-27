package ro.utcn.pt.bonta.presentation;

import java.util.ArrayList;

import ro.utcn.pt.bonta.model.Customer;
import ro.utcn.pt.bonta.model.Order;
import ro.utcn.pt.bonta.model.Product;

public interface AppInterfaceButtonEvents {
	public void nothingHappenedOnUI();

	public void newCustomerCreated(String cnp, String name, int age);

	public void customerEdited(Customer selectedCustomer, String cnp, String name, int age);

	public void newProductCreated(String id, String name, double price, int quantity);

	public void productEdited(Product selectedProduct, String id, String name, double price, int quantity);

	public void orderSent(ArrayList<Order> ordersList);

}
