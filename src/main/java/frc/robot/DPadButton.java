package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;

public final class DPadButton extends Button {
  XboxController joystick;
  Direction direction;

  public DPadButton(XboxController joystick, Direction direction) {
    this.joystick = joystick;
    this.direction = direction;
  }

  @Override
  public boolean get() {
    int degree = joystick.getPOV(0);

    return degree == direction.degree;
  }

  public enum Direction {
    Up(0), Right(90), Down(180), Left(270);

    int degree;

    Direction(int degree) {
      this.degree = degree;
    }
  }
}