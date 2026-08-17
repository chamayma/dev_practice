package suza.system.admission.models;


import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,length = 50)
    private String bookName;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
// if you told to do like this then you can then also do it
//    @ManyToOne
//    @JoinColumn(name = "author_id")
//    private List<Author> author;

}
