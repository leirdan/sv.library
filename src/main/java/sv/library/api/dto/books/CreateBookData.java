package sv.library.api.dto.books;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public record CreateBookData(
                @NotBlank(message = "{title.required}") String title,
                @NotBlank(message = "{author.required}") String author,
                String publisher,
                @NotBlank(message = "{year.required}") @Length(min = 4, message = "{year.invalid}") @Max(value = 2024, message = "{year.invalid}") String year,
                @NotNull Long genreId,
                @NotNull Long statusId) {
}
