package suza.system.admission.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import suza.system.admission.models.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
