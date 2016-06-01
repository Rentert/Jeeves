package com.jeeves.analisys.manager.parsers;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.shared.Result;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Aleksandrov Oleg
 */
public class NotCorrectEqualsOverrideParser extends Parser
{
    private final String EQUALS = "equals";
    Pattern pattern = Pattern.compile("^Object.*$");

    private List<Integer> errorLine = new ArrayList<>();

    /**
     * прегружаем функция для обхода дерева из класса ASTVisitor
     */
    @Override
    public boolean visit (MethodDeclaration node)
    {
        // получаем имя функции
        String name = node.getName().getIdentifier();

        List list = node.parameters();
        String value = list.isEmpty()? "" :list.get(0).toString();

        // проверяем не нужнаяли это нам функция
        if (name.equals(EQUALS) && !pattern.matcher(value).matches())
        {
            errorLine.add(cu.getLineNumber(node.getStartPosition()));
        }

        return true;
    }

    /**
     * Функция возвращает объект класса Result,
     * в котором содержится информация найден ли был искомый антипаттерн или нет
     * Функция принимает аргумент имя файла, который необходимо проанализировать на ошибки
     */
    @Override
    public Result execute(String fileName)
    {
        return null;//run(this, fileName, errorLine, !errorLine.isEmpty());
    }


    public NotCorrectEqualsOverrideParser(CodePreparation codePreparation)
    {
        super(codePreparation, 4);
    }
}
