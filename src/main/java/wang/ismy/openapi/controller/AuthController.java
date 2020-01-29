package wang.ismy.openapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.ismy.openapi.entity.App;
import wang.ismy.openapi.pojo.Result;
import wang.ismy.openapi.repository.AppRepository;
import wang.ismy.openapi.util.TokenUtils;

/**
 * @author MY
 * @date 2020/1/29 19:01
 */
@RestController
@AllArgsConstructor
public class AuthController {

    private AppRepository appRepository;
    private StringRedisTemplate redisTemplate;

    @GetMapping("token")
    public Result getAccessToken(App app) {
        // 获取id与secret对应的记录
        App result = appRepository.findByAppIdAndAppSecret(app.getAppId(), app.getAppSecret());
        if (result == null) {
            return new Result("error", null);
        }
        String oldToken = result.getAccessToken();
        if (!StringUtils.isEmpty(oldToken)) {
            // 删除老token
            redisTemplate.delete(oldToken);
        }
        // 验证通过生成token存入redis与数据库
        String token = TokenUtils.getToken();
        redisTemplate.opsForValue().set(token, result.getAppId(), 60 * 120);
        result.setAccessToken(token);
        appRepository.save(result);
        return new Result("success", token);
    }
}
