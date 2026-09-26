import java.util.Collections;
import java.util.List;

/**
 * Immutable holder for a completed grading result.
 * Combines the original inputs with the calculated and evaluated outputs
 * so they can be displayed (or reused) as a single unit.
 */
public class GradeResult {

    private final List<Double> enteredGrades;
    private final double finalGrade;
    private final String letterGrade;
    private final boolean passing;

    public GradeResult(List<Double> enteredGrades, double finalGrade, String letterGrade, boolean passing) {
        this.enteredGrades = Collections.unmodifiableList(enteredGrades);
        this.finalGrade = finalGrade;
        this.letterGrade = letterGrade;
        this.passing = passing;
    }

    public List<Double> getEnteredGrades() {
        return enteredGrades;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public boolean isPassing() {
        return passing;
    }

    /** Produces a clean, console-friendly summary of the result. */
    public void display() {
        System.out.println();
        System.out.println("========== GRADE RESULT ==========");
        System.out.println("Entered grades: " + enteredGrades);
        System.out.printf("Final grade (average): %.2f%n", finalGrade);
        System.out.println("Letter grade: " + letterGrade);
        System.out.println("Status: " + (passing ? "PASS" : "FAIL"));
        System.out.println("===================================");
    }
}
