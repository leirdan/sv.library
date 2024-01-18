package sv.library.api.dto.status;

import sv.library.api.domain.Status;

public record StatusDTO(
        Long id,
        String description) {
    public StatusDTO(Status status) {
        this(status.getId(), status.getDescription());
    }
}