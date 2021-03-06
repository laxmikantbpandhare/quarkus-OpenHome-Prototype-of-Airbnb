package org.acme.Controller;

import org.acme.entity.Test;
import org.acme.repository.TestJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@RestController
public class TestController {
//    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//    String secretKey = "openhome";

    @Autowired
    private TestJpaRepo repo;

    @PostMapping("/test")
    public String datetest(@RequestBody Map<String, String> payload) throws ParseException {


        String name = payload.get(payload.keySet().toArray()[0]);
        String date = payload.get(payload.keySet().toArray()[1]);

        System.out.println("name"+name+"date"+date);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = sdf1.parse(date);
        java.sql.Date sqlStartDate = new java.sql.Date(date1.getTime());
        System.out.println("name"+name+"date"+sqlStartDate);

        repo.save(new Test(name,sqlStartDate ));
        return "helloWorld";


    }
}
