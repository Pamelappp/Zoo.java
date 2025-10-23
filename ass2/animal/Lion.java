package animal;

import exceptions.OverfeedException;

public class Lion extends animal.Animal {
    private final double PORTION_FACTOR = 0.05;

    public Lion(String animalId, String name, double weightKg, int requiredMealsPerDay) {
        super(animalId, name, "", weightKg, "", requiredMealsPerDay);
        this.species = "Lion";
        this.dietProfile = "CARNIVORE";
    }

    @Override
    public void feed(String food) throws OverfeedException {
        double portionKg = dailyPortionKg();
        if (actualFeedCount == requiredMealsPerDay) throw new OverfeedException("Feeding limit reached. No more feeding allowed.");
        if (totalPortionPerDay + portionKg > dailyPortionKg()) throw new OverfeedException("This portion would surpass the allowed daily intake.");

        actualFeedCount++;
        totalPortionPerDay += portionKg;
    }

    @Override
    public double dailyPortionKg() {
        return PORTION_FACTOR * weightKg;
    }
}
