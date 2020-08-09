package com.assessment.candidate.repository;

import com.assessment.candidate.entity.CandidateDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "candidateDocument", path = "candidateDocument")
public interface ICandidateDocumentRepository extends CrudRepository<CandidateDocument, Integer> {
}
