package com.jeeves.core.search;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.shared.Result;
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


    public Parser(final CodePreparation codePreparation, int ID)
    {
        this.ID = ID;
        this.codePreparation = codePreparation;
    }

    protected final List<Integer> getEmptyErrorList ()
    {
        List<Integer> list = new ArrayList<>();
        list.add(-1);

        return list;
    }

    public final int getID()
    {
        return ID;
    }


    public abstract Result execute(String fileName);

    protected int getLineNumber(final int startPosition)
    {
        return compilationUnit.getLineNumber(startPosition);
    }
}
