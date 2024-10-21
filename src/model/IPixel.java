package model;

/**
 * This interface provides methods to retrieve information about the pixel such as its
 * red, green, and blue color values.
 */
public interface IPixel {
  /**
   * Gets the red color component of the pixel.
   *
   * @return The red color component.
   */
  int getRed();

  /**
   * Gets the green color component of the pixel.
   *
   * @return The green color component.
   */
  int getGreen();

  /**
   * Gets the blue color component of the pixel.
   *
   * @return The blue color component.
   */
  int getBlue();
}
