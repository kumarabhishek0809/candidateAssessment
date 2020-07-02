package com.assessment.candidate.model;

import com.assessment.candidate.response.GenericResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class LoginResponse extends GenericResponse {
    private String loginId;
    private String password;
}
