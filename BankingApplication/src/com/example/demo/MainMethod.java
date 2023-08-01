package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
//Inheritance
public class MainMethod extends BankingApplication{
  private static Connection connection;
  static Scanner scanner=new Scanner(System.in);
  public boolean login() {
      System.out.print("Enter your account number: ");
      boolean flag=true;
      String accountNumber = scanner.next();
      System.out.print("Enter your Emailid: ");
      String Email = scanner.next();
      try {
    	  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Prakash@3776");
          String selectUser = "SELECT * FROM user WHERE Accountno = ? AND Emailid = ?";
          PreparedStatement stmt = connection.prepareStatement(selectUser);
          stmt.setString(1, accountNumber);
          stmt.setString(2, Email);
          ResultSet resultSet = stmt.executeQuery();
          
          if (resultSet.next()) {
        	  flag=true;
              
          } else {
        	  flag=false;
          }
      } catch (SQLException e) {
          e.printStackTrace();
          System.out.println("Failed to login.");
      }
      return flag;
  }
  void createAccount() {
  	User use=new User();
  	BankAccount acc=new BankAccount();
      System.out.println("Enter your details:");
      System.out.print("AccountNo: ");
      String a=scanner.next();
      use.setAccountno(a);
      acc.setAccountNumber(a);
      System.out.print("Name: ");
      use.setUsername(scanner.next());
      System.out.print("Address: ");
      use.setAddress(scanner.next());
      System.out.print("Mobileno: ");
      use.setMobileno(scanner.next());
      System.out.print("Emailid: ");
      use.setEmailid(scanner.next());
      use.setBalance(0.0);
      acc.setBalance(0.0);
      try {
    	  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Prakash@3776");
          String insertUser= "INSERT INTO user (Accountno, Username, Address, Mobileno, Emailid) VALUES (?, ?, ?, ?, ?)";
          PreparedStatement Stmt = connection.prepareStatement(insertUser);
          Stmt.setString(1, use.getAccountno());
          Stmt.setString(2, use.getUsername());
          Stmt.setString(3,use.getAddress());
          Stmt.setString(4, use.getMobileno());
          Stmt.setString(5, use.getEmailid());
          Stmt.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
          System.out.println("Failed to create account.");
      }
      try {
          String insertUser= "INSERT INTO account (Accountno,Balance) VALUES (?, ?)";
          PreparedStatement Stmt = connection.prepareStatement(insertUser);
          Stmt.setString(1, use.getAccountno());
          Stmt.setDouble(2, use.getBalance());
          Stmt.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
          System.out.println("Failed to create account.");
      }
      System.out.println("Account created successfully " +  use.getUsername());
      System.out.println("Your Account number: " + use.getAccountno());   
  }
  public void start() {
  int choice;
  int limit=0;
  do {
      System.out.println("1. Deposit Money");
      System.out.println("2. Withdraw Money");
      System.out.println("3. Check Account Balance");
      System.out.println("0. Exit");
      System.out.print("Enter your choice: ");  
      choice = scanner.nextInt();
      scanner.nextLine();
      switch (choice) {
          case 1:
              depositMoney();
              break;
          case 2:
              limit++;
              withdrawMoney(limit);
              break;
          case 3:
              checkBalance();
              break;
          case 0:
              System.out.println("Thank you for using the Banking System. Goodbye!");
              break;
          default:
              System.out.println("Invalid choice. Please try again.");
              break;
      }
  } while (choice != 0);
}
  public static void main(String[] args) throws ClassNotFoundException,SQLException {
          MainMethod m = new  MainMethod();
          System.out.println("Welcome to the Banking System");
          System.out.println("1.New User? Register!!!");
          System.out.println("2.Already a User? Login");
          System.out.print("Enter your choice: ");
          int c = scanner.nextInt();
          scanner.nextLine();
          switch (c) {
              case 1:
                  m.createAccount();
                  m.start();
                  break;
              case 2:
                  if(m.login())
                  {
                	  System.out.println("Login Successfull");
                	  m.start();
                  }
                  else
                  {
                	  System.out.println("Invalid Accountno or Emailid!!!");
                	  if(m.login())
                      {
                    	  System.out.println("Login Successfull");
                    	  m.start();
                      }
                  }
                  break;
              default:
                  System.out.println("Invalid choice. Please try again.");
                  break;
          } 
  }
}
