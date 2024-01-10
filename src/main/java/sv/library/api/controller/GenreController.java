package sv.library.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sv.library.api.domain.Genre;
import sv.library.api.dto.genre.CreateGenreData;
import sv.library.api.dto.genre.DetailsGenreData;
import sv.library.api.dto.genre.GenreData;
import sv.library.api.dto.genre.UpdateGenreData;
import sv.library.api.services.repository.IGenreRepository;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/generos")
@NoArgsConstructor
public class GenreController {
    @Autowired
    private IGenreRepository _genreRepository;

    @GetMapping
    public ResponseEntity<Page<GenreData>> Index(Pageable page) {
        Page<GenreData> genres = _genreRepository
                .findAll(page)
                .map(GenreData::new);

        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreData> GetOne(@PathVariable Long id) {
        Genre genre = _genreRepository.getReferenceById(id);

        return ResponseEntity.ok(new GenreData(genre));
    }

    @PostMapping
    @Transactional
    public ResponseEntity Create(@RequestBody @Valid CreateGenreData data, UriComponentsBuilder builder) {
        Genre genre = new Genre(data.description());

        _genreRepository.save(genre);

        URI uri = builder.path("/generos/{id}").buildAndExpand("id", genre.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailsGenreData(genre));
    }

    @PutMapping
    @Transactional
    public ResponseEntity Update(@RequestBody UpdateGenreData data) {
        Genre genre = _genreRepository.getReferenceById(data.id());
        if (genre != null) {
            if (data.description() != null && data.description() != "") {
                genre.setDescription(data.description());
                genre.setUpdatedAt(LocalDateTime.now());
            }
        }

        return ResponseEntity.ok(new DetailsGenreData(genre));
    }
}
