import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.ImageOperationsController;
import controller.ImageOperationsControllerImpl;
import model.IImage;
import model.IPixel;
import model.Image;
import model.ImageEnhancement;
import model.ImageEnhancementImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class is used to test controller and model.
 */

public class ModelControllerTestClass {
  //  FileIOOperationsController fileCon;
  private final IPixel p1 = new Pixel(200, 89, 102);
  private final IPixel p2 = new Pixel(20, 78, 12);
  private final IPixel p3 = new Pixel(58, 42, 19);
  private final IPixel p4 = new Pixel(53, 99, 26);
  private final IPixel p5 = new Pixel(26, 11, 97);
  private final IPixel p6 = new Pixel(2, 3, 99);
  private final IPixel p7 = new Pixel(1, 99, 22);
  private final IPixel p8 = new Pixel(28, 11, 78);
  private final IPixel p9 = new Pixel(2, 30, 198);
  private final List<IPixel> listOfPixels = new ArrayList<>();
  ImageEnhancement model;
  ImageOperationsController ioCon;
  private Image iTest;

  private List<IPixel> addToListHelper(IPixel p1, IPixel p2, IPixel p3,
                                       IPixel p4, IPixel p5, IPixel p6) {
    List<IPixel> listOfPixels = new ArrayList<>();
    listOfPixels.add(p1);
    listOfPixels.add(p2);
    listOfPixels.add(p3);
    listOfPixels.add(p4);
    listOfPixels.add(p5);
    listOfPixels.add(p6);
    return listOfPixels;
  }

  private List<IPixel> addToListHelperNewOp(IPixel p1, IPixel p2, IPixel p3, IPixel p4, IPixel p5,
                                            IPixel p6, IPixel p7, IPixel p8, IPixel p9) {
    List<IPixel> listOfPixels = new ArrayList<>();
    listOfPixels.add(p1);
    listOfPixels.add(p2);
    listOfPixels.add(p3);
    listOfPixels.add(p4);
    listOfPixels.add(p5);
    listOfPixels.add(p6);
    listOfPixels.add(p7);
    listOfPixels.add(p8);
    listOfPixels.add(p9);
    return listOfPixels;
  }


  @Before
  public void setup() throws IOException {
    model = new ImageEnhancementImpl();
    ioCon = new ImageOperationsControllerImpl(model, null);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    List<IPixel> listOfPixelsNewOp = addToListHelperNewOp(p1, p2, p3, p4, p5, p6, p7, p8, p9);
    iTest = new Image("rect", 2, 3, 255, listOfPixels);
    Image iTestNewOp = new Image("rect", 3, 3, 255, listOfPixelsNewOp);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHorizontalFlipInvalidNoOfArgs() throws IOException {
    ioCon.controllerCommandCall("horizontal-flip test");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testVerticalFlipInvalidNoOfArgs() throws IOException {
    ioCon.controllerCommandCall("vertical-flip test");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBrightenInvalidNoOfArgs() throws IOException {
    ioCon.controllerCommandCall("brighten test");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDarkenInvalidNoOfArgs() throws IOException {
    ioCon.controllerCommandCall("brighten test");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHorizontalFlipImageNotInHashmap() throws IOException {
    ioCon.controllerCommandCall("horizontal-flip test test-horizontal");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testVerticalFlipImageNotInHashmap() throws IOException {
    ioCon.controllerCommandCall("vertical-flip test test-vertical");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSepiaImageNotInHashmap() throws IOException {
    ioCon.controllerCommandCall("sepia test test-sepia");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlurNotInHashmap() throws IOException {
    ioCon.controllerCommandCall("blur test test-blur");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSharpenImageNotInHashmap() throws IOException {
    ioCon.controllerCommandCall("sharpen test test-sharpen");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreyscaleImageNotInHashmap() throws IOException {
    ioCon.controllerCommandCall("greyscale-transform test test-gs");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDitherImageNotInHashmap() throws IOException {
    ioCon.controllerCommandCall("dither test test-dither");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBrightenImageNotInHashmap() throws IOException {
    ioCon.controllerCommandCall("brighten 50 test test-brighter");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDarkenImageNotInHashmap() throws IOException {
    ioCon.controllerCommandCall("brighten -50 test test-darker");
  }

  @Test
  public void testSharpenJPG() throws IOException {
    ioCon.controllerCommandCall("load test/testImgNewOp.jpg rect");
    ioCon.controllerCommandCall("sharpen rect rect-sharpen");
    IImage actualImg = model.getHashMapData("rect-sharpen");
    Pixel p1 = new Pixel(0, 0, 204);
    List<IPixel> listOfPixels = new ArrayList<>();
    listOfPixels.add(p1);
    IImage expectedImg = new Image("rect-sharpen", 1, 1, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testHorizontalFlip() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.ppm test");
    ioCon.controllerCommandCall("horizontal-flip test test-horizontal");
    IImage actualImg = model.getHashMapData("test-horizontal");
    Pixel p1 = new Pixel(20, 78, 12);
    Pixel p2 = new Pixel(200, 89, 102);
    Pixel p3 = new Pixel(53, 99, 26);
    Pixel p4 = new Pixel(58, 42, 19);
    Pixel p5 = new Pixel(2, 3, 99);
    Pixel p6 = new Pixel(26, 11, 97);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("test-horizontal", 2, 3, 255, listOfPixels);
    assertTrue(expectedImg.equals(actualImg));
  }

  @Test
  public void testVerticalFlip() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.ppm test");
    ioCon.controllerCommandCall("vertical-flip test test-vertical");
    IImage actualImg = model.getHashMapData("test-vertical");
    Pixel p1 = new Pixel(26, 11, 97);
    Pixel p2 = new Pixel(2, 3, 99);
    Pixel p3 = new Pixel(58, 42, 19);
    Pixel p4 = new Pixel(53, 99, 26);
    Pixel p5 = new Pixel(200, 89, 102);
    Pixel p6 = new Pixel(20, 78, 12);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("test-vertical", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testBrighten() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.ppm test");
    ioCon.controllerCommandCall("brighten 50 test test-brighten");
    IImage actualImg = model.getHashMapData("test-brighten");
    Pixel p1 = new Pixel(250, 139, 152);
    Pixel p2 = new Pixel(70, 128, 62);
    Pixel p3 = new Pixel(108, 92, 69);
    Pixel p4 = new Pixel(103, 149, 76);
    Pixel p5 = new Pixel(76, 61, 147);
    Pixel p6 = new Pixel(52, 53, 149);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("test-brighten", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testDarken() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.ppm test");
    ioCon.controllerCommandCall("brighten -50 test test-darker");
    IImage actualImg = model.getHashMapData("test-darker");
    Pixel p1 = new Pixel(150, 39, 52);
    Pixel p2 = new Pixel(-30, 28, -38);
    Pixel p3 = new Pixel(8, -8, -31);
    Pixel p4 = new Pixel(3, 49, -24);
    Pixel p5 = new Pixel(-24, -39, 47);
    Pixel p6 = new Pixel(-48, -47, 49);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("test-darker", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testSave() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.ppm test");
    ioCon.controllerCommandCall("save test/testSaveImg.ppm test");
    IImage actualImg = model.getHashMapData("test");
    ioCon.controllerCommandCall("load test/testSaveImg.ppm test");
    IImage expectedImg = model.getHashMapData("test");
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testSaveBmp() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.bmp test");
    ioCon.controllerCommandCall("save test/testSaveImg.bmp test");
    IImage actualImg = model.getHashMapData("test");
    ioCon.controllerCommandCall("load test/testSaveImg.bmp test");
    IImage expectedImg = model.getHashMapData("test");
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testSavePng() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.png test");
    ioCon.controllerCommandCall("save test/testSaveImg.png test");
    IImage actualImg = model.getHashMapData("test");
    ioCon.controllerCommandCall("load test/testSaveImg.png test");
    IImage expectedImg = model.getHashMapData("test");
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testSaveJpg() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.jpg test");
    ioCon.controllerCommandCall("save test/testSaveImg.jpg test");
    IImage actualImg = model.getHashMapData("test");
    ioCon.controllerCommandCall("load test/testSaveImg.jpg test");
    IImage expectedImg = model.getHashMapData("test");
    assertEquals(false, expectedImg.equals(actualImg));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoArgs() throws IOException {
    ioCon.controllerCommandCall("load");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadNotEnoughArgs() throws IOException {
    ioCon.controllerCommandCall("load res/image.ppm");
  }

  @Test
  public void testLoad() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.ppm test");
    IImage actualImg = model.getHashMapData("test");
    Pixel p1 = new Pixel(200, 89, 102);
    Pixel p2 = new Pixel(20, 78, 12);
    Pixel p3 = new Pixel(58, 42, 19);
    Pixel p4 = new Pixel(53, 99, 26);
    Pixel p5 = new Pixel(26, 11, 97);
    Pixel p6 = new Pixel(2, 3, 99);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("test", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testLoadBmp() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.bmp test");
    IImage actualImg = model.getHashMapData("test");
    Pixel p1 = new Pixel(200, 89, 102);
    Pixel p2 = new Pixel(20, 78, 12);
    Pixel p3 = new Pixel(58, 42, 19);
    Pixel p4 = new Pixel(53, 99, 26);
    Pixel p5 = new Pixel(26, 11, 97);
    Pixel p6 = new Pixel(2, 3, 99);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("test", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testLoadPng() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.png test");
    IImage actualImg = model.getHashMapData("test");
    Pixel p1 = new Pixel(200, 89, 102);
    Pixel p2 = new Pixel(20, 78, 12);
    Pixel p3 = new Pixel(58, 42, 19);
    Pixel p4 = new Pixel(53, 99, 26);
    Pixel p5 = new Pixel(26, 11, 97);
    Pixel p6 = new Pixel(2, 3, 99);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("test", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testLoadJpg() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.jpg test");
    IImage actualImg = model.getHashMapData("test");
    Pixel p1 = new Pixel(200, 89, 102);
    Pixel p2 = new Pixel(20, 78, 12);
    Pixel p3 = new Pixel(58, 42, 19);
    Pixel p4 = new Pixel(53, 99, 26);
    Pixel p5 = new Pixel(26, 11, 97);
    Pixel p6 = new Pixel(2, 3, 99);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("test", 2, 3, 255, listOfPixels);
    assertEquals(false, expectedImg.equals(actualImg));
  }

  @Test
  public void testSharpenBMP() throws IOException {
    ioCon.controllerCommandCall("load test/testImgNewOp.bmp rect");
    ioCon.controllerCommandCall("sharpen rect rect-sharpen");
    IImage actualImg = model.getHashMapData("rect-sharpen");
    IPixel p1 = new Pixel(0, 0, 244);
    List<IPixel> listOfPixels = new ArrayList<>();
    listOfPixels.add(p1);
    IImage expectedImg = new Image("rect-sharpen", 1, 1, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveNotEnoughArgs() throws IOException {
    ioCon.controllerCommandCall("save res/testImg.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRGBSplitNotEnoughArgs() throws IOException {
    ioCon.controllerCommandCall("rgb-split rect");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRGBSplitHashMapKeyMismatch() throws IOException {
    ioCon.controllerCommandCall("rgb-split recth red green blue");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRGBCombineNotEnoughArgs() throws IOException {
    ioCon.controllerCommandCall("rgb-combine rect");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRGBCombineHashMapKeyMismatch() throws IOException {
    ioCon.controllerCommandCall("rgb-combine rect redey greeney bluey");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRGBCombineHashMapKeyMismatchRed() throws IOException {
    ioCon.controllerCommandCall("rgb-combine rect redey green blue");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRGBCombineHashMapKeyMismatchGreen() throws IOException {
    ioCon.controllerCommandCall("rgb-combine rect red greren blue");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRGBCombineHashMapKeyMismatchBlue() throws IOException {
    ioCon.controllerCommandCall("rgb-combine rect red green blure");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIntensityKeyMismatch() throws IOException {
    ioCon.controllerCommandCall("greyscale intensity-component int intensity-scale");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLumaKeyMismatch() throws IOException {
    ioCon.controllerCommandCall("greyscale luma-component l l");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValueKeyMismatch() throws IOException {
    ioCon.controllerCommandCall("greyscale value-component v v");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRedKeyMismatch() throws IOException {
    ioCon.controllerCommandCall("greyscale red-component red r");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreenKeyMismatch() throws IOException {
    ioCon.controllerCommandCall("greyscale green-component green g");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlueKeyMismatch() throws IOException {
    ioCon.controllerCommandCall("greyscale blue-component blue blue-scale");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIntensityArgMismatch() throws IOException {
    ioCon.controllerCommandCall("greyscale intensity-component t");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLumaArgMismatch() throws IOException {
    ioCon.controllerCommandCall("greyscale luma-component a");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValueArgMismatch() throws IOException {
    ioCon.controllerCommandCall("greyscale value-component b");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRedArgMismatch() throws IOException {
    ioCon.controllerCommandCall("greyscale red-component t");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreenArgMismatch() throws IOException {
    ioCon.controllerCommandCall("greyscale green-component a");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlueArgMismatch() throws IOException {
    ioCon.controllerCommandCall("greyscale blue-component b");
  }

  @Test
  public void testVisualiseRComponent() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.ppm rect");
    ioCon.controllerCommandCall("greyscale red-component rect redRect");
    IImage actualImg = model.getHashMapData("redRect");
    Pixel p1 = new Pixel(200, 200, 200);
    Pixel p2 = new Pixel(20, 20, 20);
    Pixel p3 = new Pixel(58, 58, 58);
    Pixel p4 = new Pixel(53, 53, 53);
    Pixel p5 = new Pixel(26, 26, 26);
    Pixel p6 = new Pixel(2, 2, 2);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("redRect", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testVisualiseGComponent() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.ppm rect");
    ioCon.controllerCommandCall("greyscale green-component rect greenRect");
    IImage actualImg = model.getHashMapData("greenRect");
    Pixel p1 = new Pixel(89, 89, 89);
    Pixel p2 = new Pixel(78, 78, 78);
    Pixel p3 = new Pixel(42, 42, 42);
    Pixel p4 = new Pixel(99, 99, 99);
    Pixel p5 = new Pixel(11, 11, 11);
    Pixel p6 = new Pixel(3, 3, 3);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("greenRect", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testVisualiseBComponent() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.ppm rect");
    ioCon.controllerCommandCall("greyscale blue-component rect blueRect");
    IImage actualImg = model.getHashMapData("blueRect");
    Pixel p1 = new Pixel(102, 102, 102);
    Pixel p2 = new Pixel(12, 12, 12);
    Pixel p3 = new Pixel(19, 19, 19);
    Pixel p4 = new Pixel(26, 26, 26);
    Pixel p5 = new Pixel(97, 97, 97);
    Pixel p6 = new Pixel(99, 99, 99);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("blueRect", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testSharpenPNG() throws IOException {
    ioCon.controllerCommandCall("load test/testImgNewOp.png rect");
    ioCon.controllerCommandCall("sharpen rect rect-sharpen");
    IImage actualImg = model.getHashMapData("rect-sharpen");
    Pixel p1 = new Pixel(0, 0, 150);
    List<IPixel> listOfPixels = new ArrayList<>();
    listOfPixels.add(p1);
    Image expectedImg = new Image("rect-sharpen", 1, 1, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testVisualiseValueComponent() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.ppm rect");
    ioCon.controllerCommandCall("greyscale value-component rect valueRect");
    IImage actualImg = model.getHashMapData("valueRect");
    Pixel p1 = new Pixel(200, 200, 200);
    Pixel p2 = new Pixel(78, 78, 78);
    Pixel p3 = new Pixel(58, 58, 58);
    Pixel p4 = new Pixel(99, 99, 99);
    Pixel p5 = new Pixel(97, 97, 97);
    Pixel p6 = new Pixel(99, 99, 99);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("valueRect", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testVisualiseIntensityComponent() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.ppm rect");
    ioCon.controllerCommandCall("greyscale intensity-component rect intensityRect");
    IImage actualImg = model.getHashMapData("intensityRect");
    Pixel p1 = new Pixel(130, 130, 130);
    Pixel p2 = new Pixel(36, 36, 36);
    Pixel p3 = new Pixel(39, 39, 39);
    Pixel p4 = new Pixel(59, 59, 59);
    Pixel p5 = new Pixel(44, 44, 44);
    Pixel p6 = new Pixel(34, 34, 34);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("intensityRect", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testVisualiseLumaComponent() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.ppm rect");
    ioCon.controllerCommandCall("greyscale luma-component rect lumaRect");
    IImage actualImg = model.getHashMapData("lumaRect");
    Pixel p1 = new Pixel(113, 113, 113);
    Pixel p2 = new Pixel(60, 60, 60);
    Pixel p3 = new Pixel(43, 43, 43);
    Pixel p4 = new Pixel(83, 83, 83);
    Pixel p5 = new Pixel(20, 20, 20);
    Pixel p6 = new Pixel(9, 9, 9);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("lumaRect", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testRGBSplitCombine() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.ppm rect");
    ioCon.controllerCommandCall("rgb-split rect red green blue");
    IImage red = model.getHashMapData("red");
    IImage green = model.getHashMapData("green");
    IImage blue = model.getHashMapData("blue");
    ioCon.controllerCommandCall("brighten 50 red red-brighten");
    Pixel p1 = new Pixel(200, 200, 200);
    Pixel p2 = new Pixel(20, 20, 20);
    Pixel p3 = new Pixel(58, 58, 58);
    Pixel p4 = new Pixel(53, 53, 53);
    Pixel p5 = new Pixel(26, 26, 26);
    Pixel p6 = new Pixel(2, 2, 2);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("red", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(red));
    p1 = new Pixel(89, 89, 89);
    p2 = new Pixel(78, 78, 78);
    p3 = new Pixel(42, 42, 42);
    p4 = new Pixel(99, 99, 99);
    p5 = new Pixel(11, 11, 11);
    p6 = new Pixel(3, 3, 3);
    listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    expectedImg = new Image("green", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(green));
    p1 = new Pixel(102, 102, 102);
    p2 = new Pixel(12, 12, 12);
    p3 = new Pixel(19, 19, 19);
    p4 = new Pixel(26, 26, 26);
    p5 = new Pixel(97, 97, 97);
    p6 = new Pixel(99, 99, 99);
    listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    expectedImg = new Image("blue", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(blue));
    ioCon.controllerCommandCall("rgb-combine rect red green blue");
    IImage combined = model.getHashMapData("rect");
    assertEquals(true, combined.equals(iTest));
    ioCon.controllerCommandCall("rgb-combine rect red-brighten green blue");
    p1 = new Pixel(200, 89, 102);
    p2 = new Pixel(20, 78, 12);
    p3 = new Pixel(58, 42, 19);
    p4 = new Pixel(53, 99, 26);
    p5 = new Pixel(26, 11, 97);
    p6 = new Pixel(2, 3, 99);
    listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    expectedImg = new Image("rect", 2, 3, 255, listOfPixels);
    assertEquals(true, combined.equals(expectedImg));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNumberOfArg() throws IOException {
    ioCon.controllerCommandCall("rgb-split");
  }

  @Test(expected = IOException.class)
  public void testExceptionInFiles() throws IOException {
    ioCon.controllerCommandCall("run test/exceptionTest.txt");
  }

  @Test
  public void testValueInFiles() throws IOException {
    ioCon.controllerCommandCall("run test/testValueImage.txt");
    ioCon.controllerCommandCall("load test/rect-vgreyscale.ppm rect");
    ioCon.controllerCommandCall("greyscale value-component rect valueRect");
    IImage actualImg = model.getHashMapData("valueRect");
    Pixel p1 = new Pixel(200, 200, 200);
    Pixel p2 = new Pixel(78, 78, 78);
    Pixel p3 = new Pixel(58, 58, 58);
    Pixel p4 = new Pixel(99, 99, 99);
    Pixel p5 = new Pixel(97, 97, 97);
    Pixel p6 = new Pixel(99, 99, 99);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("valueRect", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testOverWriteComponent() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.ppm rect");
    ioCon.controllerCommandCall("load test/rect-vgreyscale.ppm rect");
    IImage actualImg = model.getHashMapData("rect");
    Pixel p1 = new Pixel(200, 200, 200);
    Pixel p2 = new Pixel(78, 78, 78);
    Pixel p3 = new Pixel(58, 58, 58);
    Pixel p4 = new Pixel(99, 99, 99);
    Pixel p5 = new Pixel(97, 97, 97);
    Pixel p6 = new Pixel(99, 99, 99);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("rect", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }


  @Test
  public void testSepiaPPM() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.ppm rect");
    ioCon.controllerCommandCall("sepia rect rect-sepia");
    IImage actualImg = model.getHashMapData("rect-sepia");
    Pixel p1 = new Pixel(166, 147, 115);
    Pixel p2 = new Pixel(70, 62, 48);
    Pixel p3 = new Pixel(58, 52, 40);
    Pixel p4 = new Pixel(101, 90, 70);
    Pixel p5 = new Pixel(37, 32, 25);
    Pixel p6 = new Pixel(21, 19, 15);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("rect-sepia", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testSepiaPNG() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.png rect");
    ioCon.controllerCommandCall("sepia rect rect-sepia");
    IImage actualImg = model.getHashMapData("rect-sepia");
    Pixel p1 = new Pixel(166, 147, 115);
    Pixel p2 = new Pixel(70, 62, 48);
    Pixel p3 = new Pixel(58, 52, 40);
    Pixel p4 = new Pixel(101, 90, 70);
    Pixel p5 = new Pixel(37, 32, 25);
    Pixel p6 = new Pixel(21, 19, 15);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("rect-sepia", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testSepiaBMP() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.bmp rect");
    ioCon.controllerCommandCall("sepia rect rect-sepia");
    IImage actualImg = model.getHashMapData("rect-sepia");
    IPixel p1 = new Pixel(166, 147, 115);
    IPixel p2 = new Pixel(70, 62, 48);
    IPixel p3 = new Pixel(58, 52, 40);
    IPixel p4 = new Pixel(101, 90, 70);
    IPixel p5 = new Pixel(37, 32, 25);
    IPixel p6 = new Pixel(21, 19, 15);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    IImage expectedImg = new Image("rect-sepia", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testSepiaJPG() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.bmp rect");
    ioCon.controllerCommandCall("sepia rect rect-sepia");
    IImage actualImg = model.getHashMapData("rect-sepia");
    Pixel p1 = new Pixel(166, 147, 115);
    Pixel p2 = new Pixel(70, 62, 48);
    Pixel p3 = new Pixel(58, 52, 40);
    Pixel p4 = new Pixel(101, 90, 70);
    Pixel p5 = new Pixel(37, 32, 25);
    Pixel p6 = new Pixel(21, 19, 15);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    Image expectedImg = new Image("rect-sepia", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testGreyscaleTransformPPM() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.ppm rect");
    ioCon.controllerCommandCall("greyscale-transform rect rect-gt");
    IImage actualImg = model.getHashMapData("rect-gt");
    Pixel p1 = new Pixel(113, 113, 113);
    Pixel p2 = new Pixel(60, 60, 60);
    Pixel p3 = new Pixel(43, 43, 43);
    Pixel p4 = new Pixel(83, 83, 83);
    Pixel p5 = new Pixel(20, 20, 20);
    Pixel p6 = new Pixel(9, 9, 9);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    IImage expectedImg = new Image("rect-gt", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testGreyscaleTransformPNG() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.png rect");
    ioCon.controllerCommandCall("greyscale-transform rect rect-gt");
    IImage actualImg = model.getHashMapData("rect-gt");
    Pixel p1 = new Pixel(113, 113, 113);
    Pixel p2 = new Pixel(60, 60, 60);
    Pixel p3 = new Pixel(43, 43, 43);
    Pixel p4 = new Pixel(83, 83, 83);
    Pixel p5 = new Pixel(20, 20, 20);
    Pixel p6 = new Pixel(9, 9, 9);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    IImage expectedImg = new Image("rect-gt", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testGreyscaleTransformJPG() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.jpg rect");
    ioCon.controllerCommandCall("greyscale-transform rect rect-gt");
    IImage actualImg = model.getHashMapData("rect-gt");
    Pixel p1 = new Pixel(111, 111, 111);
    Pixel p2 = new Pixel(77, 77, 77);
    Pixel p3 = new Pixel(70, 70, 70);
    Pixel p4 = new Pixel(67, 67, 67);
    Pixel p5 = new Pixel(6, 6, 6);
    Pixel p6 = new Pixel(6, 6, 6);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    IImage expectedImg = new Image("rect-gt", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testGreyscaleTransformBMP() throws IOException {
    ioCon.controllerCommandCall("load test/testImg.bmp rect");
    ioCon.controllerCommandCall("greyscale-transform rect rect-gt");
    IImage actualImg = model.getHashMapData("rect-gt");
    Pixel p1 = new Pixel(113, 113, 113);
    Pixel p2 = new Pixel(60, 60, 60);
    Pixel p3 = new Pixel(43, 43, 43);
    Pixel p4 = new Pixel(83, 83, 83);
    Pixel p5 = new Pixel(20, 20, 20);
    Pixel p6 = new Pixel(9, 9, 9);
    List<IPixel> listOfPixels = addToListHelper(p1, p2, p3, p4, p5, p6);
    IImage expectedImg = new Image("rect-gt", 2, 3, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }


  @Test
  public void testBlurPNG() throws IOException {
    ioCon.controllerCommandCall("load test/testImgNewOp.png rect");
    ioCon.controllerCommandCall("blur rect rect-blur");
    IImage actualImg = model.getHashMapData("rect-blur");
    Pixel p1 = new Pixel(39, 43, 69);
    List<IPixel> listOfPixels = new ArrayList<>();
    listOfPixels.add(p1);
    IImage expectedImg = new Image("rect-blur", 1, 1, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testBlurBMP() throws IOException {
    ioCon.controllerCommandCall("load test/testImgNewOp.bmp rect");
    ioCon.controllerCommandCall("blur rect rect-blur");
    IImage actualImg = model.getHashMapData("rect-blur");
    Pixel p1 = new Pixel(36, 43, 72);
    List<IPixel> listOfPixels = new ArrayList<>();
    listOfPixels.add(p1);
    Image expectedImg = new Image("rect-blur", 1, 1, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testBlurJPG() throws IOException {
    ioCon.controllerCommandCall("load test/testImgNewOp.jpg rect");
    ioCon.controllerCommandCall("blur rect rect-blur");
    IImage actualImg = model.getHashMapData("rect-blur");
    Pixel p1 = new Pixel(39, 51, 44);
    List<IPixel> listOfPixels = new ArrayList<>();
    listOfPixels.add(p1);
    Image expectedImg = new Image("rect-blur", 1, 1, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testBlurPPM() throws IOException {
    ioCon.controllerCommandCall("load test/testImgNewOp.ppm rect");
    ioCon.controllerCommandCall("blur rect rect-blur");
    IImage actualImg = model.getHashMapData("rect-blur");
    Pixel p1 = new Pixel(36, 43, 72);
    List<IPixel> listOfPixels = new ArrayList<>();
    listOfPixels.add(p1);
    Image expectedImg = new Image("rect-blur", 1, 1, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testSharpenPPM() throws IOException {
    ioCon.controllerCommandCall("load test/testImgNewOp.ppm rect");
    ioCon.controllerCommandCall("sharpen rect rect-sharpen");
    IImage actualImg = model.getHashMapData("rect-sharpen");
    Pixel p1 = new Pixel(0, 0, 244);
    List<IPixel> listOfPixels = new ArrayList<>();
    listOfPixels.add(p1);
    Image expectedImg = new Image("rect-sharpen", 1, 1, 255, listOfPixels);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testDitherPNG() throws IOException {
    ioCon.controllerCommandCall("load test/testImgNewOp.png rect");
    ioCon.controllerCommandCall("dither rect rect-dither");
    IImage actualImg = model.getHashMapData("rect-dither");
    Pixel p1 = new Pixel(0, 0, 0);
    Pixel p2 = new Pixel(0, 0, 0);
    Pixel p3 = new Pixel(0, 0, 0);
    Pixel p4 = new Pixel(255, 255, 255);
    Pixel p5 = new Pixel(0, 0, 0);
    Pixel p6 = new Pixel(0, 0, 0);
    Pixel p7 = new Pixel(0, 0, 0);
    Pixel p8 = new Pixel(0, 0, 0);
    Pixel p9 = new Pixel(0, 0, 0);
    List<IPixel> listOfPixelsNewOp = addToListHelperNewOp(p1, p2, p3, p4, p5, p6, p7, p8, p9);
    Image expectedImg = new Image("rect-dither", 3, 3, 255, listOfPixelsNewOp);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testDitherBMP() throws IOException {
    ioCon.controllerCommandCall("load test/testImgNewOp.bmp rect");
    ioCon.controllerCommandCall("dither rect rect-dither");
    IImage actualImg = model.getHashMapData("rect-dither");
    Pixel p1 = new Pixel(0, 0, 0);
    Pixel p2 = new Pixel(0, 0, 0);
    Pixel p3 = new Pixel(0, 0, 0);
    Pixel p4 = new Pixel(255, 255, 255);
    Pixel p5 = new Pixel(0, 0, 0);
    Pixel p6 = new Pixel(0, 0, 0);
    Pixel p7 = new Pixel(0, 0, 0);
    Pixel p8 = new Pixel(0, 0, 0);
    Pixel p9 = new Pixel(0, 0, 0);
    List<IPixel> listOfPixelsNewOp = addToListHelperNewOp(p1, p2, p3, p4, p5, p6, p7, p8, p9);
    Image expectedImg = new Image("rect-dither", 3, 3, 255, listOfPixelsNewOp);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testDitherJPG() throws IOException {
    ioCon.controllerCommandCall("load test/testImgNewOp.jpg rect");
    ioCon.controllerCommandCall("dither rect rect-dither");
    IImage actualImg = model.getHashMapData("rect-dither");
    Pixel p1 = new Pixel(255, 255, 255);
    Pixel p2 = new Pixel(0, 0, 0);
    Pixel p3 = new Pixel(0, 0, 0);
    Pixel p4 = new Pixel(0, 0, 0);
    Pixel p5 = new Pixel(0, 0, 0);
    Pixel p6 = new Pixel(0, 0, 0);
    Pixel p7 = new Pixel(0, 0, 0);
    Pixel p8 = new Pixel(0, 0, 0);
    Pixel p9 = new Pixel(0, 0, 0);
    List<IPixel> listOfPixelsNewOp = addToListHelperNewOp(p1, p2, p3, p4, p5, p6, p7, p8, p9);
    Image expectedImg = new Image("rect-dither", 3, 3, 255, listOfPixelsNewOp);
    assertEquals(true, expectedImg.equals(actualImg));
  }

  @Test
  public void testDitherPPM() throws IOException {
    ioCon.controllerCommandCall("load test/testImgNewOp.ppm rect");
    ioCon.controllerCommandCall("dither rect rect-dither");
    IImage actualImg = model.getHashMapData("rect-dither");
    Pixel p1 = new Pixel(0, 0, 0);
    Pixel p2 = new Pixel(0, 0, 0);
    Pixel p3 = new Pixel(0, 0, 0);
    Pixel p4 = new Pixel(255, 255, 255);
    Pixel p5 = new Pixel(0, 0, 0);
    Pixel p6 = new Pixel(0, 0, 0);
    Pixel p7 = new Pixel(0, 0, 0);
    Pixel p8 = new Pixel(0, 0, 0);
    Pixel p9 = new Pixel(0, 0, 0);
    List<IPixel> listOfPixelsNewOp = addToListHelperNewOp(p1, p2, p3, p4, p5, p6, p7, p8, p9);
    Image expectedImg = new Image("rect-dither", 3, 3, 255, listOfPixelsNewOp);
    assertEquals(true, expectedImg.equals(actualImg));
  }
}