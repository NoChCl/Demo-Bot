package frc.robot.commands.motors.Indexer;

import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.Seconds;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.IndexerConstants;
import frc.robot.commands.bases.DoXNTimes;
import frc.robot.commands.generics.GenericFlexMotorControl;
import frc.robot.subsystems.motors.LowerIndexer;

public interface LowerIndexerCommands {
  static class Stop extends GenericFlexMotorControl.Stop {
    public Stop(LowerIndexer indexer) {
      super(indexer);
    }
  }
  static Command Stop(LowerIndexer indexer) {
    return new Stop(indexer);
  }
  
  interface Position {
    interface Await {
      class Actively extends GenericFlexMotorControl.Position.Await.Actively {
        public Actively(LowerIndexer indexer, DoubleSupplier targetSupplier) {
          super(indexer, targetSupplier);
        }
        
        public Actively(LowerIndexer indexer, double targetPosition) {
          super(indexer, () -> targetPosition);
        }
      }

      class Passively extends GenericFlexMotorControl.Position.Await.Passively {
        public Passively(LowerIndexer indexer, DoubleSupplier targetSupplier) {
          super(indexer, targetSupplier);
        }
        
        public Passively(LowerIndexer indexer, double targetPosition) {
          super(indexer, () -> targetPosition);
        }
      }
    }

    class Set extends GenericFlexMotorControl.Position.Set {
      public Set(LowerIndexer indexer, DoubleSupplier targetSupplier) {
        super(indexer, targetSupplier);
      }
      
      public Set(LowerIndexer indexer, double targetPosition) {
        super(indexer, () -> targetPosition);
      }
    }
  }

  interface Abstracts {
    /**
     * Steps the indexer by the indexer step size constant.
     */
    public static class Step extends LowerIndexerCommands.Position.Await.Actively {
      public Step(LowerIndexer indexer) {
        super(indexer, () -> indexer.getPosition() + IndexerConstants.Control.kStepSize.abs(Radians));
        setUseStaticTarget(true);
      }
    }

    static Command Step(LowerIndexer indexer) {
      return new Step(indexer);
    }

    public static class StepAndPause extends SequentialCommandGroup {
      public StepAndPause(LowerIndexer indexer) {
        addCommands(
          new LowerIndexerCommands.Abstracts.Step(indexer),
          Commands.waitSeconds(IndexerConstants.Control.kStepWaitTime.abs(Seconds))
        );
      }
    }

    static Command StepAndPause(LowerIndexer indexer) {
      return new StepAndPause(indexer);
    }

    public class StepNTimes extends DoXNTimes {
      public StepNTimes(LowerIndexer indexer, int n) {
        super(
          n,
          new LowerIndexerCommands.Abstracts.StepAndPause(indexer)
        );
      }
      
    }

    static Command StepNTimes(LowerIndexer indexer, int n) {
      return new StepNTimes(indexer, n);
    }
  }
}
