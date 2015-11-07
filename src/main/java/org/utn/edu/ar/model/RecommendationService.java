package org.utn.edu.ar.model;

import org.utn.edu.ar.model.domain.Recommendation;
import org.utn.edu.ar.model.persistence.IRecommendationStorage;

import java.util.List;

/**
 * Created by tom on 10/12/15.
 */
public class RecommendationService {

    private static RecommendationService instance = null;

    private IRecommendationStorage storage;

    private RecommendationService(){}

    public static RecommendationService getInstance() {
        if (instance == null) {
            synchronized (RecommendationService.class) {
                if (instance == null) {
                    instance = new RecommendationService();
                }
            }
        }
        return instance;
    }

    public RecommendationService(IRecommendationStorage st){ this.storage = st; }

    public Recommendation create(Recommendation rec){
        return storage.create(rec);
    }

    public List<Recommendation> getAll(){
       return storage.getAll();
    }

    public Recommendation getById(Integer id){
        return storage.getById(id);
    }

    public void delete(Integer id){
        storage.delete(id);
    }

    public List<Recommendation> getForEmitter(String playerId){
        return storage.getForEmitter(playerId);
    }

    public List<Recommendation> getForReceiver(String playerId){
        return storage.getForReceiver(playerId);
    }
}
