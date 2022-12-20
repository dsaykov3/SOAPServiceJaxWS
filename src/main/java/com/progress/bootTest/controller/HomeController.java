package com.progress.bootTest.controller;

import com.progress.bootTest.dao.ContactDAO;
import com.progress.bootTest.model.Contact;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller routes accesses to the application to the appropriate hanlder
 * methods.
 *
 * @author www.codejava.net
 *
 */
@Controller
public class HomeController {

    @Autowired
    private ContactDAO contactDAO;

    @RequestMapping(value = "/")
    public ModelAndView listContact(ModelAndView model) throws IOException {
        //List<Contact> listContact = contactDAO.list();
        List<Contact> listContact = new ArrayList<>();
        contactDAO.findAll().forEach(listContact::add);
        model.addObject("listContact", listContact);
        model.setViewName("home");

        return model;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> listContactAll() throws IOException {
        List<Contact> listContact = new ArrayList<>();
        contactDAO.findAll().forEach(listContact::add);
        return listContact;
    }

    @RequestMapping(value = "/contact/{id}")
    @ResponseBody
    public Contact listContactById(@PathVariable int id) throws IOException {
        return contactDAO.findById(id).get();
    }

    @RequestMapping(value = "/contactEntity/{id}")
    public ResponseEntity<Contact> listContactByIdWithEntity(@PathVariable int id) throws IOException {
        Contact contact = null;
        try{
            contact = contactDAO.findById(id).get();
        } catch(Exception e){
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, "The search by id in contract rest was not successful.");
        }
        HttpStatus status = contact != null
                ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(contact, status);
    }

    @RequestMapping(value = "/getcontact/{id}")
    @ResponseBody
    public Contact listContactByIdFromRest(@PathVariable int id) {
        RestTemplate rest = new RestTemplate();
        return rest.getForObject("http://localhost:8080/contact/{id}",
                Contact.class, id);
    }

    @RequestMapping(value = "/newContact", method = RequestMethod.GET)
    public ModelAndView newContact(ModelAndView model) {
        Contact newContact = new Contact();
        model.addObject("contact", newContact);
        model.setViewName("contact_details");
        return model;
    }

    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    public ModelAndView saveContact(@ModelAttribute Contact contact) {
        contactDAO.save(contact);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public ModelAndView deleteContact(HttpServletRequest request) {
        int contactId = Integer.parseInt(request.getParameter("id"));
        contactDAO.delete(contactDAO.findById(contactId).get());
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/editContactThymeLeaf", method = RequestMethod.GET)
    public ModelAndView editContactThymeLeaf(HttpServletRequest request) {
        int contactId = Integer.parseInt(request.getParameter("id"));
        Contact contact = contactDAO.findById(contactId).get();
        ModelAndView model = new ModelAndView("contact_details.html");
        model.addObject("contact", contact);

        return model;
    }

    @RequestMapping(value = "/editContact", method = RequestMethod.GET)
    public ModelAndView editContact(HttpServletRequest request) {
        int contactId = Integer.parseInt(request.getParameter("id"));
        Contact contact = contactDAO.findById(contactId).get();
        ModelAndView model = new ModelAndView("contact_details.jsp");
        model.addObject("contact", contact);

        return model;
    }

    @RequestMapping(value = "/thymeTest", method = RequestMethod.GET)
    public ModelAndView showThymeLeaf(HttpServletRequest request) {
        return new ModelAndView("thymeTest.html");
    }

    //Spring Security see this :
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login.jsp");

        return model;

    }

}
