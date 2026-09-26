import java.util.List;

/**
 * Performs the mathematical grade calculation.
 * Contains no input handling and no conditional evaluation — calculation only,
 * kept separate so the math can be tested and changed independently.
 */
public class GradeCalculator {

    /**
     * Calculates the final grade as the arithmetic mean of the entered grades.
     *
     * @param grades a non-empty list of already-validated grade values
     * @return the calculated average grade
     */
    public double calculateFinalGrade(List<Double> grades) {
        if (grades == null || grades.isEmpty()) {
            throw new IllegalArgumentException("Cannot calculate a grade from an empty list.");
        }

        double sum = 0.0;
        for (double grade : grades) {
            sum += grade;
        }

        return sum / grades.size();
    }
}
