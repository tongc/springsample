package com.goda5.sample.spring;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.littleshoot.proxy.HttpFilters;
import org.littleshoot.proxy.HttpFiltersAdapter;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by tong on 05/08/2016.
 */
public class HttpProxy {

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(9999), 0);
//        httpServer.start();

        HttpProxyServer server =
                DefaultHttpProxyServer.bootstrap()
                        .withPort(8080)
                        .withFiltersSource(new HttpFiltersSourceAdapter() {
                            public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {
                                return new HttpFiltersAdapter(originalRequest) {
                                    @Override
                                    public HttpResponse clientToProxyRequest(HttpObject httpObject) {
                                        return null;
                                    }

                                    @Override
                                    public HttpObject serverToProxyResponse(HttpObject httpObject) {
                                        return httpObject;
                                    }
                                };
                            }
                        })
                        .start();
    }

    static class Handler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            Content content = Request.Get(httpExchange.getRequestURI()).execute().returnContent();
            httpExchange.getResponseBody().write(content.asString().getBytes());

        }
    }
}
