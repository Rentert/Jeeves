package com.jeeves.core.search.AST.Parsers;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.shared.Result;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Vector;

/**
 * @author Aleksandrov Oleg
 */
public class UseOpenFields extends AbstractASTParser
{
    private final static Logger logger = LoggerFactory.getLogger(UseOpenFields.class);

    private final String PUBLIC = "public";

    private List<Integer> lineList = new Vector<>();

    private ASTVisitor visitor = new ASTVisitor()
    {
        @Override
        public boolean visit(FieldDeclaration node)
        {
            // информацию о модификаторах переменных в классе
            node.modifiers().forEach(value ->
            {
                if (value.toString().equals(PUBLIC))
                {
                    int line = compilationUnit.getLineNumber(node.getStartPosition());
                    lineList.add(line);

                    logger.debug("Found public fields on file{}, line={}", analysisFileName, line);
                }
            });

            return true;
        }
    };

    public UseOpenFields(CodePreparation codePreparation)
    {
        super(codePreparation, 4);
    }

    @Override
    public Result execute(String fileName)
    {
        analysisFileName = fileName;

        run(visitor, fileName);

        return new Result(getID(), lineList, !lineList.isEmpty());
    }

    @Override
    protected void reInit(String analysisFileName)
    {
        lineList.clear();
    }
}
