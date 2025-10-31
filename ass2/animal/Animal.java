package animal;

import exceptions.InvalidPortionException;
import exceptions.OverfeedException;

public abstract class Animal {
    protected String animalID;
    protected String name;
    protected String species;
    protected double weightKg;
    protected String dietProfile;
    protected int requiredMealsPerDay;
    protected int actualFeedCount = 0;
    protected double totalPortionPerDay = 0;

    public String getAnimalID() {
        return animalID;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public String getDietProfile() {
        return dietProfile;
    }

    public int getRequiredMealsPerDay() {
        return requiredMealsPerDay;
    }

    public int getActualFeedCount() {
        return actualFeedCount;
    }

    public double getTotalPortionPerDay() {
        return totalPortionPerDay;
    }
    //The constructor of Animal.
    public Animal(String animalID, String name, String species, double weightKg, String dietProfile, int requiredMealsPerDay) {
        this.animalID = animalID;
        this.name = name;
        this.species = species;
        this.weightKg = weightKg;
        this.dietProfile = dietProfile;
        this.requiredMealsPerDay = requiredMealsPerDay;
    }

    // The default method of feed animal.
    public abstract void feed(String food) throws OverfeedException;


    // The custom method of feed animal
    public void feed(String food, double portionKg) throws InvalidPortionException, OverfeedException {
        // Check if the amount of portion small that 0,make sure all the portion is positive.
        if (portionKg <= 0) {
            throw new InvalidPortionException("Portion KG must be greater than 0.");
        }
        // Make sure the actual feed count not exceed the required meals per day.
        if (actualFeedCount == requiredMealsPerDay) {
            throw new OverfeedException("Feeding limit reached. No more feeding allowed.");
        }
        // Make sure the amount of total feed portion not exceed the daily portion.
        if (totalPortionPerDay + portionKg > dailyPortionKg()) {
            throw new OverfeedException("This portion would surpass the allowed daily intake.");
        }

        actualFeedCount++;
        totalPortionPerDay += portionKg;
        System.out.printf("Meal %d served - %.2fkg %s fed. Total fed today: %.2fkg\n", actualFeedCount, portionKg, food, totalPortionPerDay);
    }

    //The method to calculate the daily portion.
    public abstract double dailyPortionKg();

    // Display the animal details
    public void displayStatus() {
        System.out.println("=====ANIMAL-DETAILS=====");
        System.out.println("Animal ID: " + animalID +
                "\nName: " + name +
                "\nSpecies: " + species +
                "\nWeight: " + weightKg +
                "\nDiet Profile: " + dietProfile);
        System.out.println("======FEEDING-LOG======");
        System.out.println("Meals required: " + requiredMealsPerDay +
                "\nMeals given: " + actualFeedCount +
                "\nTotal fed: " + String.format("%.2f", totalPortionPerDay) + "kg");
        System.out.println("=======================");
    }
}
