### User stories
1. ~~Como usuario quiero poder registrarme con mi cuenta de Facebook.~~
2. Como usuario quiero poder crear un partido de un deporte. En la creación se indicará el deporte que se jugará y cuantas personas son necesarias.
3. Como usuario quiero poder anotarme en un partido creado por un amigo. Como titular en caso de que no se haya llegado al cupo solicitado, y sino como suplente.
4. Como usuario quiero poder ver el estado de mis partidos creados (inscriptos titulares y suplentes)
5. Como usuario quiero poder ver aquellos partidos en los que me anoté.
6. ~~Como usuario quiero recomendar a un amigo un partido que creé para que pueda anotarse.~~
7. ~~Como usuario quiero ver los partidos que me recomendaron, y anotarme o rechazarlos.~~
8. ~~Como usuario quiero que al crear un partido, el mismo se publique en mi muro de Facebook.~~
9. ~~Como usuario quiero recibir una notificación de Facebook cuando alguien se anote en un partido que creé.-
10. ~~Como usuario quiero recibir una notificación de Facebook cuando un partido en el que participo (lo creé o me anoté) se confirme (llega al cupo solicitado)~~
11. Como usuario que se anotó en un partido quiero poder borrarme del mismo.
12. Como administrador de la aplicación quiero ver un panel de control indicando cantidad de partidos creados e inscriptos.

Las historias que estan tachadas, son las que creo que están relacionadas y resueltas por features de fb, y no necesitan endpoints en la api.

### Contracts

Applications send their requests to `/_ah/api/{theApi}/{theVersion}/path`, 
so the idea is that if we have `/sports` on the following contracts, that really means
`localhost:8080/_ah/api/sports/v1/sports`.

Another thing to bear in mind is that Google Cloud Endpoints return lists as a JSON object of the shape
```
{
  "items" : []
}
```

#### sports
* `GET sports` returns sports
* `GET sports/{id}` returns a sport
* `POST sports` creates a sport
```
{
  name
}
```
* `PUT sports/{id}` updates a sport
```
{
  name
}
```
* `DELETE sports/{id}` deletes a sport

#### matches
* `POST matches` creates a match, createdBy is a fbId.
```
{
  date
  location
  sport
  playersNeeded
  createdBy
}
```
* `PUT matches/{matchId}` updates a match if matchId exists.
```
{
  date
  location
  sport
  playersNeeded
  createdBy
}
```
* `POST matches/{matchId}/inscriptions` subscribes a facebook user to a match
```
{
  fbId
}
```
* `DELETE matches/{matchId}/inscriptions` unsubscribes a facebook user from a match
```
{
  fbId
}
```
* `DELETE matches/{matchId}` deletes a match and its inscriptions in cascade.
* `GET matches/createdBy/{fbId}` returns created matches by a facebook user
* `GET matches` returns all created matches with its players
* `GET matches/{id}` returns match correlating the aforementioned id, if it exists.
* `GET matches/inscriptions/enrolled/{fbId}` retrieves all match ids in which `fbId` is enrolled.

#### players
* `GET players` returns all players
* `GET players?id={id}` returns the player matching the given id
* `GET players?fbId={fbId}` returns the player matching the given fbId
* `POST players` create a player with given Facebook user.
```
{
  fbId
}
``` 
* `PUT players/{id}` update a player with given Facebook User
```
{
  fbId
}
``` 
* `DELETE players/{id}` deletes the player.
* `POST players/validate` validate player against Facebook from the backend.
```
{
  accessToken
  fbId
}
```


#### Recommendations
* `GET recommendations` returns all available recommendations.
* `GET recommendations/{recommendationID}` returns the recommendation for the given id, if it exists.
* `GET recommendations?destination={playerId}` returns all recommendations for the destination player id.
* `GET recommendations?origin={playerId}` returns all recommendations created by the given player id.
* `POST recommendations` creates a recommendation for the given matchId to be delivered to the corresponding destination id,
returning the created Recommendation.
```
{
  matchId
  emitterId
  receiverId
}
``` 
* `DELETE recommendation/{id}` deletes the recommendation.

#### Facebook
* `GET facebook/app-access-token` retrieves our Facebook App Access Token.

