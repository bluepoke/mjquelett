package zanzara.mqtt.tasks;

import org.eclipse.paho.client.mqttv3.MqttClient;

import zanzara.oshi.MemoryInfo;

public class MemoryUsagePublishTask extends AbstractPublishTask {

	public MemoryUsagePublishTask(MqttClient client, String topic, long interval) {
		super(client, topic, interval);
	}

	@Override
	public String pollData() {
		return MemoryInfo.getMemoryUsagePercent();
	}

}
