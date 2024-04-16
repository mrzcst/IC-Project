
public class Automezzo {
	
	//VARS: 
	private String marca;
	private String modello;
	private String targa;
	private int anno_imm;
	private int anno_rev;
	
	//METHODS: 
	
	//Default Constructor
	public Automezzo() {
		
	}
	
	//Constructor	
	public Automezzo(String marca, String modello, String targa, Integer anno_imm, Integer anno_rev) {
		this.marca = marca;
		this.modello = modello;
		this.targa = targa;
		this.anno_imm = anno_imm;
		this.anno_rev = anno_rev;
	}
	
	//Constructor for file reading
	public Automezzo(String line, int rnum) {
		String[] carInfo = line.split(", ");
		if (carInfo.length == 5) {
			
			//error checking
			checkBrandFormat(carInfo[0], rnum);
			checkModelFormat(carInfo[1], rnum);
			if(!checkCarPlateFormat(carInfo[2].toUpperCase())) {
				System.out.println("FATAL ERROR at line " + rnum +" in input file!\nWrong format for CarPlate = '"+ carInfo[2] +"' in file!");
				System.out.println("\nAccepted car plate expression: AA000AA");
				System.out.println("[A] = any letter of Latin Alphabet (except 'I', 'O', 'U', 'Q').");
				System.out.println("[0] = any number from 0 to 9.\n");
				System.exit(0);
			}
			checkYearsFormat(carInfo[3], rnum);
			checkYearsFormat(carInfo[4], rnum);
			
			//assigning values
			this.marca = carInfo[0].toUpperCase();
			this.modello = carInfo[1];
			this.targa = carInfo[2].toUpperCase();
			this.anno_imm = Integer.parseInt(carInfo[3]);
			this.anno_rev = Integer.parseInt(carInfo[4]);
		}else {
			System.out.println("There was an error reading the input file!\nBe sure that file contains all necessary information \nand between values there is a ', ' delimitation!");
			System.out.println("\nExample of valid row input: BRAND, MODEL, CarPlate, RegistrationYear, RevisionYear");
			System.exit(0);
		}
	}
	
	//Getters
	public String getMarca() {
		return this.marca;
	}
	public String getModello() {
		return this.modello;
	}
	public String getTarga() {
		return this.targa;
	}
	public int getAnnoImm() {
		return this.anno_imm;
	}
	public int getAnnoRev() {
		return this.anno_rev;
	}
	
	//Setters
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	public void setTarga(String targa) {
		this.targa = targa;
	}
	public void setAnnoImm(int anno_imm) {
		this.anno_imm = anno_imm;
	}
	public void setAnnoRev(int anno_rev) {
		this.anno_imm = anno_rev;
	}
	
	//Car plate check [AA999AA]
	protected static boolean checkCarPlateFormat(String carPlate) {
		//check lenght
		if (carPlate.length() != 7) return false;
		
		//check if it is AA999AA;
		if (Character.isDigit(carPlate.charAt(0)) ||
				Character.isDigit(carPlate.charAt(1)) ||
				!Character.isDigit(carPlate.charAt(2)) ||
				!Character.isDigit(carPlate.charAt(3)) ||
				!Character.isDigit(carPlate.charAt(4)) ||
				Character.isDigit(carPlate.charAt(5)) ||
				Character.isDigit(carPlate.charAt(6))) return false;
		
		//check if there are any O, U, I , Q;
		for (int i = 0; i < carPlate.length(); i++) {
			if (carPlate.charAt(i) == 'O' ||
				carPlate.charAt(i) == 'U' ||
				carPlate.charAt(i) == 'I' ||
				carPlate.charAt(i) == 'Q') return false;
		}
		return true;
	}
	
	//Check year limits [1980 - 2021]
	private static boolean checkYearLimits(int year) {
		if (year > 2021 || year < 1980) return false;
		return true;
	}
	
	//Check year expression format [0000]
	protected static boolean checkYearsFormat(String s1, int rnum) {
		//Check if there are any others chars beside numbers
		for (int i = 0; i < s1.length(); i++) {
			if (!Character.isDigit(s1.toCharArray()[i])) {
				if (rnum == 0) {
					return false;
				}else {
					System.out.println("FATAL ERROR at line " + rnum +" in input file!\nInvalid value '"+ s1 +"' in file!");
					System.exit(0);
				}
			}
		}
		
		//Checking if year respects the limits
		if(s1.length() != 4 || !checkYearLimits(Integer.parseInt(s1))) {
			if (rnum == 0) {
				return false;
			}else {
				System.out.println("FATAL ERROR at line " + rnum +" in input file!\nInvalid value '"+ s1 +"' in file!");
				System.exit(0);
			}
		}
		return true;
	}
	
	//Check brand expression format [letters only, <20 chars]
	protected static boolean checkBrandFormat(String brand, int rnum) {
		//Checking lenght, there is no car brand < 3 letters
		if (brand.length() < 3) {
			if (rnum == 0) return false;
			else {
				System.out.println("FATAL ERROR at line " + rnum +" in input file!\nInvalid value for BRAND = '"+ brand +"' in file!");
				System.exit(0);
			}
		}
		//Checking if there are any numbers, there is no car brand that has numbers in name
		for(int i = 0; i < brand.length(); i++) {
			if( Character.isDigit(brand.charAt(i)) || brand.length() > 19 ) {
				if (rnum == 0) return false;
				else {
					System.out.println("FATAL ERROR at line " + rnum +" in input file!\nInvalid value for BRAND = '"+ brand +"' in file!");
					System.exit(0);
				}
			}
		}
		return true;
	}
	
	//Check model expression format[ <30 chars]
	protected static boolean checkModelFormat(String model, int rnum) {
		//Checking min and max lenght
		if (model.length() < 1 || model.length() > 29) {
			if (rnum == 0) return false;
			else {
				System.out.println("FATAL ERROR at line " + rnum +" in input file!\nInvalid value for MODEL = '"+ model +"' in file!");
				System.exit(0);
			}
		
		}
		return true;
	}
}
