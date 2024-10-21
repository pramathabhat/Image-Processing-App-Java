package controller.command;

import java.io.IOException;

import controller.ImageOperationsCommand;
import model.ImageEnhancement;

/**
 * This class defines the operations to be performed for implementing command design pattern
 * for blue component.
 */
public class BlueComponent implements ImageOperationsCommand {

  private final String token1;
  private final String token2;

  /**
   * Constructs a new BlueComponent object with the specified tokens.
   *
   * @param token1 the first token to be stored in this BlueComponent object
   * @param token2 the second token to be stored in this BlueComponent object
   */
  public BlueComponent(String token1, String token2) {
    this.token1 = token1;
    this.token2 = token2;
  }

  @Override
  public void executeCommand(ImageEnhancement m) throws IOException {
    m.visualisingComponent("blue", token1, token2);
  }
}
