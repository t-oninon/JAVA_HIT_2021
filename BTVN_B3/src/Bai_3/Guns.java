package Bai_3;

import java.util.Scanner;
public class Guns {
    private String weaponName;
    private int ammoNumber = 100;

    public Guns() {

    }

    public Guns(String weaponName) {
        this.weaponName = weaponName;
    }

    public void load(int x) {
        this.ammoNumber += x;
    }

    public String shoot(int x) {
        if (ammoNumber > x) {
            this.ammoNumber -= x;
            return Integer.toString(this.ammoNumber);
        } 
        return "Het dan";
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public int getAmmoNumber() {
        return ammoNumber;
    }

    public void setAmmoNumber(int ammoNumber) {
        this.ammoNumber = ammoNumber;
    }

    
}
