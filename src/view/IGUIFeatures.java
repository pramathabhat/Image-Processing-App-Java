package view;

import java.awt.event.ActionListener;

import javax.swing.event.ChangeListener;

import model.IImage;

/**
 * The IGUIFeatures interface defines the contract for any GUI view that supports
 * image manipulation features such as brightness adjustment and image loading.
 * This interface provides methods to set visibility and value of the brightness and darken sliders,
 * load an image, set an action listener and a change listener, and reset the combo box.
 */
public interface IGUIFeatures {
  /**
   * Sets the visibility of the brighten slider panel.
   *
   * @param visible a boolean value indicating whether the panel should be visible or not
   */
  void setBrightenSliderPanelVisible(boolean visible);

  /**
   * Sets the visibility of the darken slider panel.
   *
   * @param visible a boolean value indicating whether the panel should be visible or not
   */
  void setDarkenSliderPanelVisible(boolean visible);

  /**
   * Sets the value of the brighten slider.
   *
   * @param brightenValue an integer representing the value of the slider
   */
  void setBrightenSliderValue(int brightenValue);

  /**
   * Sets the value of the darken slider.
   *
   * @param darkenValue an integer representing the value of the slider
   */
  void setDarkenSliderValue(int darkenValue);

  /**
   * Loads the given image into the view.
   *
   * @param image an instance of IImage representing the image to be loaded
   */
  void loadGivenImage(IImage image);

  /**
   * Sets the action listener for the view.
   *
   * @param listener an instance of ActionListener to be set as the action
   *                 listener for the view
   */
  void setListener(ActionListener listener);

  /**
   * Sets the change listener for the view.
   *
   * @param listener an instance of ChangeListener to be set as the change
   *                 listener for the view
   */
  void setChangeListener(ChangeListener listener);

  /**
   * Resets the combo box in the view.
   */
  void resetComboBox();

  /**
   * Display a popup with a meaningful message.
   * @param message to be displayed.
   */
  void showPopup(String message);
}