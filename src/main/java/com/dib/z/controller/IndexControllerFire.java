package com.dib.z.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dib.z.service.SendNotificationService;

@RestController
public class IndexControllerFire {

	@Autowired
	private SendNotificationService service;

	  @RequestMapping("/send")
	  public String getIndex(Model model) {
		  
		try {
			service.sendPushNotification(1, "hello", "testNotification");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		  
		  return "done";
	  }
	
}
