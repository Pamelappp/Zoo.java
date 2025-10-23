package people;
import animal.Animal;

import java.util.ArrayList;


public class Keeper {
    private final String keeperID;
    private String name;
    private ArrayList<String> expertiseSpecies = new ArrayList<>();
    private ArrayList<Animal> assignedAnimals = new ArrayList<>();


    //private List<Animal> assignedAnimals = new ArrayList<>();

    public Keeper(String keeperID, String name) {
        this.keeperID = keeperID;
        this.name = name;
    }

    public void addExpertise(String species) {
        if (species == null || species.isBlank()) {
            return;
        }
        if (!expertiseSpecies.contains(species)) {
            expertiseSpecies.add(species);
        }
    }

    public void assignAnimal(Animal a){
        if (a == null) {
            return;
        }
        if (assignedAnimals.contains(a)) {
            return;
        }
        assignedAnimals.add(a);

    }

    public void displayAssignedAnimals(){
        System.out.println("Keeper ID: " + keeperID + " Name: " + name + " Assigned Animals:");
        for (Animal a : assignedAnimals) {
            System.out.println("\t" + a);
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
    public void setName(String name){
        this.name = name;
    }


}
