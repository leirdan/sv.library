package sv.library.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import sv.library.api.domain.Status;
import sv.library.api.dto.CreateStatusData;
import sv.library.api.dto.GenreData;
import sv.library.api.dto.StatusData;
import sv.library.api.services.IStatusRepository;

@RestController
@RequestMapping("/status")
public class StatusController {
    @Autowired
    private IStatusRepository _statusRepository;

    @GetMapping
    public Page<StatusData> Index(Pageable page) {
        return _statusRepository
                .findAll(page)
                .map(StatusData::new);
    }

    @PostMapping
    @Transactional
    public void Create(@RequestBody @Valid CreateStatusData data) {
        _statusRepository.save(new Status(data.description()));
    }
}
