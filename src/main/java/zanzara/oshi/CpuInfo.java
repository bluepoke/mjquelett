package zanzara.oshi;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

public class CpuInfo {

	public static String getCpuUsage() {
		String cpuUsage = null;
		SystemInfo si = new SystemInfo();
		HardwareAbstractionLayer hw = si.getHardware();
		
		CentralProcessor processor = hw.getProcessor();
		double load = processor.getSystemCpuLoad();
				
		cpuUsage = String.format("%3.1f%%", load*100.0);
		
		return cpuUsage;
	}
}
