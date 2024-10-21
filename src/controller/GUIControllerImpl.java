package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.command.BlueComponent;
import controller.command.Blur;
import controller.command.Brighten;
import controller.command.Dither;
import controller.command.GreenComponent;
import controller.command.GreyscaleTransform;
import controller.command.HorizontalFlip;
import controller.command.Luma;
import controller.command.RGBCombine;
import controller.command.RGBSplit;
import controller.command.RedComponent;
import controller.command.Sepia;
import controller.command.Sharpen;
import controller.command.Value;
import controller.command.VerticalFlip;
import model.IImage;
import model.ImageEnhancement;
import view.IGUIFeatures;
import view.GUIFeaturesFrame;

/**
 * GUIControllerImpl class provides a central point of control for the graphical user interface,
 * and facilitates communication between the view and the model to provide a
 * seamless user experience.
 */
public class GUIControllerImpl implements ActionListener, ChangeListener, GUIController {
  private final ImageEnhancement imageOperations;
  private final IGUIFeatures view;

  /**
   * Constructs a new instance of GUIControllerImpl with the specified
   * ImageEnhancement object and IGUIFeatures view.
   *
   * @param imageOperations ImageEnhancement object used to perform image enhancement operations.
   * @param view            the IGUIFeatures view that displays the GUI features to the user.
   */
  public GUIControllerImpl(ImageEnhancement imageOperations, IGUIFeatures view) {
    this.imageOperations = imageOperations;
    this.view = view;
    view.setListener(this);
    view.setChangeListener(this);
  }

  private void loadImage(String destFileName) {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PPM, BMP and PNG Images", "jpg", "ppm", "bmp", "png");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog((Component) view);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      try {
        IImage image = null;
        try {
          FileIOOperationsController fileOp = new FileIOOperationsControllerImpl();
          image = fileOp.loadFile(f.getAbsolutePath(), destFileName);
          imageOperations.loadFile(destFileName, image);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        view.loadGivenImage(image);
      } catch (RuntimeException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private void saveImage(String hashKey) {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog((Component) view);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      String extension = "";
      String fileName = f.getName();
      int i = fileName.lastIndexOf('.');
      if (i > 0) {
        extension = fileName.substring(i + 1);
      }
      if (!extension.equals("ppm") && !extension.equals("bmp") && !extension.equals("jpg")
              && !extension.equals("png")) {
        view.showPopup("Image format is not valid.");
      } else {
        try {
          FileIOOperationsController fileOp = new FileIOOperationsControllerImpl();
          if (imageOperations.getHashMapData(hashKey) == null) {
            view.showPopup("Image cannot be saved at " + f.getAbsolutePath());
          }
          fileOp.saveFile(imageOperations.getHashMapData(hashKey), f.getAbsolutePath());
          view.showPopup("Image saved successfully at " + f.getAbsolutePath());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  /**
   * This method is called when an action event occurs. It is responsible for performing
   * the necessary actions when a button is clicked, a menu item is selected,
   * or other events that trigger an action event.
   *
   * @param actEvent the ActionEvent object that triggered this method
   */
  public void actionPerformed(ActionEvent actEvent) {
    try {
      switch (actEvent.getActionCommand()) {
        case "Open file":
          loadImage("img");
          view.resetComboBox();
          break;
        case "Save file":
          saveImage("img");
          break;
        case "Image Operations":
          handleImageOperations(actEvent);
          break;
        default:
          break;
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void handleImageOperations(ActionEvent actEvent) throws IOException {
    ImageOperationsCommand command;
    if (actEvent.getSource() instanceof JComboBox) {
      JComboBox<String> box = (JComboBox<String>) actEvent.getSource();
      view.setBrightenSliderPanelVisible(false);
      view.setDarkenSliderPanelVisible(false);
      if (imageOperations.getHashMapData("img") == null) {
        view.showPopup("Please load a valid image before performing operations.");
        view.resetComboBox();
        return;
      }
      String selectedItem = (String) box.getSelectedItem();
      if (selectedItem != null) {
        switch (selectedItem) {
          case "Brighten":
            view.setBrightenSliderValue(0);
            view.setBrightenSliderPanelVisible(true);
            command = new Brighten("0", "img", "img");
            command.executeCommand(imageOperations);
            break;
          case "Darken":
            view.setDarkenSliderValue(0);
            view.setDarkenSliderPanelVisible(true);
            command = new Brighten("0", "img", "img");
            command.executeCommand(imageOperations);
            break;
          case "Sharpen":
            command = new Sharpen("img", "img");
            command.executeCommand(imageOperations);
            break;
          case "Blur":
            command = new Blur("img", "img");
            command.executeCommand(imageOperations);
            break;
          case "Dither":
            command = new Dither("img", "img");
            command.executeCommand(imageOperations);
            break;
          case "Greyscale":
            command = new GreyscaleTransform("img", "img");
            command.executeCommand(imageOperations);
            break;
          case "Horizontal Flip":
            command = new HorizontalFlip("img", "img");
            command.executeCommand(imageOperations);
            break;
          case "Vertical Flip":
            command = new VerticalFlip("img", "img");
            command.executeCommand(imageOperations);
            break;
          case "Sepia":
            command = new Sepia("img", "img");
            command.executeCommand(imageOperations);
            break;
          case "RGB Split":
            command = new RGBSplit("img", "greyscale-red", "greyscale-green", "greyscale-blue");
            command.executeCommand(imageOperations);
            view.showPopup("Split Operation performed successfully. Save the images in the "
                 + "following popup.");
            saveImage("greyscale-red");
            saveImage("greyscale-green");
            saveImage("greyscale-blue");
            break;
          case "RGB Combine":
            command = new RGBCombine("img", "greyscale-red", "greyscale-green", "greyscale-blue");
            loadImage("greyscale-red");
            loadImage("greyscale-green");
            loadImage("greyscale-blue");
            if (imageOperations.getHashMapData("greyscale-red") == null
                    || imageOperations.getHashMapData("greyscale-red") == null
                    || imageOperations.getHashMapData("greyscale-red") == null) {
              view.showPopup("Please select three images to combine correctly");
            } else {
              command.executeCommand(imageOperations);
            }
            break;
          case "Greyscale-Intensity":
            imageOperations.visualisingComponent("intensity", "img",
                    "img");
            break;
          case "Greyscale-Luma":
            command = new Luma("img", "img");
            command.executeCommand(imageOperations);
            break;
          case "Greyscale-Value":
            command = new Value("img", "img");
            command.executeCommand(imageOperations);
            break;
          case "Greyscale-Red":
            command = new RedComponent("img", "img");
            command.executeCommand(imageOperations);
            break;
          case "Greyscale-Green":
            command = new GreenComponent("img", "img");
            command.executeCommand(imageOperations);
            break;
          case "Greyscale-Blue":
            command = new BlueComponent("img", "img");
            command.executeCommand(imageOperations);
            break;
          default:
            view.showPopup("Invalid option selected.");
            break;
        }
        IImage image = imageOperations.getHashMapData("img");
        view.loadGivenImage(image);
      }
    }
  }

  /**
   * Invoked when the state of a component has changed.
   *
   * @param e the ChangeEvent object that contains the source of the event and any additional
   *          information
   * @throws NullPointerException if the event object is null
   */
  @Override
  public void stateChanged(ChangeEvent e) {
    ImageOperationsCommand command;
    JSlider source = (JSlider) e.getSource();
    int value = source.getValue();
    try {
      IImage image = null;
      try {
        command = new Brighten(String.valueOf(value), "img", "img");
        command.executeCommand(imageOperations);
        image = imageOperations.getHashMapData("img");
      } catch (IOException e1) {
        throw new RuntimeException(e1);
      }
      view.loadGivenImage(image);
    } catch (RuntimeException e1) {
      throw new RuntimeException(e1);
    }
  }

  @Override
  public void goGUI() {
    GUIFeaturesFrame.setDefaultLookAndFeelDecorated(false);

    ((GUIFeaturesFrame) view).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ((GUIFeaturesFrame) view).setVisible(true);
  }
}

