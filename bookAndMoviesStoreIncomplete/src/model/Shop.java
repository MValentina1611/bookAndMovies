package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author angievig
 *
 */
public class Shop {

	/**
	 * Constante con el valor del impuesto
	 */
	public static final double TAX_IVA= 0.19;
	
	/**
	 * Constantes para definir la operaci0n a ejecutar
	 */
	public static final int RENT=1;
	public static final int SALE=2;
	
	/**
	 * Nombre de la tienda
	 */
	private String name;
	
	/**
	 * Lista de productos que rePREsenta el catalogo
	 */
	private ArrayList<Product> catalog;
	
	/**
	 * Valor acumulado de las ventas 
	 */
	private double totalSales;
	
	/**
	 * Valor acumulado de los alquileres
	 */
	private double totalRents;
	

	/**
	 * Metodo constructor<br>
	 * POS: se inicializa el ArrayList de productos<br>
	 * totalSales y totalRents son inicializados en cero
	 * @param aName es el nombre de la tienda
	 */
	public Shop(String aName) {
		name = aName;
		catalog = new ArrayList<Product>();
		totalSales=0;
		totalRents=0;
		
	}


	/**
	 * Metodo que agrega al catalogo un producto para vender <br>
	 * PRE: el arrayList catalog está inicializado <br>
	 * POS: catalog contiene un nuevo producto, si ya existe un producto <br>
	 * con el mismo c0digo no hay cambios.
	 * @param code, c0digo del producto
	 * @param name, nombre del producto
	 * @param units, cantidad de unidades para la venta
	 * @param price, PREcio del alquiler 
	 * @param type, tipo del productocode
	 * @return una cadena informando si el producto fue agregado al catalogo o un mensaje
	 * informando que el producto ya existe. 
	 */
	public String addProduct(String code,String name, int units, double price, ProductType type) 
	{
		Product productForSale = new ProductForSale(code, name, units, price, type);
		catalog.add( productForSale );
		return "The product for sale was added to the catalog";
	}
	
	/**
	 * Metodo que agrega al catalogo un producto para alquiler, <br>
	 * PRE: el arrayList catalog está inicializado <br>
	 * POS: catalog contiene un nuevo producto, si ya existe un producto <br>
	 * con el mismo c0digo no hay cambios. 
	 * @param code, c0digo del producto
	 * @param name, nombre del producto
	 * @param price, PREcio del alquiler 
	 * @param type, tipo del producto
	 * @return una cadena informando si el producto fue agregado al catalogo o un mensaje
	 * informando que el producto ya existe. 
	 */
	public String addProduct(String code, String name, double price, ProductType type)
	{
		Product productForRent = new ProductForRent(code, name, price, type);
		catalog.add( productForRent );

		return "The product for rent was added to the catalog";
	}
	
	/**
	 * Metodo que crea una cadena con la informaci0n de los productos <br>
	 * que hay en el catalogo.
	 * @return cadena con la informacion de los productos
	 */
	public String showCatalog() 
	{
		String info = "";

		for(int i = 0; i < catalog.size(); i++ )
		{
			info += catalog.get(i).getInformation() +"\n";
		}	
		
		return info;
	}
	
	/**
	 * Metodo que busca un producto en el catalogo por c0digo <br>
	 * PRE: catalog es un arrayList que ha sido inicializado
	 * @param code, cadena con el c0digo a buscar 
	 *        code!= null AND code != ""
	 * @return el objeto de la clase Product con el mismo
	 * c0digo que el parámetro, null en el caso en que la lista
	 * no contiene un producto con ese c0digo
	 */
	public Product findProduct(String code) {
		Product p=null;
		
		for(int i = 0; i < catalog.size(); i++ )
		{
			if( catalog.get(i).getCode() == code )
			{
				p = catalog.get(i);
			}
		}

		return p;
	}
	
	
	/*
	 * ================================================
	 * Metodos para las operaciones de venta y alquiler
	 * ================================================
	 */
	
	/**
	 * Metodo que busca un producto por c0digo e informa el tipo <br>
	 * del producto usando las constantes definidas en esta clase 
	 * @param code es una cadena con el c0digo 
	 * @return SALE si es para vender, 
	 * RENT si es para alquilar
	 */
	public int getOperation(String code) 
	{
		int operation= -1;
		Product p = findProduct(code);

		if(p instanceof Saleable)
		{
			operation = SALE;
		}
		else if(p instanceof Rentable)
		{
			operation = RENT;
		}
		return operation;
		
	}

	/**
	 * Metodo que recibe los datos para hacer una venta y llama al <br>
	 * metodo que se encarga de hacer el proceso de ventas asegurándose <br>
	 * que solo se procesen objetos que implementen la interfaz Saleable
	 * @param cod String con el codigo del producto
	 * @param units int con la cantidad de unidades a vender
	 * @param discount double con el porcentaje de descuento extra (numero entre 0 y 1)
	 * @return mensaje de respuesta de la venta
	 */
	public String saleProduct(String cod, int units, double discount) 
	{
		String answer="";
		
		Product p = findProduct(cod);
		answer = sale((Saleable)p, units, discount);
		return answer;
		
	}
	/**
	 * Metodo que recibe los datos para hacer un alquiler y llama al <br>
	 * metodo que se encarga de hacer el proceso de alquiler asegurándose <br>
	 * que solo se procesen objetos que implementen la interfaz Rentable
	 * @param cod String con el codigo del producto
	 * @param days int con la cantidad de días 
	 * @return mensaje de respuesta del alquiler
	 */
	public String rentProduct(String cod, int days) {
		String answer="";
		
		Product p = findProduct(cod);
		answer= rent((Rentable)p, days);
		return answer;
		
	}	

	/**
	 * Metodo que realiza el proceso de venta, usando los metodos <br>
	 * de la interfaz Saleable<br>
	 * PRE: la variable totalSales acumula el total de ventas<br>
	 * POS: <br> 
	 * - Se incrementa totalSales con el monto total de la venta <br>
	 * - A la cantidad de unidades de un producto se descuentan <br>
	 *  las unidades vendidas.
	 * @param p objeto que implementa la interfaz Saleable
	 * @param units int con la cantidad de unidades a vender
	 * @param discount double con el porcentaje de descuento extra
	 *  (numero entre 0 y 1)units int cantidad de unidades a vender
	 * @return un mensaje con el resultado de la venta
	 */
	private  String sale(Saleable p, int units, double discount) 
	{
		
		/*
		 * Para hacer una venta
		 * 1. Se verifica si es seguro vender, es decir, si
		 * hay suficientes unidades para vender
		 * si es seguro: 
		 * 	2. se calcula el PREcio de la venta 
		 * 	3. se aplica el descuento extra
		 * 	4. se calcula el monto de los impuestos
		 * 	5. Se calcula el total a pagar y se incrementa el total de ventas
		 * 	6. Se retorna un mensaje con el total a pagar y confirmando la venta
		 * si no: 
		 *  - Se muestra un mensaje reportando el error.
		 */

		String out = "";
		double salePrice = 0;
		double priceWithED = 0;
		double amountWithTax = 0;
		double totalToPay = 0;

		//1 
		if( p.isSafeSale(units) == true )
		{
			//2
			salePrice = p.getSalePrice(units);
			//3
			priceWithED = p.applyExtraDiscount( salePrice, discount );
			//4
			amountWithTax = priceWithED * TAX_IVA;
			//5
			totalToPay = p.calculateTax( priceWithED, amountWithTax );
			totalSales++;
			//6
			out = "\n\tTOTAL = "+totalToPay +"\n\t==The product has been sold==";

		}
		else
		{
			out = "\n\t==ERROR==\n\tNot enough units";
		}

		return out;
		
	}
	
	/**
	 *  Metodo que realiza el proceso de alquiler usando los metodos de la <br>
	 *  interfaz Rentable <br>
	 * PRE: la variable totalRents acumula el total de alquileres<br>
	 * POS: <br> 
	 * - Se incrementa totalRents con el monto total de la venta <br> 
	 * - Se cambia el estado del producto a RENTED <br>
	 * @param  p objeto de una clase que implementa la interfaz Rentable
	 * @param days cantidad de días que se alquila un producto
	 * @return un mensaje con el resultado del alquiler
	 */
	private  String rent(Rentable p, int days) {
		/*
		 * Para hacer una venta
		 * 1. Se verifica si es eguro alquilar, es decir si el producto 
		 * está disponible
		 * si es seguro: 
		 * 	2. se calcula el PREcio del alquiler
		 * 	3. se alquila el producto cambiando el estado a alquilado 
		 * y calculando la fecha de devoluci0n
		 * 	6. Se retorna un mensaje con el total a pagar y confirmando el alquiler
		 * si no: 
		 *  - Se muestra un mensaje reportando el error.
		 */
		String out = "";
		double rentPrice = 0;
		//1
		if( p.isSafeRent() == true )
		{
			//2
			rentPrice = p.getRentPrice( days );
			//3
			p.rentProduct( days );

			out = "\n\tTOTAL= "+ rentPrice +"\n\t==The product has been rented==";
		}
		else
		{
			out = "\n\t==ERROR==\n\tThe product is not available";
		}

		return out;
	}
	

	/**
	 * 
	 * @return el nombre de la tienda
	 */
	public String getName() {
		return name;
	}

	


	

}
