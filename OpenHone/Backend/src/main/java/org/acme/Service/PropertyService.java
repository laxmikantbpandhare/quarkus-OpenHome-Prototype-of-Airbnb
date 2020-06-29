package org.acme.Service;

import org.acme.entity.Person;
import org.acme.entity.Property;
import org.acme.repository.PersonJPARepo;
import org.acme.repository.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class PropertyService {

    @Autowired
    PropertyRepo propertyRepo;

    @Autowired
    PersonJPARepo personJPARepo;

    public Property getProperty(int id) {
        return propertyRepo.findById(id).orElse(null);
    }

    public void createProperty(Property property) throws IOException {//}, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {


        propertyRepo.save(property);

//        Person p = personJPARepo.findById(property.getOwner().getId());
//        String receiver = p.getEmail();

//        if(!receiver.equals("")) {
//            SendMail y = new SendMail();
//            y.sendEmail("You have created new Property in Open Home", receiver,
//                    "You have created new Property in Open Home.\n\n For more details check your dashboard\n\n " +
//                            "Thanks and Regards, \n OpenHome Team");
//        }
    }


    public void removeEntireProperty(Property property) throws IOException{ //MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {

        Property p1 = getProperty(property.getPropertyId());

        if(property.getPropertyDescription().equals("true")) {
//            deductfor7DaysRemovalProperty(p1);
//            notDeductedRemovalProperty(p1);
        }
        
        else{
          //  notDeductedRemovalProperty(p1);
        }

        p1.setStatus("Removed");
        propertyRepo.save(p1);

        Person p = personJPARepo.findById(p1.getOwner().getId());
        String receiver = p.getEmail();

//        if(!receiver.equals("")) {
//            SendMail y = new SendMail();
//            y.sendEmail("You have removed Property in Open Home", receiver,
//                    "You have removed Property in Open Home.\n\n For more details check your dashboard\n\n " +
//                            "Thanks and Regards, \n OpenHome Team");
//        }
    }

}
