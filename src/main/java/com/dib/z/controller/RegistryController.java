package com.dib.z.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dib.z.client.FcmClient;
import com.dib.z.model.UserTokenEntity;
import com.dib.z.repository.UserTokenEntityRepository;

@RestController
@CrossOrigin
public class RegistryController {

  private final FcmClient fcmClient;
  
  @Autowired
  private UserTokenEntityRepository userTokenEntityRepository;
  

  public RegistryController(FcmClient fcmClient) {
    this.fcmClient = fcmClient;
  }

  @RequestMapping("/dib-register")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void register(@RequestParam("token") String token) {
    this.fcmClient.subscribe("notipool", token);
    UserTokenEntity entity = new UserTokenEntity();
    entity.setToken(token);
    entity.setUserId(1);
    userTokenEntityRepository.save(entity);
  }
}