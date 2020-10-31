package com.company.view;

import com.company.controller.PC;
import com.company.model.HDD;
import com.company.model.Optical_Driver;
import com.company.model.RAM;

public class Main {

    public static void main(String[] args) {


        RAM ram = new RAM("16 GB", 1866);
        Optical_Driver cd_rom = new Optical_Driver("DVD-ROM", 8200);
        HDD hdd = new HDD("SSD", "Kingston", "256 GB");
        PC myPC = new PC("RomanPC", "Macbook Pro", "Mac OS", false);

        myPC.setHardDisk(hdd);

        myPC.switchOn();
        myPC.virusTest();
        myPC.getHardDisk().printCapacity();
        myPC.switchOff();
    }
}
