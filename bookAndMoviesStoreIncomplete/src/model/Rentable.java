package model;

public interface Rentable
{
	public boolean isSafeRent();
	public double getRentPrice(int amountDays );
	public void rentProduct(int amountDays );
}