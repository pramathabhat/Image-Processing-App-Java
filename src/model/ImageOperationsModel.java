package model;

import java.io.IOException;

/**
 * The ImageOperationsModel interface represents a model that can perform various
 * operations on images.
 */
public interface ImageOperationsModel {
  /**
   * Load a File into HashMap.
   *
   * @param imageName   name of the file to be loaded.
   * @param sourceImage Image object to be loaded.
   */
  void loadFile(String imageName, IImage sourceImage);

  /**
   * Visualizes a particular component of an image and saves it as a new image.
   *
   * @param componentName        The name of the component to visualize (e.g. "red",
   *                             "green", "blue").
   * @param sourceImage          The source image to visualize the component of.
   * @param destinationImageName The name to give to the new image that will be created.
   * @throws IOException If there is an error creating or saving the new image.
   */
  void visualisingComponent(String componentName, String sourceImage,
                            String destinationImageName) throws IOException;

  /**
   * Flips the given image horizontally and saves it as a new image.
   *
   * @param sourceImage          The source image to flip.
   * @param destinationImageName The name to give to the new image that will be created.
   * @throws IOException If there is an error creating or saving the new image.
   */
  void horizontalFlip(String sourceImage, String destinationImageName)
          throws IOException;

  /**
   * Flips the given image vertically and saves it as a new image.
   *
   * @param sourceImage          The source image to flip.
   * @param destinationImageName The name to give to the new image that will be created.
   * @throws IOException If there is an error creating or saving the new image.
   */
  void verticalFlip(String sourceImage, String destinationImageName)
          throws IOException;

  /**
   * Brightens the given image by the specified value and saves it as a new image.
   *
   * @param value                The amount to increase the brightness of the image by.
   * @param sourceImage          The source image to brighten.
   * @param destinationImageName The name to give to the new image that will be created.
   * @throws IOException If there is an error creating or saving the new image.
   */
  void brighten(int value, String sourceImage, String destinationImageName)
          throws IOException;

  /**
   * Splits an RGB image into its three color channels and saves them as separate images.
   *
   * @param sourceImage    the original RGB image to split
   * @param destRedImage   the filename to use when saving the red color channel image
   * @param destGreenImage the filename to use when saving the green color channel image
   * @param destBlueImage  the filename to use when saving the blue color channel image
   * @throws IOException if there is an error reading or writing the image files
   */
  void rgbSplit(String sourceImage, String destRedImage, String destGreenImage,
                String destBlueImage) throws IOException;

  /**
   * Combines three color channels into an RGB image and saves it with the specified filename.
   *
   * @param destinationImageName the filename to use when saving the RGB image.
   * @param redImage             the image containing the red color channel.
   * @param greenImage           the image containing the green color channel.
   * @param blueImage            the image containing the blue color channel.
   * @throws IOException if there is an error reading or writing the image files.
   */
  void combineImages(String destinationImageName, String redImage, String greenImage,
                     String blueImage) throws IOException;

  /**
   * Get data from namesOfImages Hashmap.
   *
   * @param imgName Key of the Hashmap.
   * @return Hashmap value for the key.
   */
  IImage getHashMapData(String imgName);
}
