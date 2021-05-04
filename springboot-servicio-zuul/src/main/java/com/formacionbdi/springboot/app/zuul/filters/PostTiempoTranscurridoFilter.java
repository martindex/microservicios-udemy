package com.formacionbdi.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);

    private static final String FILTER_TYPE = "post";
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

        log.info("iniciando post");

        Long tiempoInicio=(Long) req.getAttribute("tiempoDeInicio");
        Long tiempoFinal=System.currentTimeMillis();
        Long tiempoTotal= tiempoFinal-tiempoInicio;

        log.info(String.format("Tiempo transcurrido en segundo %s", tiempoTotal.doubleValue()/1000.0));

        return null;
    }
}
