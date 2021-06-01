package model;

public class ProductForSale extends Product implements Saleable{
	
	private double discount;

	public ProductForSale( java.lang.String code, java.lang.String name, int units, double price, ProductType type )
	{
		super(code, name, units, price, type);
		discount = 0;	
	}

	@Override
	public double applyExtraDiscount( double subtotal, double percenteageDiscount )
	{
		double finalValue = 0;

		finalValue = subtotal - percenteageDiscount;

		return finalValue;
	}

	@Override
	public double calculateTax(double totalPrice, double percenteage) 
	{
		double finalValue = 0;

		finalValue = totalPrice + percenteage;

		return finalValue;
	}

	@Override
	public java.lang.String getInformation()
	{
		String info = "";

		info = "\n\tCode: " + super.getCode() + "\n\tName: "+ super.getName() + "\n\tUnits: "+ super.getUnits() + "\n\tPrice: " + super.getPrice( super.getUnits() ) + "\n\tType: " + getType();

		return info;
	}

	@Override
	public double getSalePrice(int units)
	{
		double subtotal = 0;
		double priceMinusDiscount = price - discount;

		subtotal = units * priceMinusDiscount;

		return subtotal;
	}

	@Override
	public boolean isSafeSale(int units)
	{
		boolean safe = false;
		if( (units > 0) && ( units < super.getUnits() ) )
		{
			safe = true;
		}

		return safe;
	}

	public void setDiscount(double discount)
	{
		this.discount = discount;
	}

  }
