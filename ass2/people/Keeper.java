package people;
import animal.Animal;
import exceptions.ExpertiseMismatchException;

import java.util.ArrayList;


public class Keeper {
    private String keeperID;
    private String name;
    private ArrayList<String> expertiseSpecies = new ArrayList<>();
    private ArrayList<Animal> assignedAnimals = new ArrayList<>();



    // The constructor of the keeper
    public Keeper(String keeperID, String name) {
        this.keeperID = keeperID;
        this.name = name;
    }

    //Add the species experise for the keeper.
    public boolean addExpertise(String species) {
        // If the parameter is null or blank, return false
        if (species == null || species.isBlank()) {
            return false;
        }
        // Only if the keeper don have this expertise, it can be added.
        if (!expertiseSpecies.contains(species.toLowerCase())) {
            expertiseSpecies.add(species.toLowerCase());
            return true;
        }else{
            System.out.println("Expertise already exists!");
            return false;
        }
    }

    //Assign the specific animal to the keeper
    public void assignAnimal(Animal a) throws ExpertiseMismatchException {
        //If the parameter is null, return.
        if (a == null) {
            return;
        }
        String species = a.getSpecies().toLowerCase();

        //If the keeper can't have the skill to manage this species, this animal can not be added.
        if (!expertiseSpecies.contains(species)) {
            throw new ExpertiseMismatchException("The keeper cannot manage this species.");
        }

        //If this animal has already been managed by this keeper, it cannot be added to this keeper.
        if (assignedAnimals.contains(a)) {
            System.out.println("The keeper has already managed this animal!");
            return;
        }
        assignedAnimals.add(a);

    }

    // Display all the animals this keeper manage.
    public void displayAssignedAnimals(){
        for (Animal a : assignedAnimals) {
            System.out.println("\t" + "Animal ID: " +a.getAnimalID()+ " Animal Name: " +a.getName()+" Species: "+a.getSpecies());
        }

    }

    public String getKeeperID() {
        return keeperID;
    }
    public String getName() {
        return name;
    }
    public ArrayList<String> getExpertiseSpecies() {
        return expertiseSpecies;
    }
    public ArrayList<Animal> getAssignedAnimals() {
        return assignedAnimals;
    }



}
