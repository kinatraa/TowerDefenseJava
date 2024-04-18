package helpz;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImgFix {
    //Rotate
    public static BufferedImage getRotImg(BufferedImage img, int rotAngle) {
        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage newImg = new BufferedImage(w, h, img.getType());
        Graphics2D g2d = newImg.createGraphics();

        g2d.rotate(Math.toRadians(rotAngle), w / 2, h / 2);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return newImg;
    }

    //Image build
    public static BufferedImage buildImg(BufferedImage[] imgs) {
        int w = imgs[0].getWidth();
        int h = imgs[0].getHeight();

        BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for (BufferedImage img : imgs) {
            g2d.drawImage(img, 0, 0, null);
        }
        g2d.dispose();
        return newImg;
    }
}
