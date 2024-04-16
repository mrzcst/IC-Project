/****************************\
|  +                     +   |
|                            |
|     OLD STYLE MENU :)      |
|                            |
|  +                     +   |
\****************************/


import java.util.Scanner;

public class Menu {
	static Scanner in = new Scanner(System.in);
	boolean forceExit;
	
	//Header Text
	private void headerText() {
		System.out.println("I-------------------------------------I");
		System.out.println("|                                     |");
		System.out.println("|     Informatica e Computazione      |");
		System.out.println("|            Java Project             |");
		System.out.println("|                                     |");
		System.out.println("I-------------------------------------I");
	}
	
	//Menu Text (for all exercises to be solved + extra)
	private void menuText() {
		System.out.println("I-------------------------------------I");
		System.out.println("|        [1] Show All Cars [1]        |");
		System.out.println("|-------------------------------------|");
		System.out.println("|       [2] Insert New Car [2]        |");
		System.out.println("|-------------------------------------|");
		System.out.println("|          [3] Delete Car [3]         |");
		System.out.println("|-------------------------------------|");
		System.out.println("|         [4] Car Details [4]         |");
		System.out.println("|-------------------------------------|");
		System.out.println("|         [5] Show Cars(A) [5]        |");
		System.out.println("|-------------------------------------|");
		System.out.println("|         [6] Show Cars(B) [6]        |");
		System.out.println("|-------------------------------------|");
		System.out.println("|       [7] Show All Models [7]       |");
		System.out.println("|-------------------------------------|");
		System.out.println("|     [8] Save Models In File [8]     |");
		System.out.println("|-------------------------------------|");
		System.out.println("|          [9] Information [9]        |");
		System.out.println("|-------------------------------------|");
		System.out.println("|              [0] Exit [0]           |");
		System.out.println("I-------------------------------------I");
		System.out.println("\n\n(A) - Last revision = Registration Year = 2021 - 4(2017)");
		System.out.println("(B) - Last revision = 2021 - 2(2019)\n");
	}
	
	//Informations (Prof, Stud, Mat)
	private void Credits() {
		System.out.println("I-------------------------------------I");
		System.out.println("|             INFORMATIONS            |");
		System.out.println("|-------------------------------------|");
		System.out.println("|                                     |");
		System.out.println("|-------------------------------------|");
		System.out.println("|    Professor:  Marco Maratea        |");
		System.out.println("|-------------------------------------|");
		System.out.println("|    Student:    Morozan Constantin   |");
		System.out.println("|-------------------------------------|");
		System.out.println("|    Matricola:  S4859208             |");
		System.out.println("I-------------------------------------I");
	}
	
	//Line
	private void Line() {
		System.out.println("I-------------------------------------I");
	}
	
	//Emoji :)
	private void Emoji() {
		System.out.println("_________________________________________________");
		System.out.println("_____________¶¶¶¶¶¶___________¶¶¶¶¶¶_____________");
		System.out.println("___________¶¶______¶________¶¶______¶¶___________");
		System.out.println("__________¶_____¶___¶¶_____¶¶____¶____¶__________");
		System.out.println("_________¶¶__¶¶¶¶¶¶__¶_____¶__¶¶¶¶¶¶__¶¶_________");
		System.out.println("_________¶¶_¶¶¶¶¶¶¶¶_¶_____¶_¶¶¶¶¶¶¶¶_¶¶_________");
		System.out.println("__________¶_¶¶¶¶¶¶¶¶_¶_____¶__¶¶¶¶¶¶¶_¶__________");
		System.out.println("___________¶_¶¶¶¶¶¶¶¶_______¶_¶¶¶¶¶¶¶¶___________");
		System.out.println("______________¶¶¶¶______________¶¶¶______________");
		System.out.println("__¶¶_________________________________________¶¶__");
		System.out.println("_¶¶¶_________________________________________¶¶¶_");
		System.out.println("____¶¶_____________________________________¶¶____");
		System.out.println("______¶¶¶______________________________¶¶¶_______");
		System.out.println("__________¶¶¶¶¶__________________¶¶¶¶¶¶__________");
		System.out.println("________________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶_________________");
		System.out.println("_________________________________________________");
	}
	
	//Choose a instruction
	private int getOption() {
		int choice = -1;
		String str;
		do{
			System.out.print("\nEnter option number:");
			str = in.nextLine();	
			if(str.length() == 1 && Character.isDigit(str.charAt(0))) {
				choice = Integer.parseInt(str);
			}else System.out.print("Invalid number. Retry...!");
		}
		while(choice < 0 || choice > 9);
		return choice;
	}
	
	//Solve the instruction using GestioneAutomezzi
	private void doTheInstruction(int choice) {
		GestioneAutomezzi parco = new GestioneAutomezzi();
		
		switch(choice) {
		case 0: {
            System.out.println("Thank you for checking my project!");
            Emoji();
            in.close();
            System.exit(0);
            break;
		}
		case 1: {
			parco.showCars();
			Line();
			break;
		}
		case 2: {
			System.out.println("\n\nPlease complete the following line: ");
			System.out.println("\n[!]Registration Year and Last Revision should be done this year[!]\n");
			if(parco.insertNewCar(parco.getInfo())) System.out.println("Car Successfully Added");
			else System.out.println("Error! Registration year and latest revision doesn't match with 2021 (>_<)");
			Line();
			break;
		}
		case 3: {
			System.out.print("\n\nPlease insert car's plate to delete it: ");
			String plate = in.nextLine();
			if(parco.deleteCar(plate)) System.out.println("Car Successfully Deleted");
			else System.out.println("There is no car with ["+ plate +"] car plate!");
			Line();
			break;
		}
		case 4: {
			System.out.print("\n\nIntroduce a car's plate to get details about it: ");
			String targa = in.nextLine();
			parco.showCarInfo(targa);
			Line();
			break;
		}
		case 5: {
			System.out.println("\n\nLoading cars with registration and last revision in 2017...");
			parco.showCarsImmAndRev2017();
			Line();
			break;
		}
		case 6: {
			System.out.println("\n\nLoading cars with last revision in 2019...");
			parco.showCarsRev2019();
			Line();
			break;
		}
		case 7: {
			parco.showAllCarModels();
			Line();
			break;
		}
		case 8: {
			System.out.println("\n\nWriting models in file...");
			if(parco.putAllCarModelsInFile()) System.out.println("File was successfully wrote!");
			Line();
			break;
		}
		case 9: {
			Credits();
			break;
		}

		default:
            System.out.println("Unknown error has occured (>_<)");
		}

	}
	
	//Start the menu(Till [0]) + Force Stop
    public void startTheMenu() {
        GestioneAutomezzi parco = new GestioneAutomezzi();
        if(parco.fileInsertCar("input.txt")) {
            headerText();
            menuText();
        	System.out.println("[!]Input file reading was successfully! Data stored[!]");
        } else System.exit(0);

        while (!forceExit) {
            int choice = getOption();
            doTheInstruction(choice);
        }
    }
}
