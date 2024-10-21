package controller;

import java.io.IOException;

import model.IImage;


/**
 * This interface defines the operations to be performed by a file IO controller for loading and
 * saving image files.
 */
public interface FileIOOperationsController {
  /**
   * Loads an image file from the specified path and saves it with the specified destination
   * file name.
   *
   * @param path         the path of the image file to be loaded.
   * @param destFileName the file name of the destination image file.
   * @return the loaded Image object.
   * @throws IOException if an I/O error occurs while loading the file.
   */
  IImage loadFile(String path, String destFileName) throws IOException;

  /**
   * Saves the specified Image object to the specified path.
   *
   * @param sourceImage the Image object to be saved.
   * @param path        the path to which the Image object is to be saved.
   * @throws IOException if an I/O error occurs while saving the file.
   */
  void saveFile(IImage sourceImage, String path) throws IOException;
}