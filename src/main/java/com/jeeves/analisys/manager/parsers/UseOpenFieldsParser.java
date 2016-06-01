package com.jeeves.analisys.manager.parsers;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.shared.Result;
import org.eclipse.jdt.core.dom.FieldDeclaration;

import java.util.List;
import java.util.Vector;

/**
 * @author Aleksandrov Oleg
 */
public class UseOpenFieldsParser extends Parser
{
    private final String PUBLIC = "public";

    private List<Integer> lineList = new Vector<>();

    /**
     * прегружаем функция для обхода дерева из класса ASTVisitor
     */
    @Override
    public boolean visit(FieldDeclaration node)
    {
        // информацию о модификаторах переменных в классе
        node.modifiers().forEach(value ->
        {
            if (value.toString().equals(PUBLIC))
            {
                lineList.add(cu.getLineNumber(node.getStartPosition()));
            }
        });

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
        return null;//run(this,fileName, lineList, !lineList.isEmpty());
    }

    public UseOpenFieldsParser(CodePreparation codePreparation)
    {
        super(codePreparation, 7);
    }
}

