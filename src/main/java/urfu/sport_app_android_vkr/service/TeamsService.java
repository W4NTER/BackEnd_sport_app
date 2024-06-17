package urfu.sport_app_android_vkr.service;

import urfu.sport_app_android_vkr.domain.dto.request.TeamRequest;
import urfu.sport_app_android_vkr.domain.dto.response.TeamResponse;

import java.util.List;

public interface TeamsService {
    Long add(TeamRequest request, long authorId);
    TeamResponse editTeam(TeamRequest request, long teamId);
    void delete(long teamId);
    TeamResponse getTeam(long teamId);
    List<TeamResponse> findAll();
    void subscribe(long userId, long teamId);
    void unsubscribe(long userId, long teamId);
    List<TeamResponse> findBySport(String sport);
}
