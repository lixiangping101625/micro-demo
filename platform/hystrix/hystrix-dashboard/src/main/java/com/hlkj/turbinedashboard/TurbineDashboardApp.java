package com.hlkj.turbinedashboard;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author Lixiangping
 * @createTime 2022年10月31日 22:06
 * @decription:
 */
@SpringCloudApplication
@EnableHystrixDashboard
public class TurbineDashboardApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TurbineDashboardApp.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
