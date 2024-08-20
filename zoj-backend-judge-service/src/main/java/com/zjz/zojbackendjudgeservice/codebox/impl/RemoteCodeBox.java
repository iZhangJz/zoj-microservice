package com.zjz.zojbackendjudgeservice.codebox.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.zjz.common.annotation.CodeBoxLog;
import com.zjz.common.common.ErrorCode;
import com.zjz.common.exception.BusinessException;
import com.zjz.model.codebox.enums.CodeBoxStatusEnum;
import com.zjz.model.codebox.model.CodeBoxProperties;
import com.zjz.model.codebox.model.ExecuteRequest;
import com.zjz.model.codebox.model.ExecuteResponse;
import com.zjz.zojbackendjudgeservice.codebox.CodeBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 远程代码沙箱 调用已经实现的 OJ 系统
 */
@Component
@Slf4j
public class RemoteCodeBox implements CodeBox {

    @Resource
    private CodeBoxProperties codeBoxProperties;

    @Override
    @CodeBoxLog
    public ExecuteResponse executeCode(ExecuteRequest executeRequest) {
        String request = JSONUtil.toJsonStr(executeRequest);
        ExecuteResponse executeResponse = new ExecuteResponse();
        String language = executeRequest.getLanguage();
        try{
            String url = codeBoxProperties.getRemoteUrl() + language;
            String response = HttpUtil.createPost(url)
                    .header(codeBoxProperties.getAuthHeader(),codeBoxProperties.getSecretKey())
                    .body(request)
                    .execute()
                    .body();
            if (StrUtil.isBlank(response)) {
                log.error("调用远程代码沙箱失败,响应为空");
                executeResponse.setStatus(CodeBoxStatusEnum.ERROR.getValue());
                throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "调用远程代码沙箱失败,响应为空");
            }else {
                executeResponse = JSONUtil.toBean(response, ExecuteResponse.class);
            }
        }catch (RuntimeException e){
            log.error("调用远程代码沙箱失败,网络或服务异常",e);
            executeResponse.setStatus(CodeBoxStatusEnum.CRASH.getValue());
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "调用远程代码沙箱失败,网络或服务异常");
        }
        return executeResponse;
    }

    @Override
    public String getStatus() {
        // TODO 远程代码沙箱状态
        System.out.println("远程代码沙箱 调用已经实现的 OJ 系统状态");
        return null;
    }
}
