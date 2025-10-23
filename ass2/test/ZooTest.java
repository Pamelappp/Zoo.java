package test;

import animal.*;
import core.Zoo;
import exceptions.*;
import people.Keeper;

public class ZooTest {

    private static int passed = 0, total = 0;

    public static void main(String[] args) {
        test1_speciesFormula();                // 1) 公式计算
        test2_feedOverload();                  // 2) 重载 feed()
        test3_dietProfileStored();             // 3) 饮食档案字符串
        test4_keeperExpertiseCheck();          // 4) 专长不匹配异常
        test5_invalidPortion();                // 5) Portion<=0 异常
        test6_overfeed();                      // 6) 超过每日餐次异常
        test7_duplicateIDRejectedByZoo();      // 7) Zoo 去重
        test8_negativeInputsRejected();        // 8) 构造器负数/非法输入

        System.out.printf("%nRESULT: %d/%d tests passed.%n", passed, total);
    }

    // ======= helpers =======
    private static void assertTrue(boolean cond, String msg) {
        total++;
        if (cond) { passed++; System.out.println("[PASS] " + msg); }
        else System.out.println("[FAIL] " + msg);
    }

    private static void assertEqualsDouble(double a, double b, double eps, String msg) {
        assertTrue(Math.abs(a - b) <= eps, msg + String.format(" (expected=%.4f, actual=%.4f)", b, a));
    }

    private static void assertThrows(Class<? extends Throwable> exClass, Runnable r, String msg) {
        total++;
        try {
            r.run();
            System.out.println("[FAIL] " + msg + " (no exception)");
        } catch (Throwable t) {
            if (exClass.isInstance(t) || (t.getCause() != null && exClass.isInstance(t.getCause()))) {
                passed++;
                System.out.println("[PASS] " + msg + " (threw " + t.getClass().getSimpleName() + ")");
            } else {
                System.out.println("[FAIL] " + msg + " (threw " + t + ")");
            }
        }
    }

    // ======= tests =======

    // 1) Species formula calculation
    private static void test1_speciesFormula() {
        Animal lion = new Lion("L1", "Leo", 200.0, 2);          // 0.05 * 200 = 10
        Animal elephant = new Elephant("E1", "Ella", 1000.0, 3);// 0.03 * 1000 = 30
        Animal penguin = new Penguin("P1", "Penny", 10.0, 2);   // 0.06 * 10 = 0.6

        assertEqualsDouble(lion.dailyPortionKg(), 10.0, 1e-9, "Lion portion formula");
        assertEqualsDouble(elephant.dailyPortionKg(), 30.0, 1e-9, "Elephant portion formula");
        assertEqualsDouble(penguin.dailyPortionKg(), 0.6, 1e-9, "Penguin portion formula");
    }

    // 2) Overloaded feed(): default vs custom portion
    private static void test2_feedOverload() {
        Animal a = new Lion("L2", "Simba", 100.0, 2); // daily=5.0 => default portion = 2.5
        try {
            a.feed("meat");       // default
            a.feed("meat", 1.0);  // explicit
            assertTrue(a.getActualFeedCount() == 2, "feed() overloads both increment mealsFed");
        } catch (Exception e) {
            assertTrue(false, "feed() overloads should not throw here, but got: " + e);
        }
    }

    // 3) Diet profile stored as string
    private static void test3_dietProfileStored() {
        Animal l = new Lion("L3", "Khan", 150.0, 2);
        Animal e = new Elephant("E3", "Dum", 800.0, 3);
        Animal p = new Penguin("P3", "Pogo", 12.0, 2);
        assertTrue("CARNIVORE".equals(l.getDietProfile()), "Lion diet profile=CARNIVORE");
        assertTrue("HERBIVORE".equals(e.getDietProfile()), "Elephant diet profile=HERBIVORE");
        assertTrue("OMNIVORE".equals(p.getDietProfile()), "Penguin diet profile=OMNIVORE");
    }

    // 4) Keeper expertise mismatch -> ExpertiseMismatchException
    private static void test4_keeperExpertiseCheck() {
        Keeper k = new Keeper("KX", "Alice");
        k.addExpertise("Elephant");
        Animal lion = new Lion("L4", "Leo", 180.0, 2);

        Zoo zoo = new Zoo();
        zoo.addKeeper(k);
        zoo.addAnimal(lion);

        assertThrows(ExpertiseMismatchException.class, () -> {
            try { zoo.assignKeeperToAnimal("KX", "L4"); }
            catch (Exception e) { throw new RuntimeException(e); }
        }, "Keeper lacks expertise should throw ExpertiseMismatchException");
    }

    // 5) Invalid portion (<=0) -> InvalidPortionException
    private static void test5_invalidPortion() {
        Animal a = new Elephant("E5", "Ella", 900.0, 3);
        assertThrows(InvalidPortionException.class, () -> {
            try { a.feed("grass", 0.0); }
            catch (Exception e) { throw new RuntimeException(e); }
        }, "Feeding with non-positive portion should throw InvalidPortionException");
    }

    // 6) Overfeeding beyond daily limit -> OverfeedException
    private static void test6_overfeed() {
        Animal p = new Penguin("P6", "P", 10.0, 2); // default 2 meals/day
        try {
            p.feed("fish");   // 1st
            p.feed("fish");   // 2nd
        } catch (Exception e) {
            assertTrue(false, "First two meals should be OK, but got: " + e);
            return;
        }
        assertThrows(OverfeedException.class, () -> {
            try { p.feed("fish"); }  // 3rd should fail
            catch (Exception e) { throw new RuntimeException(e); }
        }, "Overfeeding should throw OverfeedException");
    }

    // 7) Zoo duplicate ID rejected on addAnimal()
    private static void test7_duplicateIDRejectedByZoo() {
        Zoo zoo = new Zoo();
        boolean ok1 = zoo.addAnimal(new Lion("A7", "L", 120.0, 2));
        boolean ok2 = zoo.addAnimal(new Elephant("A7", "E", 800.0, 3)); // duplicate ID
        assertTrue(ok1 && !ok2, "Zoo rejects duplicate animalID");
    }

    // 8) Constructor negative/illegal inputs -> IllegalArgumentException
    private static void test8_negativeInputsRejected() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Penguin("A8", "Pe", -5.0, 2);
        }, "Negative weight rejected by constructor");

        assertThrows(IllegalArgumentException.class, () -> {
            new Lion("A9", "", 100.0, 2);
        }, "Blank name rejected by constructor");

        assertThrows(IllegalArgumentException.class, () -> {
            new Elephant("A10", "El", 500.0, 0);
        }, "Meals per day < 1 rejected by constructor");
    }
}
