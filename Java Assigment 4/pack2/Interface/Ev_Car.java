package pack2.Interface;

public class Ev_Car implements Vechicle
{
	@Override
    public String Name_of_vehicle() {
        return "Mahindra";
    }

    @Override
    public String Model_name() {
        return "XUV 400";
    }

    @Override
    public int Seating_capacity()
    {
        return 5;
    }

    @Override
    public String color() {
        return "White-Black";
    }

    @Override
    public String type() {
        return "Electric";
    }
    
    @Override
    public String mileage() {
    	return "375 to 456 km/fullCharge";
    }
    
    @Override
    public int year() {
        return 2023;
    }
}