package controller.command;

import controller.ImageOperationsCommand;
import model.IImage;
import model.ImageEnhancement;

/**
 * This class defines the operations to be performed for implementing command design pattern
 * for load file component.
 */
public class LoadFile implements ImageOperationsCommand {

  private final String token1;
  private final IImage token2;

  /**
   * Constructs a new loadfile object with the specified tokens.
   *
   * @param token1 the first token to be stored in this Value object
   * @param token2 the second token to be stored in this Value object
   */
  public LoadFile(String token1, IImage token2) {
    this.token1 = token1;
    this.token2 = token2;
  }

  @Override
  public void executeCommand(ImageEnhancement m) {
    m.loadFile(token1, token2);
  }
}
