import model.IImage;
import model.ImageEnhancement;

/**
 * This is the mock model used to test the controller in isolation.
 */
public class MockModel implements ImageEnhancement {
  StringBuilder logger;
  IImage img;

  MockModel(StringBuilder logger) {
    this.logger = logger;
  }

  @Override
  public void loadFile(String imageName, IImage sourceImage) {
    this.logger.append("load " + imageName);
  }

  @Override
  public void visualisingComponent(String componentName, String sourceImage,
                                   String destinationImageName) {
    this.logger.append("visualisingComponent for " + componentName + " " + destinationImageName);
  }

  @Override
  public void horizontalFlip(String sourceImage, String destinationImageName) {
    this.logger.append("horizontal flip image to ").append(destinationImageName);
  }

  @Override
  public void verticalFlip(String sourceImage, String destinationImageName) {
    this.logger.append("vertical flip image to ").append(destinationImageName);
  }

  @Override
  public void brighten(int value, String sourceImage, String destinationImageName) {
    this.logger.append("brightened image to ").append(destinationImageName);
  }

  @Override
  public void rgbSplit(String sourceImage, String destRedImage,
                       String destGreenImage, String destBlueImage) {
    this.logger.append("RGB split of the image: ").append(destRedImage + " " + destGreenImage
            + " " + destBlueImage);
  }

  @Override
  public void combineImages(String destinationImageName, String redImage,
                            String greenImage, String blueImage) {
    this.logger.append("Combine images: ").append(destinationImageName);
  }

  @Override
  public IImage getHashMapData(String imgName) {
    return img;
  }

  @Override
  public void blur(String sourceImage, String destinationImageName) {
    this.logger.append("blur " + destinationImageName);
  }

  @Override
  public void sharpen(String sourceImage, String destinationImageName) {
    this.logger.append("sharpen " + destinationImageName);
  }

  @Override
  public void greyscale(String sourceImage, String destinationImageName) {
    this.logger.append("greyscale-transform " + destinationImageName);
  }

  @Override
  public void dither(String sourceImage, String destinationImageName) {
    this.logger.append("dither " + destinationImageName);
  }


  @Override
  public void sepia(String sourceImage, String destinationImageName) {
    this.logger.append("sepia " + destinationImageName);
  }
}
