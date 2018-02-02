package zanzara.mqtt.tasks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TimerTask;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class PublishJob extends TimerTask {

	private MqttClient client;
	private String topic;
	@Getter private long inverval;

	public PublishJob(MqttClient client, String topic, long interval) throws FileNotFoundException, MqttException, IOException {
		this.topic = topic;
		this.client = client;
		this.inverval = interval;
	}
	
	/**
	 * Implement this method to retrieve sensor data
	 * @return Sensor Data
	 */
	public abstract String pollData();
	
	@Override
	public void run() {
		String data = pollData();
		MqttMessage message = new MqttMessage(data.getBytes());
		try {
			log.debug("Publishing {} => {}", this.topic, data);
			this.client.publish(this.topic, message);
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
}
