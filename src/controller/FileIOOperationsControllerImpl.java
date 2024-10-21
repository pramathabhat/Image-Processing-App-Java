package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import model.IImage;
import model.IPixel;
import model.Image;
import model.Pixel;

/**
 * The FileIOOperationsControllerImpl class implements the FileIOOperationsController interface and
 * provides methods to load and save images in PPM format. It reads and writes PPM files.
 **/
public class FileIOOperationsControllerImpl implements FileIOOperationsController {

  private static IImage loadPpmFile(String path, String destFileName) throws IOException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File " + path + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;
    try {
      token = sc.next();
    } catch (NoSuchElementException e) {
      sc.close();
      throw new NoSuchElementException("Invalid PPM file: no P3 identifier found");
    }
    if (!token.equals("P3")) {
      sc.close();
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width;
    int height;
    int maxValue;
    try {
      width = sc.nextInt();
      height = sc.nextInt();
      maxValue = sc.nextInt();
    } catch (NoSuchElementException e) {
      sc.close();
      throw new NoSuchElementException("Invalid PPM file: missing image dimensions or max value");
    }
    List<IPixel> listOfPixels = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        try {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          Pixel p = new Pixel(r, g, b);
          listOfPixels.add(p);
        } catch (NoSuchElementException e) {
          sc.close();
          throw new NoSuchElementException("Invalid PPM file: not enough color components");
        }
      }
    }
    sc.close();
    IImage img = new Image(destFileName, width, height, maxValue, listOfPixels);
    return img;
  }

  private static IImage loadOtherFileFormats(String path, String destFileName) throws IOException {
    File file = new File(path);
    BufferedImage bufferedImage = null;
    try {
      bufferedImage = ImageIO.read(file);
    } catch (IOException e) {
      throw new IOException("Unable to load image from file " + path);
    }
    if (bufferedImage != null) {
      int width = bufferedImage.getWidth();
      int height = bufferedImage.getHeight();
      int maxValue = 255; // for RGB color model
      List<IPixel> listOfPixels = new ArrayList<>();
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int rgb = bufferedImage.getRGB(x, y);
          int r = (rgb >> 16) & 0xFF;
          int g = (rgb >> 8) & 0xFF;
          int b = rgb & 0xFF;
          Pixel p = new Pixel(r, g, b);
          listOfPixels.add(p);
        }
      }
      IImage img = new Image(destFileName, width, height, maxValue, listOfPixels);
      return img;
    } else {
      throw new IOException("Unable to load image from file " + path);
    }
  }

  @Override
  public IImage loadFile(String path, String destFileName) throws IOException {
    IImage img;
    if (!path.endsWith(".ppm")) {
      img = loadOtherFileFormats(path, destFileName);
    } else {
      img = loadPpmFile(path, destFileName);
    }
    return img;
  }

  private static void saveOtherFileFormat(IImage sourceImage, String extension, String path)
          throws IOException {
    BufferedImage image = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < sourceImage.getHeight(); y++) {
      for (int x = 0; x < sourceImage.getWidth(); x++) {
        IPixel pixel = sourceImage.getImageData().get(y * sourceImage.getWidth() + x);
        int rgb = (pixel.getRed() << 16) | (pixel.getGreen() << 8) | pixel.getBlue();
        image.setRGB(x, y, rgb);
      }
    }
    ImageIO.write(image, extension, new File(path));
  }

  private static void savePPM(IImage sourceImage, String path) throws IOException {
    File file = new File(path);
    File parent = file.getParentFile();
    if (parent != null && !parent.exists()) {
      parent.mkdirs();
    }
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
      // Write PPM header
      writer.write("P3\n");
      writer.write(sourceImage.getWidth() + " " + sourceImage.getHeight() + "\n");
      writer.write(sourceImage.getMaxValue() + "\n");

      for (IPixel pixel : sourceImage.getImageData()) {
        writer.write(pixel.getRed() + "\n");
        writer.write(pixel.getGreen() + "\n");
        writer.write(pixel.getBlue() + "\n");
      }
    } catch (IOException e) {
      throw new IOException("Error writing file: " + e.getMessage(), e);
    }
  }

  @Override
  public void saveFile(IImage sourceImage, String path) throws IOException {
    String extension = "";

    int i = path.lastIndexOf('.');
    if (i > 0 && i < path.length() - 1) {
      extension = path.substring(i + 1).toLowerCase();
    }
    if (!extension.equals("ppm")) {
      saveOtherFileFormat(sourceImage, extension, path);

    } else {
      if (sourceImage == null) {
        throw new IllegalArgumentException("Image not found");
      }
      savePPM(sourceImage, path);
    }
  }

}
