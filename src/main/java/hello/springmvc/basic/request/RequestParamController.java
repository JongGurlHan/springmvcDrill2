package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username= {}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody //controller를 Restcontroller로 바꾸고 싶지 않다면!
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String membername,
            @RequestParam("age") int memberAge){

        log.info("username = {}, age = {}", membername, memberAge);

        return "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String membername, //생략가능 조건: 변수명과 파라미터 이름이 같아야한다.
            @RequestParam int memberAge){

        log.info("username = {}, age = {}", membername, memberAge);

        return "ok";
    }

    /**
    * String, int등 단순타입이면 @RequestParam도 생략가능
    * */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String membername, int memberAge){ //

        log.info("username = {}, age = {}", membername, memberAge);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String membername, //required 안넣으면 기본값이 필수다.
            @RequestParam(required = false) Integer memberAge){ // java에서 int에는 null이 들어 갈 수 없어서(int a = null 이안된다)

        log.info("username = {}, age = {}", membername, memberAge);

        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String membername,  // 빈문자가 오면 디폴트값이 입력된다.
            @RequestParam(defaultValue = "-1") int age){

        log.info("username = {}, age = {}", membername, age);

        return "ok";
    }

    /*
    * 모든 요청을 map으로 받고 싶을때
    * */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){

        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username={}, age={},", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){ //@ModelAttribute는 생략가능
        log.info("username={}, age={},", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
