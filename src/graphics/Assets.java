package graphics;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage backgroundImage;

    public static void init() {
        backgroundImage = ImageLoader.loadImage("/background_image.jpg");
    }
}
