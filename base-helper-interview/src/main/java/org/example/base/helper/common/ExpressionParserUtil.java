package org.example.base.helper.common;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;
import java.util.Objects;

/**
 * https://www.cnblogs.com/strongmore/p/15335622.html
 */
public interface ExpressionParserUtil {
    /**
     * 根据表达式解析参数值
     * @param dataMap
     * @param expression
     * @param tClass
     * @return
     * @param <T>
     * @throws EvaluationException
     */
    static <T> T parse(Map<String, Object> dataMap, String expression, Class<T> tClass) throws EvaluationException{
        if (MapUtils.isEmpty(dataMap) || StringUtils.isBlank(expression) || Objects.isNull(tClass)) {
            return null;
        }
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        dataMap.forEach(context::setVariable);
        return parser.parseExpression(expression).getValue(context, tClass);
    }
}
