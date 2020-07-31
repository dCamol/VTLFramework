package com.capgemini.istat.vtlcompiler.vtlsqlbuilder.expression.clause.drop;

import com.capgemini.istat.vtlcompiler.vtlcommon.model.KeyVariables;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.operator.Operator;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlDataset;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.translation.SqlResult;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.VtlExpression;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.vtl.expression.clause.drop.VtlKeepOrDropClauseExpression;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.TranslationFactory;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.model.OperatorTranslation;
import com.capgemini.istat.vtlcompiler.vtlsqlbuilder.service.sqlresult.ISqlResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class DropClauseTranslation implements OperatorTranslation {
    private VtlKeepOrDropClauseExpression vtlKeepOrDropClauseExpression;
    private TranslationFactory translationFactory;
    private ISqlResultService sqlResultService;

    @Override
    public void setVtlExpression(VtlExpression vtlExpression) {
        this.vtlKeepOrDropClauseExpression = (VtlKeepOrDropClauseExpression) vtlExpression;
    }

    @Autowired
    public void setTranslationFactory(TranslationFactory translationFactory) {
        this.translationFactory = translationFactory;
    }


    public void setSqlResultService(ISqlResultService sqlResultService) {
        this.sqlResultService = sqlResultService;
    }
    
    @Override
    public SqlResult translate(Map<KeyVariables, Object> variables) throws Exception {
        SqlResult sqlResult = new SqlResult();
        if (vtlKeepOrDropClauseExpression.getVtlExpression() != null) {
            sqlResult = translationFactory.translate(vtlKeepOrDropClauseExpression.getVtlExpression(), variables);
        } else {
            sqlResult.setSqlDataset((SqlDataset) variables.get(KeyVariables.DATASET_IN_CLAUSE));
        }
        if (vtlKeepOrDropClauseExpression.getOperator().equals(Operator.KEEP))
            sqlResult = sqlResultService.applyKeepClause(vtlKeepOrDropClauseExpression, sqlResult);
        else
            sqlResult = sqlResultService.applyDropClause(vtlKeepOrDropClauseExpression, sqlResult);
        return sqlResult;
    }    
}