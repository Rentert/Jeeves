package com.jeeves.analisys.manager.parsers;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.shared.Result;
import org.eclipse.jdt.core.dom.ASTVisitor;

/**
 * @author Aleksandrov Oleg
 */
public class CreationManyConstrictorForMandatoryFieldsParserInterface extends ASTVisitor implements ParserInterface
{
    /**
     * Функция возвращает объект класса Result,
     * в котором содержится информация найден ли был искомый антипаттерн или нет
     * Функция принимает аргумент имя файла, который необходимо проанализировать на ошибки
     */
    @Override
    public Result execute(String fileName) {
        return new Result(2,-1,false);
    }

    public CreationManyConstrictorForMandatoryFieldsParserInterface(CodePreparation codePreparation)
    {}
}
