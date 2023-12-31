package features.promotions;

import features.utilityClasses.CustomRandomGenerator;

public class Discount {
    private static float[] numbersToGenerate = { 0f, 0.15f, 0.20f, 0.50f };
    private static float[] probabilities = { 0.8f, 0.1f, 0.08f, 0.02f };

    public static float[] getNumbersToGenerate() {
        return numbersToGenerate;
    }

    public static float[] getProbabilities() {
        return probabilities;
    }

    public static float tryingToGetDiscount() {
        return CustomRandomGenerator.generateNumber(numbersToGenerate, probabilities);
    }

}
