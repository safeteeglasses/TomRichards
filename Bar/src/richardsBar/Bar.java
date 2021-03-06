package richardsBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Bar{
	
private double balance = 0.0;
private int counter    = 0;
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double money) {
		this.balance = money;
	}
	public void cashOut() {
		System.out.printf("Your total order comes to : $%.2f\n", getBalance());
		String [] cashOrCredit = {"Cash", "Credit"};
		String choice = "";
		choice =  (String)menu.getChoiceFromOptions(cashOrCredit);
		if(choice.equals("Cash")){
			feedMoney();
		}
			else {
				 if(choice.equals("Credit")) { 
					enterCard();
			}
		}
		while(getBalance() > 0) {
			feedMoney();
		}
		 {
			finishingTransaction();
		}	
	}
	public void feedMoney() {
		System.out.printf("\nCurrent bill is $%.2f",getBalance());
		System.out.println("\n");
		System.out.println("Dollar amount");
		String [] dollarAmounts = {"1.00", "5.00", "10.00", "20.00", "50.00"};
		String choice = "";
		choice = (String)menu.getChoiceFromOptions(dollarAmounts);
		double dollar = Double.parseDouble(choice);
		
		dollar = getBalance() - dollar;
		setBalance(dollar);  
	}
	public Bar() {
		balance = 0.0;
	}
	public void enterCard() {
		Scanner keyboard = new Scanner(System.in);
		String creditCard = "";
		String cardExpire = "";
		System.out.println("Please enter your 16 digit card number");
		creditCard=keyboard.nextLine();
		System.out.println("Please enter the expiration date xx/xxxx");
		cardExpire=keyboard.nextLine();
			setBalance(0);
		System.out.println("\nApproved, Thank you!");	
	}
	private List<Drinks> options = new ArrayList<Drinks>();
	private static final String   MAIN_MENU_OPTIONS_DISPLAY  = "Display today's drinks";
	private static final String   MAIN_MENU_OPTIONS_PURCHASE = "Purchase";
	private static final String   MAIN_MENU_OPTIONS_EXIT     = "Exit";
	private static final String[] MAIN_MENU_OPTIONS			 = { MAIN_MENU_OPTIONS_DISPLAY,
																 MAIN_MENU_OPTIONS_PURCHASE,
																 MAIN_MENU_OPTIONS_EXIT, 
																 };
	private Menu menu;
	
	public Bar (Menu menu) {
		this.menu = menu;
	}
	public void run() throws FileNotFoundException {
		System.out.println("Welcome to Tom's Bar!");
	
		while(true) {
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			
			if(choice.equals(MAIN_MENU_OPTIONS_DISPLAY)) {
				displayOptions();	
			} else if(choice.equals(MAIN_MENU_OPTIONS_PURCHASE)) {
				
				while(choice != "Finish Transaction") {
					//System.out.printf("\nCurrent money provided %.2f", getBalance());
					choice = (String)menu.getChoiceFromOptions(purchaseOptions());
					
				if(choice.equals("Cash out bill")) {
					cashOut();
				}
				if(choice.equals("Select Drink")) {
				List<String>choices = new ArrayList<String>();
				choices = fileReader();
				String[] choicess = new String[choices.size()];
				int count = 0;
				for (String option: choices) {
					choicess[count] = option;
					count++;
				}
				counter++;
				choice = (String)menu.getChoiceFromOptions(choicess);
				purchaseItem(choice);
				}
			}
		}
		else if(choice.equals(MAIN_MENU_OPTIONS_EXIT)) {
			return;
			}
		}
	}		
	public void displayOptions() throws FileNotFoundException {
		List<String> result = new ArrayList<String>();
		String line = "";
		File inputFile = new File("menu.txt");
		Scanner fileScanner = new Scanner(inputFile);
		while(fileScanner.hasNextLine()) {
			line += fileScanner.nextLine() + "\n";
			}
			line = "\n" + line;
			System.out.println(line);
	}
	public String[] purchaseOptions() {
		String[] choices = {"Select Drink", "Cash out bill"
				+ "", "Finish Transaction"};
		return choices;
	}
	public List<String>fileReader() throws FileNotFoundException{
		List<String> result = new ArrayList<String>();
		String line = "";
		File inputFile = new File("menu.txt");
		Scanner fileScanner = new Scanner(inputFile);
		while(fileScanner.hasNextLine()) {
			line += fileScanner.nextLine();
		}
		result = menuReader(line);
		return result;
	}
	public List<String>menuReader(String line){
		List<String>result = new ArrayList<String>();
		String[]splitItems = line.split("\\|");
		for(int i = 0; i<splitItems.length; i++) {
			if(i % 4 == 0) {
				result.add(splitItems[i]);
			}
			if(i % 4 == 3) {
				String name        = splitItems[i-3];
				double price       = Double.parseDouble(splitItems[i-1]);
				String description = splitItems[i-2];
				if(splitItems[i].equals("cocktail")) {
				options.add(new Cocktail(name, description, price));
				}
			}
		}
		return result;	
	}
	public void purchaseItem(String name) {
		String purchaseName = "";
		int count = 0;
		for(Drinks drink: options) {
			if(drink.getName().equals(name) && count == 0 ){
				if((getBalance() >= drink.getPrice()) || (getBalance() <= drink.getPrice())){
				setBalance(getBalance() + drink.getPrice());
				count++;
				drink.setNumberBought();
				purchaseName = drink.getName();
				}
			else {
				System.out.println("Out of stock");
				count++;
				}
			}
		}
		System.out.printf("\nYour current bill is: $%.2f\n",getBalance());	
	}		
	public void finishingTransaction() {
		System.out.println("you are finishing your order\n");
		
		double holder = getBalance();
		double changeReturned = getBalance();
		int tenDollar  = 10;
		int fiveDollar = 5;
		int oneDollar  = 1;
		double quarter = .25;
		double dime    = .10;
		double nickel  = .05;
		double penny   = .01;
		int [] amountOfChange = {0, 0, 0, 0, 0, 0, 0};
		
		while(changeReturned <= -10) {
			changeReturned += tenDollar;
			amountOfChange[0]++;
		}
		while(changeReturned <= -5) {
			changeReturned += fiveDollar;
			amountOfChange[1]++;
		}
		while(changeReturned <= -1) {
			changeReturned += oneDollar;
			amountOfChange[2]++;
		}
		while(changeReturned <= -.25) {
			changeReturned += quarter;
			amountOfChange[3]++;
		}
		while(changeReturned <= -.10) {
			changeReturned += dime;
			amountOfChange[4]++;
		}
		while(changeReturned <= -.05) {
			changeReturned += nickel;
			amountOfChange[5]++;
		}
		while(changeReturned <= -.009) {
			changeReturned += penny;
			amountOfChange[6]++;
		}
		
		holder = getBalance() * -1;
		System.out.printf("Your total change is: $%.2f",holder);
		System.out.println("\n");
		setBalance(0);
		
		System.out.println("Tens:     " + amountOfChange[0]);
		System.out.println("Fives:    " + amountOfChange[1]);
		System.out.println("Ones:     " + amountOfChange[2]);
		System.out.println("quarters: " + amountOfChange[3]);
		System.out.println("dimes:    " + amountOfChange[4]);
		System.out.println("nickels:  " + amountOfChange[5]);
		System.out.println("pennies:  " + amountOfChange[6]);
		}
	
public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		Bar myBar = new Bar(menu);
		myBar.run();
	}
}
