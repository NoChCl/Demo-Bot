package frc.robot.commands.motors;

import static edu.wpi.first.units.Units.RPM;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IntakeConstants;
import frc.robot.commands.generics.GenericMotorControl;
import frc.robot.subsystems.motors.Intake;

public interface IntakeCommands {
  interface Driver {
    static class Stop extends GenericMotorControl.Stop {
      public Stop(Intake intake) {
        super(intake);
      }
    }
    static Command Stop(Intake intake) {
      return new Stop(intake);
    }

    public interface Run {
      public interface Await {
        public static class Actively extends GenericMotorControl.Velocity.Await.Actively {
          public Actively(Intake intake) {
            super(intake, IntakeConstants.Control.kIntakeSpeed.in(RPM));
          }
        }

        static Command Actively(Intake intake) {
          return new Actively(intake);
        }
      
        public static class Passively extends GenericMotorControl.Velocity.Await.Passively {
          public Passively(Intake intake) {
            super(intake, IntakeConstants.Control.kIntakeSpeed.in(RPM));
          }
        }

        static Command Passively(Intake intake) {
          return new Passively(intake);
        }
      }

      public class Indefinitely extends GenericMotorControl.Velocity.Set {
        public Indefinitely(Intake intake, double speed) {
          super(intake, speed);
        }

        public Indefinitely(Intake intake) {
          this(intake, IntakeConstants.Control.kIntakeSpeed.in(RPM));
        }

      }

      public static Command Indefinitely(Intake intake, double speed) {
        return new Indefinitely(intake, speed);
      }

      public static Command Indefinitely(Intake intake) {
        return new Indefinitely(intake);
      }
    }
  }

}
