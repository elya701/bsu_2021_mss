package com.company.view;

import com.company.model.Individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {



    public static void main(String[] args) {
        ArrayList<Individual> totalIndividuals = new ArrayList<Individual>();

        Individual individual = new Individual("Roman", 800, 0,
                0, 0, 200);


        Individual individual3 = new Individual("Petr", 1000, 40,
                50, 20, 100);

        Individual individual4 = new Individual("Kirill", 10000, 40,
                50, 20, 100);

        totalIndividuals.add(individual);
        totalIndividuals.add(individual4);
        totalIndividuals.add(individual3);

        System.out.println("--------------------------------------");
        totalIndividuals.forEach(individual2 -> individual2.printTotalTax());
        System.out.println("--------------------------------------");

        Collections.sort(totalIndividuals,
                Comparator.comparingDouble(Individual::getTotalTax).reversed());
        totalIndividuals.forEach(individual2 -> individual2.printSortedTax());
        System.out.println("--------------------------------------");
    }

//    public void createAndAppendIndividual() {
//        Individual individual = new Individual("Roman", 800, 0,
//                0, 0, 200);
//        totalIndividuals.add(individual);
//    }
//
//    public void printSortedTax() {
//        Collections.sort(totalIndividuals,
//                Comparator.comparingDouble(Individual::getTotalTax));
//        totalIndividuals.forEach(individual -> System.out.println(individual));
//    }
}
