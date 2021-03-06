package org.utn.edu.ar.model.persistence.memoryStorage;

import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.persistence.ISportStorage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SportStorage implements ISportStorage {

    private List<Sport> sportList = new ArrayList<Sport>();

    public SportStorage() {
    }

    public SportStorage(List<Sport> sportList) {
        this.sportList = sportList;
    }

    @Override
    public List<Sport> getAllSports() {
        return sportList;
    }

    @Override
    public Sport getSportById(Long id) {
        Sport outputSport = null;
        for (Sport currentSport : sportList) {
            if (id.equals(currentSport.getId())) {
                outputSport = currentSport;
            }
        }
        return outputSport;
    }

    @Override
    public boolean exists(String sportName) {
        for(Sport s : sportList) {
            if(s.getName().equals(sportName))
                return true;
        }
        return false;
    }

    @Override
    public void updateSport(Long sportId, String sportName) {
        removeSport(sportId);
        Sport currentSport = new Sport(sportId, sportName);
        sportList.add(currentSport);
    }

    @Override
    public Sport createSport(String sportName) {
        long lastId = getLastId();
        Sport currentSport = new Sport(lastId, sportName);
        sportList.add(currentSport);
        return currentSport;
    }

    @Override
    public boolean exists(Long sportId) {
        boolean found = false;
        Iterator<Sport> itSport = sportList.iterator();
        while (itSport.hasNext() && !found) {
            Sport currentSport = itSport.next();
            if (sportId.equals(currentSport.getId())) {
                found = true;
            }
        }
        return found;
    }

    public void removeSport(Long sportId) {
        Sport sportToRemove = new Sport(sportId);
        sportList.remove(sportToRemove);
    }

    private long getLastId() {
        long lastInt = 0;

        for (Sport currentSport : sportList) {
            if (currentSport.getId() > lastInt) {
                lastInt = currentSport.getId();
            }
        }

        return lastInt + 1;
    }
}
