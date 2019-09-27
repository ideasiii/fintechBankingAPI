package api.bank.huanan.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Created by Jugo on 2019-08-19
 */

@Provider
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter
{
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {//Request Filter
        
        requestContext.getHeaders().add( "Access-Control-Allow-Credentials", "true" );
        requestContext.getHeaders().add( "Access-Control-Allow-Origin", "*");
        requestContext.getHeaders().add( "Access-Control-Allow-Methods", "OPTIONS, GET, POST, DELETE, PUT" );
        requestContext.getHeaders().add( "Access-Control-Allow-Headers", "Content-Type" );
        
        // When HttpMethod comes as OPTIONS, just acknowledge that it accepts...
        if ( requestContext.getRequest().getMethod().equals( "OPTIONS" ) ) {
            
            
            // Just send a OK signal back to the browser (Abort the filter chain with a response.)
            Response response = Response.status( Response.Status.OK )
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .header("Access-Control-Allow-Headers", "Content-Type, accept, headers")
                    .build();
            requestContext.abortWith( response );
            
        }
        
    }
    
    
    @Override
    public void filter(ContainerRequestContext requestContext,
            ContainerResponseContext responseContext) throws IOException
    {
        responseContext.getHeaders().add(
                "Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Credentials", "true");
//        responseContext.getHeaders().add(
//                "Access-Control-Allow-Headers",
//                "origin, content-type, accept, authorization");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}