package com.eis.broker.controller;

import com.eis.broker.entity.Weight;
import com.eis.broker.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/broadcast/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;

    @Autowired
    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @RequestMapping("/addLoggingProduct")
    public boolean addLoggingProduct(String product) {
        analysisService.addLoggingProduct(product);
        return true;
    }

    @RequestMapping("/removeLoggingProduct")
    public boolean removeLoggingProduct(String product) {
        analysisService.removeLoggingProduct(product);
        return true;
    }

    @RequestMapping("/getWeight")
    public Weight getWeight(String product, Integer hours) {
        return analysisService.getWeight(product, hours);
    }
}
