package br.univille.apidacs2022.opentrace;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.samplers.ConstSampler;

@Configuration
public class JaegerConfig {
    @Bean
    public JaegerTracer jaegerTracer(){
        return new io.jaegertracing.Configuration("dacsapi-waltercoan")
            .withSampler(new SamplerConfiguration().withType(ConstSampler.TYPE)
            .withParam(1))
            .withReporter(new ReporterConfiguration().withLogSpans(true))
            .getTracer();
    }
}
