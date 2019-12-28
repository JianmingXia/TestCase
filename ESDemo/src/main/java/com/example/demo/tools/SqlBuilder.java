package com.example.demo.tools;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLQueryExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import com.alibaba.druid.sql.parser.Token;
import com.unboundid.util.json.JSONObject;
import org.nlpcn.es4sql.domain.Select;
import org.nlpcn.es4sql.exception.SqlParseException;
import org.nlpcn.es4sql.parse.ElasticSqlExprParser;
import org.nlpcn.es4sql.parse.SqlParser;
import org.nlpcn.es4sql.query.ESActionFactory;
import org.nlpcn.es4sql.query.QueryAction;
import org.nlpcn.es4sql.query.SqlElasticRequestBuilder;
import sun.tools.jstat.ParserException;

public class SqlBuilder {
    public static String buildESSql(String sql) {
        try {
            QueryAction queryAction = ESActionFactory.create(null, sql);

            SqlElasticRequestBuilder sqlElasticRequestBuilder = queryAction.explain();

            return sqlElasticRequestBuilder.explain();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SQLExpr toSqlExpr(String sql) throws ParserException {
        SQLExprParser parser = new ElasticSqlExprParser(sql);
        SQLExpr expr = parser.expr();

        if (parser.getLexer().token() != Token.EOF) {
            throw new ParserException("illegal sql expr : " + sql);
        }

        return expr;
    }
}
