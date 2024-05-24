package urfu.sport_app_android_vkr.domain.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import urfu.sport_app_android_vkr.domain.dto.request.TeamRequest;
import urfu.sport_app_android_vkr.domain.dto.response.TeamResponse;
import urfu.sport_app_android_vkr.domain.repository.TeamsRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcTeamsRepository implements TeamsRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTeamsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(TeamRequest team) {
        jdbcTemplate.update("insert into teams (sport, count_teammates, team_level, title, description, author_id) values (?,?,?,?,?,?)",
                team.sport(), team.countTeammates(), team.teamLevel(), team.title(), team.description(), team.authorId());
    }

    @Override
    @Transactional
    public void delete(Long teamId) {
        jdbcTemplate.update("delete from teams where team_id = ?", teamId);
    }

    @Override
    @Transactional
    public TeamResponse getTeam(long teamId) {
        return jdbcTemplate.queryForObject("select team_id, sport, count_teammates, team_level, title, description, author_id" +
                        " from teams where team_id = ?",
                (rs, rowNum) -> createResponse(rs), teamId);
    }

    @Override
    @Transactional
    public TeamResponse editTeam(TeamRequest team) {
        jdbcTemplate.update("update teams set team_id = ?," +
                " sport = ?, count_teammates = ?, team_level = ?, title = ?, description = ? where team_id = ? and author_id = ?",
                team.sport(), team.countTeammates(), team.teamLevel(), team.title(), team.description(), team.teamId(), team.authorId());
        return getTeam(team.teamId());
    }

    @Override
    @Transactional
    public List<TeamResponse> findAll() {
        return jdbcTemplate.query("select team_id, sport, count_teammates, team_level, title, description, author_id " +
                "from teams", (rs, rowNum) -> createResponse(rs));
    }


    private TeamResponse createResponse(ResultSet resultSet) throws SQLException {
        return new TeamResponse(
                resultSet.getLong("team_id"),
                resultSet.getString("sport"),
                resultSet.getLong("count_teammates"),
                resultSet.getString("team_level"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getLong("author_id")
        );
    }
}
