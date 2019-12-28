package com.example.demo.controller;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLQueryExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import com.alibaba.druid.sql.parser.Token;
import com.example.demo.tools.SqlBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nlpcn.es4sql.domain.Select;
import org.nlpcn.es4sql.parse.ElasticSqlExprParser;
import org.nlpcn.es4sql.parse.SqlParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.tools.jstat.ParserException;

@RestController
@RequestMapping("/sql_build")
@Api(tags = "es sql 构建接口")
public class SQLSearchController {
    @ApiOperation("build es 搜索条件")
    @GetMapping("/condition")
    public String buildESSqlCondition(@RequestParam String sql) {
        return SqlBuilder.buildESSql(sql);
    }

    @ApiOperation("build es 提取参数")
    @GetMapping("/fields")
    public Object buildESSql(@RequestParam String sql) {
        try {
            SQLQueryExpr sqlExpr = (SQLQueryExpr) SqlBuilder.toSqlExpr(sql);

            Select select = new SqlParser().parseSelect(sqlExpr);

            return select.getFields();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
