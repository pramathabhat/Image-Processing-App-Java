import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.FileIOOperationsController;
import controller.FileIOOperationsControllerImpl;
import controller.ImageOperationsController;
import controller.ImageOperationsControllerImpl;
import model.IPixel;
import model.Image;
import model.ImageEnhancement;
import model.ImageEnhancementImpl;
import model.Pixel;
import view.ImageView;

import static org.junit.Assert.assertEquals;

/**
 * Junit test class for testing controller in isolation.
 **/
public class ControllerTestClass {
  private final Pixel p1 = new Pixel(200, 89, 102);
  private final Pixel p2 = new Pixel(20, 78, 12);
  private final Pixel p3 = new Pixel(58, 42, 19);
  private final Pixel p4 = new Pixel(53, 99, 26);
  private final Pixel p5 = new Pixel(26, 11, 97);
  private final Pixel p6 = new Pixel(2, 3, 99);
  private final List<IPixel> listOfPixels = new ArrayList<>();
  private ImageOperationsControllerImpl controller;
  private StringBuilder logger;
  private Image iTest;

  @Before
  public void setUp() throws IOException {
    logger = new StringBuilder();
    MockModel model = new MockModel(logger);
    ImageView view = null;
    controller = new ImageOperationsControllerImpl(model, view);
    listOfPixels.add(p1);
    listOfPixels.add(p2);
    listOfPixels.add(p3);
    listOfPixels.add(p4);
    listOfPixels.add(p5);
    listOfPixels.add(p6);
    iTest = new Image("Rectangle", 3, 2, 255, listOfPixels);
  }

  @Test
  public void testHorizontalFlip() throws IOException {
    controller.controllerCommandCall("horizontal-flip src/res/Koala.ppm koala");
    assertEquals("horizontal flip image to koala", logger.toString());
  }

  @Test
  public void testVerticalFlip() throws IOException {
    controller.controllerCommandCall("horizontal-flip src/res/Koala.ppm koala");
    assertEquals("horizontal flip image to koala", logger.toString());
  }

  @Test
  public void testBrightenImage() throws IOException {
    controller.controllerCommandCall("brighten 50 src/res/Koala.ppm koala-brighten");
    assertEquals("brightened image to koala-brighten", logger.toString());
  }

  @Test
  public void testDarkenImage() throws IOException {
    controller.controllerCommandCall("brighten -50 src/res/Koala.ppm koala-darken");
    assertEquals("brightened image to koala-darken", logger.toString());
  }


  @Test
  public void testRGBSplit() throws IOException {
    ImageEnhancement im = new ImageEnhancementImpl();
    ImageOperationsController ic = new ImageOperationsControllerImpl(im, null);
    FileIOOperationsController fIO = new FileIOOperationsControllerImpl();
    fIO.saveFile(iTest, "test/rect.ppm");
    ic.controllerCommandCall("load test/rect.ppm rect");
    controller.controllerCommandCall("rgb-split koala koala-red koala-green koala-blue");
    assertEquals("RGB split of the image: koala-red koala-green koala-blue", logger.toString());
  }

  @Test
  public void testCombineGreyscaleCommand() throws IOException {
    controller.controllerCommandCall("rgb-combine rectangle-combined rectangle-red "
            + "rectangle-green rectangle-blue");
    assertEquals("Combine images: rectangle-combined", logger.toString());
  }

  @Test
  public void testVisualisingValueComponents() throws IOException {
    controller.controllerCommandCall("greyscale value-component koala koala-value");
    assertEquals("visualisingComponent for value koala-value", logger.toString());
  }

  @Test
  public void testVisualisingIntensityComponents() throws IOException {
    controller.controllerCommandCall("greyscale intensity-component koala koala-intensity");
    assertEquals("visualisingComponent for intensity koala-intensity", logger.toString());
  }

  @Test
  public void testVisualisingLumaComponents() throws IOException {
    controller.controllerCommandCall("greyscale luma-component koala koala-luma");
    assertEquals("visualisingComponent for luma koala-luma", logger.toString());
  }

  @Test
  public void testVisualisingRedComponents() throws IOException {
    controller.controllerCommandCall("greyscale red-component koala koala-red");
    assertEquals("visualisingComponent for red koala-red", logger.toString());
  }

  @Test
  public void testVisualisingGreenComponents() throws IOException {
    controller.controllerCommandCall("greyscale green-component rect rect-green");
    assertEquals("visualisingComponent for green rect-green", logger.toString());
  }

  @Test
  public void testVisualisingBlueComponents() throws IOException {
    controller.controllerCommandCall("greyscale blue-component koala koala-blue");
    assertEquals("visualisingComponent for blue koala-blue", logger.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoArgs() throws IOException {
    controller.controllerCommandCall("load");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadNotEnoughArgs() throws IOException {
    controller.controllerCommandCall(
            "load res/image.ppm");
  }
}