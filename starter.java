
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class starter {

    public static void main(String[] args) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("cat.jpg"));

            var cropped = Crop(bufferedImage, 10, 10, 1000, 1000);
            var translated = Translate(bufferedImage, 50, 50, false);

            ImageIO.write(translated, "PNG", new File("out.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static BufferedImage Translate(BufferedImage bufferedImage, int i, int j, boolean b) {
        BufferedImage toReturn = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (var y = 0; y < toReturn.getHeight(); y++) {
            for (var x = 0; x < toReturn.getWidth(); x++) {
                int originalX = x - i;
                int originalY = y - j;

                if(originalX < 0 || originalX >=bufferedImage.getWidth() || originalY < 0 || originalY >= bufferedImage.getHeight())
                    continue;

                var pixelInt = bufferedImage.getRGB(originalX, originalY);
                 toReturn.setRGB(x, y, pixelInt);
            }
        }

        return toReturn;
    }

    private static BufferedImage Crop(BufferedImage bufferedImage, int ulx, int uly, int lrx, int lry) {
        var width = lrx - ulx;
        var height = lry - uly;
        var toReturn = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (var h = 0; h < height; h++) {
            for (var w = 0; w < width; w++) {
                var pixelInt = bufferedImage.getRGB(w + ulx, h + uly);
                var pixelColor = new Color(pixelInt);
                toReturn.setRGB(w, h, pixelColor.getRGB());
            }
        }

        return toReturn;

    }
}
