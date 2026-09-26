/**
 * Centralized, configurable grading rules for the Automated Grading System.
 *
 * CONFIGURABLE ASSUMPTION:
 * The exact letter-grade thresholds and the pass/fail cutoff were not
 * specified in the project requirements. The values below are a common,
 * reasonable default (a standard percentage-based scale) and are isolated
 * in this single class so they can be changed without touching any
 * calculation or evaluation logic elsewhere in the application.
 */
public final class GradingConfig {

    private GradingConfig() {
        // Prevent instantiation; this class only holds configuration constants.
    }

    // Valid numeric grade range accepted as input.
    public static final double MIN_VALID_GRADE = 0.0;
    public static final double MAX_VALID_GRADE = 100.0;

    // CONFIGURABLE ASSUMPTION: letter-grade thresholds (inclusive lower bound).
    public static final double THRESHOLD_A = 90.0;
    public static final double THRESHOLD_B = 80.0;
    public static final double THRESHOLD_C = 70.0;
    public static final double THRESHOLD_D = 60.0;
    // Any average below THRESHOLD_D is evaluated as "F".

    // CONFIGURABLE ASSUMPTION: minimum average required to pass.
    public static final double PASS_THRESHOLD = 60.0;
}
