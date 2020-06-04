package com.assessment.candidate.response;

import com.assessment.candidate.model.Candidate;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class CandidatesSearchResponse extends GenericResponse{

    private List<Candidate> candidates;


}
