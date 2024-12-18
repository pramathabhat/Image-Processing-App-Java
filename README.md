## Design:

We have an MVC design for this assignment and MVC is represented as 3 subpackages namely model, view and controller respectively.

### Model:

**Image Class:** The `Image` class represents an image with a given file name, width, height, maximum pixel value, and a list of pixels. The `Image` class is designed to work with image data, and provides methods to access the attributes and data of an image. The class is immutable, meaning that once an Image object is created, its attributes cannot be changed.

**`Pixel` Class:** This is a Java class named `Pixel` that represents a single pixel in an image, with red, green, and blue color components.

**`ImageOperationsModel` Interface:** This is a Java interface called `ImageOperationsModel`, which represents a model that can perform various operations on images. It contains several methods that take in Image objects and perform specific image manipulation tasks.

**`ImageOperationsModelImpl` Class:** This is a Java class that implements the `ImageOperationsModel` interface, which defines several methods for performing image processing operations.

New classes and interfaces added for this assignment in model:

**`ImageEnhancement` Interface:** This Java interface called `ImageEnhancement` has the new method signatures of the image operations blur, sharpen, sepia, greyscale and dither.

This interface extends the existing interface ImageOperationsModel.

**`ImageEnhancementImpl` Class:** This Java class called `ImageEnhancementImpl` implements the new interface created `ImageEnhancement`.

### Controller:

**`FileIOOperationsController` Interface:** This is an interface for a File IO controller that provides operations to load and save image files.

**`FileIOOperationsControllerImpl` Class:** This is a Java class named `FileIOOperationsControllerImpl` that implements the `FileIOOperationsController` interface. It provides methods for loading and saving images in PPM format.

**`ImageOperationsController` Interface:** The `ImageOperationsController` interface defines the methods required to control image operations. Includes method to analyze the command entered by the user and process it.

**`ImageOperationsControllerImpl` Class:** Implementation of the `ImageOperationsController` interface that provides methods to perform various image operations. This class uses an `ImageOperationsModel` instance to execute image operations and maintains a Map of image names and its respective Image objects.

### View:

**`ImageView` Class:** The `ImageView` class is responsible for displaying the output that is passed by the controller.

### Main:

**Main Class:** This class contains the main method that serves as the starting point of the program execution. It creates an object of the ImageView class and controller object and calls the controller method.

## Working

The main method calls the controller class, and the program prompts the user to enter a command. Once the user enters a command, the query is handled controller. If the operation is "load" or "save," then the controller will call the load and save methods and return to the view to display the result of the operation. Otherwise, the controller calls the model to perform the operation. The model will return the result to the controller, which will then return the result to the view.

Controller and model is connected by **`ImageOperationsControllerImpl` and `ImageOperationsModelImpl`** classes.

Controller and view is connected by **`ImageOperationsControllerImpl` and `ImageView` classes.**

To run the file:

Run the main method and when it prompts for-

"Enter a command: "

Enter the command you wish to.

An example for the list of possible commands is:

```

load res/hp.ppm hp

save res/hp-saved.ppm hp

brighten 100 hp hp-brighter

save hp-brighter.ppm hp-brighter

vertical-flip hp hp-vertical

save res/hp-vertical.ppm hp-vertical

horizontal-flip hp hp-horizontal

save res/hp-horizontal.ppm hp-horizontal

brighten -100 hp hp-darker

save res/hp-darker.ppm hp-darker

greyscale value-component hp hp-vgreyscale

save res/hp-vgreyscale.ppm hp-vgreyscale

greyscale luma-component hp hp-lgreyscale

save res/hp-lgreyscale.ppm hp-lgreyscale

greyscale intensity-component hp hp-igreyscale

save res/hp-igreyscale.ppm hp-igreyscale

rgb-split hp hp-red hp-green hp-blue

save res/hp-red.ppm hp-red

save res/hp-green.ppm hp-green

save res/hp-blue.ppm hp-blue

rgb-combine hp-combined hp-red hp-green hp-blue

save res/hp-combined.ppm hp-combined

```

You can also provide a file which will contain all the commands and the program reads and runs each line to give the modified image after the image operations. An example of the command to run a file of commands is :

`run test/commands.txt`

where test/commands.txt is the relative path of the file which is given by the user that contains the list of commands.

The new commands that are supported for this assignment are as follows:

```

load res/hp.ppm hp

dither hp hp-dither

blur hp hp-blur

sharpen hp hp-sharpen

greyscale-transform hp hp-greyscale-transform

sepia hp hp-sepia

```

Citation:

Image used is our own and we authorize its use in the project.

## Assignment 5 updates

A new interface, `ImageEnhancement`, was created by extending the existing `ImageOperationsModel` interface. This new interface includes methods for image filtering (blur and sharpen), color transformation (greyscale and sepia), and dithering. The implementation of these methods is included in the `ImageEnhancemntImpl` class.

The Controller was updated to include support for the new commands.

The command design pattern is implemented in the Controller as this method allows easier code maintenance.

## Assignment 6

## How to use the program
USEME.pdf

## Parts of the program that are complete
All the parts of the program are complete. 

The graphical user interface has been developed using Java Swing, with additional features beyond the code examples provided in lectures. The GUI displays the image currently being worked on, even if it is larger than the allocated area, allowing the user to scroll through it. Any changes made to the image through image operations are immediately visible on the GUI.

The histogram of the visible image is displayed as a line chart (not a bar chart) at all times, showing the red, green, blue, and intensity components. The histogram is automatically refreshed when the image is manipulated.

All the required features of the program, such as flips, component visualization, greyscale, blurring, sharpening, and sepia, are exposed through the user interface. However, interactive scripting is not supported through the GUI.

When saving an image in PNG/PPM/JPG format, the program saves what the user is currently seeing. The user can specify the image to be loaded and saved, and the program cannot assume a hardcoded file or folder.

Any error conditions are suitably displayed to the user through pop-up messages or clearly visible text. The layout of the UI is reasonable, with items properly proportioned and arranged. Oversized or haphazardly arranged buttons, text fields, or labels, even if functional, will result in point deduction.

All user interactions and inputs are reasonably user-friendly, avoiding poor UI design such as forcing the user to type the path to a file. The program does not need to be sophisticated or snazzy to be considered user-friendly.

## Design changes and justifications
1. View changes - GUI - We have implemented swing features in the new view class `GUIFeaturesFrame` class and we have an interface `IGUIFeatures` with the necessary methods. 
This enables the user to interact with the application using user interface.
2. Controller changes - We have added a new controller `GUIControllerImpl` which implements `GUIController`, `ActionListener` and `ChangeListener`. 
This delegates the operations to model and the GUI view. We have added this as a new Controller by following open-closed principles of SOLID principles.
3. In the main method, we are supporting  program (from IntelliJ or the JAR file) to accept command-line inputs. When the jar file is run, the program supports following commands:
   java -jar Program.jar -file path-of-script-file
   java -jar Program.jar -text
   java -jar Program.jar
4. In the main method, `GUIController` object is created and the `goGUI()` method is called when the jar file is run without any arguments.
