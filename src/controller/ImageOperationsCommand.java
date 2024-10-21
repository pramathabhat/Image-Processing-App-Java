package controller;

import java.io.IOException;

import model.ImageEnhancement;

/**
 * This interface defines the operations to be performed for implementing command design pattern.
 */
public interface ImageOperationsCommand {
  /** Command design pattern to execute the commands.
   *
   *
   * @param m         object of the image enhancement interface.
   * @throws IOException if an I/O error occur.
   */
  void executeCommand(ImageEnhancement m) throws IOException;
}
