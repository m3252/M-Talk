package com.msc.mtalk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.io.InputStream;

public class IntegrationTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected ResourceLoader resourceLoader;

    protected final String identifier = "{class-name}/{method-name}";

    protected <T> T readValue(final String path, Class<T> clazz) throws IOException {
        final InputStream json = resourceLoader.getResource(path).getInputStream();
        return objectMapper.readValue(json, clazz);
    }

    protected String readJson(final String path) throws IOException {
        final InputStream inputStream = resourceLoader.getResource(path).getInputStream();
        final ByteSource byteSource = new ByteSource() {
            @Override
            public InputStream openStream() {
                return inputStream;
            }
        };
        return byteSource.asCharSource(Charsets.UTF_8).read();
    }
}
