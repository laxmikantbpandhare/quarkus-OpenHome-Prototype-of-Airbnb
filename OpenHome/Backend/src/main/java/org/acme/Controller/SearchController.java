package org.acme.Controller;

import org.acme.Service.SearchPropertyService;
import org.acme.entity.Property;
import org.acme.entity.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    SearchPropertyService searchPropertyService;
//    private PersonSpringDataRepo repo;

//    @GetMapping("/search/property")
//    public List<Property> retrivePropertyDetails(@RequestBody Property property) {
//        System.out.println(property.getCity());
//        return searchPropertyService.searchPropertiesByCity(property.getCity());
//
//
//      //  return searchPropertyService.searchProperties();
//    }


    @PostMapping("/search/property")
    public List<Property> retrivePropertyDetails(@RequestBody Search property) throws ParseException {

        System.out.println(property.getCity());
        System.out.println(property.getEndDate());
        System.out.println(property.getStartDate());
        System.out.println(property.getPropertyDescription());


//
//            System.out.println(property.getPropertyType());
//            System.out.println(property.getPriceRange());
//            System.out.println(property.getSharingType());
//            System.out.println(property.getCity());
//            System.out.println(property.getEndDate());
//            System.out.println(property.getStartDate());
////          System.out.println(property.getStartDate().getDay());
//            System.out.println(property.getWifi());
//            System.out.println(property.getPropertyDescription());

//        System.out.println(property1);
        // return searchPropertyService.retrievePropertiesByCriteria(property);

        return searchPropertyService.retrievePropertiesByCriteria(property);
        //  return searchPropertyService.searchProperties();
    }
}
