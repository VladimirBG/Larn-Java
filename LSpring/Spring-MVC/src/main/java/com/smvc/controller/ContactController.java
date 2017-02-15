package com.smvc.controller;

import com.smvc.model.Contact;
import com.smvc.service.ContactService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/contacts")
@Controller
public class ContactController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ContactController.class);
    private ContactService contactService;
    @RequestMapping("/contacts")
    public String list(Model uiModel) {
        logger.info("Listining contacts");
        List<Contact> contacts = contactService.findAll();
        uiModel.addAttribute("contacts", contacts);
        logger.info("No. of contscts: " + contacts.size());
        return "contacts/list";
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }
}
