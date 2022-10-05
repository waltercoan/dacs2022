package br.univille.apidacs2022.opentrace;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.Configuration.SenderConfiguration;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.jaegertracing.internal.samplers.RateLimitingSampler;

@Configuration
public class JaegerConfig {
    @Value("${dacs2022.jaeger.server}")
    private String JAEGER_SERVER;
    @Bean
    public JaegerTracer jaegerTracer(){
        var sampler = new SamplerConfiguration().withType(ConstSampler.TYPE)
        .withParam(1);
        var reporter = new ReporterConfiguration().withLogSpans(true)
        .withSender(new SenderConfiguration().withAgentHost(JAEGER_SERVER));
        return new io.jaegertracing.Configuration("dacsapi-waltercoan")
            .withSampler(sampler) //10 Traces por segundo
            //.withSampler(new SamplerConfiguration().withType(RateLimitingSampler.TYPE)
            //.withParam(10)) //10 Traces por segundo
            .withReporter(reporter)
            .getTracer();
    }
}
