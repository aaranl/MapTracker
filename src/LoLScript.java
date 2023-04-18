import org.opencv.core.Core;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import static java.lang.Thread.sleep;

public class LoLScript {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public static void main(String[] args) throws Exception {
        try {
            ScreenCapture.initScreen();
            ScreenCapture.initScreen2();

            String inFile = "champions/Ezreal_OriginalCircle.png";
            String template = "screenshot.png";
            String outFile = "cool.png";
            Rectangle trackingArea = new Rectangle(1500, 650, 450, 450);
            Robot robot = new Robot();

            while (true) {
                inFile = "champions/" + ScreenCapture.getSelectedValue()+ "_OriginalCircle.png";
                BufferedImage screenCapture = robot.createScreenCapture(trackingArea);
                ImageIO.write(screenCapture, "png", new File("screenshot.png"));
                new MatchTemplate().run(template, inFile, outFile, Imgproc.TM_CCOEFF);
                BufferedImage image = ImageIO.read(new File(outFile));
                ScreenCapture.updateScreen(image);
                sleep(1);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}