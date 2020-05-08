package com.kline.prometheusstarter.config;

import com.kline.prometheusstarter.endpoint.Prometheus;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.micrometer.prometheus.PrometheusRenameFilter;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@Data
@ConditionalOnClass(Prometheus.class)
public class Config {

    @Bean
    @ConditionalOnMissingBean
    public Prometheus ExecuteTimeTrackerAdvice(){
        return new Prometheus();
    }

    @Bean
    @ConditionalOnMissingBean
    public PrometheusMeterRegistry initPrometheusMeterRegistry(){
        PrometheusMeterRegistry prometheusMeterRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
        prometheusMeterRegistry.config().meterFilter(new PrometheusRenameFilter());
        return prometheusMeterRegistry;
    }
}
