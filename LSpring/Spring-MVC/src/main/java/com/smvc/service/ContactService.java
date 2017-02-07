package com.smvc.service;

import com.smvc.model.Contact;

import java.util.List;

/**
 * Created by Vladimir on 07.02.2017.
 */
public interface ContactService {
    List<Contact> findAll();
    Contact getById(Long id);
    Contact save(Contact contact);
}
