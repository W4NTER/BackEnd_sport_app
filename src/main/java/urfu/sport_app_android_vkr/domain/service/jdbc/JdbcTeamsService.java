package urfu.sport_app_android_vkr.domain.service.jdbc;

import org.springframework.stereotype.Repository;
import urfu.sport_app_android_vkr.domain.dto.request.TeamRequest;
import urfu.sport_app_android_vkr.domain.dto.response.TeamResponse;
import urfu.sport_app_android_vkr.domain.repository.jdbc.JdbcTeamsRepository;
import urfu.sport_app_android_vkr.domain.repository.jdbc.JdbcTeamsToUsersRepository;
import urfu.sport_app_android_vkr.domain.service.TeamsService;

import java.util.List;

@Repository
public class JdbcTeamsService implements TeamsService {
    private final JdbcTeamsRepository jdbcTeamsRepository;
    private final JdbcTeamsToUsersRepository jdbcTeamsToUsersRepository;

    public JdbcTeamsService(JdbcTeamsRepository jdbcTeamsRepository, JdbcTeamsToUsersRepository jdbcTeamsToUsersRepository) {
        this.jdbcTeamsRepository = jdbcTeamsRepository;
        this.jdbcTeamsToUsersRepository = jdbcTeamsToUsersRepository;
    }


    @Override
    public TeamResponse addOrEdit(TeamRequest request) {
        try {
            jdbcTeamsRepository.getTeam(request.authorId());
            return jdbcTeamsRepository.editTeam(request);
        } catch (Exception e) {
            jdbcTeamsRepository.add(request);
            return jdbcTeamsRepository.getTeam(request.teamId());
        }
    }

    @Override
    public void delete(long teamId) {
        jdbcTeamsRepository.delete(teamId);
    }

    @Override
    public TeamResponse getTeam(long teamId) {
        return jdbcTeamsRepository.getTeam(teamId);
    }

    @Override
    public List<TeamResponse> findAll() {
        return jdbcTeamsRepository.findAll();
    }

    @Override
    public void subscribe(long userId, long teamId) {
        jdbcTeamsToUsersRepository.add(userId, teamId);
    }

    @Override
    public void deleteSub(long userId, long teamId) {
        jdbcTeamsToUsersRepository.delete(userId, teamId);
    }
}
