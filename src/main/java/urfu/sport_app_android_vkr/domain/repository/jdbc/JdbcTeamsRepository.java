package urfu.sport_app_android_vkr.domain.repository.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import urfu.sport_app_android_vkr.domain.dto.request.TeamRequest;
import urfu.sport_app_android_vkr.domain.dto.response.TeamResponse;
import urfu.sport_app_android_vkr.domain.repository.TeamsRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class JdbcTeamsRepository implements TeamsRepository {
    private final Logger LOGGER = LogManager.getLogger();
    private final JdbcTemplate jdbcTemplate;

    public JdbcTeamsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long add(TeamRequest team, long authorId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into teams (sport, count_teammates, team_level, title, description, author_id) values (?,?,?,?,?,?)";

        jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, team.sport());
                    ps.setLong(2, team.countTeammates());
                    ps.setString(3, team.teamLevel());
                    ps.setString(4, team.title());
                    ps.setString(5, team.description());
                    ps.setLong(6, authorId);
                    return ps;
        }, keyHolder);
        LOGGER.info("team_id = " + keyHolder.getKeys().get("team_id"));
        return (Long) keyHolder.getKeys().get("team_id");
    }

    @Override
    public void delete(Long teamId) {
        jdbcTemplate.update("delete from teams where team_id = ?", teamId);
    }

    @Override
    public TeamResponse getTeam(long teamId) {
        return jdbcTemplate.queryForObject("select team_id, sport, count_teammates, team_level, title, description, author_id" +
                        " from teams where team_id = ?",
                (rs, rowNum) -> createResponse(rs), teamId);
    }

    @Override
    public TeamResponse editTeam(TeamRequest team, long teamId) {
        LOGGER.info("update");
        jdbcTemplate.update("update teams set" +
                " sport = ?, count_teammates = ?, team_level = ?, title = ?, description = ? where team_id = ?",
                team.sport(), team.countTeammates(), team.teamLevel(), team.title(), team.description(), teamId);
        return getTeam(teamId);
    }

    @Override
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
