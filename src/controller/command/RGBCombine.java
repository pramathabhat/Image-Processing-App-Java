package controller.command;

import java.io.IOException;

import controller.ImageOperationsCommand;
import model.ImageEnhancement;

/**
 * This class defines the operations to be performed for implementing command design pattern
 * for rgb combine component.
 */
public class RGBCombine implements ImageOperationsCommand {

  private final String token1;
  private final String token2;
  private final String token3;
  private final String token4;

  /**
   * Constructs a new RGBCombine object with the specified tokens.
   *
   * @param token1 the first token to be stored in this Value object
   * @param token2 the second token to be stored in this Value object
   * @param token3 the third token to be stored in this Value object
   * @param token4 the fourth token to be stored in this Value object
   */
  public RGBCombine(String token1, String token2, String token3, String token4) {
    this.token1 = token1;
    this.token2 = token2;
    this.token3 = token3;
    this.token4 = token4;
  }

  @Override
  public void executeCommand(ImageEnhancement m) throws IOException {
    m.combineImages(token1, token2, token3, token4);
  }
}
