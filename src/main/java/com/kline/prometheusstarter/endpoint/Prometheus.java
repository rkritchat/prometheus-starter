package com.kline.prometheusstarter.endpoint;

import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.micrometer.prometheus.PrometheusRenameFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@RestController
@RequestMapping("/prometheus")
public class Prometheus {

    @Autowired
    private PrometheusMeterRegistry prometheusMeterRegistry;

    @GetMapping
    public void test(HttpServletResponse httpExchange){
        String response = prometheusMeterRegistry.scrape();
        httpExchange.setStatus(HttpServletResponse.SC_OK);
        try (OutputStream os = httpExchange.getOutputStream()) {
            os.write(response.getBytes());
        } catch (IOException e) {
            log.error("Cannot connect prometheus!!!");
        }
    }

}
