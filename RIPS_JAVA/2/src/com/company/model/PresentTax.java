package com.company.model;

public class PresentTax extends Tax {

    private int input;
    private int currentPeriodInput;

    public PresentTax(int input, int year) {
        this.input = input;
        if (year == 2020) {
            this.currentPeriodInput = input;
        }
    }

    public double calculateIndividualTax(Boolean forCurrentPeriod) {
        if (forCurrentPeriod) {
            return currentPeriodInput * 0;
        } else {
            return input * 0;
        }
    }

    public double calculatePhysicalTax(Boolean forCurrentPeriod) {
        if (forCurrentPeriod) {
            return currentPeriodInput * 0;
        } else {
            return input * 0;
        }
    }

    public void addIncome(int input, int year) {
        this.input += input;
        if (year == 2020) {
            this.currentPeriodInput += input;
        }
    }

}
