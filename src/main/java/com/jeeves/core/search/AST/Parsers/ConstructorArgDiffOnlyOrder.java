package com.jeeves.core.search.AST.Parsers;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.core.search.AST.Parsers.helper.ConstructorParser;
import com.jeeves.shared.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * @author Aleksandrov Oleg
 */
public class ConstructorArgDiffOnlyOrder extends AbstractASTParser
{
    private final static Logger logger = LoggerFactory.getLogger(ConstructorArgDiffOnlyOrder.class);

    private ConstructorParser visitor = new ConstructorParser();

    public ConstructorArgDiffOnlyOrder(CodePreparation codePreparation)
    {
        super(codePreparation, 0);
    }

    @Override
    public Result execute (String fileName)
    {
        run(visitor, fileName);

        List<Set<String>> constructorsArgs = visitor.getConstructorsArgs();

        int listSize = constructorsArgs.size();

        logger.debug("In file={} constructors numb={}", fileName, listSize);

        if (listSize <= 1)
        {
            return new Result(getID(), getEmptyErrorList(), false);
        }
        else
        {
            int checkFlag = 1;

            boolean antipatternPatternFlag = false;

            for (int i = 0; i < listSize; ++i)
            {
                for (int j = checkFlag; j < listSize; ++j)
                {
                    if (constructorsArgs.get(i).equals(constructorsArgs.get(j)))
                    {
                        logger.debug("Args equals, arg1={}. arg2={}", constructorsArgs.get(i), constructorsArgs.get(j));

                        antipatternPatternFlag = true;
                    }
                }

                checkFlag++;
            }

            return new Result(getID(), getEmptyErrorList(), antipatternPatternFlag);
        }
    }

    @Override
    protected void reInit (String analysisFileName)
    {
        visitor.clear();
    }
}
