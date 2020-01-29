package wang.ismy.openapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.ismy.openapi.entity.App;
import wang.ismy.openapi.pojo.Result;
import wang.ismy.openapi.repository.AppRepository;

import java.util.List;

/**
 * @author MY
 * @date 2020/1/29 19:25
 */
@RestController
@AllArgsConstructor
public class ApiController {

    private StringRedisTemplate redisTemplate;
    private AppRepository appRepository;

    @GetMapping("api")
    public Result getUser(String token){
        // 根据token查询对应app id并判断是否可用
        String appId = redisTemplate.opsForValue().get(token);
        if (StringUtils.isEmpty(token)){
            return new Result("error:invalid token",null);
        }
        if (!StringUtils.isEmpty(appId)){
            appId = appId.trim();
        }
        App app = appRepository.findByAppId(appId);
        if (app == null){
            return new Result("error:app id not exist",null);
        }

        if (!"true".equals(app.getAvailable())){
            return new Result("error:permission denied",null);
        }

        // 调用真实业务
        return new Result("success", List.of("cxk","wyf"));
    }
}
