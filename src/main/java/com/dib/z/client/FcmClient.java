package com.dib.z.client;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.dib.z.config.FcmSettings;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.TopicManagementResponse;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;

@Service
public class FcmClient {

	public FcmClient(FcmSettings settings) {
		java.nio.file.Path p = Paths.get(settings.getServiceAccountFile());
		try (InputStream serviceAccount = Files.newInputStream(p)) {
			@SuppressWarnings("deprecation")
			FirebaseOptions options = new FirebaseOptions.Builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
			FirebaseApp.initializeApp(options);
		}
		catch (IOException e) {
			System.err.println("init fcm"+ e);
		}
		catch (Exception e) {
			System.err.println("init fcm"+ e);
		}
	}

	public void send(Map<String, String> data)
			throws InterruptedException, ExecutionException {

		Message message = Message.builder().putAllData(data).setTopic("notipool")
				.setWebpushConfig(WebpushConfig.builder().putHeader("ttl", "300")
						.setNotification(new WebpushNotification(data.get("title"),
								data.get("body"), ""))
						.build())
				.build();

		String response = FirebaseMessaging.getInstance().sendAsync(message).get();
		System.out.println("Sent message: " + response);
	}

	public void subscribe(String topic, String clientToken) {
		try {
			TopicManagementResponse response = FirebaseMessaging.getInstance()
					.subscribeToTopicAsync(Collections.singletonList(clientToken), topic).get();
			System.out
			.println(response.getSuccessCount() + " tokens were subscribed successfully");
		}
		catch (InterruptedException | ExecutionException e) {
		}
	}

}
