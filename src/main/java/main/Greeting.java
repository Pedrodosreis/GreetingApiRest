package main;

import javax.persistence.Entity;
import javax.persistence.*;


@Entity
public class Greeting {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String content;

    Greeting() {
        this.id = 0;
        this.content = "World";
    }

    public Greeting(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public Greeting(String content) {
        this.content = content;
    }

    Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "id: " + id + " content: " + content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}