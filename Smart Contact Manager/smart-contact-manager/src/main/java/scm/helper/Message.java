package scm.helper;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    @NotNull
    private String content;

    @NotNull
    @Builder.Default
    private MessageType type=MessageType.green;
}
