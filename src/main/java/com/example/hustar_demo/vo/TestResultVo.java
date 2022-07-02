package com.example.hustar_demo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestResultVo {
    @Schema(description = "출력 값")
    String result;

    @Builder
    public TestResultVo(String result) {
        this.result = result;
    }
}
