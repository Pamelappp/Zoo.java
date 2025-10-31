package core;

import animal.Animal;
import animal.Elephant;
import animal.Lion;
import animal.Penguin;
import exceptions.ExpertiseMismatchException;
import exceptions.InvalidInputException;
import exceptions.InvalidPortionException;
import exceptions.OverfeedException;
import people.Keeper;
import java.util.ArrayList;


public class Zoo {
    private static ArrayList<Animal> animals = new ArrayList<>();
    private static ArrayList<Keeper> keepers = new ArrayList<>();

    // Assign the specified animal to the specified keeper
    public static boolean assignKeeperToAnimal(String keeperID, String animalID) throws ExpertiseMismatchException {
        // If
        if (keeperID == null || animalID == null){
            return false;
        }
        for (Keeper k : keepers) {
            if (k.getKeeperID().equals(keeperID)) {
                for (Animal a : animals) {
                    if (a.getAnimalID().equals(animalID)){
                        k.assignAnimal(a);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void feedAnimal(String animalID) throws OverfeedException {
        if (animalID == null) {
            return;
        }
        for (Animal a : animals) {
            if (a.getAnimalID().equals(animalID)) {
                a.feed(a.getDietProfile());
                //System.out.println( a.getAnimalID()+"has been feeded"+a.getActualFeedCount()+"/"+a.getRequiredMealsPerDay() );
                return;
            }
        }

    }

    public static void feedAnimal(String animalID, double portionKg) throws InvalidPortionException, OverfeedException {
        if (animalID == null) {
            return;
        }
        for (Animal a : animals) {
            if (a.getAnimalID().equals(animalID)) {
                a.feed(a.getDietProfile(), portionKg);
            }
        }
    }

    public static void displayAllAnimals() {
        System.out.println("The animals are: ");
        if (animals.isEmpty()) {
            System.out.println("There is no animal");

        }else {
            for (Animal a : animals) {
                a.displayStatus();
            }
        }
    }

    public static void displayAllKeepers() {
        if (keepers.isEmpty()) {
            System.out.println("There is no keeper");
        }else{
            System.out.println("The keepers are: ");
            for (Keeper k : keepers) {
                System.out.println("ID: " + k.getKeeperID());
                System.out.println("Name: " + k.getName());
                System.out.println("Expertise: " + k.getExpertiseSpecies());
                System.out.println("Assigned Animals:");
                k.displayAssignedAnimals();
            }
        }
    }

    public static void summary(){
        System.out.println("====ZOO-SUMMARY====");
        System.out.println("Total animals: " + animals.size());
        displayAllAnimals();
        System.out.println("Total keepers: " + keepers.size());
        displayAllKeepers();

    }

    public static boolean addAnimal(String animalID, String name, double weightKg, int requiredMealsPerDay, String spieces) throws InvalidInputException{
        if (animalID == null||name == null||spieces == null) {
            System.out.println("Add animal failed");
            return false;
        }
        if (weightKg <= 0) {
            throw new InvalidInputException("Please input a positive value for weight!");
        }

        if (requiredMealsPerDay < 1) {
            throw new InvalidInputException("Please input a positive integer for required meals per day!");
        }

        Animal a;
        if (spieces.equals("Lion")) a = new Lion(animalID, name, weightKg, requiredMealsPerDay);
        else if (spieces.equals("Elephant")) a = new Elephant(animalID, name, weightKg, requiredMealsPerDay);
        else if (spieces.equals("Penguin")) a = new Penguin(animalID, name, weightKg, requiredMealsPerDay);
        else throw new InvalidInputException("Our zoo don't want this species!");

        for (Animal an : animals) {
            if (an.equals(a)) {
                System.out.println("The animal" + an.getAnimalID() + "is already in the zoo.");
                return false;
            }
        }
        //System.out.println("zoo add animal test");
        //animals.add(a);
        //System.out.println(animals);
        return animals.add(a);
    }

    public static boolean addKeeper(Keeper a) {
        if (a == null) {
            return false;
        }
        for (Keeper k : keepers) {
            if (k.equals(a)) {
                System.out.println("The keeper " + a.getKeeperID() + " is already in the zoo.");
                return false;
            }
        }
        return keepers.add(a);
    }

    public static ArrayList<Animal> getAnimals() {
        return animals;
    }

    public static ArrayList<Keeper> getKeepers() {
        return keepers;
    }

    public static boolean removeAnimal(String animalID) {
        if (animalID == null) {
            return false;
        }
        for (Animal a : animals) {
            if (a.getAnimalID().equals(animalID)) {
                animals.remove(a);
                return true;
            }
        }
        return false;
    }

    public static boolean removeKeeper(String KeeperID) {
        if (KeeperID == null) {
            return false;
        }
        for (Keeper k : keepers) {
            if (k.getKeeperID().equals(KeeperID)) {
                keepers.remove(k);
                return true;
            }
        }
        return false;
    }

    public static boolean findAnimals(String animalID) {
        for (Animal animal : animals) {
            if (animal.getAnimalID().equals(animalID)) {
                return true;
            }
        }
        return false;
    }

    public static boolean findKeepers(String keeperID) {
        for (Keeper keeper : keepers) {
            if (keeper.getKeeperID().equals(keeperID)) {
                return true;
            }
        }
        return false;
    }

    public static void assignExpertiseToKeeper(String keeperID, String expertise) {
        if (expertise == null||expertise.isEmpty()) {
            return;
        }
        if (keeperID == null) {
            return;
        }

        for (Keeper keeper : keepers) {
            if (keeper.getKeeperID().equals(keeperID)) {
                if(keeper.addExpertise(expertise)){
                    System.out.println("The expertise " + expertise + " is added to the keeper "+ keeper.getName()+".");
                }
                else{
                    System.out.println("Add failed!");
                }
                return;
            }
        }
        System.out.println("The keeper is not in the zoo.");
    }



}

