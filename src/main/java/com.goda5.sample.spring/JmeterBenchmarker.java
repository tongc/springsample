package com.goda5.sample.spring;

import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerBase;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.property.StringProperty;
import org.apache.jmeter.util.JMeterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by tong on 20/05/2016.
 */
public class JmeterBenchmarker {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmeterBenchmarker.class);

    static {
        JMeterUtils.loadJMeterProperties("src/main/resources/jmeter.properties");
        JMeterUtils.setProperty("HTTPResponse.parsers", "htmlParser");
        JMeterUtils.setProperty("htmlParser.className", "org.apache.jmeter.protocol.http.parser.LagartoBasedHtmlParser");
        JMeterUtils.setProperty("htmlParser.types", "text/html");
    }

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        final HTTPSampler sampler = new HTTPSampler();
        sampler.setProperty(new StringProperty("HTTPResponse.parsers", "htmlParser"));
        sampler.setPath("https://www.bbc.co.uk");
        sampler.setMethod(HTTPSamplerBase.GET);
        sampler.setAutoRedirects(true);
        sampler.setConcurrentDwn(true);
        sampler.setConcurrentPool("8");
        sampler.setImageParser(true);

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SampleResult sample = sampler.sample();
                LOGGER.error("time taken {}", sample.getTime());
            }
        };
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

//        HTTPSampler httpBase = new HTTPSampler();
//        httpBase.setConnectTimeout("1000");
//        JmeterBenchmarker httpJava = new JmeterBenchmarker(httpBase);
//        System.out.println(httpJava.resultProcessing(true, 0, httpJava.test()).getTime());
    }
}
