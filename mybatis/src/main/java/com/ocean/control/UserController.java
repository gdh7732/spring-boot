package com.ocean.control;


import com.ocean.model.NewUser;
import com.ocean.model.OldUser;
import com.ocean.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author guodahai
 * @version 2018/4/12 下午5:14
 */
@RestController
@RequestMapping("user")
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/new")
    public NewUser queryNewUser(@PathParam("id") Integer id) {
        return userService.queryNewUser(id);
    }

    @GetMapping("/old")
    public OldUser queryOldUser(@PathParam("id") Integer id) {
        return userService.queryOldUser(id);
    }
}
