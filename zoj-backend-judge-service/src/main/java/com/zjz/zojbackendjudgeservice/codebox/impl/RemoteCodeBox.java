package com.zjz.zojbackendjudgeservice.codebox.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.zjz.common.annotation.CodeBoxLog;
import com.zjz.common.common.ErrorCode;
import com.zjz.common.exception.BusinessException;
import com.zjz.model.codebox.model.CodeBoxProperties;
import com.zjz.model.codebox.model.ExecuteRequest;
import com.zjz.model.codebox.model.ExecuteResponse;
import com.zjz.zojbackendjudgeservice.codebox.CodeBox;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 远程代码沙箱 调用已经实现的 OJ 系统
 */
@Component
public class RemoteCodeBox implements CodeBox {

    @Resource
    private CodeBoxProperties codeBoxProperties;

    @Override
    @CodeBoxLog
    public ExecuteResponse executeCode(ExecuteRequest executeRequest) {
        String request = JSONUtil.toJsonStr(executeRequest);
        String response = HttpUtil.createPost(codeBoxProperties.getRemoteUrl())
                .header(codeBoxProperties.getAuthHeader(),codeBoxProperties.getSecretKey())
                .body(request)
                .execute()
                .body();
        if (StrUtil.isBlank(response)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "调用远程代码沙箱失败");
        }
        return JSONUtil.toBean(response, ExecuteResponse.class);
    }

    @Override
    public String getStatus() {
        // TODO 远程代码沙箱状态
        System.out.println("远程代码沙箱 调用已经实现的 OJ 系统状态");
        return null;
    }
}
