package richardsBar;

public class Drinks extends Bar {

	
	private String name;
	private int numberBought = 0;
	private double price;
	private String description;

	public Drinks(String name, String description, double price) {
		this.name = name;
		this.price = price;
		this.description = description;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberBought() {
		return numberBought;
	}

	public void setNumberBought() {
		this.numberBought += 1;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
