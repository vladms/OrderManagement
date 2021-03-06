package ro.utcn.pt.bonta.model;

import java.util.ArrayList;

public class Order {
	private String id;
	private String customerCNP;
	private String productID;
	private int quantity;
	
	public Order(){
		this.id = "-1";
		this.customerCNP = "";
	}

	public Order(String id, String customerCNP){
		this.id = id;
		this.setCustomerCNP(customerCNP);
	}
	
	public Order(String id, String customerCNP, String productID, int quantity) {
		this.id = id;
		this.customerCNP = customerCNP;
		this.setProductID(productID);
		this.quantity = quantity;
	}
	
//	public void addProduct(Product product){
//		int size = products.size();
//		int index;
//		for (index = 0;index < size; ++index){
//			if (product.getId() == products.get(index).getId()){
//				products.remove(index);
//				break;
//			}
//		}
//		this.products.add(product);
//	}
//	
//	public void deleteProduct(Product productDelete){
//		int index = 0;
//		for (Product product : this.products){
//			if (product == productDelete){
//				this.products.remove(index);
//			}
//			index++;
//		}
//	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerCNP() {
		return customerCNP;
	}

	public void setCustomerCNP(String customerCNP) {
		this.customerCNP = customerCNP;
	}

	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	
}
