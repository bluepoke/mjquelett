package zanzara.mqtt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class ServerConnector {


	public static MqttClient connectClient(String clientId) throws MqttException, FileNotFoundException, IOException {
		Properties connectionProps = new Properties();
		connectionProps.load(new FileInputStream("connection.properties"));
		
		String serverURI = connectionProps.getProperty("server.uri");

		MqttClient client = new MqttClient(serverURI, clientId);
		MqttConnectOptions options = new MqttConnectOptions();
		
		String userName = connectionProps.getProperty("username");
		String password = connectionProps.getProperty("password");
		if (userName != null && password != null) {
			options.setUserName(userName);
			options.setPassword(password.toCharArray());
		}
		client.connect(options);
		return client;
	}
}
