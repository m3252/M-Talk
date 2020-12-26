package com.msc.mtalk.domain.inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureRestDocs // (1)
public abstract class InjectControllerTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

}
