package com.goda5.sample.spring;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerBase;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.SetupThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

/**
 * Created by tong on 20/05/2016.
 */
public class JmeterBenchmarker {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmeterBenchmarker.class);

    static {
//        JMeterUtils.loadJMeterProperties("src/main/resources/jmeter.properties");
    }

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        // Engine
        StandardJMeterEngine jm = new StandardJMeterEngine();
        JMeterUtils.setJMeterHome("c:/apachejm");
        // jmeter.properties
        JMeterUtils.loadJMeterProperties("src/main/resources/jmeter.properties");

        HashTree hashTree = new HashTree();

        final HTTPSampler sampler = new HTTPSampler();
        sampler.setPath("https://www.bbc.co.uk");
        sampler.setMethod(HTTPSamplerBase.GET);
        sampler.setAutoRedirects(true);
        sampler.setConcurrentDwn(true);
        sampler.setConcurrentPool("8");
        sampler.setImageParser(true);

//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                SampleResult sample = sampler.sample();
//                LOGGER.error("time taken {}", sample.getTime());
//            }
//        };
//        executorService.submit(runnable);
//        executorService.submit(runnable);
//        executorService.submit(runnable);
//        executorService.submit(runnable);
//        executorService.submit(runnable);
//        executorService.submit(runnable);
//        executorService.submit(runnable);
//        executorService.submit(runnable);
//        executorService.submit(runnable);
//        executorService.submit(runnable);
//
//        executorService.shutdown();
//        executorService.awaitTermination(10, TimeUnit.SECONDS);

//        HTTPSampler httpBase = new HTTPSampler();
//        httpBase.setConnectTimeout("1000");
//        JmeterBenchmarker httpJava = new JmeterBenchmarker(httpBase);
//        System.out.println(httpJava.resultProcessing(true, 0, httpJava.test()).getTime());

        // Loop Controller
        TestElement loopCtrl = new LoopController();
        ((LoopController)loopCtrl).setLoops(1);
        ((LoopController)loopCtrl).addTestElement(sampler);
        ((LoopController)loopCtrl).setFirst(true);

        // Thread Group
        SetupThreadGroup threadGroup = new SetupThreadGroup();
        threadGroup.setNumThreads(1);
        threadGroup.setRampUp(1);
        threadGroup.setSamplerController((LoopController)loopCtrl);

        // Test plan
        TestPlan testPlan = new TestPlan("MY TEST PLAN");


        hashTree.add("testPlan", testPlan);
        hashTree.add("loopCtrl", loopCtrl);
        hashTree.add("threadGroup", threadGroup);
        hashTree.add("httpSampler", sampler);

        jm.configure(hashTree);

        Summariser summer = null;
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (summariserName.length() > 0) {
            summer = new Summariser(summariserName);
        }

        String logFile = "c:/tmp/test-report/test.jtl";
        ResultCollector logger = new ResultCollector(summer);
        logger.setFilename(logFile);
        hashTree.add(hashTree.getArray()[0], logger);

        jm.run();
    }
}
