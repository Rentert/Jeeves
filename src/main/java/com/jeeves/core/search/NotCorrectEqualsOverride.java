package com.jeeves.core.search;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.shared.Result;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Aleksandrov Oleg
 */
public final class NotCorrectEqualsOverride extends Parser
{
    private final static Logger logger = LoggerFactory.getLogger(NotCorrectEqualsOverride.class);

    private final String EQUALS = "equals";
    Pattern pattern = Pattern.compile("^Object.*$");

    private List<Integer> errorLines = new ArrayList<>();

    /**
     * прегружаем функция для обхода дерева из класса ASTVisitor
     */
    private ASTVisitor visitor = new ASTVisitor()
    {
        @Override
        public boolean visit(MethodDeclaration node)
        {
            List list = node.parameters();
            String value = list.isEmpty()? "" :list.get(0).toString();

            // получаем имя функции
            String name = node.getName().getIdentifier();
            // проверяем не нужнаяли это нам функция
            if (name.equals(EQUALS) && !pattern.matcher(value).matches())
            {
                errorLines.add(getLineNumber(node.getStartPosition()));

                logger.debug("In file {} equals found, line {}", analysisFileName, getLineNumber(node.getStartPosition()));
            }

            return true;
        }
    };

    public NotCorrectEqualsOverride(CodePreparation codePreparation)
    {
        super(codePreparation, 5);
    }

    @Override
    public Result execute(String fileName)
    {
        run(visitor, fileName);

        return new Result(getID(), errorLines, !errorLines.isEmpty());
    }

    @Override
    protected void reInit(String analysisFileName)
    {
        errorLines = new ArrayList<>();
        this.analysisFileName = analysisFileName;
    }
}
