package com.company.model;

public class InternationalTax extends Tax {

    private int input;
    private int currentPeriodInput;

    public InternationalTax(int input, int year) {
        this.input = input;
        if (year == 2020) {
            this.currentPeriodInput = input;
        }
    }

    public double calculateIndividualTax(Boolean forCurrentPeriod) {
        if (forCurrentPeriod) {
            return currentPeriodInput * 0.11;
        } else {
            return input * 0.11;
        }
    }

    public double calculatePhysicalTax(Boolean forCurrentPeriod) {
        if (forCurrentPeriod) {
            return currentPeriodInput * 0.14;
        } else {
            return input * 0.14;
        }
    }

    public void addIncome(int input, int year) {
        this.input += input;
        if (year == 2020) {
            this.currentPeriodInput += input;
        }
    }
}
