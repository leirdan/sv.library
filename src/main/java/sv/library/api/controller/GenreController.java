package sv.library.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import sv.library.api.domain.Genre;
import sv.library.api.dto.genre.CreateGenreData;
import sv.library.api.dto.genre.GenreData;
import sv.library.api.dto.genre.UpdateGenreData;
import sv.library.api.services.IGenreRepository;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/generos")
public class GenreController {
    @Autowired
    private IGenreRepository _genreRepository;

    @GetMapping
    public Page<GenreData> Index(Pageable page) {
        return _genreRepository
                .findAll(page)
                .map(GenreData::new);
    }

    @PostMapping
    @Transactional
    public void Create(@RequestBody @Valid CreateGenreData data) {
        _genreRepository.save(new Genre(data.description()));
    }

    @PutMapping
    @Transactional
    public void Update(@RequestBody UpdateGenreData data) {
        Genre genre = _genreRepository.getReferenceById(data.id());
        if (genre != null) {
            if (data.description() != null && data.description() != "") {
                genre.setDescription(data.description());
                genre.setUpdatedAt(LocalDateTime.now());
            }
        }
        else {}
    }
}
