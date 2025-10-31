package test;
import static org.junit.jupiter.api.Assertions.*;
import animal.*;
import core.Zoo;
import exceptions.ExpertiseMismatchException;
import exceptions.InvalidInputException;
import exceptions.InvalidPortionException;
import exceptions.OverfeedException;
import people.*;

public class Tests {
    private static final double DELTA = 0.00001;

    // Test some invalid input while adding a new animal
    public static void testAddAnimalInvalidValue() {
        // Test whether system could prevent non-positive weight input
        assertThrows(InvalidInputException.class, () ->
                Zoo.addAnimal("001", "Ki", -3, 2, "Lion"));
        // Test whether system could prevent meals input that is < 1
        assertThrows(InvalidInputException.class, () ->
                Zoo.addAnimal("001", "Ki", 30, 0, "Lion"));
        // Test whether system could prevent invalid species
        assertThrows(InvalidInputException.class, () ->
                Zoo.addAnimal("001", "Ki", 30, 1, "Apple"));
    }

    // Test every species' portion formula is correct
    public static void testSpeciesFormulaCalculation() {
        Animal lion = new Lion("001", "Amy", 100, 2);
        Animal elephant = new Elephant("002", "Bob", 1200, 3);
        Animal penguin = new Penguin("003", "Carl", 30, 2);

        double exceptForLion = 5;
        double exceptForElephant = 36;
        double exceptForPenguin = 1.8;
        // For lion
        assertEquals(exceptForLion, lion.dailyPortionKg(), DELTA,
                "The portion formula of lions should be 0.05 * weight");
        // For elephant
        assertEquals(exceptForElephant, elephant.dailyPortionKg(), DELTA,
                "The portion formula of elephants should be 0.03 * weight");
        // For penguin
        assertEquals(exceptForPenguin, penguin.dailyPortionKg(), DELTA,
                "The portion formula of penguins should be 0.06 * weight");
    }

    // Test when the input is valid, two feed methods could work normally
    public static void testFeedMethodsOverload() {
        Animal lion = new Lion("001", "Amy", 100, 2);

        double exceptFeedDefault = 2.5;
        double exceptFeedCustom = 1;

        try {
            // Test if we call the default feed method, and feed the correct portion
            lion.feed("meat");
            assertEquals(exceptFeedDefault, lion.getTotalPortionPerDay(),
                    "The lion should be fed default portion.");
            // Test if we call the custom feed method, and feed the correct portion
            lion.feed("meat", 1);
            assertEquals(exceptFeedCustom, lion.getTotalPortionPerDay() - exceptFeedDefault,
                    "The lion should be fed 1kg.");
            // Test if we actually feed twice
            assertEquals(2, lion.getActualFeedCount(), "The lion should have eaten 2 meals");
        } catch (Exception e) {
            fail("feed methods meet some unexpected exeption." + e);
        }
    }

    // Test if we correctly store the diet profile for each species
    public static void testDietProfileValidation() {
        Animal lion = new Lion("001", "Amy", 100, 3);
        Animal elephant = new Elephant("002", "Bob", 1200, 3);
        Animal penguin = new Penguin("003", "Carl", 30, 2);

        assertEquals("CARNIVORE", lion.getDietProfile(),
                "lion's diet profile should be \"CARNIVORE\"");
        assertEquals("HERBIVORE", elephant.getDietProfile(),
                "elephant's diet profile should be \"HERBIVORE\"");
        assertEquals("OMNIVORE", penguin.getDietProfile(),
                "penguin's diet profile should be \"OMNIVORE\"");
    }

    // Test if the ExpertiseMismatchException could work in appropriate cases
    public static void testKeeperExpertiseException() {
        Animal lion = new Lion("001", "Amy", 100, 3);
        Keeper tom = new Keeper("001", "Tom");

        tom.addExpertise("elephant");
        // Tom just has elephant expertise, but we assign a lion for him
        assertThrows(ExpertiseMismatchException.class, () -> tom.assignAnimal(lion),
                "Should throw ExpertiseMismatchException.");
    }

    // Test if the InvalidPortionException could work in appropriate cases
    public static void testInvalidPortionException() {
        Animal lion = new Lion("001", "Amy", 100, 3);

        // Test a negative input about portion
        assertThrows(InvalidPortionException.class, () -> lion.feed("meat", -1),
                "Should throw InvalidPortionException.");
    }

    // Test if the OverfeedException could work in appropriate cases
    public static void testOverfeedException() {
        Animal lion = new Lion("001", "Amy", 100, 1);
        Animal elephant = new Elephant("002", "Bob", 1200, 3);

        // Test if we feed more than 1 meal for lion Amy
        assertThrows(OverfeedException.class, () -> {
            lion.feed("meat", 0.1);
            lion.feed("meat", 0.2);
        }, "Should throw OverfeedException.");

        //Test if we feed too much portion for elephant Bob
        assertThrows(OverfeedException.class, () ->
                elephant.feed("banana", 1000),
                "Should throw OverfeedException.");
    }

    public static void main(String[] args) {
        System.out.println("=====================Running-Tests=======================");
        testAddAnimalInvalidValue();
        System.out.println("Could prevent invalid value for adding new animal.");
        testSpeciesFormulaCalculation();
        System.out.println("All animals' portion formula calculation are correct.");
        testFeedMethodsOverload();
        System.out.println("Both feed() methods work well.");
        testDietProfileValidation();
        System.out.println("All animals' diet is stored correct.");
        testKeeperExpertiseException();
        System.out.println("ExpertiseMismatchException on");
        testInvalidPortionException();
        System.out.println("InvalidPortionException on");
        testOverfeedException();
        System.out.println("OverfeedException on");
        System.out.println("=====================All-Test-Passed=====================");

    }

}
