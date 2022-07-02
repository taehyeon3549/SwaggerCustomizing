package com.example.hustar_demo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestParmVo {
    @Schema(description = "이름", example = "김태현")
    String name;

    @Schema(description = "인사말", example = "안녕하세요")
    String message;
}
