package sv.library.api.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import sv.library.api.domain.Book;
import sv.library.api.domain.Genre;
import sv.library.api.domain.Status;
import sv.library.api.domain.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class Database {

    public static List<Genre> loadGenres() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(1L, "Fantasia", LocalDateTime.now(), null, null));
        genres.add(new Genre(2L, "Terror", LocalDateTime.now(), null, null));
        genres.add(new Genre(3L, "Comedia", LocalDateTime.now(), null, null));

        return genres;
    }

    public static List<Book> loadBooks() {
        List<Genre> genres = loadGenres();
        List<Status> status = loadStatus();

        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "A Cronica do Matador do Rei", "Patrick Rothfuss", "Editora Arqueiro",
                "2009",
                LocalDateTime.now(), null, true, genres.get(0), status.get(0)));
        books.add(new Book(2L, "O Iluminado", "Stephen King", "Rocco", "1970", LocalDateTime.now(),
                null, true,
                genres.get(1), status.get(0)));

        return books;
    }

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "zayn", "nyaz", "192012", LocalDateTime.now(), null, true, null));
        users.add(new User(2L, "louis", "iouls", "102913", LocalDateTime.now(), null, true, null));

        return users;
    }

    public static List<Status> loadStatus() {
        List<Status> status = new ArrayList<Status>();
        status.add(new Status(1L, "Disponivel", LocalDateTime.now(), null, null));
        status.add(new Status(2L, "Indisponivel", LocalDateTime.now(), null, null));

        return status;
    }

}
