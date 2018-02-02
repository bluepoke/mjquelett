package zanzara;

import java.util.Arrays;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Sensors;
import oshi.software.os.OperatingSystem;

public class OshiTest {

	public static void main(String[] args) throws InterruptedException {
		SystemInfo si = new SystemInfo();
		
		OperatingSystem os = si.getOperatingSystem();
		
		System.out.println(os.getManufacturer()+" "+os.getFamily()+" "+os.getVersion());
		
		HardwareAbstractionLayer hardware = si.getHardware();
		
		
//		CentralProcessor processor = hardware.getProcessor();
//		double systemCpuLoad = processor.getSystemCpuLoad();
//		while (true) {
//			Thread.sleep(1000);
//			systemCpuLoad = processor.getSystemCpuLoad();
//			System.out.println(String.format("%d", (int)(systemCpuLoad * 100)));
//		}
		
//		Sensors sensors = hardware.getSensors();
//		double cpuTemperature = sensors.getCpuTemperature();
//		cpuTemperature = sensors.getCpuTemperature();
//		System.out.println("CPU Temp: "+cpuTemperature);
//		
//		double cpuVoltage = sensors.getCpuVoltage();
//		System.out.println("CPU Voltage: "+cpuVoltage);
//		
//		int[] fanSpeeds = sensors.getFanSpeeds();
//		System.out.println("Fan speeds: "+Arrays.toString(fanSpeeds));
		
		
		GlobalMemory memory = hardware.getMemory();
		long total = memory.getTotal();
		long available = memory.getAvailable();
		
		double freePercentage = available * 100.0 / total;
		double usedPercentage = 100.0 - freePercentage;
		System.out.println(String.format("Used Memory: %3.1f%%", usedPercentage));
		
	}

}
