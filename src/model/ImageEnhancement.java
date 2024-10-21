package model;

import java.io.IOException;

/**
 * This interface represents a model for image enhancement operations, providing methods to perform
 * operations such as blurring, sharpening, greyscaling, dithering, and sepia toning on an image.
 */
public interface ImageEnhancement extends ImageOperationsModel {
  /**
   * Blurs the input image and saves the result to the specified destination image.
   *
   * @param sourceImage          the path to the input image to be blurred
   * @param destinationImageName the path and name to save the resulting blurred image
   * @throws IOException if an I/O error occurs during the operation
   */
  void blur(String sourceImage, String destinationImageName) throws IOException;

  /**
   * Sharpens the input image and saves the result to the specified destination image.
   *
   * @param sourceImage          the path to the input image to be sharpened
   * @param destinationImageName the path and name to save the resulting sharpened image
   * @throws IOException if an I/O error occurs during the operation
   */
  void sharpen(String sourceImage, String destinationImageName) throws IOException;

  /**
   * Converts the input image to greyscale and saves the result to the specified destination image.
   *
   * @param sourceImage          the path to the input image to be converted to greyscale
   * @param destinationImageName the path and name to save the resulting greyscale image
   * @throws IOException if an I/O error occurs during the operation
   */
  void greyscale(String sourceImage, String destinationImageName) throws IOException;

  /**
   * Applies a dithering effect to the input image and saves the result to the specified
   * destination image.
   *
   * @param sourceImage          the path to the input image to be dithered
   * @param destinationImageName the path and name to save the resulting dithered image
   * @throws IOException if an I/O error occurs during the operation
   */
  void dither(String sourceImage, String destinationImageName) throws IOException;

  /**
   * Applies a sepia toning effect to the input image and saves the result to the specified
   * destination image.
   *
   * @param sourceImage          the path to the input image to be sepia toned
   * @param destinationImageName the path and name to save the resulting sepia toned image
   * @throws IOException if an I/O error occurs during the operation
   */
  void sepia(String sourceImage, String destinationImageName) throws IOException;
}