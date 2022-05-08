package ua.tunepoint.web.model;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public class IdResponse extends CommonResponse<IdPayload> {

    public static IdResponse withId(Long id) {
        return IdResponse.builder().payload(new IdPayload(id)).build();
    }
}
