package org.utn.edu.ar.controller;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import org.utn.edu.ar.Constants;
import org.utn.edu.ar.model.domain.Sport;
import org.utn.edu.ar.model.exceptions.sport.SportNameAlreadyExistException;
import org.utn.edu.ar.model.exceptions.sport.SportNotFoundException;
import org.utn.edu.ar.model.persistence.memoryStorage.SportStorage;
import org.utn.edu.ar.model.SportService;
import org.utn.edu.ar.model.request.NameRequest;

import java.util.ArrayList;
import java.util.List;

@Api(
        name = "sports",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.API_EXPLORER_CLIENT_ID}
)
public class SportController {

    private SportService service = new SportService(new SportStorage(new ArrayList<Sport>()));

    @ApiMethod(
            name = "getAll",
            path = "sports",
            httpMethod = HttpMethod.GET
    )
    public List<Sport> listAllSports()
            throws SportNotFoundException {
        return service.getAllSports();
    }

    @ApiMethod(
            name = "getById",
            path ="sports/{id}",
            httpMethod = HttpMethod.GET
    )
    public Sport getSport(@Named("id") Integer id) throws NotFoundException {
        try {
            return service.getSportById(id);
        } catch (SportNotFoundException e) {
            throw new NotFoundException("Sport with "+id+" does not exist");
        }
    }

    @ApiMethod(
            name = "add",
            path = "sports",
            httpMethod = HttpMethod.POST
    )
    public void createSport(NameRequest rq) throws ConflictException {
        try {
            service.createSport(rq.getName());
        } catch (SportNameAlreadyExistException e) {
            throw new ConflictException(e);
        }
    }

    @ApiMethod(name = "update", httpMethod = "put")
    public void updateSport(@Named("id") Integer id, @Named("name") String name) throws ConflictException {
        try {
            service.updateSport(id, name);
        } catch (SportNotFoundException e){
            throw new ConflictException(e);
        }
    }


    @ApiMethod(name = "sports.remove", httpMethod = "delete")
    public void removeSport(@Named("id") Integer id) throws NotFoundException {
        try {
            service.removeSport(id);
        } catch (Exception e) {
            throw new NotFoundException(e);
        }
    }
}
