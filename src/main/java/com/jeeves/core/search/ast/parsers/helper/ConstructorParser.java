package com.jeeves.core.search.ast.parsers.helper;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Aleksandrov Oleg
 */
public final class ConstructorParser extends ASTVisitor
{

    private List<Set<String>> constructorsArgs = new ArrayList<>();

    private final char SPACE = ' ';

    private String cutString (String str)
    {
        if (str.isEmpty())
        {
            return str;
        }

        int index = str.indexOf(SPACE);

        return str.substring(0, index);
    }

    /**
     * прегружаем функция для обхода дерева из класса ASTVisitor
     */
    @Override
    public boolean visit (MethodDeclaration node)
    {
        if (node.isConstructor())
        {
            Set<String> parametersLists = new HashSet<>();

            node.parameters().forEach( value ->
                        parametersLists.add(cutString(value.toString()))
                );

            constructorsArgs.add(parametersLists);
        }

        return true;
    }

    public List<Set<String>> getConstructorsArgs()
    {
        return constructorsArgs;
    }

    public void clear ()
    {
        constructorsArgs.clear();
    }
}
