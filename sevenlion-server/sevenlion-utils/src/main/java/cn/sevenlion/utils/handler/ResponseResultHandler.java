package cn.sevenlion.utils.handler;

import cn.sevenlion.utils.annotation.TableDeserialize;
import cn.sevenlion.utils.processor.TableFieldProcessor;
import cn.sevenlion.utils.provider.TableFieldProviderFactory;
import cn.sevenlion.utils.response.CommonResult;
import com.google.common.collect.Maps;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/21 2:34 下午
 */

@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Class<?> parameterType = methodParameter.getParameterType();
        if (parameterType.equals(CommonResult.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Map<String, Object> map = Maps.newHashMap();
        if (o instanceof CommonResult) {
            CommonResult commonResult = (CommonResult) o;
            Object data = commonResult.getData();
            if (data instanceof Collection) {
                for (Object object : ((List) data)) {
                    parseData(object, map);
                }
            }else {
                parseData(data, map);
            }
        }
        return map;
    }

    public ResponseResultHandler() {
    }

    public void parseData(Object data, Map<String, Object> map) {
        Field[] fields = data.getClass().getDeclaredFields();
        for (Field column : fields) {
            if (column.isAnnotationPresent(TableDeserialize.class)) {
                TableDeserialize declaredAnnotation = column.getDeclaredAnnotation(TableDeserialize.class);
                List<TableFieldProcessor> processor = TableFieldProviderFactory.getProcessorByType(declaredAnnotation.type());
                Object value = processor.get(0).serialize(getFieldValue(column, data));
                map.put(column.getName(), value);
            }else {
                setFieldValue(map, column, data);
            }
        }
    }

    public void setFieldValue(Map<String, Object> map, Field column, Object data) {
        try {
            column.setAccessible(true);
            map.put(column.getName(), column.get(data));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Object getFieldValue(Field column,Object data) {
        column.setAccessible(true);
        try {
            return column.get(data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
