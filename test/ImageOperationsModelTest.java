import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.IImage;
import model.IPixel;
import model.Image;
import model.ImageEnhancement;
import model.ImageEnhancementImpl;
import model.Pixel;

import static org.junit.Assert.assertTrue;

/**
 * Junit test class for testing model.
 **/
public class ImageOperationsModelTest {
  private final IPixel p1 = new Pixel(200, 89, 102);
  private final IPixel p2 = new Pixel(20, 78, 12);
  private final IPixel p3 = new Pixel(58, 42, 19);
  private final IPixel p4 = new Pixel(53, 99, 26);
  private final IPixel p5 = new Pixel(26, 11, 97);
  private final IPixel p6 = new Pixel(2, 3, 99);
  private final List<IPixel> listOfPixels = new ArrayList<>();
  ImageEnhancement iModel = new ImageEnhancementImpl();
  private Image iTest;

  @Before
  public void setUp() throws IOException {
    listOfPixels.add(p1);
    listOfPixels.add(p2);
    listOfPixels.add(p3);
    listOfPixels.add(p4);
    listOfPixels.add(p5);
    listOfPixels.add(p6);
    iTest = new Image("Rectangle", 3, 2, 255, listOfPixels);
  }

  private List<IPixel> addToListHelper(Pixel p1, Pixel p2, Pixel p3, Pixel p4, Pixel p5, Pixel p6) {
    List<IPixel> listOfPixels = new ArrayList<>();
    listOfPixels.add(p1);
    listOfPixels.add(p2);
    listOfPixels.add(p3);
    listOfPixels.add(p4);
    listOfPixels.add(p5);
    listOfPixels.add(p6);
    return listOfPixels;
  }

  @Test
  public void testVisualiseRComponent() throws IOException {
    iModel.loadFile("iTest", iTest);
    iModel.visualisingComponent("red", "iTest",
            "redRect");
    IImage actualImg = iModel.getHashMapData("redRect");
    Pixel p1 = new Pixel(200, 200, 200);
    Pixel p2 = new Pixel(20, 20, 20);
    Pixel p3 = new Pixel(58, 58, 58);
    Pixel p4 = new Pixel(53, 53, 53);
    Pixel p5 = new Pixel(26, 26, 26);
    Pixel p6 = new Pixel(2, 2, 2);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("redRect", 3, 2, 255, listOfPixels);
    assertTrue(expectedImg.equals(actualImg));
  }

  @Test
  public void testVisualiseGComponent() throws IOException {
    iModel.loadFile("iTest", iTest);
    iModel.visualisingComponent("green", "iTest",
            "greenRect");
    IImage actualImg = iModel.getHashMapData("greenRect");
    Pixel p1 = new Pixel(89, 89, 89);
    Pixel p2 = new Pixel(78, 78, 78);
    Pixel p3 = new Pixel(42, 42, 42);
    Pixel p4 = new Pixel(99, 99, 99);
    Pixel p5 = new Pixel(11, 11, 11);
    Pixel p6 = new Pixel(3, 3, 3);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("greenRect", 3, 2, 255, listOfPixels);
    assertTrue(expectedImg.equals(actualImg));
  }

  @Test
  public void testVisualiseBComponent() throws IOException {
    iModel.loadFile("iTest", iTest);
    iModel.visualisingComponent("blue",
            "iTest", "blueRect");
    IImage actualImg = iModel.getHashMapData("blueRect");
    Pixel p1 = new Pixel(102, 102, 102);
    Pixel p2 = new Pixel(12, 12, 12);
    Pixel p3 = new Pixel(19, 19, 19);
    Pixel p4 = new Pixel(26, 26, 26);
    Pixel p5 = new Pixel(97, 97, 97);
    Pixel p6 = new Pixel(99, 99, 99);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("blueRect", 3, 2, 255, listOfPixels);
    assertTrue(expectedImg.equals(actualImg));
  }

  @Test
  public void testVisualiseValueComponent() throws IOException {
    iModel.loadFile("iTest", iTest);
    iModel.visualisingComponent("value", "iTest",
            "valueRect");
    IImage actualImg = iModel.getHashMapData("valueRect");
    Pixel p1 = new Pixel(200, 200, 200);
    Pixel p2 = new Pixel(78, 78, 78);
    Pixel p3 = new Pixel(58, 58, 58);
    Pixel p4 = new Pixel(99, 99, 99);
    Pixel p5 = new Pixel(97, 97, 97);
    Pixel p6 = new Pixel(99, 99, 99);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("valueRect", 3, 2, 255, listOfPixels);
    assertTrue(expectedImg.equals(actualImg));
  }

  @Test
  public void testVisualiseIntensityComponent() throws IOException {
    iModel.loadFile("iTest", iTest);
    iModel.visualisingComponent("intensity",
            "iTest", "intensityRect");
    IImage actualImg = iModel.getHashMapData("intensityRect");
    Pixel p1 = new Pixel(130, 130, 130);
    Pixel p2 = new Pixel(36, 36, 36);
    Pixel p3 = new Pixel(39, 39, 39);
    Pixel p4 = new Pixel(59, 59, 59);
    Pixel p5 = new Pixel(44, 44, 44);
    Pixel p6 = new Pixel(34, 34, 34);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("intensityRect", 3, 2, 255, listOfPixels);
    assertTrue(expectedImg.equals(actualImg));
  }

  @Test
  public void testVisualiseLumaComponent() throws IOException {
    iModel.loadFile("iTest", iTest);
    iModel.visualisingComponent("luma", "iTest",
            "lumaRect");
    IImage actualImg = iModel.getHashMapData("lumaRect");
    Pixel p1 = new Pixel(113, 113, 113);
    Pixel p2 = new Pixel(60, 60, 60);
    Pixel p3 = new Pixel(43, 43, 43);
    Pixel p4 = new Pixel(83, 83, 83);
    Pixel p5 = new Pixel(20, 20, 20);
    Pixel p6 = new Pixel(9, 9, 9);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("lumaRect", 3, 2, 255, listOfPixels);
    assertTrue(expectedImg.equals(actualImg));
  }

  @Test
  public void testRGBSplitCombine() throws IOException {
    iModel.loadFile("iTest", iTest);
    iModel.rgbSplit("iTest", "red",
            "green", "blue");
    IImage red = iModel.getHashMapData("red");
    IImage green = iModel.getHashMapData("green");
    IImage blue = iModel.getHashMapData("blue");
    Pixel p1 = new Pixel(200, 200, 200);
    Pixel p2 = new Pixel(20, 20, 20);
    Pixel p3 = new Pixel(58, 58, 58);
    Pixel p4 = new Pixel(53, 53, 53);
    Pixel p5 = new Pixel(26, 26, 26);
    Pixel p6 = new Pixel(2, 2, 2);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    IImage expectedImg = new Image("red", 3, 2, 255, listOfPixels);
    assertTrue(expectedImg.equals(red));
    p1 = new Pixel(89, 89, 89);
    p2 = new Pixel(78, 78, 78);
    p3 = new Pixel(42, 42, 42);
    p4 = new Pixel(99, 99, 99);
    p5 = new Pixel(11, 11, 11);
    p6 = new Pixel(3, 3, 3);
    listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    expectedImg = new Image("green", 3, 2, 255, listOfPixels);
    assertTrue(expectedImg.equals(green));
    p1 = new Pixel(102, 102, 102);
    p2 = new Pixel(12, 12, 12);
    p3 = new Pixel(19, 19, 19);
    p4 = new Pixel(26, 26, 26);
    p5 = new Pixel(97, 97, 97);
    p6 = new Pixel(99, 99, 99);
    listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    expectedImg = new Image("blue", 3, 2, 255, listOfPixels);
    assertTrue(expectedImg.equals(blue));
    iModel.combineImages("Rectangle", "red", "green", "blue");
    IImage combined = iModel.getHashMapData("Rectangle");
    assertTrue(combined.equals(iTest));
  }

  @Test
  public void testBrightenImage() throws IOException {
    iModel.loadFile("iTest", iTest);
    iModel.brighten(50, "iTest", "rect-brighten");
    IImage actualImg = iModel.getHashMapData("rect-brighten");
    Pixel p1 = new Pixel(250, 139, 152);
    Pixel p2 = new Pixel(70, 128, 62);
    Pixel p3 = new Pixel(108, 92, 69);
    Pixel p4 = new Pixel(103, 149, 76);
    Pixel p5 = new Pixel(76, 61, 147);
    Pixel p6 = new Pixel(52, 53, 149);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("rect-brighten", 3, 2, 255, listOfPixels);
    assertTrue(expectedImg.equals(actualImg));
  }

  @Test
  public void testDarkenImage() throws IOException {
    iModel.loadFile("iTest", iTest);
    iModel.brighten(-50, "iTest", "rect-darken");
    IImage actualImg = iModel.getHashMapData("rect-darken");
    Pixel p1 = new Pixel(150, 39, 52);
    Pixel p2 = new Pixel(-30, 28, -38);
    Pixel p3 = new Pixel(8, -8, -31);
    Pixel p4 = new Pixel(3, 49, -24);
    Pixel p5 = new Pixel(-24, -39, 47);
    Pixel p6 = new Pixel(-48, -47, 49);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("rect-darken", 3, 2, 255, listOfPixels);
    assertTrue(expectedImg.equals(actualImg));
  }

  @Test
  public void testVerticalFlipImage() throws IOException {
    iModel.loadFile("iTest", iTest);
    iModel.verticalFlip("iTest", "rect-vertical-flip");
    IImage actualImg = iModel.getHashMapData("rect-vertical-flip");
    Pixel p1 = new Pixel(53, 99, 26);
    Pixel p2 = new Pixel(26, 11, 97);
    Pixel p3 = new Pixel(2, 3, 99);
    Pixel p4 = new Pixel(200, 89, 102);
    Pixel p5 = new Pixel(20, 78, 12);
    Pixel p6 = new Pixel(58, 42, 19);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("rect-vertical-flip", 3, 2, 255, listOfPixels);
    assertTrue(expectedImg.equals(actualImg));
  }

  @Test
  public void testHorizontalFlipImage() throws IOException {
    iModel.loadFile("iTest", iTest);
    iModel.horizontalFlip("iTest", "rect-horizontal-flip");
    IImage actualImg = iModel.getHashMapData("rect-horizontal-flip");
    Pixel p1 = new Pixel(58, 42, 19);
    Pixel p2 = new Pixel(20, 78, 12);
    Pixel p3 = new Pixel(200, 89, 102);
    Pixel p4 = new Pixel(2, 3, 99);
    Pixel p5 = new Pixel(26, 11, 97);
    Pixel p6 = new Pixel(53, 99, 26);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("rect-horizontal-flip", 3, 2, 255, listOfPixels);
    assertTrue(expectedImg.equals(actualImg));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testException() throws IOException {
    iModel.loadFile("iTest", iTest);
    iModel.visualisingComponent("intensityTest", "iTest", "intensityRect");
  }

}