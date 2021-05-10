package com.example.aws.cloudwatch.metrics;

import io.micrometer.cloudwatch.CloudWatchMeterRegistry;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*@SpringBootApplication
public class ExportMetricsToCloudwatchApplication {

    public static void main(String[] args) {

        SpringApplication.run(ExportMetricsToCloudwatchApplication.class, args);

    }

}*/


@SpringBootApplication
public class ExportMetricsToCloudwatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExportMetricsToCloudwatchApplication.class, args);

    }

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    @Bean
    ApplicationRunner runner (MeterRegistry mr){
        return args -> {
            this.executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {

                    mr.timer("transform-photo-task").record(Duration.ofMillis((long)(Math.random()*1000)));

                }
            }, 500, 500, TimeUnit.MILLISECONDS);
        };
    }

}