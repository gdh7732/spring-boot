package com.example.demo.controller;

import com.example.demo.entity.JobAndTrigger;
import com.example.demo.entity.TriggerRequest;
import com.example.demo.service.JobAndTriggerService;
import com.example.demo.service.SchedulerService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 定时任务控制层
 *
 * @author guodahai
 */
@RestController
@RequestMapping(value = "/job")
public class JobController {

    private static Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobAndTriggerService triggerService;

    @Autowired
    private SchedulerService schedulerService;

    @GetMapping(value = "/index")
    public String index(Model model) {
        PageInfo<JobAndTrigger> jobAndTrigger = triggerService.getJobAndTriggerDetails(1, 10);
        Map<String, Object> map = new HashMap<>();
        map.put("JobAndTrigger", jobAndTrigger);
        map.put("number", jobAndTrigger.getTotal());
        model.addAllAttributes(map);
        return "index.html";
    }

    @PostMapping(value = "/add")
    public void add(TriggerRequest request) throws Exception {
        schedulerService.add(request);
    }


    @PostMapping(value = "/pause")
    public void pause(TriggerRequest request) throws Exception {
        schedulerService.pause(request);
    }


    @PostMapping(value = "/resume")
    public void resume(TriggerRequest request) throws Exception {
        schedulerService.resume(request);
    }


    @PostMapping(value = "/reschedule")
    public void reschedule(TriggerRequest request) throws Exception {
        schedulerService.reschedule(request);
    }


    @PostMapping(value = "/delete")
    public void delete(TriggerRequest request) throws Exception {
        schedulerService.delete(request);
    }


    @GetMapping(value = "/query")
    public Map<String, Object> query(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageInfo<JobAndTrigger> jobAndTrigger = triggerService.getJobAndTriggerDetails(pageNum, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("JobAndTrigger", jobAndTrigger);
        map.put("number", jobAndTrigger.getTotal());
        return map;
    }


}
