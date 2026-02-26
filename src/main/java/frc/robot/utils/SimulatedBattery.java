package frc.robot.utils;

import static edu.wpi.first.units.Units.Volts;

import java.util.ArrayList;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SimulatedBattery extends SubsystemBase {
  private Voltage m_Voltage = Volts.of(RobotController.getBatteryVoltage());

  private ArrayList<DoubleSupplier> m_Suppliers = new ArrayList<DoubleSupplier>();

  public Voltage getVoltage() {
    return m_Voltage;
  }

  public void registerPowerDrain(DoubleSupplier supplier) {
    m_Suppliers.add(supplier);
  }

  @Override
  public void simulationPeriodic() {
    Supplier<double[]> currents = (() -> {
      double[] current = new double[m_Suppliers.size()];
      for (int i = 0; i < m_Suppliers.size(); i++) {
        current[i] = (m_Suppliers.get(i).getAsDouble());
      }
      return current;
    });
    m_Voltage = Volts.of(BatterySim.calculateDefaultBatteryLoadedVoltage(currents.get()));
    RoboRioSim.setVInVoltage(m_Voltage.in(Volts));
  }
}
