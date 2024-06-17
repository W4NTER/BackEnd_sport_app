package urfu.sport_app_android_vkr.domain.repository;

import urfu.sport_app_android_vkr.domain.dto.request.PlaygroundRequest;
import urfu.sport_app_android_vkr.domain.dto.response.PlaygroundResponse;

import java.util.List;

public interface PlaygroundsRepository {
    Long add(PlaygroundRequest request);
    void delete(long playgroundId);
    PlaygroundResponse editPlayground(PlaygroundRequest request, long playgroundId);
    PlaygroundResponse getPlayground(Double latitude, Double longitude);
    PlaygroundResponse getPlayground(long playgroundId);
    List<PlaygroundResponse> findAll();
}
