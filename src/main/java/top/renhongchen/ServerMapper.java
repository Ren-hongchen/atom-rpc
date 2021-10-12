package top.renhongchen;

import java.lang.reflect.Method;
import java.util.*;

public class ServerMapper {
    private final DTO dto;
    private Map<String,String> functions = new HashMap<>();
    private static String func_parameterType_1;
    private static String func_parameterType_2;
    private static String func_parameterType_3;
    private static String func_parameterType_4;

    public ServerMapper(DTO dto) {
        this.dto = dto;
    }

    public List<Map<String,String>> list() {
        List<Map<String,String>> methodList = new ArrayList<>();
        Class<ServerFunction> clazz = ServerFunction.class;
        Method[] methods = clazz.getDeclaredMethods();
        for(int i = 0; i<methods.length; i ++) {
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
                    case 0: func_parameterType_1 = parameterTypes[j].getName();break;
                    case 1: func_parameterType_2 = parameterTypes[j].getName();break;
                    case 2: func_parameterType_3 = parameterTypes[j].getName();break;
                    case 3: func_parameterType_4 = parameterTypes[j].getName();break;
                }
            }
            functions.put("func_parameterType_1",func_parameterType_1);
            functions.put("func_parameterType_2",func_parameterType_2);
            functions.put("func_parameterType_3",func_parameterType_3);
            functions.put("func_parameterType_4",func_parameterType_4);
            methodList.add(functions);
            functions.clear();
        }
        return methodList;
    }

    public Object invoke(DTO dto) throws Exception{
        if (dto.getName() == null || dto.getParameters() == null) {
            return null;
        }

        Class<ServerFunction> clazz = ServerFunction.class;
        Method method = clazz.getMethod(dto.getName());
        Object returnValue = method.invoke(clazz.getDeclaredConstructor().newInstance(),dto.getParameters());
        return returnValue;
    }

}
