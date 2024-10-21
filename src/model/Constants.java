package model;

/**
 * This class contains constant values for image processing kernels.
 */
public class Constants {

  /**
   * A constant 3x3 kernel used for blurring an image.
   */
  public static final double[][] BLUR_KERNEL = {
          {1.0 / 16.0, 2.0 / 16.0, 1.0 / 16.0},
          {2.0 / 16.0, 4.0 / 16.0, 2.0 / 16.0},
          {1.0 / 16.0, 2.0 / 16.0, 1.0 / 16.0}
  };
  /**
   * A constant 5x5 kernel used for sharpening an image.
   */
  public static final double[][] SHARPEN_KERNEL = {
          {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0},
          {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
          {-1.0 / 8.0, 1.0 / 4.0, 1, 1.0 / 4.0, -1.0 / 8.0},
          {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
          {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0}
  };
}

