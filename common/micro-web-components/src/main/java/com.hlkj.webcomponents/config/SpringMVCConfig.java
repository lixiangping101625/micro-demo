package com.hlkj.webcomponents.config;
//import com.hlkj.mallclassic.interceptor.PermissionInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置拦截器
 */
@Configuration
public class SpringMVCConfig extends WebMvcConfigurationSupport {

//  @Bean
//  public PermissionInterceptor getTokenInterceptor() {
//    return new PermissionInterceptor();
//  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
//    InterceptorRegistration ir = registry.addInterceptor(this.getTokenInterceptor());
//    //拦截所有
//    ir.addPathPatterns("/**")
//            //放行无需token认证的api
//            .excludePathPatterns("/swagger-resources/**", "/static/**","/img/*", "/webjars/**", "/v2/**", "/swagger-ui.js/**")
//            .excludePathPatterns("/user/login/username/pwd")//登录api
//            .excludePathPatterns("/error/req")//无该路径
//            .excludePathPatterns("/wel")//首页
//            .excludePathPatterns("/token/**")//首页
//            .excludePathPatterns("/banner/**")//首页
//            .excludePathPatterns("/category/**")
//            .excludePathPatterns("/sku/**")
//            .excludePathPatterns("/spu/list/page")
//            .excludePathPatterns("/wechat/**")
//            .excludePathPatterns("/cart/sync")
//            .excludePathPatterns("/test/lxp")
//            .excludePathPatterns("/captchaImage/**")
//            .excludePathPatterns("/coupon/all")//活动页面展示所有优惠券
//            .excludePathPatterns("/404.do");//404跳转
//    super.addInterceptors(registry);
    /**
     * 由于前后端分离，所以拦截后会提示相关token错误信息给前端，由前端跳转到首页并给出提示！
     */
  }

  /**
   * 静态资源的映射
   * @param registry
   */
  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/resources/")
            .addResourceLocations("classpath:/static/")
            .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("swagger-ui.js")
            .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    super.addResourceHandlers(registry);
  }

  //跨域配置
  @Override
  protected void addCorsMappings(CorsRegistry registry) {
    super.addCorsMappings(registry);
    registry.addMapping("/**")
            .allowedOrigins("*");
  }

}