package view;

/**
 * The ImageView class is responsible for displaying a command prompt and receiving user input
 * for executing image operations. It creates an instance of ImageOperationsControllerImpl and
 * ImageOperationsModelImpl to interact with the model and execute the user's requested commands.
 */
public class ImageView implements IImageView {

  /**
   * Constructs an ImageView object and initializes the controller with an instance of
   * ImageOperationsControllerImpl and ImageOperationsModelImpl.
   */
  public ImageView() {
    // constructor initialization
  }

  @Override
  public void start(String output) {
    System.out.println(output);
  }
}
