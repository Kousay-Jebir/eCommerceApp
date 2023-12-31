package features.utilityClasses;

import java.util.Random;

public class CustomRandomGenerator {

    public static float generateNumber(float[] numbers, float[] probabilities) {
        if (numbers.length != probabilities.length) {
            throw new IllegalArgumentException("Arrays must have the same length");
        }

        float[] cumulativeProbabilities = new float[probabilities.length];
        cumulativeProbabilities[0] = probabilities[0];

        for (int i = 1; i < probabilities.length; i++) {
            cumulativeProbabilities[i] = cumulativeProbabilities[i - 1] + probabilities[i];
        }

        Random random = new Random();
        float randomValue = random.nextFloat(); // Generates a random value between 0 (inclusive) and 1 (exclusive)

        for (int i = 0; i < cumulativeProbabilities.length; i++) {
            if (randomValue < cumulativeProbabilities[i]) {
                return numbers[i];
            }
        }

        // This point should not be reached, but if it does, return the last number
        return numbers[numbers.length - 1];
    }
}
