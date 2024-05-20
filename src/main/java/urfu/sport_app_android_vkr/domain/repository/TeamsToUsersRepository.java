package urfu.sport_app_android_vkr.domain.repository;

public interface TeamsToUsersRepository {
    void add(long user_id, long team_id);
    void delete(long user_id, long team_id);
}
