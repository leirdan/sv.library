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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import sv.library.api.domain.Genre;
import sv.library.api.dto.genre.CreateGenreDTO;
import sv.library.api.dto.genre.DetailsGenreDTO;
import sv.library.api.dto.genre.GenreDTO;
import sv.library.api.dto.genre.UpdateGenreDTO;
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
    public ResponseEntity<Page<GenreDTO>> Index(Pageable page) {
        Page<GenreDTO> genres = _genreRepository
                .findAll(page)
                .map(GenreDTO::new);

        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> GetOne(@PathVariable Long id) {
        Genre genre = _genreRepository.getReferenceById(id);

        return ResponseEntity.ok(new GenreDTO(genre));
    }

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DetailsGenreDTO> Create(@RequestBody @Valid CreateGenreDTO data,
            UriComponentsBuilder builder) {
        Genre genre = new Genre(data.description());

        _genreRepository.save(genre);

        URI uri = builder.path("/generos/{id}").buildAndExpand("id", genre.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailsGenreDTO(genre));
    }

    @PutMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DetailsGenreDTO> Update(@RequestBody UpdateGenreDTO data) {
        Genre genre = _genreRepository.getReferenceById(data.id());
        if (genre != null) {
            if (data.description() != null && data.description() != "") {
                genre.setDescription(data.description());
                genre.setUpdatedAt(LocalDateTime.now());
            }
        }

        return ResponseEntity.ok(new DetailsGenreDTO(genre));
    }
}
