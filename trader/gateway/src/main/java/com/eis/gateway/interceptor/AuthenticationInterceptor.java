package com.eis.gateway.interceptor;

import com.eis.common.util.Msg;
import com.eis.common.util.MsgCode;
import com.eis.gateway.GatewayApplication;
import com.eis.gateway.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final String TAG="AuthInterceptor ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        GatewayApplication.logger.info(TAG+"request url: "+request.getRequestURL());
        String token = request.getHeader("token");
        if (token != null && !token.equals("null")) {
            boolean result = TokenUtil.authenticationVerify(token);
            if (result) {
                GatewayApplication.logger.info(TAG+"authentication passed");
                return true;
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            Msg msg=new Msg(MsgCode.AUTHENTICATION_FAILURE,null);
            response.getWriter().append(msg.toString());
            GatewayApplication.logger.info(TAG+"authentication failure");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;
    }
}
