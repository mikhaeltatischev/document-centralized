package document.centralized.main.client;

import document.centralized.main.dto.TinFindRequest;

public interface TinClient {

    String getTin(TinFindRequest request);

}