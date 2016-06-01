package com.jeeves.analisys.manager.parsers;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.shared.Result;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

/**
 * Отвечает за обнаружение антипаттерна “переопределение функции equals
 * без переопределения функции hashCode”.
 * Поиск осуществляет посредством анализа AST
 * Не возвращает номер строки содержащий антипаттерн
 * @author Aleksandrov Oleg
 */
public final class OverrideEqualsWithOutHashCodeParser extends Parser
{
    private final String EQUALS = "equals";
    private final String HASH_CODE = "hashCode";
    private boolean hasEquals = false;
    private boolean hasHashCode = false;

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

            // проверяем не нужнаяли это нам функция
            hasEquals = name.equals(EQUALS);
            hasHashCode = name.equals(HASH_CODE);

            return true;
        }
    };

    /**
     * Функция возвращает объект класса Result,
     * в котором содержится информация найден ли был искомый антипаттерн или нет
     * Функция принимает аргумент имя файла, который необходимо проанализировать на ошибки
     */
    @Override
    public Result execute(String fileName)
    {
        // на тот случай если переменные успели изменить свое состояние
        hasHashCode = hasEquals = false;
        init(fileName);

        cu.accept(visitor);

        // возвращаем результат проерки
        // hasEquals && !hasHashCode проверям есть ли антипаттерн или нет
        return new Result(6, -1, hasEquals && !hasHashCode);
//        System.out.println(hasEquals);
          //return run(visitor, fileName, getEmptyErrorList(), hasEquals && !hasHashCode);
    }


    public OverrideEqualsWithOutHashCodeParser(final CodePreparation codePreparation)
    {
        super(codePreparation, 6);
    }
}
