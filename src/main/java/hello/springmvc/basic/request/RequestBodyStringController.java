package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
public class RequestBodyStringController {
    // HttpEntity: HTTP header, body 정보를 편리하게 조회
    @PostMapping("/request-body-string-v4")
    public HttpEntity<String> requestBodyString(@RequestBody String messageBody){ //httpmessage body 읽어서 넣어준다.

        log.info("messageBody ={}", messageBody);

        return new HttpEntity<>("ok");
    }
}
