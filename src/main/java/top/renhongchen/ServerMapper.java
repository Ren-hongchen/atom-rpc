package top.renhongchen;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ServerMapper {
    private final DTO dto;
    private Map<String,String> functions;

    public ServerMapper(DTO dto) {
        this.dto = dto;
    }

    public List<Method> list() {
        Class<ServerFunction> clazz = ServerFunction.class;
        Method[] methods = clazz.getMethods();
        for(int i = 0; i<methods.length; i ++) {
            String func_name = methods[i].getName();
            String func_returnType = methods[i].getReturnType().getName();
            int parameters_count = methods[i].getParameterTypes().length;
            String func_parameters_count =  String.valueOf(parameters_count);
            for(int j = 0; j<parameters_count; i++) {

            }
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
