package org.utn.edu.ar.model;

import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.exceptions.sport.SportNameAlreadyExistException;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.persistence.ISportStorage;
import org.utn.edu.ar.model.persistence.memoryStorage.SportStorage;

import java.util.ArrayList;
import java.util.List;

public class SportService {

    private static SportService instance = null;

    private ISportStorage storage;

    public SportService(ISportStorage storage) { this.storage = storage; }


    public static SportService getInstance() {
        if (instance == null) {
            synchronized (MatchService.class) {
                if (instance == null) {
                    instance = new SportService(new SportStorage());
                }
            }
        }
        return instance;
    }

    /**
     * Get all sports. In case the storage returns null, throws NotFoundException.
     * @return List<Sport>
     */
    public List<Sport> getAllSports() throws SportNotFoundException {
        List<Sport> outputList = storage.getAllSports();
        if(outputList == null){
            throw new SportNotFoundException("There are not sports stored");
        }
        return outputList;
    }

    public Sport getSportById(Integer id) throws SportNotFoundException {
        Sport storedSport = storage.getSportById(id);
        if(storedSport == null){
            throw new SportNotFoundException(id);
        }
        return storedSport;
    }

    public void setStorage(ISportStorage storage) {
        this.storage = storage;
    }

    public Sport createSport(String sportName) throws SportNameAlreadyExistException {
        if(storage.exists(sportName)){
            throw new SportNameAlreadyExistException(sportName);
        }
        return storage.createSport(sportName);
    }

    public void updateSport(Integer sportId, String sportName)
            throws SportNotFoundException, SportNameAlreadyExistException {
        if(!storage.exists(sportId)){
            throw new SportNotFoundException(sportId);
        }
        if(storage.exists(sportName))
            throw new SportNameAlreadyExistException(sportName);
        storage.updateSport(sportId, sportName);
    }

    public void removeSport(Integer sportId) throws SportNotFoundException {
        if(!storage.exists(sportId)){
            throw new SportNotFoundException(sportId);
        }
        storage.removeSport(sportId);
    }
}
