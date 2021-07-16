package com.smart;

import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

public class ResourcePatternResolverTest {

    @Test
    public void smoke() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        // Resource[] resources = resolver.getResources("classpath*:*.xml");
        Resource[] resources = resolver.getResources("classpath*:/**/*.xml");
        for (Resource resource : resources) {
            if ("file".equals(resource.getURL().getProtocol())) {
                System.out.println(resource.getFile().getAbsolutePath());
            }
        }
    }
}
