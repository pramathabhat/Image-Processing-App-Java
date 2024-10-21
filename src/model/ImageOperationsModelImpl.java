package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ImageOperationsModelImpl class will implement all the methods from the ImageOperationsModel
 * interface and this file performs all the image operations.
 */
public class ImageOperationsModelImpl implements ImageOperationsModel {

  protected final Map<String, IImage> namesOfImages = new HashMap<>();

  protected static void checkIfHashMapHasKey(IImage sourceImg) {
    if (sourceImg == null) {
      throw new IllegalArgumentException("Hashmap does not contain the key");
    }
  }

  private static IImage createComponentChannel(IImage sourceImage, String destinationImageName)
          throws IOException {
    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();
    int maxvalue = sourceImage.getMaxValue();
    return new Image(destinationImageName, width, height, maxvalue,
            new ArrayList<>());
  }

  private static IImage createValueImage(IImage sourceImage, String destinationImageName)
          throws IOException {
    IImage componentChannel = createComponentChannel(sourceImage, destinationImageName);
    List<IPixel> componentPixel = sourceImage.getImageData();
    for (IPixel pixel : componentPixel) {
      int value = Math.max(Math.max(pixel.getRed(), pixel.getGreen()), pixel.getBlue());
      Pixel valuePixel = new Pixel(value, value, value);
      componentChannel.getImageData().add(valuePixel);
    }
    return componentChannel;
  }

  private static IImage createIntensityImage(IImage sourceImage, String destinationImageName)
          throws IOException {
    IImage componentChannel = createComponentChannel(sourceImage, destinationImageName);
    List<IPixel> componentPixel = sourceImage.getImageData();
    for (IPixel pixel : componentPixel) {
      int intensity = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
      Pixel valuePixel = new Pixel(intensity, intensity, intensity);
      componentChannel.getImageData().add(valuePixel);
    }
    return componentChannel;
  }

  private static IImage createLumaImage(IImage sourceImage, String destinationImageName)
          throws IOException {
    IImage componentChannel = createComponentChannel(sourceImage, destinationImageName);
    List<IPixel> componentPixel = sourceImage.getImageData();
    for (IPixel pixel : componentPixel) {
      int luma = (int) (0.2126 * pixel.getRed() + 0.7152 * pixel.getGreen()
              + 0.0722 * pixel.getBlue());
      Pixel valuePixel = new Pixel(luma, luma, luma);
      componentChannel.getImageData().add(valuePixel);
    }
    return componentChannel;
  }

  private static IImage visualiseRGBComponent(IImage sourceImage, String componentName,
                                              String destinationImageName) throws IOException {
    IImage componentChannel = createComponentChannel(sourceImage, destinationImageName);
    Pixel componentPixel = new Pixel(0, 0, 0);
    for (IPixel pixel : sourceImage.getImageData()) {
      if (componentName.equals("red")) {
        int red = pixel.getRed();
        componentPixel = new Pixel(red, red, red);
      } else if (componentName.equals("green")) {
        int green = pixel.getGreen();
        componentPixel = new Pixel(green, green, green);
      } else if (componentName.equals("blue")) {
        int blue = pixel.getBlue();
        componentPixel = new Pixel(blue, blue, blue);
      }
      componentChannel.getImageData().add(componentPixel);
    }
    return componentChannel;
  }

  @Override
  public void loadFile(String imageName, IImage sourceImage) {
    namesOfImages.put(imageName, sourceImage);
  }

  @Override
  public void visualisingComponent(String componentName, String sourceImageKey,
                                   String destinationImageName) throws IOException {
    IImage sourceImage = namesOfImages.get(sourceImageKey);
    checkIfHashMapHasKey(sourceImage);
    IImage visualizedImage;
    switch (componentName) {
      case "red":
      case "green":
      case "blue":
        visualizedImage = visualiseRGBComponent(sourceImage, componentName, destinationImageName);
        break;
      case "value":
        visualizedImage = createValueImage(sourceImage, destinationImageName);
        break;
      case "luma":
        visualizedImage = createLumaImage(sourceImage, destinationImageName);
        break;
      case "intensity":
        visualizedImage = createIntensityImage(sourceImage, destinationImageName);
        break;
      default:
        throw new IllegalArgumentException("Component argument not found");
    }
    namesOfImages.put(destinationImageName, visualizedImage);
  }


  @Override
  public void horizontalFlip(String sourceImageKey, String destinationImageName)
          throws IOException {
    IImage sourceImage = namesOfImages.get(sourceImageKey);
    checkIfHashMapHasKey(sourceImage);
    List<IPixel> sourceData = sourceImage.getImageData();

    List<IPixel> flippedData = new ArrayList<>();
    for (int y = 0; y < sourceImage.getHeight(); y++) {
      for (int x = sourceImage.getWidth() - 1; x >= 0; x--) {
        IPixel p = sourceData.get(y * sourceImage.getWidth() + x);
        flippedData.add(p);
      }
    }
    IImage flippedImage = new Image(destinationImageName, sourceImage.getWidth(),
            sourceImage.getHeight(), sourceImage.getMaxValue(), flippedData);
    namesOfImages.put(destinationImageName, flippedImage);
  }


  @Override
  public void verticalFlip(String sourceImageKey, String destinationImageName)
          throws IOException {
    IImage sourceImage = namesOfImages.get(sourceImageKey);
    checkIfHashMapHasKey(sourceImage);
    List<IPixel> sourceData = sourceImage.getImageData();

    List<IPixel> flippedData = new ArrayList<>();
    for (int y = sourceImage.getHeight() - 1; y >= 0; y--) {
      for (int x = 0; x < sourceImage.getWidth(); x++) {
        IPixel p = sourceData.get(y * sourceImage.getWidth() + x);
        flippedData.add(p);
      }
    }
    IImage flippedImage = new Image(destinationImageName, sourceImage.getWidth(),
            sourceImage.getHeight(), sourceImage.getMaxValue(), flippedData);
    namesOfImages.put(destinationImageName, flippedImage);
  }


  @Override
  public void brighten(int brightness, String sourceImageKey, String destinationImageName)
          throws IOException {
    IImage sourceImage = namesOfImages.get(sourceImageKey);
    checkIfHashMapHasKey(sourceImage);
    List<IPixel> pixels = sourceImage.getImageData();
    List<IPixel> newPixels = new ArrayList<>();

    for (IPixel pixel : pixels) {
      int newRed = Math.min(pixel.getRed() + brightness, 255);
      int newGreen = Math.min(pixel.getGreen() + brightness, 255);
      int newBlue = Math.min(pixel.getBlue() + brightness, 255);

      newPixels.add(new Pixel(newRed, newGreen, newBlue));
    }

    IImage brightenedImage = new Image(destinationImageName, sourceImage.getWidth(),
            sourceImage.getHeight(), sourceImage.getMaxValue(), newPixels);
    namesOfImages.put(destinationImageName, brightenedImage);
  }


  @Override
  public void rgbSplit(String sourceImageKey, String destRedImage, String destGreenImage,
                       String destBlueImage) throws IOException {
    IImage sourceImage = namesOfImages.get(sourceImageKey);
    checkIfHashMapHasKey(sourceImage);
    IImage redChannel = visualiseRGBComponent(sourceImage, "red", destRedImage);
    IImage greenChannel = visualiseRGBComponent(sourceImage, "green", destGreenImage);
    IImage blueChannel = visualiseRGBComponent(sourceImage, "blue", destBlueImage);

    namesOfImages.put(destRedImage, redChannel);
    namesOfImages.put(destGreenImage, greenChannel);
    namesOfImages.put(destBlueImage, blueChannel);
  }

  @Override
  public void combineImages(String destinationImageName, String redImageKey,
                            String greenImageKey, String blueImageKey) throws IOException {
    IImage redImage = namesOfImages.get(redImageKey);
    IImage greenImage = namesOfImages.get(greenImageKey);
    IImage blueImage = namesOfImages.get(blueImageKey);
    checkIfHashMapHasKey(redImage);
    checkIfHashMapHasKey(greenImage);
    checkIfHashMapHasKey(blueImage);
    IImage combinedImage = new Image(destinationImageName,
            redImage.getWidth(), redImage.getHeight(),
            redImage.getMaxValue(), new ArrayList<>());
    int red = 0;
    int blue = 0;
    int green = 0;
    List<IPixel> redPixels = redImage.getImageData();
    List<IPixel> greenPixels = greenImage.getImageData();
    List<IPixel> bluePixels = blueImage.getImageData();
    for (int i = 0; i < redPixels.size(); i++) {
      red = redPixels.get(i).getRed();
      green = greenPixels.get(i).getGreen();
      blue = bluePixels.get(i).getBlue();
      Pixel combinedImagePixel = new Pixel(red, green, blue);
      combinedImage.getImageData().add(combinedImagePixel);
      namesOfImages.put(destinationImageName, combinedImage);
    }
  }

  @Override
  public IImage getHashMapData(String imgName) {
    return namesOfImages.get(imgName);
  }

}
