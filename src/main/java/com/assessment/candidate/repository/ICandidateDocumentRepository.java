package com.assessment.candidate.repository;

import com.assessment.candidate.entity.CandidateDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "candidateDocument", path = "candidateDocument")
public interface ICandidateDocumentRepository extends CrudRepository<CandidateDocument, Integer> {

    @RestResource(path = "emailId")
    List<CandidateDocument> findByEmailId(String emailId);
}
