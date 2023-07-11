package mizulynx.app.model;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record Content(
        Integer id,
        @NotBlank
        String title,
        String desc,
        Type contentType,
        ContentStatus contentStatus,

        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String url

) {


}
