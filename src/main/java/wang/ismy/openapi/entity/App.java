package wang.ismy.openapi.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author MY
 * @date 2020/1/29 18:50
 */
@Entity
@Table(name = "m_app")
@Data
public class App {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String appName;
    private String appId;
    private String appSecret;
    private String available;
    private String accessToken;
}
