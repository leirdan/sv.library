package sv.library.api.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import sv.library.api.domain.Book;
import sv.library.api.domain.Genre;
import sv.library.api.domain.Status;
import sv.library.api.dto.books.CreateBookDTO;
import sv.library.api.dto.books.DetailsBookDTO;
import sv.library.api.services.BookService;
import sv.library.api.services.repository.IGenreRepository;
import sv.library.api.services.repository.IStatusRepository;
import sv.library.api.services.validations.interfaces.IValidatorGenre;
import sv.library.api.services.validations.interfaces.IValidatorStatus;
import sv.library.api.utils.Database;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class BooksControllerTest {
        @Autowired
        private MockMvc mockMvc;
        @MockBean
        private BookService bookService;
        @MockBean
        private IGenreRepository genreRepository;
        @MockBean
        private IStatusRepository statusRepository;
        @MockBean
        private List<IValidatorGenre> validatorGenre;
        @MockBean
        private List<IValidatorStatus> validatorStatus;
        @Autowired
        private JacksonTester<CreateBookDTO> jsonConverterCreate;
        @Autowired
        private JacksonTester<DetailsBookDTO> jsonConverterDetails;

        @Test
        @DisplayName("Deve devolver http 201 quando json eh valido")
        @WithMockUser
        void createCase1() throws Exception {
                Genre genre = Database.loadGenres().get(1);
                Status status = Database.loadStatus().get(0);

                Book book = new Book(1L, "1984", "George Orwell", "Planeta Magazine", "1948",
                                LocalDateTime.now(), null, true, genre, status);
                DetailsBookDTO bookDetails = new DetailsBookDTO(book);
                CreateBookDTO bookCreate = new CreateBookDTO(book.getTitle(), book.getAuthor(), book.getPublisher(),
                                book.getYear(), book.getGenre().getId(), book.getStatus().getId());

                setup(genre, status);
                when(bookService.create(bookCreate)).thenReturn(book);

                String expectedJson = jsonConverterDetails.write(bookDetails).getJson();
                var response = mockMvc.perform(
                                post("/livros")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(jsonConverterCreate.write(bookCreate)
                                                                .getJson()))
                                .andReturn().getResponse();

                Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
                Assertions.assertThat(response.getContentAsString()).isEqualTo(expectedJson);
        }

        @Test
        @DisplayName("Deve devolver http 400 quando livro nao existe")
        @WithMockUser // Simula uma autenticação
        void getOneCase1() throws Exception {
                when(bookService.get(ArgumentMatchers.any())).thenReturn(null);

                var response = mockMvc.perform(get("/livros/{id}", -1L))
                                .andReturn().getResponse();

                Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        @DisplayName("Deve devolver http 200 quando livro existe")
        @WithMockUser
        void getOnecase2() throws Exception {
                Collection<Book> books = Database.loadBooks();
                Book book = books.stream().findFirst().get();

                when(bookService.get(book.getId())).thenReturn(book);
                var response = mockMvc.perform(get("/livros/{id}", book.getId()))
                                .andReturn().getResponse();

                Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        }

        void setup(Genre gen, Status sts) {
                when(genreRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(gen));
                when(statusRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(sts));
                validatorGenre.forEach(g -> doNothing().when(g).validate(any()));
                validatorStatus.forEach(s -> doNothing().when(s).validate(any()));
        }
}
