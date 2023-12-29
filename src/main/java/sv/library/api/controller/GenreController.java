package sv.library.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.library.api.domain.Genre;
import sv.library.api.dto.CreateGenreData;
import sv.library.api.services.IGenreRepository;

@RestController
@RequestMapping("/generos")
public class GenreController {
    @Autowired
    private IGenreRepository _genreRepository;

    @PostMapping
    @Transactional
    public void Create(@RequestBody @Valid CreateGenreData data) {
        _genreRepository.save(new Genre(data.description()));
    }
}
