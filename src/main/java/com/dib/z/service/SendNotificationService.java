package com.dib.z.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dib.z.client.FcmClient;
import com.dib.z.repository.UserTokenEntityRepository;

@Service
public class SendNotificationService {

	@Autowired
	private FcmClient fcmClient;

	@Autowired
	private UserTokenEntityRepository repository;

	private int seq = 0;

	public SendNotificationService() {

	}

	public SendNotificationService(FcmClient fcmClient) {
		this.fcmClient = fcmClient;
	}

	public void sendPushNotification(int uid, String title, String body) throws InterruptedException, ExecutionException {
		if(repository.findByUserId(uid)!=null) {
			Map<String, String> data = new HashMap<>();
			data.put("id", String.valueOf(uid));
			data.put("seq", String.valueOf(this.seq++));
			data.put("ts", String.valueOf(System.currentTimeMillis()));
			data.put("title", title);
			data.put("body", body);
			if(repository.findByUserId(uid).getToken()!=null) {
				String token = repository.findByUserId(uid).getToken();
				this.fcmClient.subscribe("chuck",token);
				this.fcmClient.send(data);
			}
		}
	}

}
