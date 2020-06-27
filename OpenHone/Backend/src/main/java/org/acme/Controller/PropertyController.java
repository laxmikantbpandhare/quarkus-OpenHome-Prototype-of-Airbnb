package org.acme.Controller;

import org.acme.Service.PropertyService;
import org.acme.Service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @Autowired
    TimeService timeService;


}
