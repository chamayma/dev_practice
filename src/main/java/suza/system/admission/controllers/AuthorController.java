package suza.system.admission.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import suza.system.admission.models.Author;
import suza.system.admission.repositories.AuthorRepository;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {


    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping("/insert")
    public Author insert(@RequestBody Author author){
        return authorRepository.save(author);
    }

    @GetMapping("/all")
    public List<Author> getAllAuthor(){
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id){
        return authorRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        authorRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Long id,@RequestBody Author author){
        Author exist = authorRepository.findById(id).orElse(null);
        if(exist != null){
            exist.setName(author.getName());
            authorRepository.save(exist);
        }
    }
}
