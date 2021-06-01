package model;
import java.time.LocalDate;
import model.State;

public class ProductForRent extends Product implements Rentable{
	
	private LocalDate devolutionDate;
	private State state;

	public ProductForRent( java.lang.String code, java.lang.String name, double price, ProductType type )
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
		devolutionDate = devolutionDate.plusDays(5);
	}


	@Override
	public java.lang.String getInformation()
	{
		String info = "";
		info = "\n\tCode: " + super.getCode() + "\n\tName: "+ super.getName() + "\n\tUnits: "+ super.getUnits() + "\n\tPrice: " + super.getPrice(1) + "\n\tType: " + super.getType()+ "\n\tState: "+ state +  "\n\tDevolution Date: "+ devolutionDate;
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
