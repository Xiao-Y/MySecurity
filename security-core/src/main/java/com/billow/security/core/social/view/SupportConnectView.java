package com.billow.security.core.social.view;


import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 返回解绑、绑定结果
 */
public class SupportConnectView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setContentType("text/html;charset=UTF-8");

        Object connections = map.get("connections");
        // connections 表示解绑成功
        if (connections == null) {
            response.getWriter().write("<h3>解绑成功</h3>");
        } else {// 绑定成功
            response.getWriter().write("<h3>绑定成功</h3>");
        }

    }
}
