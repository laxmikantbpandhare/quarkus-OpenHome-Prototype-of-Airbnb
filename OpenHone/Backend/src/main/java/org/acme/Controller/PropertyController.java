package org.acme.Controller;

import org.acme.Service.PropertyService;
import org.acme.Service.TimeService;
import org.acme.entity.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @PostMapping("/property/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> createProperty(@RequestBody Property property) throws IOException{//}, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {
        System.out.println("Request from frontend: "+property.getPropertyDescription());
        propertyService.createProperty(property);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @RequestMapping(value="/property/remove/{property}",method = RequestMethod.POST)
//    @ResponseStatus(value = HttpStatus.OK)
//    public ResponseEntity<?> removeProperty(@PathVariable("property") Property property) throws IOException{//MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {
//        propertyService.removeEntireProperty(property);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


}
