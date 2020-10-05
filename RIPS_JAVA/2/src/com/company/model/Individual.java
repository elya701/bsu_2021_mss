package com.company.model;

import com.company.helpers.ValueComparator;

import java.util.*;

public class Individual {

    public String name;
    private double basicTax;
    private double copyrightTax;
    private double sellTax;
    private double presentTax;
    private double internationalTax;

    public Individual (String name, int basicIncome, int copyrightIncome,
                       int sellIncome, int presentIncome, int internationalIncome) {
        this.name = name;
        this.basicTax = basicIncome *  0.08;
        this.copyrightTax = copyrightIncome * 0.12;
        this.sellTax = sellIncome * 0.2;
        this.presentTax = presentIncome * 0;
        this.internationalTax = internationalIncome * 0.04;
    }

    public void printTotalTax () {
        System.out.println(name + " total tax is " + getTotalTax() + " USD");
    }

    public void printSortedTax () {
        Map<String, Double> dictionary = new HashMap<String, Double>();
        ValueComparator bvc = new ValueComparator(dictionary);
        TreeMap<String, Double> sortedDictionary = new TreeMap<String, Double>(bvc);

        dictionary.put("BasicTax", basicTax);
        dictionary.put("CopyrightTax", copyrightTax);
        dictionary.put("SellTax", sellTax);
        dictionary.put("PresentTax", presentTax);
        dictionary.put("InternationalTax", internationalTax);
        sortedDictionary.putAll(dictionary);
        System.out.println(name + " " + sortedDictionary);
    }

    public double getTotalTax() {
        return (this.basicTax + this.copyrightTax + this.sellTax + this.presentTax + this.internationalTax);
    }

    public double getBasicTax() {
        return this.basicTax;
    }

    public double getCopyrightTax() {
        return this.copyrightTax;
    }

    public double getSellTax() {
        return this.sellTax;
    }

    public double getPresentTax() {
        return this.presentTax;
    }

    public double getInternationalTax() {
        return this.internationalTax;
    }

    public void addBasicIncome(double income, int year) {
        if (year == 2020) {
            this.basicTax += income * 0.08;
        }
    }

    public void addCopyrightIncome(double income, int year) {
        if (year == 2020) {
            this.basicTax += income * 0.12;
        }
    }

    public void addSellIncome(double income, int year) {
        if (year == 2020) {
            this.basicTax += income * 0.2;
        }
    }

    public void addPresentIncome(double income, int year) {
        if (year == 2020) {
            this.basicTax += income * 0;
        }
    }

    public void addInternationalIncome(double income, int year) {
        if (year == 2020) {
            this.basicTax += income * 0.04;
        }
    }
}
