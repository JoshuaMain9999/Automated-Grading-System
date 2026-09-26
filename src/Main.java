import java.util.List;
import java.util.Scanner;

/**
 * Entry point for the Automated Grading System.
 *
 * Workflow:
 *   Input -> Validation -> Calculation -> Conditional Evaluation -> Output
 *
 * This class only orchestrates that workflow; each responsibility is
 * delegated to a dedicated class (GradeInput, GradeCalculator,
 * GradeEvaluator, GradeResult), keeping calculation logic separate
 * from input/output handling.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradeInput gradeInput = new GradeInput(scanner);
        GradeCalculator calculator = new GradeCalculator();
        GradeEvaluator evaluator = new GradeEvaluator();

        System.out.println("=====================================");
        System.out.println("   AUTOMATED GRADING SYSTEM");
        System.out.println("=====================================");

        boolean runAgain = true;
        while (runAgain) {
            List<Double> grades = gradeInput.collectGrades();

            Validation.Result listCheck = Validation.validateGradeList(grades);
            if (!listCheck.valid) {
                System.out.println(listCheck.errorMessage);
                continue;
            }

            double finalGrade = calculator.calculateFinalGrade(grades);
            String letterGrade = evaluator.determineLetterGrade(finalGrade);
            boolean passing = evaluator.isPassing(finalGrade);

            GradeResult result = new GradeResult(grades, finalGrade, letterGrade, passing);
            result.display();

            runAgain = promptRunAgain(scanner);
        }

        System.out.println("\nThank you for using the Automated Grading System.");
        scanner.close();
    }

    private static boolean promptRunAgain(Scanner scanner) {
        System.out.print("\nCalculate another grade? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }
}
