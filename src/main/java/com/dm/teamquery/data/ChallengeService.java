package com.dm.teamquery.data;

import com.dm.teamquery.model.Challenge;
import com.dm.teamquery.model.SearchEntity;
import com.dm.teamquery.search.Search;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ChallengeService {

    @Inject
    private ChallengeRepository challengeRepository;

    @Inject
    private SearchRepository searchRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Challenge updateChallenge(Challenge c) {
        if (null == c.getChallengeId()) c.setChallengeId(UUID.randomUUID());
        return challengeRepository.save(c);
    }

    public void deleteChallengeById(String id){
        challengeRepository.deleteById(UUID.fromString(id));
    }

    public List<Challenge> search (String query){
        return search(query, PageRequest.of(0,100));
    }

    public List<Challenge> search (String query, Pageable p){

        List<Challenge> results = new ArrayList<>();
        String dbQuery = new Search(Challenge.class, query).getDatabaseQuery();
        SearchEntity entity = new SearchEntity(query, dbQuery);

        try {
            results = entityManager
                    .createQuery(dbQuery)
                    .setMaxResults(p.getPageSize())
                    .setFirstResult(p.getPageNumber() * p.getPageSize())
                    .getResultList();
        } catch (Exception e) {
            entity.setErrors(e.getMessage());
        }

        searchRepository.save(entity);
        return results;
    }
}
