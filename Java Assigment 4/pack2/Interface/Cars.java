package com.pack2.Interace;

public record Cars() implements Vechicle {
	 @Override
	   public String Name_of_vehicle()
	    {
	    return "BMW";
	    }
	    @Override
	    public String Model_name()
	    {
	        return"X7";
	    }
	    public int Seating_capacity()
	    {
	        return 5;
	    }
	    
	    @Override
	    public String color()
	    {
	        return"Black,White,Blue";
	    }

	    @Override
	    public String type()
	    {
	        return "Diseil/petrol";
	    }
	    
	    public String mileage()
	    {
	    	return "11.29 to 14.31";
	    }
	    
	    @Override
	    public int year() {
	        return 2018;
	    }

}
