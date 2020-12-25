package com.msc.apigateway.filter;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Configuration
@Slf4j
public class PostFilter extends ZuulFilter {

    /* post 응답에 헤더 추가 및 API 응답속도, 메트릭 등의 수집 */

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        HttpServletResponse response = ctx.getResponse();
        log.info("Response Status = {}", response.getStatus());
        try (InputStream is = ctx.getResponseDataStream()) {
            String resData = CharStreams.toString(new InputStreamReader(is, StandardCharsets.UTF_8));
            log.info("Response Data = {}", resData);
            ctx.setResponseBody(resData);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }
}
