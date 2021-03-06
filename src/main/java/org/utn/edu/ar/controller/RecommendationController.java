package org.utn.edu.ar.controller;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import org.utn.edu.ar.Constants;
import org.utn.edu.ar.model.RecommendationService;
import org.utn.edu.ar.model.domain.Recommendation;
import org.utn.edu.ar.model.exceptions.match.MatchNotFoundException;
import org.utn.edu.ar.model.exceptions.player.PlayerNotFoundException;
import org.utn.edu.ar.model.request.RecommendationRequest;

import java.util.List;

@Api(
        name = "recommendations",
        scopes = Constants.EMAIL_SCOPE,
        clientIds = Constants.WEB_CLIENT_ID
)
public class RecommendationController {

    private RecommendationService service = RecommendationService.getInstance();
    @ApiMethod(
            name = "create",
            path = "recommendations",
            httpMethod = HttpMethod.POST
    )
    public Recommendation create(RecommendationRequest rec)
            throws MatchNotFoundException, PlayerNotFoundException {
            return service.create(rec);
    }

    @ApiMethod(
            name = "delete",
            path = "recommendations/{id}",
            httpMethod = HttpMethod.DELETE
    )
    public void delete(@Named("id") Long id) {
        service.delete(id);
    }

    @ApiMethod(
            name = "getAll",
            path = "recommendations",
            httpMethod = HttpMethod.GET
    )
    public List<Recommendation> getAll(@Nullable @Named("origin") String originId,
                                       @Nullable @Named("destination") String destinationId){
        if(originId != null) return service.getForEmitter(originId);
        if(destinationId != null) return service.getForReceiver(destinationId);
        return service.getAll();
    }

    @ApiMethod(
            name = "getById",
            path = "recommendations/{id}",
            httpMethod = HttpMethod.GET
    )
    public Recommendation getById(@Named("id") Long id){
        return service.getById(id);
    }
}
