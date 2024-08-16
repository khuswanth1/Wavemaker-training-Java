package com.pack1;

public class Main extends Cars
{
            Vehicle car = new Car("Model S", "Sedan", "Tesla", 2024);
            Vehicle bike = new Bike("Street 750", "Cruiser", "Harley Davidson", 2023);
            Vehicle evCar = new EVCAR("Model 3", "Sedan", "Tesla", 2024);
            Vehicle evBike = new EVBike("SR/F", "Streetfighter", "Zero Motorcycles", 2024);
            car.displayInfo();
            System.out.println();
            bike.displayInfo();
            System.out.println();
            evCar.displayInfo();
            System.out.println();
            evBike.displayInfo();
}
