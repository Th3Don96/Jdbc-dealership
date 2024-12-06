package com.pluralsight;

public abstract class Contract {

    private String dateOfContract, customerName, customerEmail, vehicleSold;
    private String totalPrice, monthlyPay;

    public Contract(String customerName, String dateOfContract, String customerEmail, String vehicleSold) {
        this.customerName = customerName;
        this.dateOfContract = dateOfContract;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;

    }

    @Override
    public String toString() {
        return "Contract{" +
                "dateOfContract='" + dateOfContract + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", vehicleSold='" + vehicleSold + '\'' +
                ", totalPrice=" + totalPrice +
                ", monthlyPay=" + monthlyPay +
                '}';
    }

    public String getDateOfCon() {
        return dateOfContract;
    }

    public void setDateOfCon(String dateOfCon) {
        this.dateOfContract = dateOfContract;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(String vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public abstract Double  getTotalPrice();

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public abstract Double getMonthlyPay();

    public void setMonthlyPay(String monthlyPay) {
        this.monthlyPay = monthlyPay;
    }
}
