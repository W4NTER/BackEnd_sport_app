package urfu.sport_app_android_vkr.domain.repository;

public interface AuthoritiesRepository {
    void addAuthority(String username, String authority);
    void delete(long userId);
}
