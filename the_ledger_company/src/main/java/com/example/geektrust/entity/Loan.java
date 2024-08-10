package com.example.geektrust.entity;


public class Loan {
    private String user;
    private String bank;
    private Double principal;
    private Integer number_of_years_tenure;
    private Double interest;
    public Loan(String user, String bank, Double principal, Integer tenure, Double interest){
        this.user = user;
        this.bank  = bank;
        this.principal = principal;
        this.interest = interest;
        this.number_of_years_tenure = tenure;
    }
    public Double getPrincipal() {
        return principal;
    }

    public String getUser() {
        return user;
    }

    public Double getInterest() {
        return interest;
    }

    public Integer getNumber_of_years_tenure() {
        return number_of_years_tenure;
    }

    public String getBank() {
        return bank;
    }

}
