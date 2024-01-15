package sv.library.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import sv.library.api.domain.Status;
import sv.library.api.dto.status.CreateStatusData;
import sv.library.api.dto.status.StatusData;
import sv.library.api.services.repository.IStatusRepository;

@RestController
@RequestMapping("/status")
@NoArgsConstructor
@SecurityRequirement(name = "bearer-key")
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
