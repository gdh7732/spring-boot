package com.example.controller;

import com.example.common.ControllerExecutor;
import com.example.common.ResponseResult;
import com.example.common.ServiceException;
import com.example.entity.TriggerRequest;
import com.example.service.JobAndTriggerService;
import com.example.service.SchedulerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * 定时任务控制层
 *
 * @author guodahai
 */
@RestController
@RequestMapping(value = "/job")
@Api("JobController相关api")
public class JobController {

    private static Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobAndTriggerService triggerService;

    @Autowired
    private SchedulerService schedulerService;

    @GetMapping
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("index.html");
    }

    @ApiOperation("添加定时任务")
    @PostMapping(value = "/add")
    public ResponseResult<Boolean> add(TriggerRequest request) throws ServiceException {
        return new ControllerExecutor<Boolean, TriggerRequest>(request) {

            @Override
            public void checkParam(TriggerRequest... param) throws ServiceException {

            }

            @Override
            public Boolean executeService(TriggerRequest... param) throws ServiceException {
                return schedulerService.add(param[0]);
            }
        }.execute(request);

    }


    @PostMapping(value = "/pause")
    public ResponseResult<Boolean> pause(TriggerRequest request) throws ServiceException {
        return new ControllerExecutor<Boolean, TriggerRequest>(request) {

            @Override
            public void checkParam(TriggerRequest... param) throws ServiceException {

            }

            @Override
            public Boolean executeService(TriggerRequest... param) throws ServiceException {
                return schedulerService.pause(param[0]);
            }
        }.execute(request);
    }


    @PostMapping(value = "/resume")
    public ResponseResult<Boolean> resume(TriggerRequest request) throws ServiceException {
        return new ControllerExecutor<Boolean, TriggerRequest>(request) {

            @Override
            public void checkParam(TriggerRequest... param) throws ServiceException {

            }

            @Override
            public Boolean executeService(TriggerRequest... param) throws ServiceException {
                return schedulerService.resume(param[0]);
            }
        }.execute(request);
    }


    @PostMapping(value = "/reschedule")
    public ResponseResult<Boolean> reschedule(TriggerRequest request) throws Exception {
        return new ControllerExecutor<Boolean, TriggerRequest>(request) {

            @Override
            public void checkParam(TriggerRequest... param) throws ServiceException {

            }

            @Override
            public Boolean executeService(TriggerRequest... param) throws ServiceException {
                return schedulerService.reschedule(param[0]);
            }
        }.execute(request);

    }


    @PostMapping(value = "/delete")
    public ResponseResult<Boolean> delete(TriggerRequest request) throws Exception {
        return new ControllerExecutor<Boolean, TriggerRequest>(request) {

            @Override
            public void checkParam(TriggerRequest... param) throws ServiceException {

            }

            @Override
            public Boolean executeService(TriggerRequest... param) throws ServiceException {
                return schedulerService.delete(param[0]);
            }
        }.execute(request);
    }


    @GetMapping(value = "/query")
    public ResponseResult<Map> query(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) throws Exception {
        return new ControllerExecutor<Map, Integer>(pageNum, pageSize) {

            @Override
            public void checkParam(Integer... param) throws ServiceException {

            }

            @Override
            public Map executeService(Integer... param) throws ServiceException {
                return triggerService.getJobAndTriggerDetails(param[0], param[1]);
            }
        }.execute(pageNum, pageSize);

    }


}
