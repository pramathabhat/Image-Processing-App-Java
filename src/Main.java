import java.io.IOException;

import controller.GUIController;
import controller.GUIControllerImpl;
import controller.ImageOperationsController;
import controller.ImageOperationsControllerImpl;
import model.ImageEnhancementImpl;
import view.IImageView;
import view.IGUIFeatures;
import view.ImageView;
import view.GUIFeaturesFrame;

/**
 * Main method where the execution of the program starts. In this class, we call the view class.
 **/
public class Main {

  /**
   * Main method where the program execution begins.
   **/
  public static void main(String[] args) throws IOException {
    IImageView view = new ImageView();
    IGUIFeatures guiView = new GUIFeaturesFrame();
    ImageOperationsController controller = new ImageOperationsControllerImpl(
            new ImageEnhancementImpl(), view);
    GUIController guiController = new GUIControllerImpl(
            new ImageEnhancementImpl(), guiView);
    if (args.length == 0) {
      guiController.goGUI();
    } else if (args.length == 1 && args[0].equals("-text")) {
      controller.controllerGo();
    } else if (args.length == 2 && args[0].equals("-file")) {
      String filePath = args[1];
      controller.runScriptFile(filePath);
    } else {
      System.err.println("Invalid command-line arguments");
      System.exit(1);
    }
  }
}
