package com.billow.security.core.social.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取社交账号绑定的状态<br/>
 * 查看 ConnectController.connectionStatus
 *
 * @author billow
 * @see org.springframework.social.connect.web.ConnectController
 */
@Component("connect/status")
public class SupportConnetionStatusView extends AbstractView {

    @Autowired
    private ObjectMapper objectMapper;

    // 重写视图的返回
    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, List<Connection<?>>> connections = (Map<String, List<Connection<?>>>) map.get("connectionMap");

        // 简化数据,map中就是 ConnectController.connectionStatus 中 Model 的数据
        Map<String, Boolean> results = new HashMap<>();
        for (Map.Entry<String, List<Connection<?>>> entry : connections.entrySet()) {
            results.put(entry.getKey(), CollectionUtils.isEmpty(entry.getValue()));
        }

        // 返回json
        response.setContentType("appliction/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(results));
    }
}
