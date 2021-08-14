package com.backend.template.dmosbackendtemplate.rest;

import com.backend.template.dmosbackendtemplate.service.SentimentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/api/sentiment")
public class SentimentResource
{

    @Inject
    private SentimentService sentimentService;

    @GetMapping("")
    //@RolesAllowed("dmos_read")
    public String getSentiment() {
       return sentimentService.getSentiment();
    }

}
