package com.example.controller;

import com.example.common.ControllerExecutor;
import com.example.common.ResponseResult;
import com.example.common.ServiceException;
import com.example.model.Message;
import com.example.service.SendService;
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
