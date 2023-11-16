package document.centralized.main.service;

import document.centralized.main.client.TinClient;
import document.centralized.main.dto.TinFindRequest;
import document.centralized.main.service.impl.TinServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TinServiceTest {

    @Mock
    private TinClient tinClient;

    @InjectMocks
    private TinService tinService = new TinServiceImpl(tinClient);

    private TinFindRequest request;
    private String tin;

    @BeforeEach
    public void setUp() {
        request = TinFindRequest.builder()
                .firstName("First Name")
                .lastName("Last Name")
                .surname("Surname")
                .birthday("10.10.2000")
                .docType("21")
                .docNum("10 10 100000")
                .docDateTake("10.10.2000")
                .build();

        tin = "123123123123";
    }

    @Test
    public void getTinWhenMethodInvokedReturnResponse() {
        when(tinClient.getTin(request)).thenReturn(tin);

        String response = tinService.getTin(request);

        assertEquals(tin, response);
    }

}