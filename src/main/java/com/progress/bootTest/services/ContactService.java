/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progress.bootTest.services;


import com.progress.bootTest.model.Contact;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
 

 
@WebService
public interface ContactService {
 
    @WebMethod(operationName = "findById")
    Contact findById(final int id);
    
    @WebMethod(operationName = "findAll")
    List<Contact> findAll();
 
}
