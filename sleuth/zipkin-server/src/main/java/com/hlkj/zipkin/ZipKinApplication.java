package com.hlkj.zipkin;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import zipkin.server.internal.EnableZipkinServer;

/**
 * @author Lixiangping
 * @createTime 2022年11月03日 16:47
 * @decription:
 */
@SpringBootApplication
@EnableZipkinServer
public class ZipKinApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ZipKinApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
