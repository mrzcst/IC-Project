import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GestioneAutomezzi extends Automezzo{
	static Scanner in = new Scanner(System.in);
	
	private static ArrayList<Automezzo> ParcoAuto = new ArrayList<Automezzo>(); 
	
	//Inserting Automezzi from file("input.txt"); [Good]
	public boolean fileInsertCar(String fileName) {
		try {
			FileReader input = new FileReader(fileName);
			Scanner in = new Scanner(input);
			String line;
			Automezzo readAuto;
			int row = 1;
			
			while(in.hasNext()) {
				 line = in.nextLine();
				 readAuto = new Automezzo(line, row);
				 ParcoAuto.add(readAuto);
				 row++;
			}
			in.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("There is no file with "+ fileName +" name!");
			return false;
		}
	}
	
	//Inserting new Automezzi ([anno_imm] = [anno_rev] = 2021); [Good]
	public boolean insertNewCar(Automezzo auto) {
		int currentYear = 2021;
		if (auto.getAnnoImm() == auto.getAnnoRev() && auto.getAnnoImm() == currentYear){
			ParcoAuto.add(auto);
			return true;
		}
		return false;
	}
	
	//Delete Automezzo using his [targa]; [Good]
	public boolean deleteCar(String carPlate) {
		for(int i = 0; i < ParcoAuto.size(); i++) {
			if (ParcoAuto.get(i).getTarga().equals(carPlate.toUpperCase())) {
				ParcoAuto.remove(i);
				return true;
			}
		}
		return false;
	}	
	
	//Output Automezzo details using his [targa]; [Good]
	public void showCarInfo(String carPlate) {
		Automezzo infoAuto = null;
		for(int i = 0; i < ParcoAuto.size(); i++) {
			if(ParcoAuto.get(i).getTarga().equals(carPlate.toUpperCase())) {
				infoAuto = ParcoAuto.get(i);
			}
		}	
		if (infoAuto != null) {
			System.out.print("Car Brand: "+ infoAuto.getMarca() +"\n");
			System.out.print("Car Model: "+ infoAuto.getModello() +"\n");
			System.out.print("Car Plate: "+ infoAuto.getTarga() +"\n");
			System.out.print("Registration Year: "+ infoAuto.getAnnoImm() +"\n");
			System.out.println("Latest revision: "+ infoAuto.getAnnoRev() +"\n");
		}else System.out.println("There is no car with ["+ carPlate +"] car plate!");
	}
	
	//Output all Automezzo with [anno_rev] = [anno_imm] and its 4 years earlier than 2021 (2017); [Good]
	public void showCarsImmAndRev2017() {
		int requiredYear = 2017;
		boolean carFound = false;
		for (int i = 0; i < ParcoAuto.size(); i++) {
			if (ParcoAuto.get(i).getAnnoImm() == ParcoAuto.get(i).getAnnoRev() && ParcoAuto.get(i).getAnnoImm() == requiredYear) {
				showCarInfo(ParcoAuto.get(i).getTarga());
				carFound = true;
			}
		}
		if (!carFound) {
			System.out.println("No cars found!");
		}
	}
	
	//Output all Automezzo whit [anno_rev] 2 years earlier than 2021 (2019); [Good]
	public void showCarsRev2019() {
		int requiredYear = 2019;
		boolean carFound = false;
		for (int i = 0; i < ParcoAuto.size(); i++) {
			if (ParcoAuto.get(i).getAnnoRev() == requiredYear) {
				showCarInfo(ParcoAuto.get(i).getTarga());
				carFound = true;
			}
		}
		if (!carFound) {
			System.out.println("No cars found!");
		}
	}
	
	//Output all Automezzo.modello that are in the ArrayList (without repeating values); [Good]
	public void showAllCarModels() {
		HashSet<String> models = new HashSet<String>();
		int j = 1;
		for(int i = 0; i < ParcoAuto.size(); i++) {
			models.add(ParcoAuto.get(i).getModello());
		}
		for (String i : models) {
			System.out.println("("+j+")"+ i);
			j++;
		}
	}
	
	//Output all Automezzo.modello in a file ("Modelli.txt"); [Good]
	public boolean putAllCarModelsInFile() {
		FileWriter output;
		try {
			output = new FileWriter("modelli.txt");
			PrintWriter pw = new PrintWriter(output);
			HashSet<String> models = new HashSet<String>();
			int j = 1;
			
			for(int i = 0; i < ParcoAuto.size(); i++) {
				models.add(ParcoAuto.get(i).getModello());
			}
			for (String i : models) {
				pw.println("("+j+")"+ i);
				j++;
			}
			pw.close();
			output.close();
		} catch (IOException e) {
			System.out.println("Unexpected Error! Please retry (>_<)");
			return false;
		}
		return true;
	}
	
	//Getting info for a new car;
	public Automezzo getInfo() {
		//Car brand + exceptions
		System.out.print("Car Brand: ");
		String brand= in.nextLine().toUpperCase();	
		while(!checkBrandFormat(brand, 0)) {
			System.out.print("\nInvalid Brand name! [Letters Only; Lenght < 20]\n\nIntroduce a valid name:");
			brand = in.nextLine().toUpperCase();
		}
		
		//Car Model + exceptions
		System.out.print("Car Model: ");
		String model = in.nextLine();
		while(!checkModelFormat(model, 0)) {
			System.out.print("\nInvalid Model name! [Lenght < 30]\n\nIntroduce a valid name:");
			model = in.nextLine();
		}
		
		//Car Plate + exceptions
		System.out.print("Car Plate: ");
		String plate = in.nextLine().toUpperCase();
		while(!checkCarPlateFormat(plate)) {
			System.out.println("\nError! Wrong car plate format!");
			System.out.println("Accepted car plate expression: AA000AA");
			System.out.println("[A] = any letter of Latin Alphabet (except 'I', 'O', 'U', 'Q').");
			System.out.println("[0] = any number from 0 to 9.\n");
			System.out.print("Introduce a correct car plate: ");
		    plate = in.nextLine().toUpperCase();
		}
		
		//Register Year + exceptions
		System.out.print("Year of registration: ");	
		String reg = in.nextLine();
		while(!checkYearsFormat(reg, 0)) {
			System.out.println("\nInvalid registration year!");
			System.out.println("Introduce a valid year: ");
			reg = in.nextLine();
		}
		
		//Last Revision Year + exceptions
		System.out.print("Last revision: ");
		String rev = in.nextLine();
		while(!checkYearsFormat(rev, 0)) {
			System.out.println("\nInvalid revision year!");
			System.out.println("Introduce a valid year: ");
			rev = in.nextLine();
		}
		
		//Creating new object
		Automezzo newCar = new Automezzo(brand, model, plate, Integer.parseInt(reg), Integer.parseInt(rev));
		return newCar;
	}
	
	//Output all Automezzo that are in list; 
	public void showCars() {
		if(ParcoAuto.size() != 0) {
			for(int i = 0; i < ParcoAuto.size(); i++) {
				showCarInfo(ParcoAuto.get(i).getTarga());
			}
		}else System.out.println("\nThere are no cars in list right now, please insert a new car!");
	}
}	
