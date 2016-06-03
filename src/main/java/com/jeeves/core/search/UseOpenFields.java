package com.jeeves.core.search;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.shared.Result;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;

import java.util.List;
import java.util.Vector;

/**
 * @author Aleksandrov Oleg
 */
public class UseOpenFields extends Parser
{
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
                    lineList.add(compilationUnit.getLineNumber(node.getStartPosition()));
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
        run(visitor, fileName);

        return new Result(getID(), lineList, !lineList.isEmpty());
    }

    @Override
    protected void reInit(String analysisFileName)
    {
        lineList.clear();
    }
}
