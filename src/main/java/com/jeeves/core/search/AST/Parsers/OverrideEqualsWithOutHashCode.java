package com.jeeves.core.search.AST.Parsers;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.shared.Result;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Aleksandrov Oleg
 */
public class OverrideEqualsWithOutHashCode extends AbstractASTParser
{
    private final static Logger logger = LoggerFactory.getLogger(OverrideEqualsWithOutHashCode.class);

    private final String EQUALS = "equals";
    private final String HASH_CODE = "hashCode";

    private boolean hasEquals = false;
    private boolean hasHashCode = false;

    @Override
    protected void reInit (String analysisFileName)
    {
        hasEquals = hasHashCode = false;
        this.analysisFileName = analysisFileName;
    }

    /**
     * прегружаем функция для обхода дерева из класса ASTVisitor
     */
    private ASTVisitor visitor = new ASTVisitor()
    {
        @Override
        public boolean visit(MethodDeclaration node)
        {
            // получаем имя функции
            String name = node.getName().getIdentifier();

            if (name.equals(EQUALS))
            {
                hasEquals = true;

                logger.debug("In file {} equals found, line {}", analysisFileName, getLineNumber(node.getStartPosition()));
            }
            else if (name.equals(HASH_CODE))
            {
                hasHashCode = true;

                logger.debug("In file {} hashCode found, line {}", analysisFileName, getLineNumber(node.getStartPosition()));
            }

            return true;
        }
    };

    public OverrideEqualsWithOutHashCode(CodePreparation codePreparation)
    {
        super(codePreparation, 6);
    }

    @Override
    public Result execute(String fileName)
    {
        run(visitor, fileName);

        return new Result(getID(), getEmptyErrorList(), hasEquals && !hasHashCode);
    }
}
