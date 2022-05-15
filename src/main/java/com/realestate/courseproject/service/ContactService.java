package com.realestate.courseproject.service;

import com.realestate.courseproject.constants.GlobalConstants;
import com.realestate.courseproject.controller.ContactController;
import com.realestate.courseproject.model.Contact;
import com.realestate.courseproject.repository.ContactRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
//By default, every Bean is of scope Singleton.
//@RequestScope //new bean for every request. Every time you fill form a new bean instance will be created!
//@SessionScope //new bean per session. Bean is created every time new "user" or you via different browsers will access form
//@ApplicationScope //there is one instance for single servlet context regardless of threads, users and requests! Only use if really needed! Use DB of cache instead in other cases

@Service
@Slf4j
public class ContactService {

    @Autowired
    private ContactRepo contactRepo;

    //private static Logger log = LoggerFactory.getLogger(ContactService.class); - Without @Slf4j
    public ContactService() {    }

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

    public Page<Contact> findOpenStatusMessages(int pageNum, String sortField, String sortDir){
        int pageSize = 5; //how many records per page
        //pageNum - 1 -> since pageNum is obtained from UI, and paging starts with index = 0
        Pageable pageable = PageRequest.of(
                pageNum - 1,
                pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        Page<Contact> page = contactRepo.findByStatus(GlobalConstants.OPEN, pageable);
        List<Contact> messages = contactRepo.findByStatus(GlobalConstants.OPEN);
        return page;
    }

    public boolean updateMsgStatus(int contactID){
        boolean isUpdated = false;
        int updatedRows = contactRepo.updateStatusByID(GlobalConstants.CLOSED, contactID);
        if(updatedRows > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }


}
