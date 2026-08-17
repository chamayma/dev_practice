package suza.system.admission.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import suza.system.admission.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Find book by exact name
    Optional<Book> findByBookName(String bookName);

    // Find books by author
    List<Book> findByAuthor(String author);

    // Find books containing a keyword
    List<Book> findByBookNameContaining(String keyword);

    // Find books starting with
    List<Book> findByBookNameStartingWith(String prefix);

    // Find books ending with
    List<Book> findByBookNameEndingWith(String suffix);

    // Ignore case
    Optional<Book> findByBookNameIgnoreCase(String bookName);

    // Sort ascending
    List<Book> findAllByOrderByBookNameAsc();

    // Sort descending
    List<Book> findAllByOrderByBookNameDesc();

    // Latest 5 books
    List<Book> findTop5ByOrderByIdDesc();

    // Count books by author
    long countByAuthor(String author);

    // Check existence
    boolean existsByBookName(String bookName);

    // Delete by name
    void deleteByBookName(String bookName);

    // Search by author and book name
    List<Book> findByAuthorAndBookName(
            String author,
            String bookName);

    // Pagination
    Page<Book> findAll(Pageable pageable);

    // Custom JPQL query
    @Query("SELECT b FROM Book b WHERE b.author = :author")
    List<Book> getBooksByAuthor(
            @Param("author") String author);

}