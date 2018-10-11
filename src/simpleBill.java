import java.util.Scanner;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class simpleBill{
	protected boolean flag;
	protected Double total;
	static HashMap<String,String> allBills;
	private String directory = "/YOUR/FULL/FILE/PATH/HERE"; // use your full file path example: /Users/mac/Desktop/Java_stuff
    private String filename = "bills.txt"; // create a .txt file in the directory path from above
    private Path FilePath = Paths.get(directory, filename);
	private Double income;
	private Double savings;
	static Scanner scan = new Scanner(System.in);
	public simpleBill(){
		this.allBills = new HashMap<>();
	}
	 
	void setBills(){
        try {
            List <String> read = Files.readAllLines(FilePath);
            for(int i = 0; i < read.size() -1; i+=2){

                allBills.put(read.get(i), read.get(i + 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	 Double calculateTotal(){
	 	double billTotal = 0;
		for(String bill : allBills.values()){
			billTotal += Double.parseDouble(bill);
		}
		this.total = billTotal;
		return billTotal;
	}
	Double getTotal(){
		return total;
	}
	void setIncome(double income){
		this.income = income;
	}
	Double getIncome(){
		return income;
	}
	void setSavings(double savings){
		this.savings = savings;
	}
	Double getSavings(){
		return savings;
	}
	void showAllBills(){
		System.out.println("Your Bills are as follows.");
		System.out.print("Bills: ");
		for(int i = 0; i < allBills.size();i++){
			System.out.printf("|%s: $%s|",allBills.keySet().toArray()[i],allBills.values().toArray()[i]);
		}
		System.out.println();
	}
	void saveAmount(){
		System.out.printf("You should save $%.2f every two weeks for the next billing cycle",total/2);
		System.out.println();
	}
	void showAccount(){
		System.out.printf("You have $%.2f in your savings and $%.2f as income for this month%nAccount total: $%.2f%n",savings,income,savings + income);
	}
	void deleteBill(String name){
        if(allBills.containsKey(name)){
            allBills.remove(name);
            System.out.println("Bill removed");
        } else {
            System.out.println("Invalid Bill name");
        }
    }
     void deleteBillSecret(String name){
        if(allBills.containsKey(name)){
            allBills.remove(name);
        } else {
            System.out.println("Invalid Bill name");
        }
    }
     void searchBill(String name){
        if(allBills.containsKey(name)){
            System.out.printf("%s | %s%n",name,allBills.get(name));
        } else{
            System.out.println("Invalid Bill");
        }
    }
	void writeBills(){
        ArrayList<String> names = new ArrayList<>(allBills.keySet());
        ArrayList<String> numbers = new ArrayList<>(allBills.values());
        ArrayList<String> combined = new ArrayList<>();
        for(int i = 0; i < numbers.size(); i++){
            combined.add(names.get(i));
            combined.add(numbers.get(i));
        }
        try {
            Files.write(FilePath,combined);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void addBill(String category, String billTotal){
	 	if(allBills.containsKey(category)){
            System.out.println("Sorry this bill already exists.");
            this.flag = true;
        } else {
            allBills.put(category,billTotal);
        }
		
	}
	void editBill(simpleBill placeholder, String category, String billTotal){
			System.out.println("Would you like to edit this bill? (y/n): ");
            String edit = scan.next();
            if(edit.toLowerCase().startsWith("y")){
            	placeholder.searchBill(category);
            	System.out.println("Do you want to edit category or total? (c/t): ");
            	String option = scan.next();
            	if(option.toLowerCase().startsWith("c")){
            		placeholder.deleteBillSecret(category);
            		System.out.println("Enter a new category:");
            		String neoCategory = scan.next();
            		placeholder.allBills.put(neoCategory,billTotal);
            	} else if (option.toLowerCase().startsWith("t")){
            		placeholder.deleteBillSecret(category);
            		System.out.println("Enter new bill total:");
            		String neoTotal = scan.next();
            		placeholder.allBills.put(category,neoTotal);
            	} else {
            		System.out.println("Invalid Option");
            	}
            }
        }

	public static void main(String[] args){
		boolean cont = true;
		simpleBill theBill = new simpleBill();
		theBill.setBills();
		if(theBill.allBills.size() > 0){
			theBill.showAllBills();
			System.out.printf("Your bill total is %.2f%n",theBill.calculateTotal());
			System.out.println("Do you want to add or edit bills?:");
			String addEdit = scan.next();
			if(addEdit.toLowerCase().startsWith("y")){
				cont = true;
			} else {
				cont = false;
			}
		}
		while(cont){
			System.out.println("Enter a category and bill total");
			System.out.println("Category (Food,Phone etc):");
			String category = scan.next();
			System.out.println("Bill total:");
			String bill = scan.next();
			theBill.addBill(category,bill);
			if(theBill.flag == true){
				theBill.editBill(theBill,category,bill);
			}
			System.out.println("Do you want to add another bill? (y|n):");
			String again = scan.next();
			if(again.toLowerCase().startsWith("n")){
				System.out.printf("Your bill total is %.2f%n",theBill.calculateTotal());
				System.out.println("Do you want to save your bills for future reference?");
				String save = scan.next();
				if(save.toLowerCase().startsWith("y")){
					theBill.writeBills();
				}
				cont = false;
			}
		}
		theBill.showAllBills();
		System.out.println("Enter the total amount in your savings:");
		double savings = scan.nextDouble();
		theBill.setSavings(savings);
		System.out.println("Enter your total income for this month:");
		double income = scan.nextDouble();
		theBill.setIncome(income);
		theBill.showAccount();
		System.out.printf("Your amount left over after bills will be $%.2f%n",theBill.getIncome() - theBill.getTotal()+theBill.getSavings());
		theBill.saveAmount();
	}

}
