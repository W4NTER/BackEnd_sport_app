package urfu.sport_app_android_vkr.domain.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import urfu.sport_app_android_vkr.domain.dto.request.PlaygroundRequest;
import urfu.sport_app_android_vkr.domain.dto.response.PlaygroundResponse;
import urfu.sport_app_android_vkr.domain.repository.PlaygroundsRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class JdbcPlaygroundRepository implements PlaygroundsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final static Float DEFAULT_VALUE_RATING = 0.0f;

    public JdbcPlaygroundRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long add(PlaygroundRequest request) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql =  "insert into playgrounds (latitude, longitude, path_to_image, price, sport, city, rating, address) values(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, request.latitude());
            ps.setDouble(2, request.longitude());
            ps.setString(3, request.pathToImage());
            ps.setInt(4, request.price());
            ps.setString(5, request.sport());
            ps.setString(6, request.city());
            ps.setFloat(7, DEFAULT_VALUE_RATING);
            ps.setString(8, request.address());
            return ps;
        }, keyHolder);

        return (Long) keyHolder.getKeys().get("playground_id");
    }

    @Override
    public void delete(long playgroundId) {
        jdbcTemplate.update("delete from playgrounds where playground_id = ?", playgroundId);
    }

    @Override
    public PlaygroundResponse editPlayground(PlaygroundRequest request, long playgroundId) {
        jdbcTemplate.update("update playgrounds set " +
                "latitude = ?," +
                "longitude = ?," +
                "path_to_image = ?," +
                "price = ?," +
                "sport = ?," +
                "city = ?," +
                "address = ? " +
                "where playground_id = ?",
                request.latitude(), request.longitude(), request.pathToImage(), request.price(),
                request.sport(), request.city(), request.address(), playgroundId);
        return getPlayground(playgroundId);
    }

    @Override
    public PlaygroundResponse getPlayground(Double latitude, Double longitude) {
        return jdbcTemplate.queryForObject("select playground_id, latitude, longitude, path_to_image," +
                        "price, sport,city,rating,address from playgrounds where latitude = ? and longitude = ?",
                (rs, rowNum) -> createResponse(rs), latitude, longitude);
    }

    @Override
    public PlaygroundResponse getPlayground(long playgroundId) {
        return jdbcTemplate.queryForObject("select playground_id, latitude, longitude, path_to_image, " +
                "price, sport, city, rating, address from playgrounds where playground_id = ?", (rs, rowNum) -> createResponse(rs), playgroundId);
    }

    @Override
    public List<PlaygroundResponse> findAll() {
        return jdbcTemplate.query("select playground_id, latitude, longitude, path_to_image, price, sport, city, rating, address" +
                " from playgrounds", (rs, rowNum) -> createResponse(rs));
    }

    private PlaygroundResponse createResponse(ResultSet rs) throws SQLException {
        return new PlaygroundResponse(
                rs.getLong("playground_id"),
                Math.round(rs.getDouble("latitude") * 1000000) / 1000000.0,
                Math.round(rs.getDouble("longitude") * 1000000) / 1000000.0,
                rs.getString("path_to_image"),
                rs.getInt("price"),
                rs.getString("sport"),
                rs.getString("city"),
                rs.getFloat("rating"),
                rs.getString("address")
        );
    }
}
