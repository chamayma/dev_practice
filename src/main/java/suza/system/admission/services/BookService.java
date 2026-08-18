package suza.system.admission.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import suza.system.admission.models.Book;
import suza.system.admission.repositories.BookRepository;
import suza.system.admission.exceptions.ResourceNotFoundException;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // =========================
    // CREATE
    // =========================

    public Book insert(Book book) {
        return bookRepository.save(book);
    }

    // =========================
    // READ
    // =========================

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {

        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));
    }

    public Book getByBookName(String name) {

        return bookRepository.findByBookName(name)
                .orElse(null);
    }

    public List<Book> getByAuthor(Long authorId) {

        return bookRepository.findByAuthorId(authorId);
    }

    public List<Book> searchBooks(String keyword) {

        return bookRepository.findByBookNameContaining(keyword);
    }

    public List<Book> getBooksStartingWith(String prefix) {

        return bookRepository.findByBookNameStartingWith(prefix);
    }

    public List<Book> getBooksEndingWith(String suffix) {

        return bookRepository.findByBookNameEndingWith(suffix);
    }

    public Book getBookIgnoreCase(String name) {

        return bookRepository.findByBookNameIgnoreCase(name)
                .orElse(null);
    }

    // =========================
    // UPDATE
    // =========================

    public Book update(Long id, Book book) {

        Book exist = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));

        exist.setBookName(book.getBookName());
        exist.setAuthor(book.getAuthor());

        return bookRepository.save(exist);
    }

    // =========================
    // PATCH
    // =========================

    public Book patchBook(Long id, Book book) {

        Book exist = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));

        if (book.getBookName() != null) {

            exist.setBookName(book.getBookName());

        }

        if (book.getAuthor() != null) {

            exist.setAuthor(book.getAuthor());

        }

        return bookRepository.save(exist);
    }

    // =========================
    // DELETE
    // =========================

    public void delete(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));

        bookRepository.delete(book);
    }

    public void deleteByBookName(String name) {

        bookRepository.deleteByBookName(name);
    }

    public void deleteAllBooks() {

        bookRepository.deleteAll();
    }

    // =========================
    // SORTING
    // =========================

    public List<Book> sortAscending() {

        return bookRepository.findAllByOrderByBookNameAsc();
    }

    public List<Book> sortDescending() {

        return bookRepository.findAllByOrderByBookNameDesc();
    }

    // =========================
    // LATEST
    // =========================

    public List<Book> latestBooks() {

        return bookRepository.findTop5ByOrderByIdDesc();
    }

    // =========================
    // COUNT
    // =========================

    public long totalBooks() {

        return bookRepository.count();
    }

    public long countByAuthor(Long authorId) {

        return bookRepository.countByAuthorId(authorId);
    }

    // =========================
    // EXISTS
    // =========================

    public boolean exists(String name) {

        return bookRepository.existsByBookName(name);
    }

    // =========================
    // MULTIPLE CONDITIONS
    // =========================

    public List<Book> getByAuthorAndBookName(
            Long authorId,
            String name) {

        return bookRepository.findByAuthorIdAndBookName(
                authorId,
                name);
    }

    // =========================
    // CUSTOM QUERY
    // =========================

    public List<Book> customAuthorSearch(Long authorId) {

        return bookRepository.getBooksByAuthorId(authorId);
    }

    // =========================
    // PAGINATION
    // =========================

    public Page<Book> pagination(Pageable pageable) {

        return bookRepository.findAll(pageable);
    }

}