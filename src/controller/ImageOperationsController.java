package controller;

import java.io.IOException;

/**
 * The ImageOperationsController interface defines the methods required to control image operations.
 * It includes methods for executing a controller command and running a script file.
 */
public interface ImageOperationsController {

  /**
   * Executes a controller command based on the input parameter.
   *
   * @param input the input command to be executed.
   * @throws IOException if there is an error executing the command.
   */
  void controllerCommandCall(String input) throws IOException;

  /**
   * Runs a script file that contains a sequence of controller commands.
   *
   * @param file the name of the script file to be executed.
   * @throws IOException if there is an error executing the script file.
   */
  void runScriptFile(String file) throws IOException;

  /**
   * Starting point for controller call with commands.
   */
  void controllerGo();
}