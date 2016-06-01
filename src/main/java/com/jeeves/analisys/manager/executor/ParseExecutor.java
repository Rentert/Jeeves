package com.jeeves.analisys.manager.executor;

import com.jeeves.shared.Result;

import java.util.List;
import java.util.Map;

/**
 * @author Aleksandrov Oleg
 */
public interface ParseExecutor {
    /**
     * функция execute возвращает в словаре результат
     * поиска антипаттернов, где ключ это имя анализируемого
     * файла, а значение это список результатов анализа
     * содержимого данного файла, на наличие в нем различных
     * антипаттернов.
     */
    Map<String, List<Result>> execute(Map<String, String> map);
}
