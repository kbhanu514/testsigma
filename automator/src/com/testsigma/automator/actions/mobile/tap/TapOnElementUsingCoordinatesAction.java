package com.testsigma.automator.actions.mobile.tap;

import com.testsigma.automator.actions.mobile.MobileElementAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.util.Arrays;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

public class TapOnElementUsingCoordinatesAction extends MobileElementAction {

  public static final String SUCCESS_MESSAGE = "Successfully executed tap action on the element using it's coordinates.";
  public static final String FAILURE_MESSAGE = "Unable to click on the element <b>\"%s\"</b> using it's coordinates.";

  @Override
  protected void execute() throws Exception {

    findElement();
    Rectangle rect = getElement().getRect();

    PointerInput FINGER = new PointerInput(TOUCH, "finger");
    Sequence tap = new Sequence(FINGER, 1)
            .addAction(FINGER.createPointerMove(ofMillis(0), viewport(), rect.x + rect.width /2, rect.y + rect.height /2))
            .addAction(FINGER.createPointerDown(LEFT.asArg()))
            .addAction(new Pause(FINGER, ofMillis(2)))
            .addAction(FINGER.createPointerUp(LEFT.asArg()));
    getDriver().perform(Arrays.asList(tap));
    setSuccessMessage(SUCCESS_MESSAGE);
  }

  @Override
  protected void handleException(Exception e) {
    super.handleException(e);
    setErrorMessage(String.format(FAILURE_MESSAGE, getLocatorValue()));
  }

}
