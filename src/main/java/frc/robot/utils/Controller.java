package frc.robot.utils;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecond;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;

public class Controller {
  public static double applyDriveYFilters(double controllerInput) {
      return -MathUtil.applyDeadband(controllerInput, OIConstants.kDriveDeadband) * DriveConstants.kMaxSpeed.in(MetersPerSecond);
  }

  public static double applyDriveYFilters(DoubleSupplier controllerInput) {
    return applyDriveYFilters(controllerInput.getAsDouble());
  }

  public static double applyDriveXFilters(double controllerInput) {
    return MathUtil.applyDeadband(controllerInput, OIConstants.kDriveDeadband) * DriveConstants.kMaxSpeed.in(MetersPerSecond);
  }

  public static double applyDriveXFilters(DoubleSupplier controllerInput) {
    return applyDriveXFilters(controllerInput.getAsDouble());
  }

  public static double applyDriveRotationFilters(double controllerInput) {
    return -MathUtil.applyDeadband(controllerInput, OIConstants.kDriveDeadband) * DriveConstants.kMaxAngularSpeed.in(RadiansPerSecond);
  }

  public static double applyDriveRotationFilters(DoubleSupplier supplier) {
    return applyDriveRotationFilters(supplier.getAsDouble());
  }

  public static DoubleSupplier applyDriveYFiltersSupplier(DoubleSupplier supplier) {
    return () -> applyDriveYFilters(supplier);
  }

  public static DoubleSupplier applyDriveXFiltersSupplier(DoubleSupplier supplier) {
    return () -> applyDriveXFilters(supplier);
  }
}
