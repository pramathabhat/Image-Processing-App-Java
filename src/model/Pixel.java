package model;

import java.util.Objects;

/**
 * The Pixel class represents a single pixel in an image, with red, green, and
 * blue color components.
 */
public class Pixel implements IPixel {
  /**
   The red color component of the pixel.
   */
  private final int red;

  /**
   * The green color component of the pixel.
   */
  private final int green;

  /**
   * The blue color component of the pixel.
   */
  private final int blue;

  /**
   * Constructs a new Pixel object with the given red, green, and blue color components.
   *
   * @param r The red color component.
   * @param g The green color component.
   * @param b The blue color component.
   */
  public Pixel(int r, int g, int b) {
    red = r;
    green = g;
    blue = b;
  }

  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }

  /**
   * Checks whether this Pixel object is equal to another object.
   *
   * @param o The object to compare to.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Pixel)) {
      return false;
    }
    Pixel pixel = (Pixel) o;
    if (red != pixel.red) {
      return false;
    }
    if (green != pixel.green) {
      return false;
    }
    return blue == pixel.blue;
  }

  /**
   * Calculates hashcode for the class.
   *
   * @return hashcode value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue);
  }

}