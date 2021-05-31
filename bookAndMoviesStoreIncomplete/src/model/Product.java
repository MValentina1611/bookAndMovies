package model;

/**
 * ImplementaciOn de la superclase Producto
 * @author angievig
 *
 */
public abstract class Product {
	
	/**
	 * Codigo de un producto, identificador unico
	 */
	private String code;
	
	/**
	 * Nombre del producto
	 */
	private String  name;
	
	/**
	 * Unidades disponibles
	 */
	private int units;
	
	/**
	 * Precio, que puede ser de alquiler o de venta
	 */
	protected double price;
	
	/**
	 * Tipo de un producto que corresponde a una constante del Enum Type
	 */
	private ProductType type;


	/**
	 * Metodo constructor
	 * @param code String con el cOdigo del producto
	 * @param name String con el nombre del producto
	 * @param units int con la cantidad de productos a la venta
	 * @param price double con el precio a la venta
	 * @param type tipo del producto
	 */
	public Product(String code, String name, int units, double price, ProductType type) {
		super();
		this.code = code;
		this.name = name;
		this.units = units;
		this.price = price;
		this.type = type;
	}
	

	
	/**
	 * Metodo abstracto, sirve para mostrar la informacion de un producto
	 * @return un String con la informacion del producto
	 */
	public abstract String getInformation(); 
	
	
	/*
	 * ============ Getters & Setters
	 */
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	
	public double getPrice(int units) {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		this.type = type;
	}


	


}
