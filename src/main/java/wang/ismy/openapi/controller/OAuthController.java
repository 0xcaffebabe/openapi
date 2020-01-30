package wang.ismy.openapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.print.DocFlavor;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author MY
 * @date 2020/1/30 9:11
 */
@Controller
public class OAuthController {

    private String appId = "appid";
    private String appSecret = "appSecret";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("login")
    public String login() throws UnsupportedEncodingException {
        // 生成微信认证URL
        String redirectUrl = URLEncoder.encode("http://r1495937a2.imwork.net/callback","utf8");
        return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+redirectUrl+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
    }

    @GetMapping("callback")
    @ResponseBody
    public String callback(String code,String state){
        // 根据微信回调传过来的code获取access token与openid
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";
        String result = restTemplate.getForObject(url, String.class);
        Map map = new Gson().fromJson(result, Map.class);
        if (map.containsKey("errcode")){
            return "<h1>登录失败</h1>";
        }
        // 根据openid 与access token获取用户信息
        String accessToken = map.get("access_token").toString();
        String openId = map.get("openid").toString();

        url = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
        map = new Gson().fromJson(restTemplate.getForObject(url, String.class), Map.class);

        if (map.containsKey("errcode")){
            return "<h1>获取用户信息失败</h1>";
        }
        String html = "<h1>昵称:"+map.get("nickname")+"</h1>"+
                "<h1>省份:"+map.get("province")+"</h1>"+
                "<img src= '"+map.get("headimgurl")+"'/>";
        return html;
    }
}
