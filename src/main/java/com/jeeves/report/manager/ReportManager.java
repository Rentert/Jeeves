package com.jeeves.report.manager;

import com.jeeves.shared.Report;
import com.jeeves.shared.Result;

import java.util.List;
import java.util.Map;

/**
 * @author Aleksandrov Oleg
 */
public interface ReportManager {
    /**
     * Функция отвечает за формирование отчетов,
     * о результатах поиска антипаттернов программирования
     * в исходных кодах
     */
    Report createReport(Map<String, List<Result>> map);
    Report getLastReport(String name);
    void saveReport(Map<String, List<Result>> map);
}
