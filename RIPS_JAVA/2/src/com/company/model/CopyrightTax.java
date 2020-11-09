package com.company.model;

public class CopyrightTax extends TaxFactory {

    private int input;
    private int currentPeriodInput;

    public CopyrightTax(int input, int year) {
        this.input = input;
        if (year == 2020) {
            this.currentPeriodInput = input;
        }
    }

    public double calculateIndividualTax(Boolean forCurrentPeriod) {
        if (forCurrentPeriod) {
            return currentPeriodInput * 0.1;
        } else {
            return input * 0.1;
        }
    }

    public double calculatePhysicalTax(Boolean forCurrentPeriod) {
        if (forCurrentPeriod) {
            return currentPeriodInput * 0.15;
        } else {
            return input * 0.15;
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
        System.out.println("Calculated! CopyrightTax");
    }
}
