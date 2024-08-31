package functions;

import commands.AppBotCommand;

import java.util.Random;

public class FilterOperation {
    @AppBotCommand(name = "/greyScale", description = "greyScale filter", showInKeyboard = true)
    public static float[] greyScale(float[] rgb) {
        final float mean = (rgb[0] + rgb[1] + rgb[2]) / 3;
        rgb[0] = mean;
        rgb[1] = mean;
        rgb[2] = mean;
        return rgb;
    }

    @AppBotCommand(name = "/onlyRed", description = "onlyRed filter", showInKeyboard = true)
    public static float[] onlyRed(float[] rgb) {
        rgb[1] = 0;
        rgb[2] = 0;
        return rgb;
    }

    @AppBotCommand(name = "/onlyGreen", description = "onlyGreen filter", showInKeyboard = true)
    public static float[] onlyGreen(float[] rgb) {
        rgb[0] = 0;
        rgb[2] = 0;
        return rgb;
    }

    @AppBotCommand(name = "/onlyBlue", description = "onlyBlue filter", showInKeyboard = true)
    public static float[] onlyBlue(float[] rgb) {
        rgb[0] = 0;
        rgb[1] = 0;
        return rgb;
    }

    @AppBotCommand(name = "/sepia", description = "sepia filter", showInKeyboard = true)
    public static float[] sepia(float[] rgb) {
        Random random = new Random();
        final float randomValue = random.nextFloat() * 50 / 255;

        rgb[0] += randomValue;
        rgb[1] += randomValue;
        rgb[2] += randomValue;

        for (int i = 0; i < rgb.length; i++) {
            if (rgb[i] > 1) {
                rgb[i] = 1;
            }
        }
        return rgb;
    }
}