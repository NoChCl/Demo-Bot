package frc.robot.commands.motors.Indexer;

import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.Seconds;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.IndexerConstants;
import frc.robot.commands.bases.DoXNTimes;
import frc.robot.commands.generics.GenericMotorControl;
import frc.robot.subsystems.motors.UpperIndexer;

public interface UpperIndexerCommands {
  static class Stop extends GenericMotorControl.Stop {
    public Stop(UpperIndexer indexer) {
      super(indexer);
    }
  }
  static Command Stop(UpperIndexer indexer) {
    return new Stop(indexer);
  }
  
  interface Position {
    interface Await {
      class Actively extends GenericMotorControl.Position.Await.Actively {
        public Actively(UpperIndexer indexer, DoubleSupplier targetSupplier) {
          super(indexer, targetSupplier);
        }
        
        public Actively(UpperIndexer indexer, double targetPosition) {
          super(indexer, () -> targetPosition);
        }
      }

      class Passively extends GenericMotorControl.Position.Await.Passively {
        public Passively(UpperIndexer indexer, DoubleSupplier targetSupplier) {
          super(indexer, targetSupplier);
        }
        
        public Passively(UpperIndexer indexer, double targetPosition) {
          super(indexer, () -> targetPosition);
        }
      }
    }

    class Set extends GenericMotorControl.Position.Set {
      public Set(UpperIndexer indexer, DoubleSupplier targetSupplier) {
        super(indexer, targetSupplier);
      }
      
      public Set(UpperIndexer indexer, double targetPosition) {
        super(indexer, () -> targetPosition);
      }
    }
  }

  interface Abstracts {
    /**
     * Steps the indexer by the indexer step size constant.
     */
    public static class Step extends UpperIndexerCommands.Position.Await.Actively {
      public Step(UpperIndexer indexer) {
        super(indexer, () -> indexer.getPosition() + IndexerConstants.Control.kStepSize.abs(Radians));
        setUseStaticTarget(true);
      }
    }

    static Command Step(UpperIndexer indexer) {
      return new Step(indexer);
    }

    public static class StepAndPause extends SequentialCommandGroup {
      public StepAndPause(UpperIndexer indexer) {
        addCommands(
          new UpperIndexerCommands.Abstracts.Step(indexer),
          Commands.waitSeconds(IndexerConstants.Control.kStepWaitTime.abs(Seconds))
        );
      }
    }

    static Command StepAndPause(UpperIndexer indexer) {
      return new StepAndPause(indexer);
    }

    public class StepNTimes extends DoXNTimes {
      public StepNTimes(UpperIndexer indexer, int n) {
        super(
          n,
          new UpperIndexerCommands.Abstracts.StepAndPause(indexer)
        );
      }
      
    }

    static Command StepNTimes(UpperIndexer indexer, int n) {
      return new StepNTimes(indexer, n);
    }
  }
}
