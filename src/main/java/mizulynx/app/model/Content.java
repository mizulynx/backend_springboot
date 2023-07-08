package mizulynx.app.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record Content(
        Integer id,
        @NotBlank
        String title,
        String desc,
        Type contentType,
        Status status,

        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String url

) {


}
