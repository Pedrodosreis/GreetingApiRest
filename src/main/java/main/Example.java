package main;

import com.google.gson.Gson;

import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class Example {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    GreetingRepository repository;

    List<Greeting> greetList = new ArrayList<>();

    Gson gson = new Gson();

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
       repository.save(new Greeting(name));
        return "Greeting Added";
    }

    @RequestMapping("/{id}")
    public Greeting getGreeting(@PathVariable("id") long id) {

        Greeting g = repository.findById(id);
            if(g != null) {
                return g;
        }

        return null;
    }

    @RequestMapping("/")
    public String allGreeting() {
        return gson.toJson(repository.findAll());
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String add(@RequestBody Greeting greet) {
        repository.save(new Greeting(greet.getContent()));
        return "200 OK";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteGreeting(@PathVariable("id") long id) {

        Greeting g = repository.findById(id);
        if(g != null) {
            repository.delete(g);
            return "200 OK";
        }

        return "ID NOT FOUND";
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public String deleteAllGreeting() {

        for (Greeting g: repository.findAll()) {
            repository.delete(g);
        }
        return "200 OK";
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public String updateGreeting(@RequestBody Greeting greet) {

        Greeting g = repository.findById(greet.getId());

        if(g != null) {
            g.setContent(greet.getContent());
            repository.save(g);
            return "200 OK";
        }
        return "ID not found";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }

}