package org.springframework.batch.jsr.domain;

public class Customer {
	private String name;
	private String phone;
	private String email;
	private String address;
	private String city;
	private static int counter = 0;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		counter++;
		System.out.println("************************** counter: " + counter);
		return counter + "," + name + "," + phone + "," + email + "," + address + "," + city;
	}
}
