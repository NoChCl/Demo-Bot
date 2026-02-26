package frc.robot.commands.motors;

import static edu.wpi.first.units.Units.Rotations;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClimberConstants;
import frc.robot.commands.generics.GenericMotorControl;
import frc.robot.subsystems.motors.Climber;

public interface ClimberCommands {
  interface Await {
    interface Extend {
      public class Actively extends GenericMotorControl.Position.Await.Actively {
        public Actively(Climber climber) {
          super(climber, ClimberConstants.Control.kExtendedPosition.in(Rotations));
        }
      }

      static Command Actively(Climber climber) {
        return new Actively(climber);
      }

      public class Passively extends GenericMotorControl.Position.Await.Passively {
        public Passively(Climber climber) {
          super(climber, ClimberConstants.Control.kExtendedPosition.in(Rotations));
        }
      }

      static Command Passively(Climber climber) {
        return new Passively(climber);
      }
    }

    interface Retract {
      public class Actively extends GenericMotorControl.Position.Await.Actively {
        public Actively(Climber climber) {
          super(climber, ClimberConstants.Control.kRetractedPosition.in(Rotations));
        }
      }

      static Command Actively(Climber climber) {
        return new Actively(climber);
      }

      public class Passively extends GenericMotorControl.Position.Await.Passively {
        public Passively(Climber climber) {
          super(climber, ClimberConstants.Control.kRetractedPosition.in(Rotations));
        }
      }

      static Command Passively(Climber climber) {
        return new Passively(climber);
      }
    }

  }
}
