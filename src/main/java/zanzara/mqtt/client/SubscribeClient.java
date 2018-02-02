package zanzara.mqtt.client;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import lombok.extern.slf4j.Slf4j;
import zanzara.mqtt.ServerConnector;

@Slf4j
public class SubscribeClient {

	public static void main(String[] args) throws FileNotFoundException, MqttException, IOException {
		MqttClient client = ServerConnector.connectClient();
		client.setCallback(new MqttCallback() {
			
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				log.info("{} <= {}", topic, new String(message.getPayload()));
			}
			
			@Override
			public void deliveryComplete(IMqttDeliveryToken token) {
				
			}
			
			@Override
			public void connectionLost(Throwable cause) {
				log.error("Connection lost!");
			}
		});
		String topic = "zanzara/#";
		log.debug("Subscribing to {}", topic);
		client.subscribe(topic);
	}

}
