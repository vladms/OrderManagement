package ro.utcn.pt.bonta.model;

public class Customer {
	private String cnp;
	private String name;
	private int age;
	
	public Customer(String CNP, String name, int age){
		this.cnp = CNP;
		this.name = name;
		this.age = age;
		
	}

	public String getCNP() {
		return cnp;
	}

	public void setCNP(String cnp) {
		this.cnp = cnp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
