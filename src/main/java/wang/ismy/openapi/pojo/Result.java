package wang.ismy.openapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author MY
 * @date 2020/1/29 19:01
 */
@Data
@AllArgsConstructor
public class Result {

    private String msg;
    private Object data;
}
