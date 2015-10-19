'use strict';

angular.module('bookingMatches')

.factory('MatchService', function($http, CONFIG) {
    return{
        get: function(id){
            return $http.get(CONFIG.API_URL + 'matches/v1/matches/' + id);
        },
        getAll: function(){
            return $http.get(CONFIG.API_URL + 'matches/v1/matches');
        },
		getCreatedBy: function(fbId) {
			return $http.get(CONFIG.API_URL + 'matches/v1/matches?createdBy=' + fbId)
		},		
        save: function(name){
            return $http.post(CONFIG.API_URL + 'matches/v1/matches', name);
        },		
        subscribe: function(id, fbId){
            return $http.post(CONFIG.API_URL + 'matches/v1/matches/' + id + '/inscriptions', fbId);
        },	
        unsubscribe: function(id, fbId){
            return $http.delete(CONFIG.API_URL + 'matches/v1/matches/' + id + '/inscriptions', fbId);
        },
        delete: function(id){
            return $http.delete(CONFIG.API_URL + 'matches/v1/matches/' + id);
        }
    }
});