package zanzara;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import lombok.extern.slf4j.Slf4j;
import zanzara.mqtt.ServerConnector;
import zanzara.mqtt.tasks.PublishJob;
import zanzara.mqtt.tasks.CpuUsagePublishJob;
import zanzara.mqtt.tasks.MemoryUsagePublishJob;

@Slf4j
public class Zanzara {
	
	public static void main(String[] args) throws FileNotFoundException, MqttException, IOException, InterruptedException {
		
		System.out.println(Logo.getLogo());
		System.out.println();
		
		MqttClient client = ServerConnector.connectClient();
		Set<PublishJob> jobs = new HashSet<>();
		jobs.add(new MemoryUsagePublishJob(client, "zanzara/usedMem", 5000));
		jobs.add(new CpuUsagePublishJob(client, "zanzara/cpu", 1000));
		
		log.debug("Scheduling jobs");
		for (PublishJob job : jobs) {
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(job, 0, job.getInverval());
		}
		
		
	}

}
