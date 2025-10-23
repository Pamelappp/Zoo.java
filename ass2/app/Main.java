package app;

import animal.*;
import core.Zoo;
import people.Keeper;
import java.util.*;


public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1: viewAnimals(); break;
                case 2: viewKeepers(); break;

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

        for (Animal a : zoo.getAnimals()) {
            System.out.println("ID: "+a.getAnimalID());
            System.out.println("Name: "+a.getName());
            System.out.println("Weight: "+a.getWeightKg());
            System.out.println("Species: "+a.getSpecies());
            System.out.println("Diet Profile"+a.getDietProfile());
            System.out.println("Requirement meal per day"+a.getRequiredMealsPerDay());
            System.out.println("ActualFeedCount"+a.getActualFeedCount());
            System.out.println("Total portion per day"+a.getTotalPortionPerDay());
        }
    }

    private static void viewKeepers() {
        Zoo zoo = new Zoo();

        for (Keeper k : zoo.getKeepers()) {
            System.out.println("ID: "+k.getKeeperID());
            System.out.println("Name: "+k.getName());
        }

    }

    private static void addAnimal() {
        Zoo zoo = new Zoo();

    }
}