package com.jeeves.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Aleksandrov Oleg
 * @since 13.07.16
 */
@Controller
@RequestMapping("/*")
public class WebController
{
    @RequestMapping
    public String getMainPage()
    {
        return "index";
    }
}