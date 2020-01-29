package wang.ismy.openapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wang.ismy.openapi.entity.App;

/**
 * @author MY
 * @date 2020/1/29 18:50
 */
@Repository
public interface AppRepository extends JpaRepository<App,Long> {

    /**
     * 根据所给条件查询
     * @param appId
     * @param appSecret
     * @return
     */
    App findByAppIdAndAppSecret(String appId,String appSecret);

    /**
     * 根据App id查询
     * @param appId
     * @return
     */
    App findByAppId(String appId);
}
