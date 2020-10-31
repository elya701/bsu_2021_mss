package com.company.view;

import com.company.controller.Person;

import java.util.ArrayList;

public class Main {



    public static void main(String[] args) {
        ArrayList<Person> totalPersons = new ArrayList<Person>();

        Person firstPerson = new Person(false, "Roman",800,200,300,500,400);
        Person secondPerson = new Person(true, "Maksim",1000,300,505,203,1230);
        Person thirdPerson = new Person(false, "Samir",3127,312,431,312,312);

        totalPersons.add(firstPerson);
        totalPersons.add(secondPerson);
        totalPersons.add(thirdPerson);

        System.out.println("Calculate total tax for current period");
        System.out.println("--------------------------------------");
        totalPersons.forEach(person -> System.out.println(person.name + " " + person.tax.calculateOverAllTax() + " USD"));
        System.out.println("--------------------------------------");

        System.out.println("Calculate overall tax");
        System.out.println("--------------------------------------");
        totalPersons.forEach(person -> System.out.println(person.name + " " + person.tax.calculateTotalTax() + " USD"));
        System.out.println("--------------------------------------");

        System.out.println("Calculate tax for current period");
        System.out.println("--------------------------------------");
        totalPersons.forEach(person -> person.calculateAllTax(true));
        System.out.println("--------------------------------------");

        System.out.println("Calculate tax overall");
        System.out.println("--------------------------------------");
        totalPersons.forEach(person -> person.calculateAllTax(false));
        System.out.println("--------------------------------------");
//        System.out.println("Pri tax overall");
//
//        Collections.sort(totalPersons,
//                Comparator.comparingDouble(Person::calculateAllTax(true)));
//        totalPersons.forEach(person -> person.calculateAllTax(true));
//        System.out.println("--------------------------------------");
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
