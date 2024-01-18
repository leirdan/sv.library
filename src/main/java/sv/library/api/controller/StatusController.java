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
import sv.library.api.dto.status.CreateStatusDTO;
import sv.library.api.dto.status.StatusDTO;
import sv.library.api.services.repository.IStatusRepository;

@RestController
@RequestMapping("/status")
@NoArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class StatusController {
    @Autowired
    private IStatusRepository _statusRepository;

    @GetMapping
    public Page<StatusDTO> Index(Pageable page) {
        return _statusRepository
                .findAll(page)
                .map(StatusDTO::new);
    }

    @PostMapping
    @Transactional
    public void Create(@RequestBody @Valid CreateStatusDTO data) {
        _statusRepository.save(new Status(data.description()));
    }
}
