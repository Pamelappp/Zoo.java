package test;
import static org.junit.jupiter.api.Assertions.*;
import animal.*;
import exceptions.ExpertiseMismatchException;
import exceptions.InvalidPortionException;
import exceptions.OverfeedException;
import people.*;

public class Tests {
    private static final double DELTA = 0.00001;

    public static void testSpeciesFormulaCalculation() {
        Animal lion = new Lion("001", "Amy", 100, 2);
        Animal elephant = new Elephant("002", "Bob", 1200, 3);
        Animal penguin = new Penguin("003", "Carl", 30, 2);

        double exceptForLion = 5;
        double exceptForElephant = 36;
        double exceptForPenguin = 1.8;
        assertEquals(exceptForLion, lion.dailyPortionKg(), DELTA,
                "The portion formula of lions should be 0.05 * weight");
        assertEquals(exceptForElephant, elephant.dailyPortionKg(), DELTA,
                "The portion formula of elephants should be 0.03 * weight");
        assertEquals(exceptForPenguin, penguin.dailyPortionKg(), DELTA,
                "The portion formula of penguins should be 0.06 * weight");
    }

    public static void testFeedMethodsOverload() {
        Animal lion = new Lion("001", "Amy", 100, 2);

        double exceptFeedDefault = 2.5;
        double exceptFeedCustom = 1;

        try {
            lion.feed("meat");
            assertEquals(exceptFeedDefault, lion.getTotalPortionPerDay(),
                    "The lion should be fed default portion.");
            lion.feed("meat", 1);
            assertEquals(exceptFeedCustom, lion.getTotalPortionPerDay() - exceptFeedDefault,
                    "The lion should be fed 1kg.");
        } catch (Exception e) {
            fail("feed methods meet some unexpected exeption." + e);
        }
    }

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

    public static void testKeeperExpertiseException() {
        Animal lion = new Lion("001", "Amy", 100, 3);
        Keeper tom = new Keeper("001", "Tom");

        tom.addExpertise("elephant");
        try {
            tom.assignAnimal(lion);
            fail("The ExpertiseMismatchException should be thrown.");
        } catch (ExpertiseMismatchException e) {}
    }

    public static  void testInvalidPortionException() {
        Animal lion = new Lion("001", "Amy", 100, 3);

        try {
            lion.feed("meat", -1);
            fail("The InvalidPortionException should be thrown.");
        } catch(OverfeedException e) {
            fail("The thrown exception should be InvalidPortionException, instead of " + e);
        } catch(InvalidPortionException e) {}
    }

    public static void testOverfeedException() {
        Animal lion = new Lion("001", "Amy", 100, 1);

        try {
            lion.feed("meat", 0.1);
            lion.feed("meat", 0.2);
            fail("The OverfeedException should be thrown.");
        } catch(InvalidPortionException e) {
            fail("The thrown exception should be OverfeedException, instead of " + e);
        } catch(OverfeedException e) {}
    }

    public static void main(String[] args) {
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

    }

}
