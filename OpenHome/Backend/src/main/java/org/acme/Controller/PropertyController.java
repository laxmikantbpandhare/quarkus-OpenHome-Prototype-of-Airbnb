package org.acme.Controller;

import org.acme.Service.PropertyService;
import org.acme.Service.TimeService;
import org.acme.entity.Person;
import org.acme.entity.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.List;


@RestController
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @PostMapping("/property/add")
    //@ResponseStatus(value = HttpStatus.CREATED)
    public String createProperty(@RequestBody Property property) throws IOException{//}, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {
        System.out.println("Request from frontend: "+property.getPropertyDescription());
        propertyService.createProperty(property);
        return "Success";
    }

//    @RequestMapping(value="/property/remove/{property}",method = RequestMethod.POST)
//    @ResponseStatus(value = HttpStatus.OK)
//    public ResponseEntity<?> removeProperty(@PathVariable("property") Property property) throws IOException{//MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {
//        propertyService.removeEntireProperty(property);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


    @GetMapping("/property/all")
    public List<Property> getAllProperties() {
       // System.out.println("Request from frontend: ");
        return propertyService.getAllProperties();
    }


    @GetMapping("/property/{id}")
    public Property getProperty(@PathVariable int id) {

        System.out.println("id"+id);
        return propertyService.getProperty(id);
    }

    @GetMapping("/property/owner/{ownerId}")
    public List<Property> getOwnerProperties(@PathVariable String ownerId) {
        System.out.println(ownerId);
        Person person=new Person();
        person.setId((Integer.parseInt(ownerId)));
        return propertyService.getHostProperties(person);
    }

    //check the function dateComparision afterwards, it has Criteria Query with Predicates
    //pending with testing
    @PostMapping("/property/availability")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> changeAvailaibility(@RequestBody Property property)throws IOException{ //throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {

        propertyService.updateProperty(property);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/property/delete")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> deleteProperty(@RequestBody Property property) throws IOException{//throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {
        System.out.println("deleteProperty"+property);
        propertyService.removeEntireProperty(property);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
