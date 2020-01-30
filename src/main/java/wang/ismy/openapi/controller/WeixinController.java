package wang.ismy.openapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MY
 * @date 2020/1/30 9:16
 */
@RestController
public class WeixinController {

    @RequestMapping("weixin")
    public String weixin(@RequestParam String echostr){
        System.out.println(echostr);
        return echostr;
    }
}
