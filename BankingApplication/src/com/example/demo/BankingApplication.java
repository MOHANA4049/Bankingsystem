package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
//abstraction
class BankingApplication extends Bank  {
    private Scanner scanner;
    private Connection connection;
    public BankingApplication() {
        scanner = new Scanner(System.in);
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Prakash@3776");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database.");
        }
    }
    @Override
    void depositMoney() {
        System.out.print("Enter Your account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter the deposit amount: ");
        Long amount = scanner.nextLong();
        scanner.nextLine();
        try {
        	String sql = "UPDATE account SET Balance = Balance + ? WHERE Accountno = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, amount);
            statement.setString(2, accountNumber);
            statement.executeUpdate();
            System.out.println("Amount deposited successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to deposit money.");    
        }
    }
    @Override
    void withdrawMoney() {
        System.out.print("Enter Your account number: ");
        String accountNumber = scanner.next();      
        System.out.print("Enter the withdrawal amount: ");
        Long amount = scanner.nextLong();
        scanner.nextLine(); 
        	   try {
                   String updateBalance = "UPDATE account SET Balance =Balance-? WHERE Accountno = ?";
                   PreparedStatement Stmt = connection.prepareStatement(updateBalance);
                   Stmt.setLong(1,amount);
                   Stmt.setString(2,accountNumber);
                   Stmt.executeUpdate();
               } catch (SQLException e) {
                   e.printStackTrace();
                   System.out.println("Failed to Withdraw money.");    
               }
        	   System.out.println("Withdrawal successful.");
        } 
    @Override
    void withdrawMoney(int limit) {
        System.out.print("Enter Your account number: ");
        String accountNumber = scanner.next();      
        System.out.print("Enter the withdrawal amount: ");
        Long amount = scanner.nextLong();
        scanner.nextLine(); 
        if(limit<=3)
        {
        	   try {
                   String updateBalance = "UPDATE account SET Balance =Balance-? WHERE Accountno = ?";
                   PreparedStatement Stmt = connection.prepareStatement(updateBalance);
                   Stmt.setLong(1,amount);
                   Stmt.setString(2,accountNumber);
                   Stmt.executeUpdate();
               } catch (SQLException e) {
                   e.printStackTrace();
                   System.out.println("Failed to Withdraw money.");    
               }
        	   System.out.println("Withdraw successful.");
        }
        else
        {
        	System.out.println("Limit exceeded");
        }
        } 
    @Override
    void checkBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        try {
            String selectBalance = "SELECT Balance FROM account WHERE Accountno = ?";
            PreparedStatement Stmt = connection.prepareStatement(selectBalance);
            Stmt.setString(1, accountNumber);
            ResultSet resultSet = Stmt.executeQuery();
            if (resultSet.next()) {
                double balance = resultSet.getDouble("Balance");
                System.out.println("Account Balance: " + Math.abs(balance));
            } else {
                System.out.println("Account not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to check account balance.");
        }
    }
}