import java.util.Arrays;
import java.util.List;

/**
 * Lightweight, dependency-free self-test harness for the calculation and
 * evaluation logic. No external testing framework (e.g., JUnit) is used,
 * in line with the project's constraint against unnecessary technologies.
 *
 * Run with: java -cp out GradingSystemTest
 */
public class GradingSystemTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testAverageCalculation();
        testLetterGradeBoundaries();
        testPassFailBoundary();
        testValidationRejectsInvalidInput();
        testValidationAcceptsValidInput();

        System.out.println();
        System.out.println("Results: " + passed + " passed, " + failed + " failed.");

        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void testAverageCalculation() {
        GradeCalculator calculator = new GradeCalculator();

        List<Double> grades = Arrays.asList(80.0, 90.0, 70.0);
        check("Average of 80, 90, 70 is 80.0", calculator.calculateFinalGrade(grades) == 80.0);

        List<Double> singleGrade = Arrays.asList(55.5);
        check("Average of a single grade equals that grade",
                calculator.calculateFinalGrade(singleGrade) == 55.5);
    }

    private static void testLetterGradeBoundaries() {
        GradeEvaluator evaluator = new GradeEvaluator();

        check("89.9 -> B", evaluator.determineLetterGrade(89.9).equals("B"));
        check("90.0 -> A", evaluator.determineLetterGrade(90.0).equals("A"));
        check("79.9 -> C", evaluator.determineLetterGrade(79.9).equals("C"));
        check("80.0 -> B", evaluator.determineLetterGrade(80.0).equals("B"));
        check("69.9 -> D", evaluator.determineLetterGrade(69.9).equals("D"));
        check("70.0 -> C", evaluator.determineLetterGrade(70.0).equals("C"));
        check("59.9 -> F", evaluator.determineLetterGrade(59.9).equals("F"));
        check("60.0 -> D", evaluator.determineLetterGrade(60.0).equals("D"));
        check("100.0 -> A", evaluator.determineLetterGrade(100.0).equals("A"));
        check("0.0 -> F", evaluator.determineLetterGrade(0.0).equals("F"));
    }

    private static void testPassFailBoundary() {
        GradeEvaluator evaluator = new GradeEvaluator();

        check("59.9 is failing", !evaluator.isPassing(59.9));
        check("60.0 is passing", evaluator.isPassing(60.0));
    }

    private static void testValidationRejectsInvalidInput() {
        check("Empty input rejected", !Validation.validateGradeText("").valid);
        check("Whitespace-only input rejected", !Validation.validateGradeText("   ").valid);
        check("Non-numeric input rejected", !Validation.validateGradeText("abc").valid);
        check("Negative value rejected", !Validation.validateGradeText("-5").valid);
        check("Over-range value rejected", !Validation.validateGradeText("150").valid);
    }

    private static void testValidationAcceptsValidInput() {
        check("Valid boundary value 0 accepted", Validation.validateGradeText("0").valid);
        check("Valid boundary value 100 accepted", Validation.validateGradeText("100").valid);
        check("Valid decimal value accepted", Validation.validateGradeText("87.5").valid);
    }

    private static void check(String description, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("[PASS] " + description);
        } else {
            failed++;
            System.out.println("[FAIL] " + description);
        }
    }
}
