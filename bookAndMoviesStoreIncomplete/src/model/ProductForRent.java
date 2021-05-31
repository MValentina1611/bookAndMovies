package model;
import java.time.LocalDate;
import model.State;

public class ProductForRent extends Product implements Rentable{
	
	private LocalDate devolutionDate;
	private State state;

	public ProductForRent( java.lang.String code, java.lang.String name, int units, double price, ProductType type, int numDays )
	{
		super(code, name, 1, price, type);
		state = State.AVAILABLE;
		devolutionDate= LocalDate.of(2021, 05, 28);

	}

	@Override
	public double getRentPrice(int amountDays)
	{
		double rentPrice = 0;

		rentPrice = amountDays * price;

		return rentPrice;
	}

	public void setState( State state )
	{
		this.state = state;
	}

	public State getState()
	{
		return state;
	}
	@Override
	public void rentProduct(int amountDays)
	{
		setState( State.RENTED);
		devolutionDate = localDate.plusDays(5);
	}


	@Override
	public java.lang.String getInformation()
	{
		String info = "";
		info = "\n\tCode: " + code + "\n\tName: "+ name + "\n\tUnits: "+ units + "\n\tPrice: " + price + "\n\tType: " + type;+ "\n\tState: "+ state + "\n\tDevolution Date: "+ devolutionDate;
		return info;
	}

	@Override
	public boolean isSafeRent()
	{
		boolean safe = false;

		if( getState() == State.AVAILABLE )
		{
			safe = true;
		}

		return safe;

	}
}
