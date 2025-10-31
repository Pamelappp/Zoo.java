package app;

import animal.*;
import core.Zoo;
import exceptions.ExpertiseMismatchException;
import exceptions.InvalidInputException;
import exceptions.InvalidPortionException;
import exceptions.OverfeedException;
import people.Keeper;
import java.util.*;


public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            String choice = sc.nextLine();
            switch (choice) {
                case "1": viewAnimals(); break;
                case "2": viewKeepers(); break;
                case "3": addAnimal(); break;
                case "4": removeAnimal(); break;
                case "5": addKeeper(); break;
                case "6": removeKeeper(); break;
                case "7": assignKeeperToAnimal(); break;
                case "8": feedAnimal(); break;
                case "9": assignExpertise(); break;
                case "10": zooSummary(); break;
                case "0":
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
        Zoo.displayAllAnimals();
    }

    private static void viewKeepers() {
        Zoo.displayAllKeepers();
    }

    private static void addAnimal() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter animal ID: ");
        String animalID = sc.nextLine().trim();

        if (Zoo.findAnimals(animalID)) {
            System.out.println("This animal already exists!");
            return;
        }

        System.out.print("Enter animal name: ");
        String animalName = sc.nextLine();

        double weightKg;
        do {
            try {
                System.out.print("Enter animal weight(kg): ");
                weightKg = sc.nextDouble();
                sc.nextLine(); // discard the \n
                if (weightKg <= 0) {
                    throw new InvalidInputException("Please input a positive value for weight!");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, Please enter a numeric value for weight!");
                sc.nextLine();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        int requiredMealsPerDay;
        do {
            try {
                System.out.print("Enter animal requirement meal per day: ");
                requiredMealsPerDay = sc.nextInt();
                sc.nextLine(); // discard the \n
                if (requiredMealsPerDay < 1) {
                    throw new InvalidInputException("Please input a positive integer for required meals per day!");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, Please enter a numeric integer for required meals!");
                sc.nextLine();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        boolean speciesChoose = true;
        boolean animalAdded = false;
        while (speciesChoose) {
            System.out.print("Enter animal species (1. Elephant 2.Lion 3.Penguin): ");
            String speciesNumber = sc.nextLine();

            try {
                switch (speciesNumber) {
                    case "1":
                        animalAdded=Zoo.addAnimal(animalID, animalName, weightKg, requiredMealsPerDay, "Elephant");
                        speciesChoose = false;
                        break;
                    case "2":
                        animalAdded=Zoo.addAnimal(animalID, animalName, weightKg, requiredMealsPerDay, "Lion");
                        speciesChoose = false;
                        break;
                    case "3":
                        animalAdded=Zoo.addAnimal(animalID, animalName, weightKg, requiredMealsPerDay, "Penguin");
                        speciesChoose = false;
                        break;
                    default:
                        System.out.println("Invalid species!");

                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        if (animalAdded) {
            System.out.println("Animal added successfully!");
        }
    }

    private static void removeAnimal(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter animal ID: ");
        //Zoo zoo = new Zoo();
        String animalID = sc.nextLine().trim();

        if (!Zoo.findAnimals(animalID)) {
            System.out.println("This animal does not exist!");
            return;
        }

        if(Zoo.removeAnimal(animalID)){
            System.out.println("Animal removed!");
        }
        else{
            System.out.println("Removing animal failed!");
        }
    }

    private static void addKeeper() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter keeper ID: ");
        String keeperID = sc.nextLine().trim();

        if (Zoo.findKeepers(keeperID)) {
            System.out.println("This keeper exists!");
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
        System.out.print("Enter keeper ID: ");
        String keeperID = sc.nextLine().trim();

        if (!Zoo.findKeepers(keeperID)) {
            System.out.println("This keeper does not exist!");
            return;
        }

        if (Zoo.removeKeeper(keeperID)) {
            System.out.println("Keeper removed!");
        }else{
            System.out.println("Removing keeper failed!");
        }

    }

    private static void assignKeeperToAnimal() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter animal ID: ");
        String animalID = sc.nextLine().trim();
        if (!Zoo.findAnimals(animalID)) {
            System.out.println("This animal does not exist!");
            return;
        }
        System.out.print("Enter keeper ID: ");
        String keeperID = sc.nextLine().trim();
        if (!Zoo.findKeepers(keeperID)) {
            System.out.println("This keeper does not exist!");
            return;
        }
        try {
            if(Zoo.assignKeeperToAnimal(animalID, keeperID)){
                System.out.println("Animal assigned successfully!");
            }else{
                System.out.println("Animal assignment failed!");
            }
        } catch (ExpertiseMismatchException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void feedAnimal(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter animal ID: ");
        String animalID = sc.nextLine().trim();

        if (Zoo.findAnimals(animalID)) {
            System.out.println("Enter the feed portion (1.Default 2.Custom): ");
            String choice = sc.nextLine().trim();
            if (choice.equals("1")) {
                try {
                    Zoo.feedAnimal(animalID);
                }  catch (OverfeedException e) {
                    System.out.println(e.getMessage());
                }
            }else if (choice.equals("2")) {
                double feedPortion = 0;
                do {
                    try{
                        System.out.println("The amount of feed:");
                        feedPortion = sc.nextDouble();
                        sc.nextLine(); // discard the \n
                        Zoo.feedAnimal(animalID,feedPortion);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a numeric value for portion!");
                        sc.nextLine();
                    } catch (InvalidPortionException e) {
                        System.out.println(e.getMessage());
                    } catch (OverfeedException e) {
                        System.out.println(e.getMessage());
                    }
                } while (true);
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
            return;
            }
        System.out.println("This keeper does not exist!");
    }

    private static void zooSummary() {
        Zoo.summary();
    }


}