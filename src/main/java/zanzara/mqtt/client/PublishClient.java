package zanzara.mqtt.client;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import zanzara.mqtt.ServerConnector;
import zanzara.mqtt.tasks.MemoryUsagePublishTask;

public class PublishClient {

	public static void main(String[] args) throws FileNotFoundException, MqttException, IOException {
		MqttClient client = ServerConnector.connectClient("zanzara_publish");
		
		MemoryUsagePublishTask publishTask = new MemoryUsagePublishTask(client, "zanzara/cpu", 5000);
	}
}
