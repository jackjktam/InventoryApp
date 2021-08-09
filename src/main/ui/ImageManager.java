package ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager {
    private static final String source = "./resources/splash.png";

    // EFFECTS: returns a BufferedImage from source
    public BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(source));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
