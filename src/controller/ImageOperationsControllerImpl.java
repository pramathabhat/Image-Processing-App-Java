package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.command.BlueComponent;
import controller.command.Blur;
import controller.command.Brighten;
import controller.command.Dither;
import controller.command.GreenComponent;
import controller.command.GreyscaleTransform;
import controller.command.HorizontalFlip;
import controller.command.Intensity;
import controller.command.LoadFile;
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
import view.IImageView;

/**
 * Implementation of the ImageOperationsController interface that provides methods to perform
 * various image operations. This class uses an ImageOperationsModel instance to execute image
 * operations and maintains a Map of image names to Image objects.
 */

public class ImageOperationsControllerImpl implements ImageOperationsController {


  private final ImageEnhancement imageOperations;
  private final IImageView view;

  /**
   * Constructs an ImageOperationsControllerImpl object with the specified model and view.
   *
   * @param model the ImageEnhancement model used by the controller
   * @param view  the IImageView view used by the controller
   */
  public ImageOperationsControllerImpl(ImageEnhancement model, IImageView view) {
    this.imageOperations = model;
    this.view = view;
  }

  private void validateArguments(String[] tokens, int minArgs) {
    if (tokens.length < minArgs) {
      throw new IllegalArgumentException("Invalid argument set");
    }
  }


  @Override
  public void controllerGo() {
    Scanner scanner = new Scanner(System.in);
    boolean isRunning = true;
    while (isRunning) {
      System.out.print("Enter a command: ");
      String input = scanner.nextLine();
      if (input.equalsIgnoreCase("exit")) {
        isRunning = false;
        continue;
      }
      try {
        controllerCommandCall(input);
        String[] tokens = input.split(" ");
        view.start(tokens[0] + ": Input operation is successful");
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }

  @Override
  public void controllerCommandCall(String input) throws IOException {
    try {
      IImage outputImage;
      ImageOperationsCommand command;
      String[] tokens = input.split(" ");
      if (tokens[0].equals("quit")) {
        System.exit(0);
      }
      if (tokens.length < 2) {
        throw new IllegalArgumentException("Not enough arguments");
      }
      switch (tokens[0]) {
        case "load":
          validateArguments(tokens, 3);
          FileIOOperationsController fileInput = new FileIOOperationsControllerImpl();
          outputImage = fileInput.loadFile(tokens[1], tokens[2]);
          command = new LoadFile(tokens[2], outputImage);
          command.executeCommand(imageOperations);
          break;
        case "save":
          validateArguments(tokens, 3);
          FileIOOperationsController fileOutput = new FileIOOperationsControllerImpl();
          fileOutput.saveFile(imageOperations.getHashMapData(tokens[2]), tokens[1]);
          break;
        case "greyscale":
          validateArguments(tokens, 4);
          command = getGreyscaleTransformCommand(tokens[1], tokens[2], tokens[3]);
          command.executeCommand(imageOperations);
          break;
        case "horizontal-flip":
          validateArguments(tokens, 3);
          command = new HorizontalFlip(tokens[1], tokens[2]);
          command.executeCommand(imageOperations);
          break;
        case "vertical-flip":
          validateArguments(tokens, 3);
          command = new VerticalFlip(tokens[1], tokens[2]);
          command.executeCommand(imageOperations);
          break;
        case "brighten":
          validateArguments(tokens, 4);
          command = new Brighten(tokens[1], tokens[2], tokens[3]);
          command.executeCommand(imageOperations);
          break;
        case "rgb-split":
          validateArguments(tokens, 5);
          command = new RGBSplit(tokens[1], tokens[2], tokens[3], tokens[4]);
          command.executeCommand(imageOperations);
          break;
        case "rgb-combine":
          validateArguments(tokens, 5);
          command = new RGBCombine(tokens[1], tokens[2], tokens[3], tokens[4]);
          command.executeCommand(imageOperations);
          break;
        case "run":
          validateArguments(tokens, 2);
          runScriptFile(tokens[1]);
          break;
        case "blur":
          validateArguments(tokens, 3);
          command = new Blur(tokens[1], tokens[2]);
          command.executeCommand(imageOperations);
          break;
        case "sharpen":
          validateArguments(tokens, 3);
          command = new Sharpen(tokens[1], tokens[2]);
          command.executeCommand(imageOperations);
          break;
        case "greyscale-transform":
          validateArguments(tokens, 3);
          command = new GreyscaleTransform(tokens[1], tokens[2]);
          command.executeCommand(imageOperations);
          break;
        case "sepia":
          validateArguments(tokens, 3);
          command = new Sepia(tokens[1], tokens[2]);
          command.executeCommand(imageOperations);
          break;
        case "dither":
          validateArguments(tokens, 3);
          command = new Dither(tokens[1], tokens[2]);
          command.executeCommand(imageOperations);
          break;
        default:
          throw new InputMismatchException("No such command found");
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e);
    } catch (IOException e) {
      throw new IOException(e);
    } catch (InputMismatchException e) {
      throw new InputMismatchException(e.getMessage());
    }
  }

  private ImageOperationsCommand getGreyscaleTransformCommand(
          String component, String inputImageKey, String outputImageKey) {
    switch (component) {
      case "red-component":
        return new RedComponent(inputImageKey, outputImageKey);
      case "green-component":
        return new GreenComponent(inputImageKey, outputImageKey);
      case "blue-component":
        return new BlueComponent(inputImageKey, outputImageKey);
      case "value-component":
        return new Value(inputImageKey, outputImageKey);
      case "luma-component":
        return new Luma(inputImageKey, outputImageKey);
      case "intensity-component":
        return new Intensity(inputImageKey, outputImageKey);
      default:
        throw new InputMismatchException("Invalid greyscale component");
    }
  }

  @Override
  public void runScriptFile(String file) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (!line.isEmpty() && !line.startsWith("#")) {
          controllerCommandCall(line);
        }
      }
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException(e.getMessage());
    } catch (IOException e) {
      throw new IOException(e.getMessage());
    }
  }

}
