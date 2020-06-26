package org.acme.Controller;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.acme.entity.Person;
import org.acme.repository.PersonNotFound;
import org.acme.repository.PersonSpringDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;


import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
//@RequestMapping("/book")
public class PersonResource {

    //PasswordFactory passwordFactory = PasswordFactory.getInstance(BCryptPassword.ALGORITHM_BCRYPT, ELYTRON_PROVIDER);


   // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private PersonSpringDataRepo repo;

    String secretKey = "openhome";

//    @RequestMapping(value = "/sendemail")
//    public String sendEmail(@RequestBody Map<String, String> payload) throws MessagingException, IOException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {
//        String subject = payload.get(payload.keySet().toArray()[0]);
//        String recevier = payload.get(payload.keySet().toArray()[1]);
//        String body = payload.get(payload.keySet().toArray()[2]);
//        SendMail y = new SendMail();
//        y.sendEmail(subject,recevier,body);
//        return "Email sent successfully";
//    }

    @GetMapping("/verifyUser/{id}")
    public String validatePerson(@PathVariable int id) {
        Optional<Person> person = repo.findById(id);

        if (!person.isPresent())
            throw new PersonNotFound("id-" + id);

        Person p = retrievePerson(id);
        p.setVerification("yes");
        repo.save(p);
        return "Your Email ID is verified And your Account is now active";
    }


    @GetMapping("/verifyemail/{email}")
    public Map<String, String> validatePersonWithEmail(@PathVariable String email) {
        Optional<Person> person = Optional.ofNullable(repo.findByEmail(email));

        if (!person.isPresent())
            throw new PersonNotFound("Not Present");

        String encodedNameAsToken = "";
        System.out.println("Password Validated");


       // encodedNameAsToken = encoder.encode(person.get().getName().concat(secretKey));




     //   System.out.println(encoder.matches(person.get().getName().concat(secretKey),encodedNameAsToken));
        HashMap<String, String> map = new HashMap<>();
        map.put("token", encodedNameAsToken);
        System.out.println(encodedNameAsToken);
        map.put("name", person.get().getName());
        map.put("role", person.get().getRole());
        map.put("email", person.get().getEmail());
        map.put("id", String.valueOf(person.get().getId()));
        map.put("verified", String.valueOf(person.get().getVerification()));

        return map;
    }

//    @GetMapping("/persons")
//    public List<Person> retriveAllPersons(@RequestHeader HttpHeaders headers)
//    {

//        System.out.println(headers.get("name").get(0));
//        System.out.println(headers.get("token").get(0));


//        if(encoder.matches(headers.get("name").get(0).concat(secretKey),headers.get("token").get(0))){
//            System.out.println("Token Validated");
//            return repo.findAll();
//        }
//        else{
//            throw new PersonNotFound("Invalid Token");
//        }


//    }


    @PostMapping("/persons")
    public String createStudent(@RequestBody Person person) throws IOException {
        System.out.println("This is" + person.getEmail());
        System.out.println("This is" + repo.findByEmail(person.getEmail()));
        Optional<Person> p = Optional.ofNullable(repo.findByEmail(person.getEmail()));
        if (!p.isPresent()) {

            System.out.println("in if This is" + person.getEmail());
            Person savedPerson;
            savedPerson = repo.save(person);
//            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                    .buildAndExpand(savedPerson.getId()).toUri();
//            System.out.println(savedPerson.getEmail());
//            String subject = "Please Verify your Email ID with Open Home";
//            String recevier = savedPerson.getEmail();
//            String body = "Hi " + savedPerson.getName() + ",\n\nPlease verify your email with us by clicking on below link:\n http://localhost:8181/verifyUser/" + savedPerson.getId();
//            SendMail y = new SendMail();
//            y.sendEmail(subject, recevier, body);


            return "Success";

        }
        else{
            System.out.println("in else This is" + person.getEmail());
            return "Not Success";
        }
    }


    @GetMapping("/persons/{id}")
    public Person retrievePerson(@PathVariable int id) {
        Optional<Person> person = repo.findById(id);

        System.out.println("Person This is" + person);
        if (!person.isPresent())
            throw new PersonNotFound("id-" + id);

        return person.get();
    }


    @PostMapping("/authenticate")
    public Map<String, String> auth(@RequestBody Map<String, String> payload) {


        String email = payload.get(payload.keySet().toArray()[0]);

        System.out.println("email"+email);

        String password = payload.get(payload.keySet().toArray()[1]);

        System.out.println("password"+password);

        Optional<Person> person = Optional.ofNullable(repo.findByEmail(email));
        if (!person.isPresent()){

            System.out.println("Person is not present in DB");
            throw new PersonNotFound("Not Found" +person);
        }
        else{
            //Person is Present in DB
            System.out.println("Person From DB");
            System.out.println(person);

            if(password.equals(person.get().getPassword())){
                String encodedNameAsToken = "";
                System.out.println("Password Validated");


             //   encodedNameAsToken = encoder.encode(person.get().getName().concat(secretKey));




             //   System.out.println(encoder.matches(person.get().getName().concat(secretKey),encodedNameAsToken));
                HashMap<String, String> map = new HashMap<>();
                map.put("token", encodedNameAsToken);
                System.out.println(encodedNameAsToken);
                map.put("name", person.get().getName());
                map.put("role", person.get().getRole());
                map.put("email", person.get().getEmail());
                map.put("id", String.valueOf(person.get().getId()));
                map.put("verified", String.valueOf(person.get().getVerification()));

                return map;
            }
            else{

                System.out.println("Password inValid");
                throw new PersonNotFound("Password Wrong");
            }
        }

    }
}
