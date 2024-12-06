package com.pluralsight;

public class LeaseContract extends Contract {

    private double principal, interestRate;
    private double leasingFee;

    public LeaseContract(String customerName, String dateOfCon, String customerEmail, String vehicleSold) {
        super(customerName, dateOfCon, customerEmail, vehicleSold);
        this.leasingFee = (principal * 0.07);
        this.interestRate = (0.04);
    }


    @Override
    public Double getTotalPrice() {
        double overMonthlyPayment = (principal * this.interestRate * Math.pow((1 + this.interestRate),36)) / (Math.pow((1+this.interestRate),36) - 1);
        double totalPrice=(overMonthlyPayment*36)+(this.leasingFee);
        return totalPrice;
    }

    @Override
    public Double getMonthlyPay(){
        double monthlyPayment = (principal * this.interestRate * Math.pow((1 + this.interestRate),36)) / (Math.pow((1+this.interestRate),36) - 1);
        return monthlyPayment;
    }

}
