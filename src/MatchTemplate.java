import org.opencv.core.*;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;


class MatchTemplate {
//    public void run(String inFile, int match_method) throws Exception {
//        System.out.println("\nRunning Template Matching");
//
//        Mat templ = Imgcodecs.imread(inFile);
//
//        // / Create the result matrix
//        int result_cols = templ.cols() + 1;
//        int result_rows = templ.rows() + 1;
//        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
//
//        Robot robot = new Robot();
//
//        Rectangle screenRect = new Rectangle(200, 200, 300, 300);
//
//        while (true) {
//
//
//            BufferedImage screenImage = robot.createScreenCapture(screenRect);
//            Mat img = bufferedImageToMat(screenImage);
//
//            // / Do the Matching and Normalize
//            Imgproc.matchTemplate(img, templ, result, match_method);
//            Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
//
//            // / Localizing the best match with minMaxLoc
//            MinMaxLocResult mmr = Core.minMaxLoc(result);
//
//            Point matchLoc;
//            if (match_method == Imgproc.TM_SQDIFF
//                    || match_method == Imgproc.TM_SQDIFF_NORMED) {
//                matchLoc = mmr.minLoc;
//            } else {
//                matchLoc = mmr.maxLoc;
//            }
//
//            Graphics2D g2d = (Graphics2D) screenImage.getGraphics();
//            g2d.setColor(Color.RED);
//            g2d.drawRect((int) matchLoc.x, (int)matchLoc.y, templ.cols(), templ.rows());
//            g2d.dispose();
//
//            ScreenCapture.updateScreen(screenImage);
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private static Mat bufferedImageToMat(BufferedImage image) throws Exception {
//        DataBuffer dataBuffer = image.getRaster().getDataBuffer();
//        int dataType = dataBuffer.getDataType();
//        byte[] pixels;
//        if (dataType == DataBuffer.TYPE_BYTE) {
//            pixels = ((DataBufferByte) dataBuffer).getData();
//        } else if (dataType == DataBuffer.TYPE_INT) {
//            int[] intPixels = ((DataBufferInt) dataBuffer).getData();
//            pixels = new byte[intPixels.length * 4];
//            for (int i = 0; i < intPixels.length; i++) {
//                int pixel = intPixels[i];
//                pixels[i * 4] = (byte) ((pixel >> 16) & 0xFF); // blue
//                pixels[i * 4 + 1] = (byte) ((pixel >> 8) & 0xFF); // green
//                pixels[i * 4 + 2] = (byte) (pixel & 0xFF); // red
//                pixels[i * 4 + 3] = (byte) ((pixel >> 24) & 0xFF); // alpha
//            }
//        } else {
//            throw new IllegalArgumentException("Unsupported data buffer type: " + dataType);
//        }
//        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
//        mat.put(0, 0, pixels);
//        return mat;
//    }
public void run(String inFile, String templateFile, String outFile,
                int match_method) {
    //System.out.println("\nRunning Template Matching");

    Mat img = Imgcodecs.imread(inFile);
    Mat templ = Imgcodecs.imread(templateFile);

    // / Create the result matrix
    int result_cols = img.cols() - templ.cols() + 1;
    int result_rows = img.rows() - templ.rows() + 1;
    Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

    // / Do the Matching and Normalize
    Imgproc.matchTemplate(img, templ, result, match_method);
    Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

    // / Localizing the best match with minMaxLoc
    MinMaxLocResult mmr = Core.minMaxLoc(result);

    Point matchLoc;
    if (match_method == Imgproc.TM_SQDIFF
            || match_method == Imgproc.TM_SQDIFF_NORMED) {
        matchLoc = mmr.minLoc;
    } else {
        matchLoc = mmr.maxLoc;
    }

    // / Show me what you got
    Imgproc.rectangle(img, matchLoc, new Point(matchLoc.x + templ.cols(), matchLoc.y + templ.rows()), new Scalar(0, 255, 0));

    // Save the visualized detection.
    //System.out.println("Writing " + outFile);
    Imgcodecs.imwrite(outFile, img);

    }
}

