package core;

import animal.Animal;
import people.Keeper;
import java.util.ArrayList;


public class Zoo {
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Keeper> keepers = new ArrayList<>();


    public void assignKeeperToAnimal(String keeperID, String animalID) {
        if (keeperID == null || animalID == null){
            return;
        }
        for (Keeper k : keepers) {
            if (k.getKeeperID().equals(keeperID)) {
                for (Animal a : animals) {
                    if (a.getAnimalID().equals(animalID)){
                        k.assignAnimal(a);
                    }
                }
            }
        }
    }

    public void feedAnimal(String animalID) throws exceptions.OverfeedException {
        if (animalID == null) {
            return;
        }
        for (Animal a : animals) {
            if (a.getAnimalID().equals(animalID)) {
                a.feed(a.getDietProfile());
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
        for (Animal a : animals) {
            System.out.println(a);
        }
    }

    public void displayAllKeepers() {
        System.out.println("The keepers are: ");
        for (Keeper k : keepers) {
            System.out.println(k);
        }
    }

    public void summary(){
        System.out.println("=== Zoo Summary ===");
        System.out.println("Total animals: " + animals.size());
        System.out.println("Total keepers: " + keepers.size());

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
            }
        }
        return true;
    }
}

