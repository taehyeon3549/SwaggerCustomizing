package com.example.hustar_demo.controller;

import com.example.hustar_demo.vo.TestParmVo;
import com.example.hustar_demo.vo.TestResultVo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Operation(summary = "이름을 출력하는 Controller", description = "입력 파라미터 이름 출력")
    @PostMapping("/test")
    public TestResultVo test(@RequestBody TestParmVo testParmVo){
        return TestResultVo.builder()
                .result(testParmVo.getName())
                .build();
    }

    @Operation(summary = "메세지를 출력하는 Controller", description = "입력 파라미터 메세지 출력")
    @PostMapping("/test2")
    public TestResultVo test2(@RequestBody TestParmVo testParmVo){
        return TestResultVo.builder()
                .result(testParmVo.getMessage())
                .build();
    }
}
