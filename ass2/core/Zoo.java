package core;

import animal.Animal;
import exceptions.ExpertiseMismatchException;
import people.Keeper;
import java.util.ArrayList;


public class Zoo {
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Keeper> keepers = new ArrayList<>();


    public boolean assignKeeperToAnimal(String keeperID, String animalID) throws ExpertiseMismatchException {
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
            }else{
                System.out.println("Not found keeper ID: " + keeperID);
            }
        }
        return false;
    }

    public void feedAnimal(String animalID) throws exceptions.OverfeedException {
        if (animalID == null) {
            return;
        }
        for (Animal a : animals) {
            if (a.getAnimalID().equals(animalID)) {
                a.feed(a.getDietProfile());
                System.out.println( a.getAnimalID()+"has been feeded"+a.getActualFeedCount()+"/"+a.getRequiredMealsPerDay() );

            }
        }
    }

    public void feedAnimal(String animalID, double portionKg) throws exceptions.InvalidPortionException, exceptions.OverfeedException {
        if (animalID == null||portionKg <= 0) {
            return;
        }
        for (Animal a : animals) {
            if (a.getAnimalID().equals(animalID)) {
                a.feed(a.getDietProfile(), portionKg);
            }
        }
    }

    public void displayAllAnimals() {
        System.out.println("The animals are: ");
        if (animals.isEmpty()) {
            System.out.println("There is no animal");

        }else {
            for (Animal a : animals) {
                a.displayStatus();
            }
        }
    }

    public void displayAllKeepers() {
        if (keepers.isEmpty()) {
            System.out.println("There is no keeper");
        }else{
            System.out.println("The keepers are: ");
            for (Keeper k : keepers) {
                System.out.println("ID: " + k.getKeeperID());
                System.out.println("Name: " + k.getName());
                k.displayAssignedAnimals();
            }
        }
    }

    public void summary(){
        System.out.println("=== Zoo Summary ===");
        System.out.println("Total animals: " + animals.size());
        displayAllAnimals();
        System.out.println("Total keepers: " + keepers.size());
        displayAllKeepers();

    }

    public boolean addAnimal(Animal a) {
        if (a == null) {
            return false;
        }
        for (Animal an : animals) {
            if (an.equals(a)) {
                System.out.println("The animal" + an + "is already in the zoo.");
                return false;
            }
        }
        return animals.add(a);
    }

    public boolean addKeeper(Keeper a) {
        if (a == null) {
            return false;
        }
        for (Keeper k : keepers) {
            if (k.equals(a)) {
                System.out.println("The keeper" + a + "is already in the zoo.");
                return false;
            }
        }
        return keepers.add(a);
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public ArrayList<Keeper> getKeepers() {
        return keepers;
    }

    public boolean removeAnimal(String animalID) {
        if (animalID == null) {
            return false;
        }
        for (Animal a : animals) {
            if (a.getAnimalID().equals(animalID)) {
                animals.remove(a);

            }
        }
        return true;
    }

    public boolean removeKeeper(String KeeperID) {
        if (KeeperID == null) {
            return false;
        }
        for (Keeper k : keepers) {
            if (k.getKeeperID().equals(KeeperID)) {
                keepers.remove(k);
            }else{
                System.out.println("The keeper" + KeeperID + "is not in the zoo.");
                return false;
            }
        }
        return true;
    }

    public boolean findAnimals(String animalID) {
        for (Animal animal : animals) {
            if (animal.getAnimalID().equals(animalID)) {
                return true;
            }
        }
        return false;
    }

    public boolean findKeepers(String keeperID) {
        for (Keeper keeper : keepers) {
            if (keeper.getKeeperID().equals(keeperID)) {
                return true;
            }
        }
        return false;
    }
}

