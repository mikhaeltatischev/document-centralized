package document.centralized.main.client.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import document.centralized.main.client.BaseClient;
import document.centralized.main.client.TinClient;
import document.centralized.main.dto.TinFindRequest;
import document.centralized.main.dto.TinGetRequest;
import document.centralized.main.dto.TinResponse;
import document.centralized.main.exception.TinNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Service
public final class TinClientImpl extends BaseClient implements TinClient {

    private final ObjectMapper mapper;

    private static final String TIN_PATH = "/inn-new-proc.json";
    private static final String QUERY_ID = "?c={c}&fam={fam}&nam={nam}&otch={otch}&bdate={bdate}&doctype={doctype}&docno={docno}&docdt={docdt}";
    private static final String QUERY_TIN = "?c={c}&requestId={requestId}";
    private static final String CATEGORY = "c";
    private static final String FIND_PARAM = "find";
    private static final String GET_PARAM = "get";
    private static final String LAST_NAME_PARAM = "fam";
    private static final String FIRST_NAME_PARAM = "nam";
    private static final String SURNAME_PARAM = "otch";
    private static final String DATE_PARAM = "bdate";
    private static final String DOCTYPE_PARAM = "doctype";
    private static final String NUMBER_PARAM = "docno";
    private static final String DOC_DT_PARAM = "docdt";
    private static final String ID_PARAM = "requestId";


    public TinClientImpl(@Value("${service.tax.url}") String baseUrl, RestTemplateBuilder builder, ObjectMapper mapper) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(baseUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
        this.mapper = mapper;
    }

    @Override
    @SneakyThrows
    public String getTin(TinFindRequest request) {
        Map<String, Object> param = Map.of(CATEGORY, FIND_PARAM,
                LAST_NAME_PARAM, request.getLastName(),
                FIRST_NAME_PARAM, request.getFirstName(),
                SURNAME_PARAM, request.getSurname(),
                DATE_PARAM, request.getBirthday(),
                DOCTYPE_PARAM, request.getDocType(),
                NUMBER_PARAM, request.getDocNum(),
                DOC_DT_PARAM, request.getDocDateTake());

        ResponseEntity<Object> response = post(TIN_PATH + QUERY_ID, param);

        TinGetRequest tinGetRequest = mapper.convertValue(response.getBody(), TinGetRequest.class);

        return getTinFromId(tinGetRequest);
    }

    private String getTinFromId(TinGetRequest tinGetRequest) {
        Map<String, Object> param = Map.of(CATEGORY, GET_PARAM,
                ID_PARAM, tinGetRequest.getRequestId());

        ResponseEntity<Object> response = post(TIN_PATH + QUERY_TIN, param);

        TinResponse tinResponse = mapper.convertValue(response.getBody(), TinResponse.class);
        checkState(tinResponse);

        return tinResponse.getInn();
    }

    private void checkState(TinResponse tinResponse) {
        if (tinResponse.getState() == 0) {
            throw new TinNotFoundException();
        }
    }

}