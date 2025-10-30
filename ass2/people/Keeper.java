package people;
import animal.Animal;
import exceptions.ExpertiseMismatchException;

import java.util.ArrayList;


public class Keeper {
    private String keeperID;
    private String name;
    private ArrayList<String> expertiseSpecies = new ArrayList<>();
    private ArrayList<Animal> assignedAnimals = new ArrayList<>();


    //private List<Animal> assignedAnimals = new ArrayList<>();

    public Keeper(String keeperID, String name) {
        this.keeperID = keeperID;
        this.name = name;
    }

    public boolean addExpertise(String species) {
        if (species == null || species.isBlank()) {
            return false;
        }
        if (!expertiseSpecies.contains(species.toLowerCase())) {
            expertiseSpecies.add(species.toLowerCase());
            return true;
        }else{
            System.out.println("Expertise already exists!");
            return false;
        }
    }

    public void assignAnimal(Animal a) throws ExpertiseMismatchException {
        if (a == null) {
            return;
        }

        String species = a.getSpecies().toLowerCase();
        if (!expertiseSpecies.contains(species)) {
            throw new ExpertiseMismatchException("The keeper cannot manage this species");
        }

        if (assignedAnimals.contains(a)) {
            System.out.println("The keeper has already managed this animal!");
            return;
        }
        assignedAnimals.add(a);

    }

    public void displayAssignedAnimals(){
        //System.out.println("Keeper ID: " + keeperID + " Name: " + name + " Assigned Animals:");
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
