package com.company.controller;

import com.company.helpers.ValueComparator;
import com.company.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Person {

    public Tax tax;
    public String name;

    public Person (Boolean individual, String name, int basicIncome, int copyrightIncome, int sellIncome, int presentIncome, int internationalIncome) {
        this.name = name;
        this.tax = new Tax(individual, basicIncome, copyrightIncome, sellIncome, presentIncome, internationalIncome);
    }

    public void calculateAllTax(Boolean currentPeriod) {
        Map<String, Double> dictionary = new HashMap<String, Double>();
        ValueComparator bvc = new ValueComparator(dictionary);
        TreeMap<String, Double> sortedDictionary = new TreeMap<String, Double>(bvc);

        dictionary.put("BasicTax", tax.basicTax.calculateIndividualTax(currentPeriod));
        dictionary.put("CopyrightTax", tax.copyrightTax.calculateIndividualTax(currentPeriod));
        dictionary.put("SellTax", tax.sellTax.calculateIndividualTax(currentPeriod));
        dictionary.put("PresentTax", tax.presentTax.calculateIndividualTax(currentPeriod));
        dictionary.put("InternationalTax", tax.internationalTax.calculateIndividualTax(currentPeriod));
        sortedDictionary.putAll(dictionary);
        System.out.println(name + " " + sortedDictionary);
    }
}
