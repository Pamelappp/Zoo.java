package animal;

import exceptions.OverfeedException;

public class Penguin extends animal.Animal {
    private final double PORTIONFACTOR = 0.06;

    public Penguin(String animalId, String name, double weightKg, int requiredMealsPerDay) {
        super(animalId, name, "", weightKg, "", requiredMealsPerDay);
        this.species = "Penguin";
        this.dietProfile = "OMNIVORE";
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
