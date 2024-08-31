package utils;

import functions.ImageOperation;
import org.telegram.telegrambots.meta.api.objects.File;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class PhotoMessageUtils {
    public static List<String> savePhotos(List<File> files, String botToken) throws IOException {
        Random random = new Random();
        List<String> paths = new ArrayList<>();

        for (File file : files) {
            final String imageUrl = "https://api.telegram.org/file/bot" + botToken + '/' + file.getFilePath();
            final String localFileName = "src/main/resources/" + new Date().getTime() + random.nextLong() + ".jpeg";
            saveImage(imageUrl, localFileName);
            paths.add(localFileName);
        }
        return paths;
    }

    public static void saveImage(String url, String fileName) throws IOException {
        URL urlModel = new URL(url);
        InputStream inputStream = urlModel.openStream();
        OutputStream outputStream = new FileOutputStream(fileName);
        byte[] b = new byte[2048];
        int length;

        while ((length = inputStream.read(b)) != -1) {
            outputStream.write(b, 0, length);
        }

        inputStream.close();
        outputStream.close();
    }

    public static void processingImage(String fileName, ImageOperation operation) throws Exception {
        final BufferedImage image = ImageUtils.getImage(fileName);
        final RgbManager rgbManager = new RgbManager(image);

        rgbManager.changeImage(operation);
        ImageUtils.saveImage(image, fileName);
    }
}