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

    public Animal(String animalID, String name, String species, double weightKg, String dietProfile, int requiredMealsPerDay) {
        this.animalID = animalID;
        this.name = name;
        this.species = species;
        this.weightKg = weightKg;
        this.dietProfile = dietProfile;
        this.requiredMealsPerDay = requiredMealsPerDay;
    }

    public abstract void feed(String food) throws OverfeedException;

    public void feed(String food, double portionKg) throws InvalidPortionException, OverfeedException {
        if (portionKg <= 0) throw new InvalidPortionException("Portion KG must be greater than 0.");
        if (actualFeedCount == requiredMealsPerDay) throw new OverfeedException("Feeding limit reached. No more feeding allowed.");
        if (totalPortionPerDay + portionKg > dailyPortionKg()) throw new OverfeedException("This portion would surpass the allowed daily intake.");

        actualFeedCount++;
        totalPortionPerDay += portionKg;
        System.out.printf("Meal %d served - %.2fkg fed. Total fed today: %.2fkg\n", actualFeedCount, portionKg, totalPortionPerDay);
    }

    public abstract double dailyPortionKg();

    public void displayStatus() {
        System.out.println("========DETAILS========");
        System.out.println("Animal ID: " + animalID +
                "\nName: " + name +
                "\nSpecies: " + species +
                "\nWeight: " + weightKg +
                "\nDiet Profile: " + dietProfile);
        System.out.println("======FEEDING-LOG======");
        System.out.println("Meals required: " + requiredMealsPerDay +
                "\nMeals given: " + actualFeedCount +
                "\nTotal fed: " + String.format(".2f", totalPortionPerDay) + "kg");
        System.out.println("=======================");
    }
}
