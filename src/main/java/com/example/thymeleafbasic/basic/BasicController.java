package com.example.thymeleafbasic.basic;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {


    @GetMapping("text-basic")
    public String gettextBasic(Model model){
        model.addAttribute("data","<b>hello spring</b>");
        return "/basic/text-basic";
    }
    // value값 defaul
    // notes는 method name

    @ApiResponses({
            @ApiResponse(code=200, message = "로그인성공"),
            @ApiResponse(code=400, message = "잘못된접근"),
            @ApiResponse(code=404, message = "페이지가 존재하지않음"),
            @ApiResponse(code=500, message = "서버 에러"),
    })
    @ApiOperation(value="이스케이프 적용", notes="th:text , th:utext에서 text-unescaped의 차이를 알아보는 api")
    @ApiImplicitParam(name="Model" , value="html단으로 넘길 정보", required = false, dataType="Model")
    @GetMapping("text-unescaped")
    public String unescaped(Model model){
        model.addAttribute("data","<b>hello spring</b>");
        return "/basic/text-unescaped";
    }

    @GetMapping("variable")
    public String variable(Model model){
        User userA = new User("userA",10);
        User userB = new User("userB",20);
        List<User> list = new ArrayList<User>();
        list.add(userA);
        list.add(userB);
        Map<String,User> map = new HashMap<String,User>();
        map.put("userA",userA);
        map.put("userB",userB);

        model.addAttribute("user",userA);
        model.addAttribute("users",list);
        model.addAttribute("userMap",map);

        return "/basic/variable";
    }

    @GetMapping("basic-objects")
    public String basicObjects(HttpSession session){
//        session.setAttributes를 넘기면 세션정보가 전달됨
        session.setAttribute("sessionData","Hello Session");
        return "/basic/basic-objects.html";
    }

    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    @GetMapping("/literal")
    public String literal(Model model){
        model.addAttribute("data","Spring!");
        return "basic/literal";
    }

    @GetMapping("/operation")
    public String operation(Model model){
        model.addAttribute("nullData",null);
        model.addAttribute("data","Spring!");
        return "basic/operation";
    }

    @GetMapping("/attribute")
    public String attribute(){
        return "basic/attribute";
    }

    @GetMapping("/each")
    public String each(Model model){
        addUsers(model);
        return "basic/each";
    }

    @GetMapping("/condition")
    public String condition(Model model){
        addUsers(model);
        return "basic/condition";
    }

    @GetMapping("/comments")
    public String comments(Model model){
        model.addAttribute("data","Spring!");
        return "basic/comments";
    }

    @GetMapping("/block")
    public String block(Model model){
        addUsers(model);
        return "basic/block";
    }

    @GetMapping("/javascript")
    public String javascript(Model model){

        model.addAttribute("user",new User("UserA",10));
        addUsers(model);
        return "basic/javascript";
    }

    private void addUsers(Model model){
        List<User> list = new ArrayList<>();
        list.add(new User("UserA",10));
        list.add(new User("UserB",20));
        list.add(new User("UserC",30));

        model.addAttribute("users",list);
    }


    @Component("helloBean")
    static class HelloBean{
        public String hello(String data){
            return "Hello "+data;
        }
    }



    @Data
    @AllArgsConstructor
    public class User {

//        DTO에 대한 정보를 나타내주는 어노테이션인듯 ? 한번 사용해보긴해야함, MODEL이 아닌 ~DTO를 받는 메소드를 하나 만들어서
        @ApiModelProperty(example="사용자이름")
        private String username;

        @ApiModelProperty(example = "나이")
        private int age;

    }

}
