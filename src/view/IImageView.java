package view;

/**
 * Image view class.
 */
public interface IImageView {
  /**
   * Starts the ImageView program and continuously prompts the user to enter a command until
   * the user enters "exit". The user's input is passed to the controller to execute the command.
   * The output of the command is printed to the console or an error message is displayed if
   * an exception is caught.
   * @param output is the output of the command line operations.
   */
  void start(String output);

}
