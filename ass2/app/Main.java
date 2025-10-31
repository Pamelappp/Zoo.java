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

    // The zoo interface menu
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

    // The method to view all the animals in the zoo.
    private static void viewAnimals() {
        Zoo.displayAllAnimals();
    }

    // The method to view all the keepers in the zoo.
    private static void viewKeepers() {
        Zoo.displayAllKeepers();
    }

    // The method of add animal to zoo.
    private static void addAnimal() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter animal ID: ");
        String animalID = sc.nextLine().trim();

        //If this animal is existing in the zoo, close the method.
        if (Zoo.findAnimals(animalID)) {
            System.out.println("This animal already exists!");
            return;
        }

        System.out.print("Enter animal name: ");
        String animalName = sc.nextLine();

        double weightKg;
        // Using the do-while loop to make sure the animal's weight is valid.
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
        // Using the do-while loop to make sure the requiredMealsPerDay is valid.
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

        boolean speciesChoose = true; // The field to control the loop.
        boolean animalAdded = false; // The field to record if the animal successes be added.

        //Using while loop to make sure user choose the right loop
        while (speciesChoose) {
            System.out.print("Enter animal species (1. Elephant 2.Lion 3.Penguin): ");
            String speciesNumber = sc.nextLine();

            try {
                // Using switch to create the right animal species.
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
        // if animal be added successful, pop up a message.
        if (animalAdded) {
            System.out.println("Animal added successfully!");
        }
    }

    // Remove animal from the zoo.
    private static void removeAnimal(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter animal ID: ");
        //Zoo zoo = new Zoo();
        String animalID = sc.nextLine().trim();

        // If the zoo does have this animal, show the wrong message.
        if (!Zoo.findAnimals(animalID)) {
            System.out.println("This animal does not exist!");
            return;
        }

        //Show the message to tell user if the action successes.
        if(Zoo.removeAnimal(animalID)){
            System.out.println("Animal removed!");
        }
        else{
            System.out.println("Removing animal failed!");
        }
    }

    // Add the keeper to the zoo.
    private static void addKeeper() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter keeper ID: ");
        String keeperID = sc.nextLine().trim();


        // If this keeper is in the zoo, show the message and return.
        if (Zoo.findKeepers(keeperID)) {
            System.out.println("This keeper exists!");
            return;
        }

        System.out.print("Enter keeper name: ");
        String keeperName = sc.nextLine();
        if(Zoo.addKeeper(keeperID, keeperName)){
            System.out.println("Keeper added successfully!");
        }
    }

    //Removing the specific keeper from the zoo.
    private static void removeKeeper() {
        //Zoo zoo = new Zoo();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter keeper ID: ");
        String keeperID = sc.nextLine().trim();

        // If zoo does not have this keeper, show the message and return.
        if (!Zoo.findKeepers(keeperID)) {
            System.out.println("This keeper does not exist!");
            return;
        }

        // Trying to remove keeper.
        if (Zoo.removeKeeper(keeperID)) {
            System.out.println("Keeper removed!");
        }else{
            System.out.println("Removing keeper failed!");
        }

    }

    // Assigning the specific keeper to the specific animal, user will need to enter the keeperID and animalID.
    private static void assignKeeperToAnimal() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter animal ID: ");
        String animalID = sc.nextLine().trim();
        //Make sure the animal is correct in the zoo.
        if (!Zoo.findAnimals(animalID)) {
            System.out.println("This animal does not exist!");
            return;
        }
        System.out.print("Enter keeper ID: ");
        String keeperID = sc.nextLine().trim();

        //Make sure the keeper is correct and in the zoo.
        if (!Zoo.findKeepers(keeperID)) {
            System.out.println("This keeper does not exist!");
            return;
        }
        try {
            // Using the method from Zoo class to complete action.
            if(Zoo.assignKeeperToAnimal(animalID, keeperID)){
                System.out.println("Animal assigned successfully!");
            }else{
                System.out.println("Animal assignment failed!");
            }
        } catch (ExpertiseMismatchException e) {
            System.out.println(e.getMessage());
        }

    }

    // The method to feed specific animal with two feed ways.
    private static void feedAnimal(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter animal ID: ");
        String animalID = sc.nextLine().trim();

        // Make sure this animal is in the zoo.
        if (Zoo.findAnimals(animalID)) {
            System.out.println("Enter the feed portion (1.Default 2.Custom): ");
            String choice = sc.nextLine().trim();
            // If the user choose using default portion to feed, using the feedAnimal(default) method to feed it.
            if (choice.equals("1")) {
                try {
                    Zoo.feedAnimal(animalID);
                }  catch (OverfeedException e) {
                    System.out.println(e.getMessage());
                }
            // If the user choose using custom amount to feed, using the feedAnimal(custom) method to feed it.
            }else if (choice.equals("2")) {
                double feedPortion = 0;
                do {
                    try{
                        // User enter the feed amount.
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

    // Assign expertise to the specific keeper.
    private static void assignExpertise(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter keeper ID: ");
        String keeperID = sc.nextLine().trim();

        // Make sure the keeper is in the zoo.
        if (Zoo.findKeepers(keeperID)) {
            System.out.print("Enter the expertise: ");
            String expertise = sc.nextLine().trim();
            Zoo.assignExpertiseToKeeper(keeperID, expertise.toLowerCase());
            return;
            }
        System.out.println("This keeper does not exist!");
    }

    // Show the summary of zoo.
    private static void zooSummary() {
        Zoo.summary();
    }


}