package org.uppower.sevenlion.web.order.common.model.typehandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/3/10 1:35 下午
 */
abstract class JsonTypeHandler<T> extends BaseTypeHandler<T> {

    private ObjectMapper objectMapper = new ObjectMapper();

    private Class<T> type;

    public JsonTypeHandler(Class<T> type) {
        this.type = type;
    }

    @SneakyThrows(Exception.class)
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T t, JdbcType jdbcType) {
        preparedStatement.setString(i,objectMapper.writeValueAsString(t));
    }

    @Override
    public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return parse(resultSet.getString(s));
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return parse(resultSet.getString(i));
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return parse(callableStatement.getString(i));
    }

    private T  parse(String s) {
        if (StringUtils.isBlank(s)) {
            return null;
        } else {
            try {
                return objectMapper.readValue(s, type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
