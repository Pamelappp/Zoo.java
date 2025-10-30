package app;

import animal.*;
import core.Zoo;
import exceptions.ExpertiseMismatchException;
import exceptions.InvalidPortionException;
import exceptions.OverfeedException;
import people.Keeper;
import java.util.*;


public class Main{
    //static Zoo zoo = new Zoo();
    public static void main(String[] args) throws ExpertiseMismatchException, InvalidPortionException, OverfeedException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine(); // discard the \n
            switch (choice) {
                case 1: viewAnimals(); break;
                case 2: viewKeepers(); break;
                case 3: addAnimal(); break;
                case 4: removeAnimal(); break;
                case 5: addKeeper(); break;
                case 6: removeKeeper(); break;
                case 7: assignKeeperToAnimal(); break;
                case 8: feedAnimal(); break;
                case 9: assignExpertise(); break;
                case 10: zooSummary(); break;
                case 0:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }

    }


    private static void printMenu() {
        System.out.println("\n=========ZOO-MENU=========");
        System.out.println("1: View animals");
        System.out.println("2: View keepers");
        System.out.println("3: Add animal");
        System.out.println("4: Remove animal");
        System.out.println("5: Add keeper");
        System.out.println("6: Remove keeper");
        System.out.println("7: Assign keeper to animal");
        System.out.println("8: Feed animal (default)");
        System.out.println("9: Add expertise to keeper");
        System.out.println("10: Zoo Summary");
        System.out.println("0: Exit");
        System.out.println("==========================");
        System.out.print("Enter your choice: ");
    }

    private static void viewAnimals() {
        //Zoo zoo = new Zoo();
        Zoo.displayAllAnimals();
    }

    private static void viewKeepers() {
        //Zoo zoo = new Zoo();
        Zoo.displayAllKeepers();
    }

    private static void addAnimal() {
        //Zoo zoo = new Zoo();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter animal ID: ");
        String animalID = sc.nextLine().trim();

        if (Zoo.findAnimals(animalID)) {
            System.out.println("This animal already exists!");
            return;
        }

        System.out.print("Enter animal name: ");
        String animalName = sc.nextLine();
        System.out.print("Enter animal weight: ");
        double weightKg = sc.nextDouble();
        sc.nextLine(); // discard the \n
        System.out.print("Enter animal requirement meal per day: ");
        int requiredMealsPerDay = sc.nextInt();
        sc.nextLine(); // discard the \n

        boolean speciesChoose = true;
        boolean animalAdded = false;
        while (speciesChoose) {
            System.out.print("Enter animal species (1. Elephant 2.Lion 3.Penguin): ");
            String speciesNumber = sc.nextLine();

            switch (speciesNumber) {
                case "1":
                    Elephant elephant = new Elephant(animalID, animalName, weightKg, requiredMealsPerDay);
                    animalAdded=Zoo.addAnimal(elephant);
                    speciesChoose = false;
                    break;
                case "2":
                    Lion lion = new Lion(animalID, animalName, weightKg, requiredMealsPerDay);
                    animalAdded=Zoo.addAnimal(lion);
                    speciesChoose = false;
                    break;
                case "3":
                    Penguin penguin = new Penguin(animalID, animalName, weightKg, requiredMealsPerDay);
                    animalAdded=Zoo.addAnimal(penguin);
                    speciesChoose = false;
                    break;
                default:
                    System.out.println("Invalid species!");

            }

        }
        if (animalAdded) {
            System.out.println("Animal added successfully!");
        }
        //sc.close();
    }

    private static void removeAnimal(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter animal ID: ");
        //Zoo zoo = new Zoo();
        String animalID = sc.nextLine().trim();

        if (!Zoo.findAnimals(animalID)) {
            System.out.println("This animal does not exist!");
        }

        if(Zoo.removeAnimal(animalID)){
            System.out.println("Animal removed!");
        }
        else{
            System.out.println("Removing animal failed!");
        }
    }

    private static void addKeeper() {
        //Zoo zoo = new Zoo();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter keeper ID: ");
        String keeperID = sc.nextLine().trim();

        if (Zoo.findKeepers(keeperID)) {
            System.out.println("This keeper does exist!");
            return;
        }

        System.out.print("Enter keeper name: ");
        String keeperName = sc.nextLine();
        Keeper keeper = new Keeper(keeperID, keeperName);
        if(Zoo.addKeeper(keeper)){
            System.out.println("Keeper added successfully!");
        }
    }

    private static void removeKeeper() {
        //Zoo zoo = new Zoo();
        Scanner sc = new Scanner(System.in);
        String keeperID = sc.nextLine().trim();
        if (Zoo.removeKeeper(keeperID)) {
            System.out.println("Keeper removed!");
        }else{
            System.out.println("Removing keeper failed!");
        }

    }

    private static void assignKeeperToAnimal() throws ExpertiseMismatchException {
        //Zoo zoo = new Zoo();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter animal ID: ");
        String animalID = sc.nextLine().trim();
        System.out.print("Enter keeper ID: ");
        String keeperID = sc.nextLine().trim();

        if (Zoo.findAnimals(animalID)|| Zoo.findKeepers(keeperID)) {
            if(Zoo.assignKeeperToAnimal(animalID, keeperID)){
                System.out.println("Animal assigned successfully!");
            }else{
                System.out.println("Animal assignment failed!");
            }
        }

    }

    private static void feedAnimal() throws OverfeedException, InvalidPortionException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter animal ID: ");
        String animalID = sc.nextLine().trim();
        //Zoo zoo = new Zoo();
        if (Zoo.findAnimals(animalID)) {
            System.out.println("Enter the feed portion (1.Default 2.Custom): ");
            String choice = sc.nextLine().trim();
            if (choice.equals("1")) {
                Zoo.feedAnimal(animalID);
            }else if (choice.equals("2")) {
                System.out.println("The amount of feed:");
                double feed = sc.nextDouble();
                Zoo.feedAnimal(animalID,feed);
            }else{
                System.out.println("Invalid choice!");
            }
        }else{
            System.out.println("Animal does not exist!");
        }

    }

    private static void assignExpertise(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter keeper ID: ");
        String keeperID = sc.nextLine().trim();
        if (Zoo.findKeepers(keeperID)) {
            System.out.print("Enter the expertise: ");
            String expertise = sc.nextLine().trim();
            Zoo.assignExpertiseToKeeper(keeperID, expertise.toLowerCase());
            }

    }

    private static void zooSummary() {
        //Zoo zoo = new Zoo();
        Zoo.summary();
    }


}