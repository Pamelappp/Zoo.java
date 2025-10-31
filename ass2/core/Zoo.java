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
        // If the parameters are null, return false.
        if (keeperID == null || animalID == null){
            return false;
        }
        // Through the keeperID and animalID to find the targets and assign them.
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
    // Feeding animal through the animalID using the default portion.
    public static void feedAnimal(String animalID) throws OverfeedException {
        // If the parameter is null, close method.
        if (animalID == null) {
            return;
        }
        // Finding the specific animal
        for (Animal a : animals) {
            if (a.getAnimalID().equals(animalID)) {
                a.feed(a.getDietProfile());
                //System.out.println( a.getAnimalID()+"has been fed"+a.getActualFeedCount()+"/"+a.getRequiredMealsPerDay() );
                return;
            }
        }
    }

    // Feeding the specific animal using the custom portion.
    public static void feedAnimal(String animalID, double portionKg) throws InvalidPortionException, OverfeedException {
        // If the parameters is null, close.
        if (animalID == null) {
            return;
        }
        // Finding the target animal and using the custom portion to feed it.
        for (Animal a : animals) {
            if (a.getAnimalID().equals(animalID)) {
                a.feed(a.getDietProfile(), portionKg);
            }
        }
    }

    // Show all the animals in the zoo.
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

    // Show the all keepers in the zoo.
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
    // Show the all animals and keeper that in the zoo.
    public static void summary(){
        System.out.println("====ZOO-SUMMARY====");
        System.out.println("Total animals: " + animals.size());
        displayAllAnimals();
        System.out.println("Total keepers: " + keepers.size());
        displayAllKeepers();

    }

    //Add animal in the zoo.
    public static boolean addAnimal(String animalID, String name, double weightKg, int requiredMealsPerDay, String spieces) throws InvalidInputException{
        // Make sure the important parameter not be null.
        if (animalID == null||name == null||spieces == null) {
            System.out.println("Add animal failed");
            return false;
        }

        // Make sure the weight is positive.
        if (weightKg <= 0) {
            throw new InvalidInputException("Please input a positive value for weight!");
        }

        // Make sure the requirement meal per day is valid.
        if (requiredMealsPerDay < 1) {
            throw new InvalidInputException("Please input a positive integer for required meals per day!");
        }

        Animal a;
        // Identify the species and create the target animal.
        if (spieces.equals("Lion")) a = new Lion(animalID, name, weightKg, requiredMealsPerDay);
        else if (spieces.equals("Elephant")) a = new Elephant(animalID, name, weightKg, requiredMealsPerDay);
        else if (spieces.equals("Penguin")) a = new Penguin(animalID, name, weightKg, requiredMealsPerDay);
        else throw new InvalidInputException("Our zoo don't want this species!");

        //Make sure the new animal do not in the zoo.
        for (Animal an : animals) {
            if (an.equals(a)) {
                System.out.println("The animal" + an.getAnimalID() + "is already in the zoo.");
                return false;
            }
        }
        return animals.add(a);
    }

    // Adding keeper to the zoo.
    public static boolean addKeeper(String name, String keeperID) {
        // Making sure the name and ID not null.
        if (name == null||keeperID == null) {
            return false;
        }

        Keeper a = new Keeper(name, keeperID);

        // Make sure the keeper not in the zoo.
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

    // Remove animal from the zoo.
    public static boolean removeAnimal(String animalID) {
        // Make sure animal ID is not null.
        if (animalID == null) {
            return false;
        }
        // Find the target animals and remove it.
        for (Animal a : animals) {
            if (a.getAnimalID().equals(animalID)) {
                animals.remove(a);
                return true;
            }
        }
        return false;
    }

    // Remove keeper from zoo.
    public static boolean removeKeeper(String KeeperID) {
        // Make sure the keeper ID not null.
        if (KeeperID == null) {
            return false;
        }
        // Find the target keeper and remove it.
        for (Keeper k : keepers) {
            if (k.getKeeperID().equals(KeeperID)) {
                keepers.remove(k);
                return true;
            }
        }
        return false;
    }

    // Using animalID to find the target animal in the zoo.
    public static boolean findAnimals(String animalID) {
        for (Animal animal : animals) {
            if (animal.getAnimalID().equals(animalID)) {
                return true;
            }
        }
        return false;
    }

    // Using the keeper using the keeper ID.
    public static boolean findKeepers(String keeperID) {
        for (Keeper keeper : keepers) {
            if (keeper.getKeeperID().equals(keeperID)) {
                return true;
            }
        }
        return false;
    }

    // Add the expertise to a specific keeper.
    public static void assignExpertiseToKeeper(String keeperID, String expertise) {
        // Make the expertise is valid.
        if (expertise == null||expertise.isEmpty()) {
            return;
        }
        // Make sure keeper ID not null.
        if (keeperID == null) {
            return;
        }

        // find the target keeper
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

