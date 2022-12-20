/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progress.bootTest.dao;

import com.progress.bootTest.model.Contact;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dimitar Saykov
 */
@Component
public interface ContractDAO2 {

    public void saveOrUpdate(Contact contact);

    public void delete(int contactId);

    public Contact get(int contactId);

    public List<Contact> list();

}
