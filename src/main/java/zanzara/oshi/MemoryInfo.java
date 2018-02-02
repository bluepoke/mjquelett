package zanzara.oshi;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

public class MemoryInfo {

	public static String getMemoryUsagePercent() {
		String memUsage = null;
		SystemInfo si = new SystemInfo();
		HardwareAbstractionLayer hw = si.getHardware();
		GlobalMemory mem = hw.getMemory();
		long avail = mem.getAvailable();
		long total = mem.getTotal();
		
		double freePercent = avail * 100.0 / total;
		double usedPercent = 100.0 - freePercent;
		
		memUsage = String.format("%3.1f%%", usedPercent);
		
		return memUsage;
	}
}
