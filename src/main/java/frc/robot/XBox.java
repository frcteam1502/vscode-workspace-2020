/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * Add your docs here.
 */
public class XBox {
  private final XboxController controller;

  public final Button DP_UP, DP_LEFT, DP_DOWN, DP_RIGHT;
  public final Button A, B, X, Y;
  public final Button LB, RB;
  public final Button START, BACK;
  public final Button L3; // TODO make sure this is right

  enum Direction {
    Up(0), Right(90), Down(180), Left(270);

    int degree;

    Direction(int degree) {
      this.degree = degree;
    }
  }

  public XBox(int port) {
    controller = new XboxController(port);
    DP_UP = new DPad(Direction.Up);
    DP_LEFT = new DPad(Direction.Left);
    DP_DOWN = new DPad(Direction.Down);
    DP_RIGHT = new DPad(Direction.Right);
    A = new JoystickButton(controller, 1);
    B = new JoystickButton(controller, 2);
    X = new JoystickButton(controller, 3);
    Y = new JoystickButton(controller, 4);
    LB = new JoystickButton(controller, 5);
    RB = new JoystickButton(controller, 6);
    START = new JoystickButton(controller, 7);
    BACK = new JoystickButton(controller, 8);
    L3 = new JoystickButton(controller, 9);
  }

  class DPad extends Button {

    private final Direction direction;

    public DPad(Direction direction) {
      this.direction = direction;
    }

    public boolean get() {
      int degree = controller.getPOV(0);

      return degree == direction.degree;
    }

  }

  public void setLeftRumble(int intensity) {
    controller.setRumble(RumbleType.kLeftRumble, intensity);
  }

  public void setRightRumble(int intensity) {
    controller.setRumble(RumbleType.kRightRumble, intensity);
  }
}