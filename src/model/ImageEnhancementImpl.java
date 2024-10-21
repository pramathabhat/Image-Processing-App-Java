package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * This class implements the ImageEnhancement interface and extends the
 * ImageOperationsModelImpl class.
 * It provides methods for enhancing images, such as adjusting brightness, contrast, and sharpness.
 */
public class ImageEnhancementImpl extends ImageOperationsModelImpl implements ImageEnhancement {

  /**
   * Constructor to initialize parent class.
   */
  public ImageEnhancementImpl() {
    super();
  }

  private static int clamp(int value, int min, int max) {
    return Math.min(Math.max(value, min), max);
  }

  private void filter(double[][] kernel, String sourceImageKey, String destinationImageName)
          throws IOException {
    IImage sourceImage = namesOfImages.get(sourceImageKey);
    checkIfHashMapHasKey(sourceImage);

    List<IPixel> sourceData = sourceImage.getImageData();
    List<IPixel> filteredData = new ArrayList<>();

    for (int y = 1; y < sourceImage.getHeight() - 1; y++) {
      for (int x = 1; x < sourceImage.getWidth() - 1; x++) {
        double redSum = 0;
        double greenSum = 0;
        double blueSum = 0;

        for (int ky = -1; ky <= 1
                ; ky++) {
          for (int kx = -1; kx <= 1; kx++) {
            int index = (y + ky) * sourceImage.getWidth() + (x + kx);
            IPixel p = sourceData.get(index);
            double kernelValue = kernel[ky + 1][kx + 1];

            redSum += p.getRed() * kernelValue;
            greenSum += p.getGreen() * kernelValue;
            blueSum += p.getBlue() * kernelValue;
          }
        }

        int red = (int) Math.round(redSum);
        int green = (int) Math.round(greenSum);
        int blue = (int) Math.round(blueSum);
        red = clamp(red, 0, 255);
        green = clamp(green, 0, 255);
        blue = clamp(blue, 0, 255);

        Pixel filteredPixel = new Pixel(red, green, blue);
        filteredData.add(filteredPixel);
      }
    }

    Image filteredImage = new Image(destinationImageName, sourceImage.getWidth() - 2,
            sourceImage.getHeight() - 2, sourceImage.getMaxValue(), filteredData);
    namesOfImages.put(destinationImageName, filteredImage);
  }

  @Override
  public void blur(String sourceImageKey, String destinationImageName) throws IOException {
    double[][] kernel = Constants.BLUR_KERNEL;
    filter(kernel, sourceImageKey, destinationImageName);
  }

  @Override
  public void sharpen(String sourceImageKey, String destinationImageName) throws IOException {
    double[][] kernel = Constants.SHARPEN_KERNEL;
    filter(kernel, sourceImageKey, destinationImageName);
  }

  private Image createTransformedImage(String sourceImageKey, String destinationImageName,
                                       Function<IPixel, IPixel> transform) throws IOException {
    IImage sourceImage = namesOfImages.get(sourceImageKey);
    checkIfHashMapHasKey(sourceImage);

    List<IPixel> sourceData = sourceImage.getImageData();
    List<IPixel> transformedData = new ArrayList<>();

    for (IPixel p : sourceData) {
      IPixel transformedPixel = transform.apply(p);
      transformedData.add(transformedPixel);
    }

    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();
    int maxValue = sourceImage.getMaxValue();
    return new Image(destinationImageName, width, height, maxValue, transformedData);
  }

  @Override
  public void greyscale(String sourceImageKey, String destinationImageName) throws IOException {
    Function<IPixel, IPixel> transform = p -> {
      int grey = (int) (0.2126 * p.getRed() + 0.7152 * p.getGreen() + 0.0722 * p.getBlue());
      grey = clamp(grey, 0, 255);
      return new Pixel(grey, grey, grey);
    };

    Image transformedImage = createTransformedImage(sourceImageKey,
            destinationImageName, transform);
    namesOfImages.put(destinationImageName, transformedImage);
  }

  @Override
  public void sepia(String sourceImageKey, String destinationImageName) throws IOException {
    Function<IPixel, IPixel> transform = p -> {
      int sepiaRed = (int) (0.393 * p.getRed() + 0.769 * p.getGreen() + 0.189 * p.getBlue());
      int sepiaGreen = (int) (0.349 * p.getRed() + 0.686 * p.getGreen() + 0.168 * p.getBlue());
      int sepiaBlue = (int) (0.272 * p.getRed() + 0.534 * p.getGreen() + 0.131 * p.getBlue());

      sepiaRed = clamp(sepiaRed, 0, 255);
      sepiaGreen = clamp(sepiaGreen, 0, 255);
      sepiaBlue = clamp(sepiaBlue, 0, 255);

      return new Pixel(sepiaRed, sepiaGreen, sepiaBlue);
    };

    Image transformedImage = createTransformedImage(sourceImageKey,
            destinationImageName, transform);
    namesOfImages.put(destinationImageName, transformedImage);
  }

  @Override
  public void dither(String sourceImageKey, String destinationImageName) throws IOException {
    IImage sourceImage = namesOfImages.get(sourceImageKey);
    checkIfHashMapHasKey(sourceImage);

    greyscale(sourceImageKey, "greyscale-" + destinationImageName);
    IImage greyscaledImage = namesOfImages.get("greyscale-" + destinationImageName);
    List<IPixel> transformedData = greyscaledImage.getImageData();

    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();
    int maxVal = sourceImage.getMaxValue();

    int[][] pixelMatrix = convertPixelListToMatrix(transformedData, width, height);

    int[][] newPixelMatrix = applyDithering(pixelMatrix, width, height, maxVal);

    List<IPixel> ditheredData = convertMatrixToPixelList(newPixelMatrix, width, height);

    Image outputImage = new Image(destinationImageName, width, height, 255, ditheredData);
    namesOfImages.put(destinationImageName, outputImage);
  }

  private int[][] convertPixelListToMatrix(List<IPixel> pixelList, int width, int height) {
    int[][] pixelMatrix = new int[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixelMatrix[i][j] = pixelList.get(i * width + j).getRed();
      }
    }

    return pixelMatrix;
  }

  private int[][] applyDithering(int[][] pixelMatrix, int width, int height, int maxVal) {
    int[][] newPixelMatrix = new int[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int oldColor = pixelMatrix[i][j];
        int newColor = Math.round((float) oldColor / maxVal) * maxVal;
        int error = oldColor - newColor;
        newPixelMatrix[i][j] = newColor;

        if (j < width - 1) {
          pixelMatrix[i][j + 1] += Math.round(error * 7.0 / 16.0);
        }

        if (i < height - 1 && j > 0) {
          pixelMatrix[i + 1][j - 1] += Math.round(error * 3.0 / 16.0);
        }

        if (i < height - 1) {
          pixelMatrix[i + 1][j] += Math.round(error * 5.0 / 16.0);
        }

        if (i < height - 1 && j < width - 1) {
          pixelMatrix[i + 1][j + 1] += Math.round(error * 1.0 / 16.0);
        }
      }
    }

    return newPixelMatrix;
  }

  private List<IPixel> convertMatrixToPixelList(int[][] pixelMatrix, int width, int height) {
    List<IPixel> pixelList = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int color = pixelMatrix[i][j];
        Pixel pixel = new Pixel(color, color, color);
        pixelList.add(pixel);
      }
    }

    return pixelList;
  }

}