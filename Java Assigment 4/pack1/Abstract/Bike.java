package com.pack1;
class Bike extends Vehicle {
    public Bike(String model, String type, String name,String color, int year) {
        super(model, type, name, color, year);
    }
    @Override
    public void displayInfo() {
        System.out.println("Bike Details:");
        super.displayInfo();
    }
}