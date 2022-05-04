package com.realestate.courseproject.service;

import com.realestate.courseproject.constants.GlobalConstants;
import com.realestate.courseproject.controller.ContactController;
import com.realestate.courseproject.model.Contact;
import com.realestate.courseproject.repository.ContactRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
//By default, every Bean is of scope Singleton.
//@RequestScope //new bean for every request. Every time you fill form a new bean instance will be created!
//@SessionScope //new bean per session. Bean is created every time new "user" or you via different browsers will access form
//@ApplicationScope //there is one instance for single servlet context regardless of threads, users and requests! Only use if really needed! Use DB of cache instead in other cases
public class ContactService {

    @Autowired
    private ContactRepo contactRepo;

    //private static Logger log = LoggerFactory.getLogger(ContactService.class); - Without @Slf4j
    public ContactService() {
        System.out.println("Hey! Contact Service Bean initialized!!!!");
    }

    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(GlobalConstants.OPEN);
        //contact.setCreatedBy(GlobalConstants.ANONYMOUS); - Don't need since there is AuditAwareImpl
        //contact.setCreatedAt(LocalDateTime.now());

        Contact savedContact = contactRepo.save(contact);
        if(savedContact != null && savedContact.getContactID()>0){
            //if operation was successful
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findOpenStatusMessages(){
        List<Contact> messages = contactRepo.findByStatus(GlobalConstants.OPEN);
        return messages;
    }

    public boolean updateMsgStatus(int contactID){
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepo.findById(contactID);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(GlobalConstants.CLOSED);
        });
        //calling contact.get() because it is optional and we need an actual object of Contact
        Contact updatedContact = contactRepo.save(contact.get());
        if(null != updatedContact && updatedContact.getUpdatedBy()!=null) {
            //if operation was successful
            isUpdated = true;
        }
        return isUpdated;
    }


}
