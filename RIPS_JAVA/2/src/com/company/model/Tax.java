package com.company.model;

public class Tax {

    public BasicTax basicTax;
    public CopyrightTax copyrightTax;
    public SellTax sellTax;
    public PresentTax presentTax;
    public InternationalTax internationalTax;
    public Boolean individual;

    public Tax(boolean individual, int basicIncome, int copyrightIncome, int sellIncome, int presentIncome, int internationalIncome) {
        this.basicTax = new BasicTax(basicIncome, 2020);
        this.copyrightTax = new CopyrightTax(copyrightIncome, 2020);
        this.sellTax = new SellTax(sellIncome, 2020);
        this.presentTax = new PresentTax(presentIncome, 2020);
        this.internationalTax = new InternationalTax(internationalIncome, 2020);
        this.individual = individual;
    }

    public Tax() {
    }

    public void addBasicIncome(int input, int year) {
        basicTax.addIncome(input, year);
    }

    public void addCopyrightIncome(int input, int year) {
        copyrightTax.addIncome(input, year);
    }

    public void addSellIncome(int input, int year) {
        sellTax.addIncome(input, year);
    }

    public void addPresentIncome(int input, int year) {
        presentTax.addIncome(input, year);
    }

    public void addInternationalIncome(int input, int year) {
        internationalTax.addIncome(input, year);
    }

    public double calculateTotalTax () {
        if (individual) {
            return (basicTax.calculateIndividualTax(true) + copyrightTax.calculateIndividualTax(true)
                    + sellTax.calculateIndividualTax(true) + presentTax.calculateIndividualTax(true)
                    + internationalTax.calculateIndividualTax(true));
        } else {
            return (basicTax.calculatePhysicalTax(true) + copyrightTax.calculatePhysicalTax(true)
                    + sellTax.calculatePhysicalTax(true) + presentTax.calculatePhysicalTax(true)
                    + internationalTax.calculatePhysicalTax(true));
        }
    }

    public double calculateOverAllTax () {
        if (individual) {
            return (basicTax.calculateIndividualTax(false) + copyrightTax.calculateIndividualTax(false)
                    + sellTax.calculateIndividualTax(false) + presentTax.calculateIndividualTax(false)
                    + internationalTax.calculateIndividualTax(false));
        } else {
            return (basicTax.calculatePhysicalTax(false) + copyrightTax.calculatePhysicalTax(false)
                    + sellTax.calculatePhysicalTax(false) + presentTax.calculatePhysicalTax(false)
                    + internationalTax.calculatePhysicalTax(false));
        }
    }

}
