import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class AppMain {
	
	static Scanner input = new Scanner(System.in);             // Scanner for user input
	static List<Profile> clients = new ArrayList<Profile>();   // List of clients added to the database
	static List<Profile> trainers = new ArrayList<Profile>();  // List of trainers added to the database
	
	public static void main(String[] args){
		Map<Integer, String> specialties = new HashMap<Integer, String>();
		specialties = constructSet(specialties);
		System.out.println("Enter (1) New Profile || (2) Find Trainers || (3) Find Clients || (4) Quit");
		int ans = input.nextInt();
		while(ans != 4) {
			if(ans == 1) {
				Profile p = createProfile(specialties);
				if(p.isTrainer == true) {
					trainers.add(p);
				}
				else if(p.isTrainer == false) {
					clients.add(p);
				}
				else{
					System.out.println("Given profile's isTrainer value invalid! isTrainer = " + p.isTrainer);
				}
				
			}
			else if(ans == 2) {
				System.out.println("Enter (1) Display All || (2) Search By Name || (3) Search By Zip");
				int operation = input.nextInt();
				if(operation == 1) {
					printList(trainers);
				}
				else if(operation == 2) {
					System.out.print("Enter First Name: ");
					String name = input.next() + " ";
					System.out.print("Enter Last Name: ");
					name += input.next();
					System.out.println("\"= - = - = - = - = - = - = - = - = - = - = - = - = - =\"");
					for(Profile p: trainers) {
						if(p.name.equals(name)) {
							System.out.println("Trainer:   " + p.name);
							System.out.println("Specialty: " + specialties.get(p.specialty));
							System.out.println("Zip:       " + p.zip);
							System.out.println("Trainer #: " + trainers.indexOf(p));
							System.out.println("\"= - = - = - = - = - = - = - = - = - = - = - = - = - =\"");
						}
					}
				}
				else if(operation == 3) {
					System.out.println("Enter Zip: ");
					int zip = input.nextInt();
					for(Profile p: clients) {
						if(p.zip == zip) {
							System.out.println("Trainer:   " + p.name);
							System.out.println("Specialty: " + specialties.get(p.specialty));
							System.out.println("Zip Code:  " + p.zip);
							System.out.println("Trainer #: " + trainers.indexOf(p));
							
						}
					}
				}
			}
			else if(ans == 3) {
				System.out.println("Enter (1) Display All || (2) Search By Name || (3) Search By Zip");
				int operation = input.nextInt();
				if(operation == 1) {
					printList(clients);
				}
				else if(operation == 2) {
					System.out.print("Enter First Name: ");
					String name = input.next() + " ";
					System.out.print("Enter Last Name: ");
					name += input.next();
					System.out.println("= - = - = - = - = - = - = - = - = - = - = - = - = - =");
					for(Profile p: clients) {
						if(p.name.equals(name)) {
							System.out.println("Trainer:   " + p.name);
							System.out.println("Goal:      " + specialties.get(p.specialty));
							System.out.println("Zip Code:  " + p.zip);
							System.out.println("Trainer #: " + clients.indexOf(p) + 1);
							System.out.println("--------------------------------------------");
						}
					}
				}
				else if(operation == 3) {
					System.out.println("Enter Zip: ");
					int zip = input.nextInt();
					System.out.println("-----------------------------------------------");
					for(Profile p: clients) {
						if(p.zip == zip) {
							System.out.println("Trainer:   " + p.name);
							System.out.println("Specialty: " + specialties.get(p.specialty));
							System.out.println("Zip Code:  " + p.zip);
							System.out.println("Trainer #: " + clients.indexOf(p) + 1);
							System.out.println("------------------------------------------");
							
						}
					}
				}
			}	
			System.out.println("Enter (1) New Profile || (2) Find Trainers || (3) Find Clients || (4) Quit");
			ans = input.nextInt();
		}
	}
	
	
	public static Profile createProfile(Map<Integer, String> specialties) {
		System.out.println("Trainer or Client?");
		String occupation = input.next();
		boolean isTrainer;
		
		if(occupation.equalsIgnoreCase("Trainer")) {
			isTrainer = true;
			return createTrainer(specialties, isTrainer);
			
		}
		
		else if (occupation.equalsIgnoreCase("Client")){
			isTrainer = false;
			return createClient(specialties, isTrainer);
			
		}
		
		else{
			
			System.out.println("Invalid input. Please enter 'Trainer' or 'Client'.");
			
			createProfile(specialties);
			
		}
		
		return null;
				
	}
	public static Profile createClient(Map<Integer, String> specialties, boolean isTrainer) {
		System.out.println("Client's first name?");
		String clientName = input.next();
		System.out.println("Last name?");
		clientName += " " + input.next();
		
		System.out.println("Client's goal?");
		System.out.println(specialties.toString());
		int clientGoal = input.nextInt();
		
		System.out.println("Client's zip code?");
		int clientZip = input.nextInt();
		
		Profile p = new Profile(isTrainer, clientName, clientGoal, clientZip);
		System.out.println("Added new client " + clientName + " to database.");
		
		return p;
		
	}
	
	
	public static Profile createTrainer(Map<Integer, String> specialties, boolean isTrainer) {
		System.out.println("Trainer's first name?");
		String trainerName = input.next();
		System.out.println("Last name?");
		trainerName += " " + input.next();
		
		System.out.println("Trainer's specialty? (Enter corresponding number) ");
		System.out.println(specialties.toString());
		Integer trainerSpecialty = input.nextInt();
		
		System.out.println("Trainer's zip code?");
		Integer trainerZip = input.nextInt();
		Profile p = new Profile(isTrainer, trainerName, trainerSpecialty, trainerZip);
		
		System.out.println("Added new trainer " + trainerName + " to the database.");
		return p;
		
	}
	
	public static Map<Integer, String> constructSet(Map<Integer, String> specialties) {
		
		specialties.put(1, "Weight Loss");
		specialties.put(2, "Muscle Gain");
		specialties.put(3, "Aerobics");
		specialties.put(4, "Sports Training (Basketball)");
		specialties.put(5, "Sports Training (Volleyball)");
		
		return specialties;
		
	}
	
	public static void printList(List<Profile> list) {
		System.out.print("[");
		for(int i = 0; i < list.size(); i++) {
			
			System.out.print(list.get(i).name + ", ");
			
		}
		System.out.println("]");
	}
	
}
