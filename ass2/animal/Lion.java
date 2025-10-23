package animal;

import exceptions.OverfeedException;

public class Lion extends animal.Animal {
    private final double PORTIONFACTOR = 0.05;
    private final String NAME = "Penguin";

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
        return PORTIONFACTOR * weightKg;
    }
}
