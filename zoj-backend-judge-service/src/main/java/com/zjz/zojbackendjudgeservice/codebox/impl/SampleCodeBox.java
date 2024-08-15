package com.zjz.zojbackendjudgeservice.codebox.impl;


import com.zjz.common.annotation.CodeBoxLog;
import com.zjz.model.codebox.model.ExecuteRequest;
import com.zjz.model.codebox.model.ExecuteResponse;
import com.zjz.model.enums.JudgeStatusEnum;
import com.zjz.zojbackendjudgeservice.codebox.CodeBox;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 示例代码沙箱 用于跑通业务流程
 */
@Component
public class SampleCodeBox implements CodeBox {
    @Override
    @CodeBoxLog
    public ExecuteResponse executeCode(ExecuteRequest executeRequest) {
        // TODO 示例代码沙箱
        System.out.println("示例代码沙箱");
        return ExecuteResponse.builder()
                .outputs(executeRequest.getInputs())
                .message(JudgeStatusEnum.SUCCESS.getText())
                .executeInfos(new ArrayList<>())
                .build();
    }

    @Override
    public String getStatus() {
        // TODO 示例代码沙箱状态
        System.out.println("示例代码沙箱状态");
        return null;
    }
}
