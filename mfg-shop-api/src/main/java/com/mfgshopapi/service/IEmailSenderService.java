package com.mfgshopapi.service;

import javax.mail.MessagingException;

public interface IEmailSenderService {
    int sendCodeToConfirmEmail(String email) throws MessagingException;
}
