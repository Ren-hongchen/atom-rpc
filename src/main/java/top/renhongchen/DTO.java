package top.renhongchen;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DTO {
    private String name;  //远程调用函数名
    private List<Object> call_parameters; //远程调用参数
    private String order;
    private Object returnValue;
    private List<Map<String,String>> returnList;
}
