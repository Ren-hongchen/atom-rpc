package top.renhongchen;

import lombok.Data;

import java.util.List;

@Data
public class DTO {
    private String name;  //远程调用函数名
    private List<Object> parameters; //远程调用参数
}
