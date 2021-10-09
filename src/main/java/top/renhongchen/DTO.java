package top.renhongchen;

import lombok.Data;

import java.util.Map;

@Data
public class DTO {
    private String name;  //远程调用函数名
    private Map<Integer,Object> parameters; //远程调用参数
}
