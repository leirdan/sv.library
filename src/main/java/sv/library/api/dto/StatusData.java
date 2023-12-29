package sv.library.api.dto;

import sv.library.api.domain.Status;

public record StatusData(
        Long id,
        String description
) {
    public StatusData(Status status) {
        this(status.getId(), status.getDescription());
    }
}