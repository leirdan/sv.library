package sv.library.api.dto;

import jakarta.validation.constraints.*;

public record CreateBookData(
        @NotBlank
        String title,
        @NotBlank
        String author,
        String publisher,
        @NotBlank
        @Pattern(regexp = "\\d{2,4}")
        String year,
        Long genreId,
        Long userId,
        Long statusId
        ) {
}
