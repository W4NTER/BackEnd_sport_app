package urfu.sport_app_android_vkr.service.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import urfu.sport_app_android_vkr.domain.dto.request.TeamRequest;
import urfu.sport_app_android_vkr.domain.dto.response.TeamResponse;
import urfu.sport_app_android_vkr.domain.repository.TeamsRepository;
import urfu.sport_app_android_vkr.domain.repository.TeamsToUsersRepository;
import urfu.sport_app_android_vkr.service.TeamsService;

import java.util.List;

@Repository
public class JdbcTeamsService implements TeamsService {
    private final Logger LOGGER = LogManager.getLogger();
    private final TeamsRepository teamsRepository;
    private final TeamsToUsersRepository teamsToUsersRepository;

    public JdbcTeamsService(
            TeamsRepository teamsRepository,
            TeamsToUsersRepository teamsToUsersRepository) {
        this.teamsRepository = teamsRepository;
        this.teamsToUsersRepository = teamsToUsersRepository;
    }


    @Override
    @Transactional
    public Long add(TeamRequest request, long authorId) {
        try {
            return teamsRepository.add(request, authorId);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return -1L;
        }
    }

    @Override
    @Transactional
    public TeamResponse editTeam(TeamRequest request, long teamId) {
        return teamsRepository.editTeam(request, teamId);
    }

    @Override
    @Transactional
    public void delete(long teamId) {
        teamsRepository.delete(teamId);
    }

    @Override
    @Transactional
    public TeamResponse getTeam(long teamId) {
        return teamsRepository.getTeam(teamId);
    }

    @Override
    @Transactional
    public List<TeamResponse> findAll() {
        return teamsRepository.findAll();
    }

    @Override
    @Transactional(timeout = 10)
    @Lock(LockMode.PESSIMISTIC_WRITE)
    public void subscribe(long userId, long teamId) {
        if (teamsRepository.getTeam(teamId).count_teammates() > 5) {
            throw new IllegalArgumentException();
        }
        teamsToUsersRepository.add(userId, teamId);
    }

    @Override
    @Transactional
    public void unsubscribe(long userId, long teamId) {
        teamsToUsersRepository.delete(userId, teamId);
    }

    @Override
    @Transactional
    public List<TeamResponse> findBySport(String sport) {
        List<TeamResponse> teams = teamsRepository.findAll();
        return teams.stream().filter(team -> team.sport().equals(sport)).toList();
    }
}
