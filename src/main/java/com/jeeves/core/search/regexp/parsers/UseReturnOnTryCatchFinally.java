package com.jeeves.core.search.regexp.parsers;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.core.search.Parser;
import com.jeeves.shared.Result;

import java.util.regex.Pattern;

/**
 * @author Aleksandrov Oleg
 */
public class UseReturnOnTryCatchFinally extends Parser
{
    Pattern patternOne = Pattern.compile(".*try.*return.*finally.*return.*$");
    Pattern patternTwo = Pattern.compile(".*try.*return.*catch.*return.*finally.*return.*$");
    Pattern patternThree = Pattern.compile(".*catch.*return.*finally.*return.*$");

    public UseReturnOnTryCatchFinally(final CodePreparation codePreparation) {
        super(codePreparation, 6);
    }

    @Override
    public Result execute(String fileName)
    {
        String code = codePreparation.getCodeOnString(fileName);
        return new Result(getID(), getEmptyErrorList(), patternOne.matcher(code).matches() ||
                                                        patternThree.matcher(code).matches() ||
                                                        patternTwo.matcher(code).matches());
    }
}
