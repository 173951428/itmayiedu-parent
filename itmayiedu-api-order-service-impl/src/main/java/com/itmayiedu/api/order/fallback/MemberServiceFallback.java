package com.itmayiedu.api.order.fallback;

import com.itmayiedu.api.entity.UserEntity;
import com.itmayiedu.api.order.feign.MemberServiceFeign;
import com.itmayiedu.base.BaseApiService;
import com.itmayiedu.base.ResponseBase;
import org.springframework.stereotype.Component;

/**
 * @Author zxp
 * @Date 2020/2/26 12:42
 */
@Component  // 加入@Component 注解，表示这是一个组件，让springboot扫描到
public class MemberServiceFallback  extends BaseApiService implements MemberServiceFeign  {

    @Override
    public UserEntity getMember(String name) {
        return null;
    }

    /**
     * 以类的方式进行服务降级
     * 订单调用服务端,服务降级处理的方法,可以写服务降级的友好提示
     * @return
     */
    @Override
    public ResponseBase getUserInfo() {
        return setResultError("服务器忙2,请稍后重试");
    }
}
