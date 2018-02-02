package zanzara.mqtt.tasks;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import zanzara.oshi.MemoryInfo;

public class MemoryUsagePublishJob extends PublishJob {

	public MemoryUsagePublishJob(MqttClient client, String topic, long interval) throws FileNotFoundException, MqttException, IOException {
		super(client, topic, interval);
	}

	@Override
	public String pollData() {
		return MemoryInfo.getMemoryUsagePercent();
	}

}
