package com.formacionbdi.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);

    private static final String FILTER_TYPE = "pre";
    private static final Integer FILTER_ORDER = 1;


    @Override
    public String filterType() {
        return FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();

        log.info(String.format("%s request enrutado a %s", req.getMethod(), req.getRequestURL().toString()));
        Long tiempoDeInicio = System.currentTimeMillis();
        req.setAttribute("tiempoDeInicio", tiempoDeInicio);

        return null;
    }
}
