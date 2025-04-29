package com.contribhub.contribhubbackend.controller;

import com.contribhub.contribhubbackend.model.StackOverflowUser;
import com.contribhub.contribhubbackend.service.StackOverflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stackoverflow")
public class StackOverflowController {

    @Autowired
    StackOverflowService stackOverflowService;

    @GetMapping("/{userid}")
    public StackOverflowUser stackoverflow(@PathVariable("userid") String userid) {
        return stackOverflowService.fetchStackOverflowUserData(userid);
    }

    @GetMapping("/dashboard/{userid}")
    public StackOverflowUser getDashboardData(@PathVariable("userid") String userid) {
        return stackOverflowService.getStackOverflowUserFromDatabase(userid);
    }
}
