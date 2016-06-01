package com.jeeves.analisys.manager.parsers;

import com.jeeves.shared.Result;

/**
 * @author Aleksandrov Oleg
 */
public interface ParserInterface
{
    /**
     * Функция возвращает объект класса Result,
     * в котором содержится информация найден ли был искомый антипаттерн или нет
     * Функция принимает аргумент имя файла, который необходимо проанализировать на ошибки
     */
    Result execute(String fileName);
}

