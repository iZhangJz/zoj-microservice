package com.zjz.zojbackendjudgeservice.codebox.factory;


import com.zjz.zojbackendjudgeservice.codebox.CodeBox;
import com.zjz.zojbackendjudgeservice.codebox.impl.RemoteCodeBox;
import com.zjz.zojbackendjudgeservice.codebox.impl.SampleCodeBox;
import com.zjz.zojbackendjudgeservice.codebox.impl.ThirdPartyCodeBox;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 代码沙箱服务类，相当于简单工厂
 */
@Service
public class CodeBoxFactory {

    @Resource
    private ThirdPartyCodeBox thirdPartyCodeBox;

    @Resource
    private RemoteCodeBox remoteCodeBox;

    @Resource
    private SampleCodeBox sampleCodeBox;

    public CodeBox getCodeBox(String type) {
        switch (type) {
            case "sample":
                return sampleCodeBox;
            case "remote":
                return remoteCodeBox;
            case "third":
                return thirdPartyCodeBox;
            default:
                throw new IllegalArgumentException("type not support");
        }
    }
}
