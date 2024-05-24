package urfu.sport_app_android_vkr.domain.service;

import urfu.sport_app_android_vkr.domain.dto.request.TeamRequest;
import urfu.sport_app_android_vkr.domain.dto.response.TeamResponse;

import java.util.List;

public interface TeamsService {
    TeamResponse addOrEdit(TeamRequest request);
    void delete(long teamId);
    TeamResponse getTeam(long teamId);
    List<TeamResponse> findAll();
    void subscribe(long userId, long teamId);
    void deleteSub(long userId, long teamId);
}
