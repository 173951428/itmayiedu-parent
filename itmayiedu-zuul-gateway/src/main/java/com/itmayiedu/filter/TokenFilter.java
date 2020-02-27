package com.itmayiedu.filter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author zxp
 * @Date 2020/2/27 10:57
 * 使用过滤器验证客户端是否有登陆。
 */
@Component
public class TokenFilter  extends ZuulFilter {

    @Value("${server.port}")
    private String serverPort;
    /**
     * 过滤类型 "pre",表示在请求之前进行执行的方法
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序,当一个请求在同一阶段的时候,存在多个过滤器的时候,多个过滤器执行顺序
     * 通过数字指定 ,优先级为0，数字越大，优先级越低
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * /表示过滤器是否生效，默认为false，现在配置为true，
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 编写过滤器拦截的代码
     * 案例: 拦截所有的请求接口,判断服务接口上是否有传递userToken
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        System.out.println("请求的url被拦截了");
        //1.获取上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        //2.获取Request对象
        HttpServletRequest request = requestContext.getRequest();
        //3.获取token的时候,从请求头中获取
        String token=request.getHeader("token");

        if(StringUtils.isBlank(token)){
            token=request.getParameter("token");
        }

        if(StringUtils.isBlank(token)){
            // 如果token为空,不会去调用任何服务的接口,网关服务直接响应给客户端
            requestContext.setSendZuulResponse(false); // 设置zull返回状态错误
            requestContext.setResponseBody("token is null");
            requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);// 返回401
        }

        System.out.println("网关服务器发端口号:"+serverPort);
        return null;
    }
}
