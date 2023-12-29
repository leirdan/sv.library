package sv.library.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import sv.library.api.domain.Genre;
import sv.library.api.dto.CreateGenreData;
import sv.library.api.dto.GenreData;
import sv.library.api.services.IGenreRepository;

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
}
