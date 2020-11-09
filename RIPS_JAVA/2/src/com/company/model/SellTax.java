package com.company.model;

public class SellTax extends TaxFactory {

    private int input;
    private int currentPeriodInput;

    public SellTax(int input, int year) {
        this.input = input;
        if (year == 2020) {
            this.currentPeriodInput = input;
        }
    }

    public double calculateIndividualTax(Boolean forCurrentPeriod) {
        if (forCurrentPeriod) {
            return currentPeriodInput * 0.18;
        } else {
            return input * 0.18;
        }
    }

    public double calculatePhysicalTax(Boolean forCurrentPeriod) {
        if (forCurrentPeriod) {
            return currentPeriodInput * 0.2;
        } else {
            return input * 0.2;
        }
    }

    public void addIncome(int input, int year) {
        this.input += input;
        if (year == 2020) {
            this.currentPeriodInput += input;
        }
    }

    @Override
    public void _print() {
        System.out.println("Calculated! SellTax");
    }
}

