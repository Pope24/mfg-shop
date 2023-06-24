package com.mfgshopapi.controller;

import com.mfgshopapi.service.IEmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/public")
@CrossOrigin("*")
public class TestEmailSenderController {
    @Autowired
    private IEmailSenderService emailSenderService;
    @GetMapping("/test-email")
    public ResponseEntity<?> testSendEmail() throws MessagingException {
        emailSenderService.sendCodeToConfirmEmail("chinhlvde170423@fpt.edu.vn");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
