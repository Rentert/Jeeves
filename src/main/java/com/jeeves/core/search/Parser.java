package com.jeeves.core.search;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.shared.Result;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksandrov Oleg
 */
public abstract class Parser
{
    private final int ID;
    protected final CodePreparation codePreparation;
    protected CompilationUnit compilationUnit;

    protected String analysisFileName;


    public Parser(final CodePreparation codePreparation, int ID)
    {
        this.ID = ID;
        this.codePreparation = codePreparation;
    }

    protected static final List<Integer> getEmptyErrorList ()
    {
        List<Integer> list = new ArrayList<>();
        list.add(-1);

        return list;
    }

    protected void run(final ASTVisitor visitor, final String fileName)
    {
        reInit(fileName);

        // на тот случай если переменные успели изменить свое состояние
        ASTParser parser = codePreparation.getCodeOnAST(fileName);
        compilationUnit = (CompilationUnit) parser.createAST(null);

        compilationUnit.accept(visitor);
    }

    public final int getID()
    {
        return ID;
    }


    public abstract Result execute(String fileName);

    protected abstract void reInit (String analysisFileName);

    protected int getLineNumber(final int startPosition)
    {
        return compilationUnit.getLineNumber(startPosition);
    }
}
