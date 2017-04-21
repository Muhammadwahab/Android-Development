package com.example.accountapp.logic;

import com.example.accountapp.ui.OutputInterface;

/**
 * This file defines the Account class.  It provides the basis for a
 * series of improvements you'll need to make as you progress through
 * the lessons in Module 6.
 */
public class Account<newNumber> {
    /**
     * This is the variable that stores our OutputInterface instance.
     * <p/>
     * This is how we will interact with the User Interface
     * [MainActivity.java].
     * </p>
     * This was renamed to 'mOut' from 'out', as it is in the video
     * lessons, to better match Android/Java naming guidelines.
     */

    final OutputInterface mOut;

    /**
     * Name of the account holder.
     */
    private String name;

    /**
     * Number of the account.
     */
    public newNumber number;

    /**
     * Current balance in the account.
     */
    private double balance;

    /**
     * Constructor initializes the field
     */
    private static  int count=0;
    public Account(OutputInterface out) {
        mOut = out;
    }
    public Account(String name,OutputInterface out,newNumber numbere)
    {
        this.name=name;
        this.number=numbere;
        mOut=out;
    }
    public Account(String name,double balance,OutputInterface out,newNumber number)
    {
        this(name,out,number);
        this.balance=balance;
    }

    /**
     * Deposit @a amount into the account.
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Withdraw @a amount from the account.  Prints "Insufficient
     * Funds" if there's not enough money in the account.
     */
    public boolean withdrawal(double amount) {
        if (balance > amount)
        {
            balance -= amount;
            return true;
        }


        else
        {
            mOut.println("Insufficient Funds");
            return false;
        }

    }

    /**
     * Display the current @a amount in the account.
     */
    public void displayBalance() {
        mOut.println("The balance on account " 
                     + number
                     + " is " 
                     + balance);
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;

    }

    public  newNumber getNumber(){
        return this.number;
    }
    public double getBalance()
    {

        return this.balance;
    }

    @Override
    public String toString() {
        return this.getName()+this.getNumber();
    }
    public boolean equals(Object object)
    {
        if(object instanceof Account)
        {
            Account account=(Account) object;
            return number==account.number;
        }
        else
        {
            return false;
        }
    }
    private String getAccountNumber()
    {
        count=(count+1)%100;
        return (16+this.getName()+count);

    }
}
