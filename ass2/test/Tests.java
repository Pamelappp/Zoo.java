package test;
import static org.junit.jupiter.api.Assertions.*;
import animal.*;

public class Tests {
    public static void main(String[] args) {

    }

    public void testSpeciesFormulaCalculation() {
        Animal lion = new Lion("001", "Amy", 100, 3);
        Animal elephant = new Elephant("002", "Bob", 1200, 3);
        Animal penguin = new Penguin("003", "Carl", 30, 2);

        double exceptForLion = 5;
        double exceptForElephant = 36;
        double exceptForPenguin = 1.8;
        assertEquals(exceptForLion, lion.dailyPortionKg(), "The portion formula of lions should be 0.05 * weight");
        assertEquals(exceptForElephant, elephant.dailyPortionKg(), "The portion formula of elephants should be 0.03 * weight");
        assertEquals(exceptForPenguin, penguin.dailyPortionKg(), "The portion formula of penguins should be 0.06 * weight");
    }

}
