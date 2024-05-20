package urfu.sport_app_android_vkr.domain.repository;

import urfu.sport_app_android_vkr.domain.dto.request.TeamRequest;
import urfu.sport_app_android_vkr.domain.dto.response.TeamResponse;

import java.util.List;

public interface TeamsRepository {
    void add(TeamRequest team);
    void delete(Long teamId);
    TeamResponse getTeam(long team_id);
    TeamResponse editTeam(TeamRequest team, long teamId);
    List<TeamResponse> findAll();
}
