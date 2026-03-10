package frc.robot.commands.motors;

import static edu.wpi.first.units.Units.RPM;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.FeederConstants;
import frc.robot.commands.generics.GenericFlexMotorControl;
import frc.robot.subsystems.motors.BeltFeeder;

public interface FeederCommands {
  public class Stop extends GenericFlexMotorControl.Stop {
    public Stop(BeltFeeder feeder) {
      super(feeder);
    }
  }
  static Command Stop(BeltFeeder feeder) {
    return new Stop(feeder);
  }

  interface Await {
    class Actively extends GenericFlexMotorControl.Velocity.Await.Actively {
      public Actively(BeltFeeder feeder) {
        super(feeder, FeederConstants.Control.kTargetSpeed.in(RPM));
      }
    }

    static Command Actively(BeltFeeder feeder) {
      return new Actively(feeder);
    }

    class Passively extends GenericFlexMotorControl.Velocity.Await.Passively {
      public Passively(BeltFeeder feeder) {
        super(feeder, FeederConstants.Control.kTargetSpeed.in(RPM));
      }
    }

    static Command Passively(BeltFeeder feeder) {
      return new Passively(feeder);
    }
  }
  class Run extends GenericFlexMotorControl.Velocity.Set {
    public Run(BeltFeeder feeder) {
      super(feeder, FeederConstants.Control.kTargetSpeed.in(RPM));
    }
  }

  static Command Run(BeltFeeder feeder) {
    return new Run(feeder);
  }
}
