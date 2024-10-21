package model;

import java.util.List;

/**
 An interface for an image.
 This interface provides methods to retrieve information about the image such as its width, height,
 and maximum pixel value, as well as the list of pixels that make up the image.
 */
public interface IImage {
  /**
   * Gets the width of the image in pixels.
   *
   * @return The width of the image.
   */
  int getWidth();

  /**
   * Gets the height of the image in pixels.
   *
   * @return The height of the image.
   */
  int getHeight();

  /**
   * Gets the maximum pixel value of the image.
   *
   * @return The maximum pixel value of the image.
   */
  int getMaxValue();

  /**
   * Gets the list of pixels that make up the image.
   *
   * @return The list of pixels that make up the image.
   */
  List<IPixel> getImageData();
}
