import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles collecting grade values from the console user.
 * This class is responsible only for the input/output interaction —
 * no calculation or evaluation logic lives here.
 */
public class GradeInput {

    private final Scanner scanner;

    public GradeInput(Scanner scanner) {
        this.scanner = scanner;
    }

    /** Prompts for how many grades to enter, then collects each valid grade in turn. */
    public List<Double> collectGrades() {
        int count = collectGradeCount();
        List<Double> grades = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            double grade = collectSingleGrade(i);
            grades.add(grade);
        }

        return grades;
    }

    private int collectGradeCount() {
        while (true) {
            System.out.print("How many grades would you like to enter? ");
            String raw = scanner.nextLine();

            try {
                int count = Integer.parseInt(raw.trim());
                if (count <= 0) {
                    System.out.println("Please enter a whole number greater than 0.");
                    continue;
                }
                return count;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private double collectSingleGrade(int index) {
        while (true) {
            System.out.printf("Enter grade #%d (%.0f-%.0f): ",
                    index, GradingConfig.MIN_VALID_GRADE, GradingConfig.MAX_VALID_GRADE);
            String raw = scanner.nextLine();

            Validation.Result result = Validation.validateGradeText(raw);
            if (result.valid) {
                return Double.parseDouble(raw.trim());
            } else {
                System.out.println(result.errorMessage);
            }
        }
    }
}
