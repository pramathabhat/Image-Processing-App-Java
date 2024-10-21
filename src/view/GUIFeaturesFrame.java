package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import model.IImage;
import model.IPixel;
import model.Image;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * This class represents the main GUI window that contains different elements, such as
 * image display, sliders, buttons, and combo boxes for image operations. It also serves as
 * the listener for all the GUI components.
 */
public class GUIFeaturesFrame extends JFrame implements IGUIFeatures {
  private JPanel histogramPanel;
  private JLabel imageLabel = new JLabel();
  private JScrollPane imageScrollPane = new JScrollPane();
  private JPanel imagePanel;
  private JPanel sliderPanel = new JPanel();
  private JPanel darkenSliderPanel = new JPanel();
  private JSlider brightnessSlider;
  private JSlider darknessSlider;
  private JButton fileOpenButton;
  private JButton fileSaveButton;
  private JComboBox<String> combobox;

  /**
   * Creates a new instance of the GUIFeaturesFrame class.
   * Initializes the main window with the appropriate size and title.
   * Sets up the main panel with box layout and adds it to the main scroll pane.
   * Creates dialog boxes, file open and save buttons, and combo boxes for image operations.
   */
  public GUIFeaturesFrame() {
    super();
    setTitle("Image Operations");
    setSize(1000, 1000);


    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder(""));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);

    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    fileOpenButton = new JButton("Open an image");
    fileOpenButton.setActionCommand("Open file");
    fileopenPanel.add(fileOpenButton);

    //file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    fileSaveButton = new JButton("Save the image");
    fileSaveButton.setActionCommand("Save file");
    filesavePanel.add(fileSaveButton);

    //combo boxes
    JPanel comboboxPanel = new JPanel();
    comboboxPanel.setBorder(BorderFactory.createTitledBorder("Select an image operation "
            + "that you want to perform: "));
    comboboxPanel.setLayout(new BoxLayout(comboboxPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(comboboxPanel);

    String[] options = {"", "Brighten", "Darken", "Sharpen", "Blur", "Dither", "Greyscale",
                        "Horizontal Flip", "Vertical Flip", "Sepia", "RGB Split",
                        "RGB Combine", "Greyscale-Value", "Greyscale-Intensity",
                        "Greyscale-Luma", "Greyscale-Red", "Greyscale-Green",
                        "Greyscale-Blue",
    };
    combobox = new JComboBox<String>();
    //the event listener when an option is selected
    combobox.setActionCommand("Image Operations");
    for (int i = 0; i < options.length; i++) {
      combobox.addItem(options[i]);
    }
    comboboxPanel.add(combobox);

    // Create a new JPanel to hold the slider
    sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.PAGE_AXIS));
    dialogBoxesPanel.add(sliderPanel);
    darkenSliderPanel.setLayout(new BoxLayout(darkenSliderPanel, BoxLayout.PAGE_AXIS));
    dialogBoxesPanel.add(darkenSliderPanel);

    // Add a new JLabel to display the current value of the slider
    JLabel brightnessLabel = new JLabel("Brightness:");
    sliderPanel.add(brightnessLabel);
    brightnessSlider = new JSlider(0, 255, 0);
    brightnessSlider.setMajorTickSpacing(50);
    brightnessSlider.setMinorTickSpacing(10);
    brightnessSlider.setPaintTicks(true);
    brightnessSlider.setPaintLabels(true);
    sliderPanel.add(brightnessSlider);
    setBrightenSliderPanelVisible(false);

    // Add a new JLabel to display the current value of the slider
    JLabel darknessLabel = new JLabel("Darkness:");
    darkenSliderPanel.add(darknessLabel);
    darknessSlider = new JSlider(-255, 0, 0);
    darknessSlider.setMajorTickSpacing(50);
    darknessSlider.setMinorTickSpacing(10);
    darknessSlider.setPaintTicks(true);
    darknessSlider.setPaintLabels(true);
    darkenSliderPanel.add(darknessSlider);
    setDarkenSliderPanelVisible(false);

    //show an image with a scrollbar
    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(imagePanel);

    //show an image with a scrollbar
    histogramPanel = new JPanel();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    histogramPanel.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(histogramPanel);

    // Create a pop-up window
    JFrame frame = new JFrame("Load RGB Images");
    frame.setSize(400, 300);
    frame.setLocationRelativeTo(null);

    // Create file input fields for red, green, and blue channels
    JFileChooser redChooser = new JFileChooser();
    JPanel redPanel = new JPanel(new BorderLayout());
    redPanel.add(new JLabel("Red channel image:"), BorderLayout.NORTH);
    redPanel.add(redChooser, BorderLayout.CENTER);

    JFileChooser greenChooser = new JFileChooser();
    JPanel greenPanel = new JPanel(new BorderLayout());
    greenPanel.add(new JLabel("Green channel image:"), BorderLayout.NORTH);
    greenPanel.add(greenChooser, BorderLayout.CENTER);

    JFileChooser blueChooser = new JFileChooser();
    JPanel bluePanel = new JPanel(new BorderLayout());
    bluePanel.add(new JLabel("Blue channel image:"), BorderLayout.NORTH);
    bluePanel.add(blueChooser, BorderLayout.CENTER);

    JPanel panel = new JPanel(new BorderLayout());
    panel.add(redPanel, BorderLayout.NORTH);
    panel.add(greenPanel, BorderLayout.CENTER);
    panel.add(bluePanel, BorderLayout.SOUTH);
  }

  private void generateHistogram(BufferedImage loadedImage) {
    Raster raster = loadedImage.getRaster();
    final int w = loadedImage.getWidth();
    final int h = loadedImage.getHeight();
    double[] r = new double[w * h];
    r = raster.getSamples(0, 0, w, h, 0, r);
    HistogramDataset dataset = new HistogramDataset();
    dataset.addSeries("Red", r, 256);
    r = raster.getSamples(0, 0, w, h, 1, r);
    dataset.addSeries("Green", r, 256);
    r = raster.getSamples(0, 0, w, h, 2, r);
    dataset.addSeries("Blue", r, 256);
    double[] i = calculateIntensityChannel(raster, w, h);
    dataset.addSeries("Intensity", i, 256);
    Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.BLACK};
    XYSeriesCollection lineDataset = createLineDataset(dataset);
    JFreeChart chart = createLineChart(lineDataset);
    setLineColors(chart, colors);
    displayHistogram(chart);
  }

  private double[] calculateIntensityChannel(Raster raster, int w, int h) {
    double[] i = new double[w * h];
    for (int j = 0; j < i.length; j++) {
      i[j] = (raster.getSample(j % w, j / w, 0) + raster.getSample(j % w, j / w, 1)
              + raster.getSample(j % w, j / w, 2)) / 3.0;
    }
    return i;
  }

  private XYSeriesCollection createLineDataset(HistogramDataset dataset) {
    XYSeriesCollection lineDataset = new XYSeriesCollection();
    for (int k = 0; k < dataset.getSeriesCount(); k++) {
      double[] values = new double[dataset.getItemCount(k)];
      for (int j = 0; j < values.length; j++) {
        values[j] = dataset.getY(k, j).doubleValue();
      }
      XYSeries series = new XYSeries(dataset.getSeriesKey(k));
      for (int j = 0; j < values.length; j++) {
        series.add(j, values[j]);
      }
      lineDataset.addSeries(series);
    }
    return lineDataset;
  }

  private JFreeChart createLineChart(XYSeriesCollection lineDataset) {
    return ChartFactory.createXYLineChart("Histogram", "Value", "Count",
            lineDataset, PlotOrientation.VERTICAL, true, true, false);
  }

  private void setLineColors(JFreeChart chart, Color[] colors) {
    XYPlot plot = (XYPlot) chart.getPlot();
    for (int k = 0; k < colors.length; k++) {
      plot.getRenderer().setSeriesPaint(k, colors[k]);
    }
  }

  private void displayHistogram(JFreeChart chart) {
    ChartPanel panel = new ChartPanel(chart);
    panel.setMouseWheelEnabled(true);
    histogramPanel.removeAll();
    histogramPanel.add(panel);
  }

  @Override
  public void loadGivenImage(IImage image) {
    BufferedImage loadedImage = null;
    if (image instanceof Image) {
      imagePanel.removeAll();
      imageLabel = new JLabel();
      imageScrollPane = new JScrollPane(imageLabel);

      loadedImage = new BufferedImage(image.getWidth(), image.getHeight(),
              BufferedImage.TYPE_INT_RGB);
      for (int y = 0; y < image.getHeight(); y++) {
        for (int x = 0; x < image.getWidth(); x++) {
          IPixel pixel = image.getImageData().get(y * image.getWidth() + x);
          int rgb = (pixel.getRed() << 16) | (pixel.getGreen() << 8) | pixel.getBlue();
          loadedImage.setRGB(x, y, rgb);
        }

        // Use the BufferedImage to create a new ImageIcon
        ImageIcon icon = new ImageIcon(loadedImage);
        imageLabel.setIcon(icon);
        imageScrollPane.setPreferredSize(new Dimension(100, 600));
        imagePanel.add(imageScrollPane);
        revalidate();
        repaint();
      }
    }
    generateHistogram(loadedImage);
  }

  @Override
  public void setListener(ActionListener listener) {
    fileOpenButton.addActionListener(listener); // Rather than adding *this* as a listener,
    fileSaveButton.addActionListener(listener); // add the provided one instead.
    combobox.addActionListener(listener);
  }

  @Override
  public void setChangeListener(ChangeListener listener) {
    brightnessSlider.addChangeListener(listener);
    darknessSlider.addChangeListener(listener);
  }

  @Override
  public void setBrightenSliderPanelVisible(boolean visible) {
    sliderPanel.setVisible(visible);
  }

  @Override
  public void setDarkenSliderPanelVisible(boolean visible) {
    darkenSliderPanel.setVisible(visible);
  }

  @Override
  public void setBrightenSliderValue(int brightenValue) {
    brightnessSlider.setValue(brightenValue);
  }

  @Override
  public void setDarkenSliderValue(int darkenValue) {
    darknessSlider.setValue(darkenValue);
  }

  @Override
  public void resetComboBox() {
    combobox.setSelectedIndex(-1);
  }

  @Override
  public void showPopup(String message) {
    JOptionPane.showMessageDialog(this, message, "Message",
            JOptionPane.PLAIN_MESSAGE);
  }
}
