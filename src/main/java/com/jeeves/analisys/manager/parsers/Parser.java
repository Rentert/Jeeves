package com.jeeves.analisys.manager.parsers;

import com.google.common.base.Preconditions;
import com.jeeves.core.preparation.CodePreparation;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksandrov Oleg
 */
public abstract class Parser extends ASTVisitor implements ParserInterface
{
    protected CodePreparation codePreparation;
    protected CompilationUnit cu;

    private int ID;

    public Parser (final CodePreparation codePreparation, int ID)
    {
        Preconditions.checkArgument(codePreparation != null, "arg is empty.");

        this.codePreparation = codePreparation;
        this.ID = ID;
    }

    public void init( final String fileName)
    {
        // на тот случай если переменные успели изменить свое состояние
        ASTParser parser = codePreparation.getCodeOnAST(fileName);
        cu = (CompilationUnit) parser.createAST(null);
    }

    public List<Integer> getEmptyErrorList ()
    {
        return new ArrayList<>();
    }
}
