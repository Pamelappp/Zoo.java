package app;

import animal.*;
import core.Zoo;
import exceptions.ExpertiseMismatchException;
import people.Keeper;
import java.util.*;


public class Main{
    public static void main(String[] args) throws ExpertiseMismatchException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1: viewAnimals(); break;
                case 2: viewKeepers(); break;
                case 3: addAnimal(); break;
                case 4: removeAnimal(); break;
                case 5: addKeeper(); break;
                case 6: removeKeeper(); break;
                case 7: assignKeeperToAnimal(); break;
                case 10: zooSummary(); break;


            }
        }

    }



    private static void printMenu() {
        System.out.println("\n=== Zoo Menu ===");
        System.out.println("1: View animals");
        System.out.println("2: View keepers");
        System.out.println("3: Add animal");
        System.out.println("4: Remove animal");
        System.out.println("5: Add keeper");
        System.out.println("6: Remove keeper");
        System.out.println("7: Assign keeper to animal");
        System.out.println("8: Feed animal (default)");
        System.out.println("9: Feed animal (custom portion)");
        System.out.println("10: Summary");
        System.out.println("0: Exit");
    }


    private static void viewAnimals() {
        Zoo zoo = new Zoo();
        zoo.displayAllAnimals();
    }

    private static void viewKeepers() {
        Zoo zoo = new Zoo();
        zoo.displayAllKeepers();
    }

    private static void addAnimal() {
        Zoo zoo = new Zoo();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter animal ID: ");
        String animalID = sc.nextLine().trim();

        if (zoo.findAnimals(animalID)) {
            System.out.println("This animal already exists!");
            return;
        }

        System.out.print("Enter animal name: ");
        String animalName = sc.nextLine();
        System.out.print("Enter animal weight: ");
        double weightKg = sc.nextDouble();
        System.out.print("Enter animal requirement meal per day: ");
        int requiredMealsPerDay = sc.nextInt();

        boolean speciesChoose = true;
        boolean animalAdded = false;
        while (speciesChoose) {
            System.out.print("Enter animal species (1. Elephant 2.Lion 3.Penguin): ");
            String speciesNumber = sc.nextLine();

            switch (speciesNumber) {
                case "1":
                    Elephant elephant = new Elephant(animalID, animalName, weightKg, requiredMealsPerDay);
                    animalAdded=zoo.addAnimal(elephant);
                    speciesChoose = false;
                    break;
                case "2":
                    Lion lion = new Lion(animalID, animalName, weightKg, requiredMealsPerDay);
                    animalAdded=zoo.addAnimal(lion);
                    speciesChoose = false;
                    break;
                case "3":
                    Penguin penguin = new Penguin(animalID, animalName, weightKg, requiredMealsPerDay);
                    animalAdded=zoo.addAnimal(penguin);
                    speciesChoose = false;
                    break;
                default:
                    System.out.println("Invalid species!");

            }

        }
        if (animalAdded) {
            System.out.println("Animal added successfully!");
        }
        sc.close();
    }

    private static void removeAnimal(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter animal ID: ");
        Zoo zoo = new Zoo();
        String animalID = sc.nextLine().trim();

        if (!zoo.findAnimals(animalID)) {
            System.out.println("This animal does not exist!");
        }

        if(zoo.removeAnimal(animalID)){
            System.out.println("Animal removed!");
        }
        else{
            System.out.println("Removing animal failed!");
        }
        sc.close();
    }

    private static void addKeeper() {
        Zoo zoo = new Zoo();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter keeper ID: ");
        String keeperID = sc.nextLine().trim();

        if (zoo.findKeepers(keeperID)) {
            System.out.println("This keeper does exist!");
            return;
        }

        System.out.print("Enter keeper name: ");
        String keeperName = sc.nextLine();
        Keeper keeper = new Keeper(keeperID, keeperName);
        if(zoo.addKeeper(keeper)){
            System.out.println("Keeper added successfully!");
        }
        sc.close();
    }

    private static void removeKeeper() {
        Zoo zoo = new Zoo();
        Scanner sc = new Scanner(System.in);
        String keeperID = sc.nextLine().trim();
        if (zoo.removeKeeper(keeperID)) {
            System.out.println("Keeper removed!");
        }else{
            System.out.println("Removing keeper failed!");
        }
        sc.close();
    }

    private static void assignKeeperToAnimal() throws ExpertiseMismatchException {
        Zoo zoo = new Zoo();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter animal ID: ");
        String animalID = sc.nextLine().trim();
        System.out.print("Enter keeper ID: ");
        String keeperID = sc.nextLine().trim();

        if (zoo.findAnimals(animalID)|| zoo.findKeepers(keeperID)) {
            if(zoo.assignKeeperToAnimal(animalID, keeperID)){
                System.out.println("Animal assigned successfully!");
            }else{
                System.out.println("Animal assignment failed!");
            }
        }
        sc.close();
    }

    private static void zooSummary() {
        Zoo zoo = new Zoo();
        zoo.summary();
    }
}