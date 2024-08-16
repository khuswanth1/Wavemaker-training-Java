package com.pack1;

public class Ev_Car extends Vehicle
{

    public Ev_Car(String model, String type, String name,String color, int year) {
        super(model, type, name, color, year);
    }
    @Override
    public void displayInfo() {
        System.out.println("Ev_Bike Details:");
        super.displayInfo();
    }
}
