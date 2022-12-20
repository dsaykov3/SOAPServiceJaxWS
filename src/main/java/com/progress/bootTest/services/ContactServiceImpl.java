/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progress.bootTest.services;

import com.progress.bootTest.dao.ContractDAO2;
import com.progress.bootTest.model.Contact;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.jws.WebService;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebService(endpointInterface = "com.progress.bootTest.services.ContactService")
public class ContactServiceImpl implements ContactService {

    private ContractDAO2 contactDAO;

    public ContactServiceImpl(ContractDAO2 contactDAO) {
        this.contactDAO = contactDAO;
    }

    @PostConstruct
    public void init() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public Contact findById(final int id) {       
        return contactDAO.get(id);
    }
    
     @Override
     public List<Contact> findAll() {
        return contactDAO.list();
    }

}
