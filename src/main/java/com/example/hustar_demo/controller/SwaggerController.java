package com.example.hustar_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

//@RestController
//@RequestMapping(path="/swagger-ui")
//public class SwaggerController {
//    @GetMapping(path="/swagger-ui.css", produces = "text/css")
//    public String getCss() {
//        String orig = toText(getClass().getResourceAsStream("/META-INF/resources/webjars/swagger-ui/4.11.1/swagger-ui.css"));
//        String append = toText(getClass().getResourceAsStream("/META-INF/resources/webjars/swagger-ui/swagger-ui.css"));
//        return orig + append;
//    }
//
//    @GetMapping(path="/index.html", produces = "text/html")
//    public String getIndex() {
//        String orig = toText(getClass().getResourceAsStream("/META-INF/resources/webjars/swagger-ui/index.html"));
//        return orig;
//    }
//
//    static String toText(InputStream in) {
//        return new BufferedReader( new InputStreamReader(in, StandardCharsets.UTF_8))
//                .lines().collect(Collectors.joining("\n"));
//    }
//}