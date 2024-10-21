package controller.command;

import java.io.IOException;

import controller.ImageOperationsCommand;
import model.ImageEnhancement;

/**
 * This class defines the operations to be performed for implementing command design pattern
 * for intensity component.
 */
public class Intensity implements ImageOperationsCommand {

  private final String token1;
  private final String token2;

  /**
   * Constructs a new Intensity object with the specified tokens.
   *
   * @param token1 the first token to be stored in this intensity object
   * @param token2 the second token to be stored in this intensity object
   */
  public Intensity(String token1, String token2) {
    this.token1 = token1;
    this.token2 = token2;
  }

  @Override
  public void executeCommand(ImageEnhancement m) throws IOException {
    m.visualisingComponent("intensity", token1, token2);
  }
}
