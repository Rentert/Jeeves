package com.jeeves.core.search.ast.parsers;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.core.search.ast.parsers.helper.ConstructorParser;
import com.jeeves.shared.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * @author Aleksandrov Oleg
 */
public class CreationManyConstrictorForMandatoryFields extends AbstractASTParser
{
    private final static Logger logger = LoggerFactory.getLogger(CreationManyConstrictorForMandatoryFields.class);

    private ConstructorParser visitor = new ConstructorParser();

    public CreationManyConstrictorForMandatoryFields(CodePreparation codePreparation)
    {
        super(codePreparation, 1);
    }

    @Override
    protected void reInit(String analysisFileName)
    {
        visitor.clear();
    }

    @Override
    public Result execute(String fileName)
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
                    if (constructorsArgs.get(i).containsAll(constructorsArgs.get(j)) || constructorsArgs.get(j).containsAll(constructorsArgs.get(i)))
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
}
