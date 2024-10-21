package controller.command;

import java.io.IOException;

import controller.ImageOperationsCommand;
import model.ImageEnhancement;

/**
 * This class defines the operations to be performed for implementing command design pattern
 * for brighten component.
 */
public class Brighten implements ImageOperationsCommand {

  private final String token1;
  private final String token2;
  private final String token3;

  /**
   * Constructs a new Brighten object with the specified tokens.
   *
   * @param token1 the first token to be stored in this Brighten object
   * @param token2 the second token to be stored in this Brighten object
   * @param token3 the second token to be stored in this Brighten object
   */
  public Brighten(String token1, String token2, String token3) {
    this.token1 = token1;
    this.token2 = token2;
    this.token3 = token3;
  }

  @Override
  public void executeCommand(ImageEnhancement m) throws IOException {
    m.brighten(Integer.parseInt(token1), token2, token3);
  }
}
