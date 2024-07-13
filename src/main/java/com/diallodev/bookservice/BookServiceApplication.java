package com.diallodev.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class BookServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }

}


record Book(@Id Integer id, String title) {}


interface BookRepository extends CrudRepository<Book, Integer> {}


@RestController
@RequestMapping("/books")
class BookController {

    private final BookRepository repository;

    BookController(BookRepository repository) {
        this.repository = repository;
    }


    @GetMapping
    Iterable<Book> getAllBooks() {
        return repository.findAll();
    }

    @PostMapping
    Book addNewBook(@RequestBody Book book) {
        return repository.save(book);
    }
}