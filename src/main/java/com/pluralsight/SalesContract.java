package com.pluralsight;

import java.io.BufferedWriter;

public class SalesContract extends Contract{

    private double salesTax, principal;//Sales Tax Amount(5%)
    private int recFee;    //Recording Fee($100)
    private boolean financed; // Yes/No do they Finance

    public SalesContract(String customerName, String dateOfCon, String customerEmail, String vehicleSold) {
        super(customerName, dateOfCon, customerEmail, vehicleSold);
        this.salesTax = (0.05 * principal);
        this.recFee = 100;
        this.financed = true;}

        int lesserFee = 295;//Under $10,000
        int higherFee = 495;//Over $10,000
        double LesserInterestRate = .0425;//(4.25%)Loan
        double HigherInterestRate = .0525;//(5.25%)Loan


    @Override
    public Double getTotalPrice(){
        if (financed && principal >=10000){
            double overMonthlyPayment = (principal * LesserInterestRate * Math.pow((1 + LesserInterestRate),48)) / (Math.pow((1+LesserInterestRate),48) - 1);
           double totalPrice=(overMonthlyPayment*48)+(this.salesTax + recFee + higherFee);
           return totalPrice;

        } else{
            double underMonthlyPayment = (principal * HigherInterestRate * Math.pow((1 + HigherInterestRate),24)) / (Math.pow((1+HigherInterestRate),24) - 1);
            double totalPrice=(underMonthlyPayment*24)+(salesTax + recFee + lesserFee);
            return totalPrice;
        }
    }
    @Override
    public Double getMonthlyPay(){
        if (financed && principal >= 10000){
            double overMonthlyPayment = (principal * LesserInterestRate * Math.pow((1 + LesserInterestRate),48)) / (Math.pow((1+LesserInterestRate),48) - 1);
            return overMonthlyPayment;
        }else{
            double underMonthlyPayment = (principal * HigherInterestRate * Math.pow((1 + HigherInterestRate),24)) / (Math.pow((1+HigherInterestRate),24) - 1);
            return underMonthlyPayment;
        }
    }

    public double getPrincipal() {
        return principal;
    }

// public static void main(String[] args) {

//     BufferedWriter saleContract = new BufferedWriter();

// }
}
