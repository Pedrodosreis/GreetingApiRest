package main;

import org.springframework.data.repository.CrudRepository;

public interface GreetingRepository extends CrudRepository<Greeting, Long> {

    Greeting findByContent(String content);

    Greeting findById(long id);


}
