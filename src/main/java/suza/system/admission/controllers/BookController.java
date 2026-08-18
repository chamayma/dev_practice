package suza.system.admission.controllers;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import suza.system.admission.models.Book;
import suza.system.admission.services.BookService;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // =========================
    // CREATE
    // =========================

    @PostMapping
    public ResponseEntity<Book> insert(@Valid @RequestBody Book book) {
        Book savedBook = bookService.insert(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    // =========================
    // READ
    // =========================

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Book> getByBookName(@PathVariable String name) {
        Book book = bookService.getByBookName(name);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Book>> getByAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(bookService.getByAuthor(authorId));
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Book>> searchBooks(@PathVariable String keyword) {
        return ResponseEntity.ok(bookService.searchBooks(keyword));
    }

    @GetMapping("/starts/{prefix}")
    public ResponseEntity<List<Book>> startsWith(@PathVariable String prefix) {
        return ResponseEntity.ok(bookService.getBooksStartingWith(prefix));
    }

    @GetMapping("/ends/{suffix}")
    public ResponseEntity<List<Book>> endsWith(@PathVariable String suffix) {
        return ResponseEntity.ok(bookService.getBooksEndingWith(suffix));
    }

    @GetMapping("/ignorecase/{name}")
    public ResponseEntity<Book> ignoreCase(@PathVariable String name) {
        Book book = bookService.getBookIgnoreCase(name);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    // =========================
    // UPDATE
    // =========================

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(
            @PathVariable Long id,
            @Valid @RequestBody Book book) {
        return ResponseEntity.ok(bookService.update(id, book));
    }

    // =========================
    // PATCH
    // =========================

    @PatchMapping("/{id}")
    public ResponseEntity<Book> patch(
            @PathVariable Long id,
            @RequestBody Book book) {
        return ResponseEntity.ok(bookService.patchBook(id, book));
    }

    // =========================
    // DELETE
    // =========================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteByBookName(
            @PathVariable String name) {
        bookService.deleteByBookName(name);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAll() {
        bookService.deleteAllBooks();
        return ResponseEntity.noContent().build();
    }

    // =========================
    // SORTING
    // =========================

    @GetMapping("/sort/asc")
    public ResponseEntity<List<Book>> sortAscending() {
        return ResponseEntity.ok(bookService.sortAscending());
    }

    @GetMapping("/sort/desc")
    public ResponseEntity<List<Book>> sortDescending() {
        return ResponseEntity.ok(bookService.sortDescending());
    }

    // =========================
    // LATEST
    // =========================

    @GetMapping("/latest")
    public ResponseEntity<List<Book>> latestBooks() {
        return ResponseEntity.ok(bookService.latestBooks());
    }

    // =========================
    // COUNT
    // =========================

    @GetMapping("/count")
    public ResponseEntity<Long> totalBooks() {
        return ResponseEntity.ok(bookService.totalBooks());
    }

    @GetMapping("/count/author/{authorId}")
    public ResponseEntity<Long> countByAuthor(
            @PathVariable Long authorId) {
        return ResponseEntity.ok(bookService.countByAuthor(authorId));
    }

    // =========================
    // EXISTS
    // =========================

    @GetMapping("/exists/{name}")
    public ResponseEntity<Boolean> exists(
            @PathVariable String name) {
        return ResponseEntity.ok(bookService.exists(name));
    }

    // =========================
    // MULTIPLE CONDITIONS
    // =========================

    @GetMapping("/filter")
    public ResponseEntity<List<Book>> filter(
            @RequestParam Long authorId,
            @RequestParam String name) {
        return ResponseEntity.ok(bookService.getByAuthorAndBookName(authorId, name));
    }

    // =========================
    // CUSTOM QUERY
    // =========================

    @GetMapping("/custom/{authorId}")
    public ResponseEntity<List<Book>> customQuery(
            @PathVariable Long authorId) {
        return ResponseEntity.ok(bookService.customAuthorSearch(authorId));
    }

    // =========================
    // PAGINATION
    // =========================

    @GetMapping("/page")
    public ResponseEntity<Page<Book>> pagination(
            @RequestParam int page,
            @RequestParam int size) {
        return ResponseEntity.ok(bookService.pagination(PageRequest.of(page, size)));
    }
}