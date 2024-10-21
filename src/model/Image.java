package model;

import java.io.IOException;
import java.util.List;

/**
 * The `Image` class represents an image with a given file name, width, height, maximum pixel value,
 * and a list of pixels.
 */
public class Image implements IImage {

  /**
   * The file name of the image.
   */
  private final String fileName;

  /**
   * The width of the image in pixels.
   */
  private final int width;

  /**
   * The height of the image in pixels.
   */
  private final int height;

  /**
   * The maximum pixel value of the image.
   */
  private final int maxValue;

  /**
   * The list of pixels that make up the image.
   */
  private final List<IPixel> imageData;

  /**
   * Creates a new `Image` object with the given file name, width, height, maximum pixel value,
   * and list of pixels.
   *
   * @param fileName The file name of the image.
   * @param w        The width of the image in pixels.
   * @param h        The height of the image in pixels.
   * @param mValue   The maximum pixel value of the image.
   * @param p        The list of pixels that make up the image.
   * @throws IOException if there is an I/O error while creating the `Image` object.
   */
  public Image(String fileName, int w, int h, int mValue, List<IPixel> p) throws IOException {
    this.fileName = fileName;
    width = w;
    height = h;
    maxValue = mValue;
    imageData = p;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getMaxValue() {
    return maxValue;
  }

  @Override
  public List<IPixel> getImageData() {
    return imageData;
  }

  /**
   * Checks if this `Image` object is equal to another object.
   *
   * @param o The object to compare with this `Image` object.
   * @return `true` if the objects are equal, `false` otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Image)) {
      return false;
    }
    Image image = (Image) o;
    if (width != image.width) {
      return false;
    }
    if (height != image.height) {
      return false;
    }
    if (maxValue != image.maxValue) {
      return false;
    }
    if (!fileName.equals(image.fileName)) {
      return false;
    }
    return imageData.equals(image.imageData);
  }

  /**
   * Calculate hashcode for image class attributes.
   *
   * @return hashcode value.
   */
  @Override
  public int hashCode() {
    int result = 17; // start with a prime number
    result = 31 * result + fileName.hashCode();
    result = 31 * result + imageData.hashCode();
    result = 31 * result + width;
    result = 31 * result + height;
    result = 31 * result + maxValue;
    return result;
  }

}
