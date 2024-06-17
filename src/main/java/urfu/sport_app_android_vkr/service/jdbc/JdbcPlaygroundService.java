package urfu.sport_app_android_vkr.service.jdbc;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import urfu.sport_app_android_vkr.domain.dto.request.PlaygroundRequest;
import urfu.sport_app_android_vkr.domain.dto.response.PlaygroundResponse;
import urfu.sport_app_android_vkr.domain.repository.PlaygroundsRepository;
import urfu.sport_app_android_vkr.service.PlaygroundService;

import java.util.List;

@Service
public class JdbcPlaygroundService implements PlaygroundService {
    private final PlaygroundsRepository playgroundRepository;

    public JdbcPlaygroundService(PlaygroundsRepository playgroundRepository) {
        this.playgroundRepository = playgroundRepository;
    }

    @Override
    @Transactional
    public Long add(PlaygroundRequest request) {
        return playgroundRepository.add(request);
    }

    @Override
    @Transactional
    public void delete(long playgroundId) {
        playgroundRepository.delete(playgroundId);
    }

    @Override
    @Transactional
    public PlaygroundResponse editPlayground(PlaygroundRequest request, long playgroundId) {
        return playgroundRepository.editPlayground(request, playgroundId);
    }

    @Override
    @Transactional
    public PlaygroundResponse getPlayground(Double latitude, Double longitude) {
        return playgroundRepository.getPlayground(latitude, longitude);
    }

    @Override
    @Transactional
    public PlaygroundResponse getPlayground(long playgroundId) {
        return playgroundRepository.getPlayground(playgroundId);
    }

    @Override
    @Transactional
    public List<PlaygroundResponse> findAll() {
        return playgroundRepository.findAll();
    }
}
