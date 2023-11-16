package document.centralized.main.controller;

import document.centralized.main.dto.TinFindRequest;
import document.centralized.main.service.TinService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/tax")
@RequiredArgsConstructor
public class TinController {

    private final TinService service;

    @GetMapping("/tin")
    @Cacheable(cacheNames = "request")
    public String getTin(@Valid @RequestBody TinFindRequest tinRequest) {
        return service.getTin(tinRequest);
    }

}