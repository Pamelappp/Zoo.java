package animal;

import exceptions.OverfeedException;

public class Penguin extends animal.Animal {
    private final double PORTION_FACTOR = 0.06;

    public Penguin(String animalId, String name, double weightKg, int requiredMealsPerDay) {
        super(animalId, name, "", weightKg, "", requiredMealsPerDay);
        this.species = "Penguin";
        this.dietProfile = "OMNIVORE";
    }

    @Override
    public void feed(String food) throws OverfeedException {
        double portionKg = dailyPortionKg() / requiredMealsPerDay;
        if (actualFeedCount == requiredMealsPerDay){
            throw new OverfeedException("Feeding limit reached. No more feeding allowed.");
        }
        if (totalPortionPerDay + portionKg > dailyPortionKg()){
            throw new OverfeedException("This portion would surpass the allowed daily intake.");
        }

        actualFeedCount++;
        totalPortionPerDay += portionKg;
        System.out.printf("Meal %d served - %.2fkg %s fed. Total fed today: %.2fkg\n", actualFeedCount, portionKg, food, totalPortionPerDay);
    }

    @Override
    public double dailyPortionKg() {
        return PORTION_FACTOR * weightKg;
    }
}
