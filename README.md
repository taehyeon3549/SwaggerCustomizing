# Swagger 사용법 그리고 커스터마이징


## 🗒️ 노트 주제

---

- Swagger란 무엇인가??
- Swagger 사용방법
- Swagger 커스터마이징 하는 방법

## 🗝️ 핵심

---

## Swagger란 무엇인가?

---

 서버는 클라이언트와 통신을 하기 위해서 인터페이스 문서를 작성을 하고, 이를 공유하면서 API 규격을 맞춰 나간다.

 기존에 팀에서는 Excel을 공유하여 문서로 관리하고, 테스트는 Postman을 통해서 테스트하고 클라이언트에게 테스트 방법을 안내할 때에는 Postman의 Export 기능을 통해 JSON파일로 테스트 명세를 전달하였다.

 그러나 Swagger의 도입으로 인해 서버가 정상 기동만 되어있으면 웹상에서 바로 확인 및 테스트가 가능하게 제공할 수 있다.

 그리고 Swagger에서 제공하는 api-docs의 JSON 파일을 이용하면 클라이언트 측에서는 Swagger Build를 통해 객체 생성 및 호출 함수까지 자동으로 완성시켜주어 개발 속도가 향상된다.

 그럼 **Swagger란 정확하게 무엇인가?** 

> **Swagger** 는 REST API를 설계, 구축, 문서화 및 사용하는 데 도움이 될 수 있는 OpenAPI 사양을 기반으로 구축된 오픈 소스 도구 세트입니다.
> 

[About Swagger Specification | Documentation | Swagger](https://swagger.io/docs/specification/about/)

 기존의 명칭은 springfox swagger 라는 명칭으로 되어 있다가, 어느부터 springdoc-openapi로 변경이 되었고, springfox swagger의 경우에는 특정 버전만 springboot에서 정상 동작하는 오류가 있었는데 springdoc-openapi로 변경됨에 따라서는 호환성 이슈는 사라진 것 같다. *프로세스가 실행중일때 excute 버튼이 비활성화 되는 기능도 추가됨*

Swagger가 제공해주는 도구 중에서 Swagger-ui 툴을 통해서 웹상에서 표출하는 방법에 대해서 작성해보겠다.

## Swagger 사용 방법

---

 Swagger의 사용방법은 Java Spring Boot + Gradle을 기준으로 설명하겠다.

1. Gradle 설정
    
    ```groovy
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.springframework.boot:spring-boot-starter-web:2.7.0'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    
    /* SWAGGER */
    implementation group: 'org.springdoc', name: 'springdoc-openapi-ui', version: '1.6.9'
    ```
    
    > Swagger-ui 버전정보는 확인을 통해 설정 할 것
    > 
    
2. API 설명 작성
- 애노테이션 방법
    
    ```java
    @OpenAPIDefinition(info = @Info(title = "Hustar API 명세서",
                    description = "API 명세서",
                    version = "v1",
                    contact = @Contact(name = "taehyeonkim", email = "taehyeonkim@kakao.com")
            )
    )
    ```
    
- Java Config 파일 방법
    
    ```java
    @Configuration
    public class SwaggerConfig {
        @Bean
        public OpenAPI openAPI() {
    
            Info info = new Info().title("Hustar API 명세서").version("v1")
                    .description("API 명세서")
                    .contact(new Contact().name("taehyeonkim").email("taehyeonkim@kakao.com"))
                    .license(new License().name("Apache License Version 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html"));
    
            return new OpenAPI()
                    .components(new Components())
                    .info(info);
        }
    }
    ```
    
    > **두가지중 한가지 방법으로 선택**
    > 

1. Controller, Vo 에 설명 작성
    
    ```java
    // Controller
    
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
    ```
    
    ```java
    @Getter
    @Setter
    @ToString
    public class TestParmVo {
        @Schema(description = "이름", example = "김태현")
        String name;
    
        @Schema(description = "인사말", example = "안녕하세요")
        String message;
    }
    ```
    
    > Swagger Annotaion 추가 설명은 공식 wiki에서 확인
    > 
    > 
    > [Annotations · swagger-api/swagger-core Wiki](https://github.com/swagger-api/swagger-core/wiki/Annotations)
    > 

1. 로컬 서버 기동 후 [localhost:8080](http://localhost:8080)/swagger-ui.html 접속
    
    ![스크린샷 2022-07-02 시간: 10.44.51.png](Swagger%20%E1%84%89%E1%85%A1%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%87%E1%85%A5%E1%86%B8%20%E1%84%80%E1%85%B3%E1%84%85%E1%85%B5%E1%84%80%E1%85%A9%20%E1%84%8F%E1%85%A5%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5%E1%84%86%E1%85%A1%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5%E1%86%BC%20839b56b3485a41b489336dd45b2c9321/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-07-02_%E1%84%89%E1%85%B5%E1%84%80%E1%85%A1%E1%86%AB_10.44.51.png)
    
2. 확인
    
    ![설명 작성한 부분 위치](Swagger%20%E1%84%89%E1%85%A1%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%87%E1%85%A5%E1%86%B8%20%E1%84%80%E1%85%B3%E1%84%85%E1%85%B5%E1%84%80%E1%85%A9%20%E1%84%8F%E1%85%A5%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5%E1%84%86%E1%85%A1%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5%E1%86%BC%20839b56b3485a41b489336dd45b2c9321/swagger_%E1%84%89%E1%85%A5%E1%86%AF%E1%84%86%E1%85%A7%E1%86%BC.jpg)
    
    설명 작성한 부분 위치
    
    ![결과 확인](Swagger%20%E1%84%89%E1%85%A1%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%87%E1%85%A5%E1%86%B8%20%E1%84%80%E1%85%B3%E1%84%85%E1%85%B5%E1%84%80%E1%85%A9%20%E1%84%8F%E1%85%A5%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5%E1%84%86%E1%85%A1%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5%E1%86%BC%20839b56b3485a41b489336dd45b2c9321/%E1%84%80%E1%85%A7%E1%86%AF%E1%84%80%E1%85%AA_%E1%84%89%E1%85%A5%E1%86%AF%E1%84%86%E1%85%A7%E1%86%BC.png)
    
    결과 확인
    

## Swagger 커스터마이징

---

 OpenAPI이므로 Swagger에 로고도 바꾸고 색상도 바꾸고 사용자의 입맛에 바꿀수가 있는데 그중 로고와 헤더 색상 바꾸는 것만 설명을 하고자 한다.

- GIt Issue에 있는 css path를 통한 커스터마이징

[https://github.com/springdoc/springdoc-openapi/issues/737](https://github.com/springdoc/springdoc-openapi/issues/737)

> 해당 방식으로 하게 되면 Swagger Path에 해당 Controller를 예외 처리 해줘야 되며, 내가 바꾸고 싶은 부분 마다 Endpoint를 만드는 느낌이라 개인적으로는 비추
> 

- Swagger UI Resource를 통한 직접 커스터마이징

1. swagger-ui resource 다운
    
    [https://github.com/swagger-api/swagger-ui](https://github.com/swagger-api/swagger-ui) 에서 dist 폴더 다운 
    
    또는 Gradle에서 Import된 Resource를 사용
    
    ![Untitled](Swagger%20%E1%84%89%E1%85%A1%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%87%E1%85%A5%E1%86%B8%20%E1%84%80%E1%85%B3%E1%84%85%E1%85%B5%E1%84%80%E1%85%A9%20%E1%84%8F%E1%85%A5%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5%E1%84%86%E1%85%A1%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5%E1%86%BC%20839b56b3485a41b489336dd45b2c9321/Untitled.png)
    

1. 인터넷 탭 아이콘 , 이름 변경
    
    ```html
    <!-- index.html -->
    
    <!-- HTML for static distribution bundle build -->
    <!DOCTYPE html>
    <html lang="en">
      <head>
        <meta charset="UTF-8">
    
    		**<!-- 해당 부분 이름 변경 -->
        <title>Hustar Swagger UI</title>
    		<!-- 해당 부분 이름 변경 끝 -->**
    
        <link rel="stylesheet" type="text/css" href="./swagger-ui.css" />
        <link rel="stylesheet" type="text/css" href="index.css" />
    
    		**<!-- 해당 부분 아이콘 리소스 변경 -->
        <link rel="icon" type="image/png" href="./hustar_32x32.png" sizes="32x32" />
        <link rel="icon" type="image/png" href="./hustar_32x32.png" sizes="16x16" />
    		<!-- 해당 부분 아이콘 리소스 변경 끝 -->**
    
      </head>
    
      <body>
        <div id="swagger-ui"></div>
        <script src="./swagger-ui-bundle.js" charset="UTF-8"> </script>
        <script src="./swagger-ui-standalone-preset.js" charset="UTF-8"> </script>
        <script src="./swagger-initializer.js" charset="UTF-8"> </script>
      </body>
    </html>
    ```
    

1. Swagger-ui 왼쪽 상단 로고 변경
    1. 적당한 이미지 로고(배경이 없는 png 파일 추천) 준비해서 Resource폴더 안에 넣어놓기
        
        ![Untitled](Swagger%20%E1%84%89%E1%85%A1%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%87%E1%85%A5%E1%86%B8%20%E1%84%80%E1%85%B3%E1%84%85%E1%85%B5%E1%84%80%E1%85%A9%20%E1%84%8F%E1%85%A5%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5%E1%84%86%E1%85%A1%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5%E1%86%BC%20839b56b3485a41b489336dd45b2c9321/Untitled%201.png)
        
    2. swagger-ui-standalone-preset.js 수정 (배너 이미지 변경)
        
        ```jsx
        // 기존
        
        return p.createElement("div", {className: "topbar"}, p.createElement("div", {className: "wrapper"}, p.createElement("div", {className: "topbar-wrapper"}, p.createElement(o, null, p.createElement("img", {
                                    height: "40",                            
                                    src: j,                            
                                    alt: "Swagger UI"
                                })), p.createElement("form", {
                                    className: "download-url-wrapper",
                                    onSubmit: f
                                }, b()(c).call(c, (function (t, e) {
                                    return (0, p.cloneElement)(t, {key: e})
                                }))))))
        ```
        
        ```jsx
        // 변경
        
        return p.createElement("div", {className: "topbar"}, p.createElement("div", {className: "wrapper"}, p.createElement("div", {className: "topbar-wrapper"}, p.createElement(o, null, p.createElement("img", {
                                    height: "40",
                                    // TODO :: 바꿈
                                    src: "./Hustar.png",
                                    // TODO :: 바꿈
                                    alt: "Hustar - Swagger UI"
                                })), p.createElement("form", {
                                    className: "download-url-wrapper",
                                    onSubmit: f
                                }, b()(c).call(c, (function (t, e) {
                                    return (0, p.cloneElement)(t, {key: e})
                                }))))))
        ```
        
    
    4. 상단 배너 Back-ground 색상 변경
    
    Index.css 파일 수정
    
    ```css
    /** index.css **/
    
    .swagger-ui .topbar {
        background-color: lightgray;
    }
    ```
    
    1. 결과 확인
        
        ![Untitled](Swagger%20%E1%84%89%E1%85%A1%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%87%E1%85%A5%E1%86%B8%20%E1%84%80%E1%85%B3%E1%84%85%E1%85%B5%E1%84%80%E1%85%A9%20%E1%84%8F%E1%85%A5%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5%E1%84%86%E1%85%A1%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5%E1%86%BC%20839b56b3485a41b489336dd45b2c9321/Untitled%202.png)
        

## 🔗 참고 노트

---
