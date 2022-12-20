package com.progress.bootTest;

import com.progress.bootTest.dao.ContractDAO2;
import com.progress.bootTest.services.ContactServiceImpl;
import javax.xml.ws.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootTestApplication implements CommandLineRunner {

    @Autowired
    private ContractDAO2 contactDAO;

    @Override
    public void run(String... args) throws Exception {
        Endpoint.publish("http://localhost:9999/service/contact", new ContactServiceImpl(contactDAO));
    }

    public static void main(String[] args) {
        SpringApplication.run(BootTestApplication.class, args);
    }
}
