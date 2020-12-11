package com.tugab.jobsearchplus.web.controllers;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/job")
public class JobController extends BaseController {

    @GetMapping("/all")
    public ModelAndView all() {
        WebClient webClient = WebClient.builder().exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                    .build())
                .build();

        WebClient.RequestBodySpec request = webClient
                .method(HttpMethod.GET)
                .uri("");

        String response2 = request.exchangeToMono(a -> a.bodyToMono(String.class)).block();
        return super.view("/job/all");
    }
}
