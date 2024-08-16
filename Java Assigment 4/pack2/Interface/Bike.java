package pack2.Interface;

public class Bike implements Vechicle
{
    @Override
    public String Name_of_vehicle() 
    {
        return "Harley-Davidson";
    }

    @Override
    public String Model_name() 
    {
        return "X440";
    }

    @Override
    public int Seating_capacity()
    {
        return 2;
    }

    @Override
    public String color() 
    {
        return "Blue-Black,Green-Black, Red-Black";
    }

    @Override
    public String type()
    {
        return"Petrol";
    }
    @Override
    public String mileage() 
    {
    	return "40kmpl KMPL";
    }
    @Override
    public int year()
    {
        return 2023;
    }
}