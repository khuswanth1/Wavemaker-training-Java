package pack2.Interface;

public class Ev_Bike implements Vechicle
{
	@Override
    public String Name_of_vehicle()
    {
        return "Hero";
    }
    @Override
    public String Model_name()
    {
        return"V1 pro";
    }
    @Override
    public int Seating_capacity()
    {
        return 2;
    }
    @Override
    public String color()
    {
        return"gray-Black";
    }

    @Override
   public  String type(){
        return "Electric";
    }
    @Override
    public String mileage() {
    	return "110km per charge";
    }
    @Override
    public int year() {
        return 2023;
    }
}