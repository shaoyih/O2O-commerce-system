package com.store.o2o.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/local")
public class LocalController {

    @RequestMapping(value = "/createAccount", method = RequestMethod.GET)
    private String createAccount() {
        return "local/createauth";
    }

    @RequestMapping(value = "/changepsw", method = RequestMethod.GET)
    private String changepsw() {
        return "local/changepsw";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private String login() {
        return "local/login";
    }
}
