package controller.command;

import java.io.IOException;

import controller.ImageOperationsCommand;
import model.ImageEnhancement;

/**
 * This class defines the operations to be performed for implementing command design pattern
 * for vertical flip component.
 */
public class VerticalFlip implements ImageOperationsCommand {
  private final String token1;
  private final String token2;

  /**
   The VerticalFlip class represents a flip operation that swaps the position of the
   two specified tokens vertically.
   @param token1 the first token to swap
   @param token2 the second token to swap
   */
  public VerticalFlip(String token1, String token2) {
    this.token1 = token1;
    this.token2 = token2;
  }

  @Override
  public void executeCommand(ImageEnhancement m) throws IOException {
    m.verticalFlip(token1, token2);
  }
}
