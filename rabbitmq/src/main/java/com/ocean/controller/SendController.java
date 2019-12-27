package com.ocean.controller;

import com.ocean.common.ControllerExecutor;
import com.ocean.common.ResponseResult;
import com.ocean.common.ServiceException;
import com.ocean.model.Message;
import com.ocean.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author guodahai
 * @version 2018/4/20 下午3:37
 */
@RestController
@RequestMapping("/send")
public class SendController {

    @Autowired
    private SendService sendService;

    @PostMapping
    public ResponseResult sendHello(@RequestBody Message request) {
        return new ControllerExecutor<Boolean, Message>(request) {

            @Override
            public void checkParam(Message... param) throws ServiceException {

            }

            @Override
            public Boolean executeService(Message... param) throws ServiceException {
                return sendService.send(request);
            }
        }.execute(request);
    }
}
