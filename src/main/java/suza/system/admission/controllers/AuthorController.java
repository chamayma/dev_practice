package suza.system.admission.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

import suza.system.admission.models.Author;
import suza.system.admission.services.AuthorService;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/insert")
    public Author insert(@Valid @RequestBody Author author){
        return authorService.saveAuthor(author);
    }

    @GetMapping("/all")
    public List<Author> getAllAuthor(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id){
        return authorService.getAuthorById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        authorService.deleteAuthor(id);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable Long id, @Valid @RequestBody Author author){
        return authorService.updateAuthor(id, author);
    }
}
