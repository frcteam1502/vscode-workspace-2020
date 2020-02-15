package frc.robot;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Button;

public class Joystick {

  private edu.wpi.first.wpilibj.Joystick joystick;
  private boolean reversed = false;

  public final Button TRIGGER;
  public final Button THUMB;
  public final Button UPPER_THREE;
  public final Button UPPER_FOUR;
  public final Button UPPER_FIVE;
  public final Button UPPER_SIX;
  public final Button LOWER_SEVEN;
  public final Button LOWER_EIGHT;
  public final Button LOWER_NINE;
  public final Button LOWER_TEN;
  public final Button LOWER_ELEVEN;
  public final Button LOWER_TWELVE;

  public Joystick(int id) {
    joystick = new edu.wpi.first.wpilibj.Joystick(id);
    TRIGGER = new JoystickButton(joystick, 1);
    THUMB = new JoystickButton(joystick, 2);
    UPPER_THREE = new JoystickButton(joystick, 3);
    UPPER_FOUR = new JoystickButton(joystick, 4);
    UPPER_FIVE = new JoystickButton(joystick, 5);
    UPPER_SIX = new JoystickButton(joystick, 6);
    LOWER_SEVEN = new JoystickButton(joystick, 7);
    LOWER_EIGHT = new JoystickButton(joystick, 8);
    LOWER_NINE = new JoystickButton(joystick, 9);
    LOWER_TEN = new JoystickButton(joystick, 10);
    LOWER_ELEVEN = new JoystickButton(joystick, 11);
    LOWER_TWELVE = new JoystickButton(joystick, 12);
  }

  public double getX() {
    return joystick.getX();
  }

  public double getY() {
    return reversed ? joystick.getY() : -joystick.getY();
  }

  public double getTwist() {
    return joystick.getTwist();
  }

  public double getThrottle() {
    return -joystick.getThrottle();
  }

  public boolean isReversed() {
    return reversed;
  }

  public void toggleDirection() {
    reversed = !reversed;
  }

}
