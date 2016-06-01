package com.jeeves.analisys.manager.parsers;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.shared.Result;
import org.eclipse.jdt.core.dom.ASTVisitor;

/**
 * @author Aleksandrov Oleg
 */
public class NotCorrectEqualsUseParser extends ASTVisitor implements ParserInterface
{
    private CodePreparation codePreparation;

    /**
     * Функция возвращает объект класса Result,
     * в котором содержится информация найден ли был искомый антипаттерн или нет
     * Функция принимает аргумент имя файла, который необходимо проанализировать на ошибки
     *
     * @param fileName
     */
    @Override
    public Result execute(String fileName) {
        return new Result(5,-1, false);
    }

    public NotCorrectEqualsUseParser(CodePreparation codePreparation)
    {
        this.codePreparation = codePreparation;
    }
}
