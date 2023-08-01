package com.example.demo;

public abstract class Bank {
		abstract void depositMoney();
		abstract void withdrawMoney();
		abstract void withdrawMoney(int limit);
		abstract void checkBalance();
}
