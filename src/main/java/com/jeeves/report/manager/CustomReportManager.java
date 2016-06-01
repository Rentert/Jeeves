package com.jeeves.report.manager;

import com.jeeves.shared.Report;
import com.jeeves.shared.Result;

import java.util.List;
import java.util.Map;

/**
 * @author Aleksandrov Oleg
 */
public class CustomReportManager implements ReportManager {
    private static CustomReportManager ourInstance = new CustomReportManager();

    public static CustomReportManager getInstance() {
        return ourInstance;
    }

    private CustomReportManager() {
    }

    /**
     * Функция отвечает за формирование отчетов,
     * о результатах поиска антипаттернов программирования
     * в исходных кодах
     *
     * @param map
     */
    @Override
    public Report createReport(Map<String, List<Result>> map) {
        return null;
    }

    @Override
    public Report getLastReport(String name) {

        return null;
    }

    @Override
    public void saveReport(Map<String, List<Result>> map) {

    }
}
