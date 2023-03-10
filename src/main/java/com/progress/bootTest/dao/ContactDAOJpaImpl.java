/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progress.bootTest.dao;

import com.progress.bootTest.model.Contact;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dimitar
 */

@Service
public class ContactDAOJpaImpl extends AbstractDAO<Contact> implements ContractDAO2 {

    public ContactDAOJpaImpl() {
        super(Contact.class);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void saveOrUpdate(Contact contact) {
        if (contact.getId() != 0) {
            update(contact);
        } else {
            create(contact);
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(int contactId) {
        super.deleteById(contactId);
    }

    @Override
    public Contact get(int contactId) {
        return findOne(contactId);
    }

    @Override
    public List<Contact> list() {
        return findAll();

    }

}
