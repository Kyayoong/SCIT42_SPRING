package net.softsociety.spring2.vo;

public class PersonVO {
	String name;
	String service;
	String phone;
	
	public PersonVO() {
		super();
	}
	public PersonVO(String name, String service, String phone) {
		super();
		this.name = name;
		this.service = service;
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "PersonVO [name=" + name + ", service=" + service + ", phone=" + phone + "]";
	}
	
	
	
}
