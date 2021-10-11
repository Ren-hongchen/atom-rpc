package top.renhongchen;

import java.lang.reflect.Method;

public class ServerMapper {
    private DTO dto;

    public ServerMapper(DTO dto) {
        this.dto = dto;
    }

    public Method[] list() {
        Class clazz = ServerFunction.class;
        Method[] method = clazz.getMethods();
        return method;
    }

    public Object invoke(DTO dto) throws Exception{
        if (dto.getName() == null || dto.getParameters() == null) {
            return null;
        }

        Class clazz = ServerFunction.class;
        Method method = clazz.getMethod(dto.getName());
        Object returnValue = method.invoke(clazz.getDeclaredConstructor().newInstance(),dto.getParameters());
        return returnValue;
    }

}
