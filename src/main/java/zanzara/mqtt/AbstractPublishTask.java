package zanzara.mqtt;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public abstract class AbstractPublishTask extends TimerTask {

	private MqttClient client;
	private String topic;
	private long interval;

	public AbstractPublishTask(MqttClient client, String topic, long interval) {
		this.client = client;
		this.topic = topic;
		this.interval = interval;
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(this, 0, this.interval);
	}
	
	public abstract String pollData();
	
	@Override
	public void run() {
		String data = pollData();
		MqttMessage message = new MqttMessage(data.getBytes());
		try {
			this.client.publish(this.topic, message);
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
}
