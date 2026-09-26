/**
 * Evaluates a calculated grade against the configured grading rules
 * to determine a letter grade and a pass/fail status.
 *
 * All thresholds are read from {@link GradingConfig} rather than hard-coded
 * here, so the grading rules can be changed in a single place.
 */
public class GradeEvaluator {

    public String determineLetterGrade(double finalGrade) {
        if (finalGrade >= GradingConfig.THRESHOLD_A) {
            return "A";
        } else if (finalGrade >= GradingConfig.THRESHOLD_B) {
            return "B";
        } else if (finalGrade >= GradingConfig.THRESHOLD_C) {
            return "C";
        } else if (finalGrade >= GradingConfig.THRESHOLD_D) {
            return "D";
        } else {
            return "F";
        }
    }

    public boolean isPassing(double finalGrade) {
        return finalGrade >= GradingConfig.PASS_THRESHOLD;
    }
}
