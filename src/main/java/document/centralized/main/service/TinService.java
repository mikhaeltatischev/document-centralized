package document.centralized.main.service;

import document.centralized.main.dto.TinFindRequest;

public interface TinService {

    String getTin(TinFindRequest request);

}