package com.company.model;

public class BasicTax extends Tax {

    private int input;
    private int currentPeriodInput;

    public BasicTax(int input, int year) {
        this.input = input;
        if (year == 2020) {
            this.currentPeriodInput = input;
        }
    }


    public double calculateIndividualTax(Boolean forCurrentPeriod) {
        if (forCurrentPeriod) {
            return currentPeriodInput * 0.08;
        } else {
            return input * 0.08;
        }
    }

    public double calculatePhysicalTax(Boolean forCurrentPeriod) {
        if (forCurrentPeriod) {
            return currentPeriodInput * 0.12;
        } else {
            return input * 0.12;
        }
    }

    public void addIncome(int input, int year) {
        this.input += input;
        if (year == 2020) {
            this.currentPeriodInput += input;
        }
    }

}
