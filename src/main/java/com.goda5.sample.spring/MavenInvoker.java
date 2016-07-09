package com.goda5.sample.spring;

import com.jcabi.aether.Aether;
import org.apache.maven.shared.invoker.*;
import org.sonatype.aether.artifact.Artifact;
import org.sonatype.aether.repository.RemoteRepository;
import org.sonatype.aether.util.artifact.DefaultArtifact;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by tong on 09/07/2016.
 */
public class MavenInvoker {
    public static void main(String[] args) throws Exception {
        org.eclipse.jetty.server.Server.main();
        File local = new File("C:\\Users\\tong\\.m2\\repository");
        Collection<RemoteRepository> remotes = Arrays.asList(
                new RemoteRepository(
                        "maven-central",
                        "default",
                        "http://repo1.maven.org/maven2/"
                ));
        Collection<Artifact> deps = new Aether(remotes, local).resolve(
                new DefaultArtifact("org.apache.openejb", "tomee-embedded", "", "jar", "1.7.4"),
                "runtime"
        );

        String command = "java -cp " + deps.stream().findFirst().get().getFile().getAbsolutePath() + " org.apache.tomee.embedded.Main";
        Process exec = Runtime.getRuntime().exec("java");
        exec.waitFor();

        StringBuffer sb = new StringBuffer();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(exec.getErrorStream()));
        String line = "";
        while ((line = reader.readLine())!= null) {
            sb.append(line + "\n");
        }
        System.out.println(sb.toString());

        String javaCommand = System.getProperty("java.home") + "/bin/java";
        ProcessBuilder processBuilder = new ProcessBuilder(javaCommand, "-cp", deps.stream().findFirst().get().getFile().getAbsolutePath(), "org.apache.tomee.embedded.Main");
        Process process = processBuilder.start();
        process.waitFor();
        reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((line = reader.readLine())!= null) {
            sb.append(line + "\n");
        }
        System.out.println(sb.toString());

        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("c:/tmp/local-repository"));
        request.setGoals(Collections.singletonList("install"));

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File("C:\\Program Files (x86)\\JetBrains\\IntelliJ IDEA Community Edition 2016.1\\plugins\\maven\\lib\\maven3"));

        try {
            invoker.execute(request);
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }

        System.out.println(execCmd("java"));
    }

    public static String execCmd(String cmd) throws java.io.IOException {
        java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(cmd).getErrorStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
