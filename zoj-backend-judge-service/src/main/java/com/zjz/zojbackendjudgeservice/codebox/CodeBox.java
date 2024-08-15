package com.zjz.zojbackendjudgeservice.codebox;

import com.zjz.model.codebox.model.ExecuteRequest;
import com.zjz.model.codebox.model.ExecuteResponse;

public interface CodeBox {
    /**
     * 执行代码接口
     */
    ExecuteResponse executeCode(ExecuteRequest executeRequest);

    /**
     * 查看代码沙箱状态
     */
    String getStatus();
}
