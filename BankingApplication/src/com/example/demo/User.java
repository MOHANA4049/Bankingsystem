package com.example.demo;
public class User {
    private String Accountno;
    private String Username;
    private String Address;
    private String Emailid;
    private String Mobileno;
    private Double Balance;
    public User() {
		// TODO Auto-generated constructor stub
	}
	// Getters and setters for Encapsulation
    public String getAccountno() {
        return Accountno;
    }
    public String getUsername() {
        return Username;
    }
    public void setUsername(String name) {
        this.Username = name;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String Address) {
        this.Address = Address;
    }
    public String getMobileno() {
        return Mobileno;
    }
    public void setMobileno(String phone) {
        this.Mobileno = phone;
    }
    public String getEmailid() {
        return Emailid;
    }
    public void setEmailid(String email) {
        this.Emailid = email;
    }
    public double getBalance() {
        return Balance;
    }
    public void setBalance(double Balance) {
        this.Balance = Balance;
    }
	public void setAccountno(String a) {
		this.Accountno = a;
		
	}
}
