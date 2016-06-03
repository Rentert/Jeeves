package com.jeeves.core.search.AST.Parsers;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.core.search.Parser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

/**
 * @author Aleksandrov Oleg
 */
public abstract class AbstractASTParser extends Parser
{
    protected CompilationUnit compilationUnit;


    public AbstractASTParser(final CodePreparation codePreparation, int ID)
    {
        super(codePreparation, ID);
    }

    protected void run(final ASTVisitor visitor, final String fileName)
    {
        reInit(fileName);

        // на тот случай если переменные успели изменить свое состояние
        org.eclipse.jdt.core.dom.ASTParser parser = codePreparation.getCodeOnAST(fileName);
        compilationUnit = (CompilationUnit) parser.createAST(null);

        compilationUnit.accept(visitor);
    }

    protected abstract void reInit (String analysisFileName);

    protected int getLineNumber(final int startPosition)
    {
        return compilationUnit.getLineNumber(startPosition);
    }
}
