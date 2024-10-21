package controller.command;

import java.io.IOException;

import controller.ImageOperationsCommand;
import model.ImageEnhancement;

/**
 * This class defines the operations to be performed for implementing command design pattern
 * for dither component.
 */
public class Dither implements ImageOperationsCommand {

  private final String token1;
  private final String token2;

  /**
   * Constructs a new Dither object with the specified tokens.
   *
   * @param token1 the first token to be stored in this Dither object
   * @param token2 the second token to be stored in this Dither object
   */
  public Dither(String token1, String token2) {
    this.token1 = token1;
    this.token2 = token2;
  }

  @Override
  public void executeCommand(ImageEnhancement m) throws IOException {
    m.dither(token1, token2);
  }
}
