package com.company.controller;

import com.company.model.HDD;
import com.company.model.Optical_Driver;
import com.company.model.RAM;

public class PC {
    private String name;
    private String model;
    private String typeOS;
    private Boolean infected;
    private String manufacturer;
    private HDD hardDisk;
    private Optical_Driver optical_driver;
    private RAM ram;


    public PC (String name, String model, String typeOS, Boolean infected) {
        this.name = name;
        this.model = model;
        this.typeOS = typeOS;
        this.infected = infected;
    }

    public void switchOn () {
        System.out.println("Switching ON PC");
    }

    public void switchOff () {
        System.out.println("Switching OFF PC");
    }

    public void virusTest () {
        String  capacityOfHDD = hardDisk.getCapacity();

        if (this.infected == true) {
            System.out.println("HDD (" + capacityOfHDD + ") was chacked. There is a danger of the viruses!");
        } else {
            System.out.println("HDD (" + capacityOfHDD + ") was chacked. There is no danger in the system");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTypeOS() {
        return typeOS;
    }

    public void setTypeOS(String typeOS) {
        this.typeOS = typeOS;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public HDD getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(HDD hardDisk) {
        this.hardDisk = hardDisk;
    }

    public Optical_Driver getCd_rom() {
        return optical_driver;
    }

    public void setCd_rom(Optical_Driver optical_driver) {
        this.optical_driver = optical_driver;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

}
