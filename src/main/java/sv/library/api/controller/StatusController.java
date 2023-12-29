package sv.library.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.library.api.domain.Status;
import sv.library.api.dto.CreateStatusData;
import sv.library.api.services.IStatusRepository;

@RestController
@RequestMapping("/status")
public class StatusController {
    @Autowired
    private IStatusRepository _statusRepository;

    @PostMapping
    @Transactional
    public void Create(@RequestBody @Valid CreateStatusData data) {
        _statusRepository.save(new Status(data.description()));
    }
}
