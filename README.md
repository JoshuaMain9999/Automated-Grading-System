# Automated Grading System

A console-based Java application that automates academic grade calculation:
entering a set of numeric grades produces a final average, a letter grade,
and a pass/fail status — replacing repetitive manual calculation with a
deterministic, repeatable process.

## Overview

The system follows a single, clear workflow:

```
Input -> Validation -> Calculation -> Conditional Evaluation -> Output
```

The user enters one or more numeric grades. Each entry is validated before
it's accepted. Once all grades are collected, the system calculates the
average, evaluates it against the configured grading rules, and displays
the result.

## Features

- Console-based grade entry for any number of grades
- Centralized input validation (empty input, non-numeric input, negative
  values, out-of-range values) with clear, non-technical error messages
- Deterministic calculation: the same inputs always produce the same result
- Letter-grade and pass/fail evaluation based on a single, centrally
  configured set of rules
- Clean separation between input, validation, calculation, evaluation, and
  output — each handled by its own class
- Ability to run multiple grade calculations in one session
- A dependency-free self-test harness covering the calculation, evaluation,
  and validation logic

## Technologies

- Java
- JDK (standard library only — no external frameworks or libraries)

## Project Structure

```
AutomatedGradingSystem/
├── src/
│   ├── Main.java              # Orchestrates the workflow
│   ├── GradeInput.java        # Console input collection
│   ├── Validation.java        # Centralized input validation
│   ├── GradeCalculator.java   # Mathematical calculation (average)
│   ├── GradeEvaluator.java    # Conditional evaluation (letter grade, pass/fail)
│   ├── GradeResult.java       # Holds and displays a completed result
│   ├── GradingConfig.java     # Centralized, configurable grading rules
│   └── GradingSystemTest.java # Self-test harness (no external framework)
└── README.md
```

Each class has a single responsibility, so the grading rules, the
calculation, and the input handling can each be changed independently.

## Grading Logic

**CONFIGURABLE ASSUMPTION:** The project requirements did not specify an
exact grading formula or letter-grade thresholds. Rather than inventing
rules and mixing them into the calculation code, this implementation uses
a common, standard percentage-based scale and isolates every threshold in
`GradingConfig.java`, so the actual rules can be reviewed and changed in
one place without touching any other class.

The final grade is calculated as the **arithmetic mean** of all entered
grades. That average is then evaluated as follows:

| Average Range | Letter Grade |
|---|---|
| 90 – 100 | A |
| 80 – 89.99 | B |
| 70 – 79.99 | C |
| 60 – 69.99 | D |
| Below 60 | F |

**Pass/fail threshold:** an average of 60 or above is a **PASS**; below 60
is a **FAIL**.

If your actual grading rules differ, update the constants in
`GradingConfig.java` — no other file needs to change.

## Input Requirements

- Each grade must be a number (decimals are allowed, e.g., `87.5`).
- Each grade must be within the range **0–100**.
- Empty input, non-numeric input, negative values, and values above 100
  are all rejected with a specific error message, and the user is
  re-prompted rather than allowed to proceed with bad data.

## How to Compile and Run

Requires a JDK (Java 8 or later; developed and written against Java 21
syntax/behavior).

From the project's root directory:

```bash
# Compile
javac -d out src/*.java

# Run the application
java -cp out Main

# Run the self-test harness
java -cp out GradingSystemTest
```

## Example Usage

```
=====================================
   AUTOMATED GRADING SYSTEM
=====================================
How many grades would you like to enter? 3
Enter grade #1 (0-100): 85
Enter grade #2 (0-100): 92
Enter grade #3 (0-100): 78

========== GRADE RESULT ==========
Entered grades: [85.0, 92.0, 78.0]
Final grade (average): 85.00
Letter grade: B
Status: PASS
===================================

Calculate another grade? (y/n): n

Thank you for using the Automated Grading System.
```

## Testing

`GradingSystemTest.java` is a lightweight, dependency-free self-test
harness (no JUnit or other testing framework is used, in line with the
project's "no unnecessary technologies" constraint). It exercises:

- **Calculation logic** — average of multiple grades, average of a single
  grade
- **Evaluation logic** — every letter-grade boundary (just below and
  exactly on each threshold), and the pass/fail boundary
- **Validation logic** — empty input, whitespace-only input, non-numeric
  input, negative values, and out-of-range values are all rejected; valid
  boundary values (0, 100) and valid decimals are accepted

Run it with `java -cp out GradingSystemTest` after compiling. It prints a
`[PASS]`/`[FAIL]` line per case and a final summary count.

> **Note:** This code was written and reviewed for correctness, but it has
> not been compiled or executed in the environment that produced it (no
> JDK compiler was available there). Please compile and run it — including
> the self-test harness — in your own environment before relying on it, and
> report back if anything doesn't behave as documented here.

## Edge Cases Covered by Design and Tests

- Minimum valid grade (0) and maximum valid grade (100)
- Values immediately below, exactly on, and immediately above each
  letter-grade threshold
- The pass/fail boundary (59.9 vs. 60.0)
- Decimal grade values
- Empty and whitespace-only input
- Non-numeric input
- Negative values
- Values above the valid range

## Future Improvements

The following are potential future directions only — **none of them are
implemented** in the current version:

- Persistent storage of student records
- Database integration
- User authentication / teacher and student accounts
- A web-based or graphical interface
- Exportable grade reports
- Weighted (rather than simple-average) grading schemes
- Statistical grade analysis across multiple students

## Known Limitations

- Console-only interface; no graphical or web UI.
- No persistence — results exist only for the duration of the program run.
- Grading is a simple average of equally weighted entries; weighted
  categories (e.g., exams vs. homework) are not implemented.
- The letter-grade and pass/fail thresholds are a configurable assumption,
  not a confirmed institutional grading policy — adjust `GradingConfig.java`
  to match your actual requirements.
