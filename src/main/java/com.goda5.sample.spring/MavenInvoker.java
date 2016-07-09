package com.goda5.sample.spring;

import com.jcabi.aether.Aether;
import org.apache.maven.shared.invoker.*;
import org.sonatype.aether.artifact.Artifact;
import org.sonatype.aether.repository.RemoteRepository;
import org.sonatype.aether.resolution.DependencyResolutionException;
import org.sonatype.aether.util.artifact.DefaultArtifact;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by tong on 09/07/2016.
 */
public class MavenInvoker {
    public static void main(String[] args) throws DependencyResolutionException {
        File local = new File("c:/tmp/local-repository");
        Collection<RemoteRepository> remotes = Arrays.asList(
                new RemoteRepository(
                        "maven-central",
                        "default",
                        "http://repo1.maven.org/maven2/"
                ));
        Collection<Artifact> deps = new Aether(remotes, local).resolve(
                new DefaultArtifact("junit", "junit-dep", "", "jar", "4.10"),
                "runtime"
        );

        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("/path/to/pom.xml"));
        request.setGoals(Collections.singletonList("install"));

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File("C:\\Program Files (x86)\\JetBrains\\IntelliJ IDEA Community Edition 2016.1\\plugins\\maven\\lib\\maven3"));

        try {
            invoker.execute(request);
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
    }
}
