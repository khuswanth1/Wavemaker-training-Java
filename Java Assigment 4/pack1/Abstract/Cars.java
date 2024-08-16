package com.pack1;

public class Cars extends Vehicle
{
    public Cars(String model, String type, String name, String color, int year) {
        super(model, type, name, color, year);
    }

    @Override
    public void displayInfo() {
        System.out.println("Car Details:");
        super.displayInfo();
    }
}