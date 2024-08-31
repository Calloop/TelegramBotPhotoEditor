package utils;

import functions.ImageOperation;

import java.awt.image.BufferedImage;

public class RgbManager {
    private final BufferedImage image;
    private final int width;
    private final int height;
    private final int[] pixels;

    public RgbManager(BufferedImage image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
    }

    public void changeImage(ImageOperation operation) throws Exception {
        for (int i = 0; i < pixels.length; i++) {
            float[] pixel = ImageUtils.rgbIntToArray(pixels[i]);
            float[] newPixel = operation.execute(pixel);
            pixels[i] = ImageUtils.arrayToRgbInt(newPixel);
        }

        image.setRGB(0, 0, width, height, pixels, 0, width);
    }
}