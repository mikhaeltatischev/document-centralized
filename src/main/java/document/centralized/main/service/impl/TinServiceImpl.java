package document.centralized.main.service.impl;

import document.centralized.main.client.TinClient;
import document.centralized.main.dto.TinFindRequest;
import document.centralized.main.service.TinService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
public final class TinServiceImpl implements TinService {

    private TinClient tinClient;

    @Autowired
    public TinServiceImpl(TinClient tinClient) {
        this.tinClient = tinClient;
    }

    @Override
    public String getTin(TinFindRequest request) {
        log.info("TinRequest received - " + request);
        return tinClient.getTin(request);
    }

}