package com.assessment.candidate.model;

import com.assessment.candidate.response.GenericResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse extends GenericResponse {
    private String loginId;
    private String password;
}
