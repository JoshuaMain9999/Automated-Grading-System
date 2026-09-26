import java.util.List;

/**
 * Centralized input validation for the Automated Grading System.
 * Keeping all validation rules here avoids duplicating checks across classes
 * and makes it easy to see exactly what counts as valid input.
 */
public final class Validation {

    private Validation() {
        // Utility class; not meant to be instantiated.
    }

    /** Outcome of a validation attempt: whether it passed, and an error message if not. */
    public static final class Result {
        public final boolean valid;
        public final String errorMessage;

        private Result(boolean valid, String errorMessage) {
            this.valid = valid;
            this.errorMessage = errorMessage;
        }

        public static Result ok() {
            return new Result(true, null);
        }

        public static Result fail(String message) {
            return new Result(false, message);
        }
    }

    /** Validates raw console text as a numeric grade within the allowed range. */
    public static Result validateGradeText(String rawInput) {
        if (rawInput == null || rawInput.trim().isEmpty()) {
            return Result.fail("Input cannot be empty. Please enter a numeric grade.");
        }

        double value;
        try {
            value = Double.parseDouble(rawInput.trim());
        } catch (NumberFormatException e) {
            return Result.fail("Please enter a valid numeric grade (e.g., 87.5).");
        }

        return validateGradeValue(value);
    }

    /** Validates an already-parsed numeric grade value against the configured range. */
    public static Result validateGradeValue(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return Result.fail("Please enter a valid numeric grade.");
        }
        if (value < GradingConfig.MIN_VALID_GRADE) {
            return Result.fail("Grade cannot be negative.");
        }
        if (value > GradingConfig.MAX_VALID_GRADE) {
            return Result.fail("Grade must be within the allowed range ("
                    + (int) GradingConfig.MIN_VALID_GRADE + "-"
                    + (int) GradingConfig.MAX_VALID_GRADE + ").");
        }
        return Result.ok();
    }

    /** Validates that at least one grade has been entered before calculating anything. */
    public static Result validateGradeList(List<Double> grades) {
        if (grades == null || grades.isEmpty()) {
            return Result.fail("At least one grade must be entered.");
        }
        return Result.ok();
    }
}
