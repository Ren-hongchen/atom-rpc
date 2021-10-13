package top.renhongchen;

import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Method;
import java.util.*;

public class ServerMapper {
    private final DTO dto;

    public ServerMapper(DTO dto) {
        this.dto = dto;
    }

    public List<Map<String,String>> list() {
        List<Map<String,String>> methodList = new ArrayList<>();
        Class<ServerFunction> clazz = ServerFunction.class;
        Method[] methods = clazz.getDeclaredMethods();
        for(int i = 0; i<methods.length; i ++) {
            Map<String,String> functions = new HashMap<>();
            String func_name = methods[i].getName();
            String func_returnType = methods[i].getReturnType().getName();
            int parameters_count = methods[i].getParameterTypes().length;
            String func_parameters_count =  String.valueOf(parameters_count);
            functions.put("func_name",func_name);
            functions.put("func_returnType",func_returnType);
            functions.put("func_parameters_count",func_parameters_count);
            for(int j = 0; j<parameters_count; j++) {
                Class[] parameterTypes = methods[i].getParameterTypes();
                switch (j) {
                    case 0: String func_parameterType_1 = parameterTypes[j].getName();
                            functions.put("func_parameterType_1",func_parameterType_1);
                            break;
                    case 1: String func_parameterType_2 = parameterTypes[j].getName();
                            functions.put("func_parameterType_2",func_parameterType_2);
                            break;
                    case 2: String func_parameterType_3 = parameterTypes[j].getName();
                            functions.put("func_parameterType_3",func_parameterType_3);
                            break;
                    case 3: String func_parameterType_4 = parameterTypes[j].getName();
                            functions.put("func_parameterType_4",func_parameterType_4);
                            break;
                }
            }
            methodList.add(functions);
        }
        return methodList;
    }

    public Object invoke(DTO dto) throws Exception{
        if (dto.getName() == null || dto.getCall_parameters() == null) {
            return null;
        }
        ServerFunction serverFunction = new ServerFunction();
        Object[] objects = dto.getCall_parameters().toArray(new Object[dto.getCall_parameters().size()]);
        Object returnValue = ReflectUtil.invoke(serverFunction,dto.getName(),objects);
        return returnValue;
    }

}
