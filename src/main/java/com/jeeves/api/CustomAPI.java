package com.jeeves.api;

import com.google.common.base.Preconditions;
import com.jeeves.analisys.manager.executor.CustomParseExecutor;
import com.jeeves.analisys.manager.executor.ParseExecutor;
import com.jeeves.report.manager.CustomReportManager;
import com.jeeves.report.manager.ReportManager;
import com.jeeves.shared.Report;
import com.jeeves.shared.Result;

import java.util.List;
import java.util.Map;

/**
 * @author Aleksandrov Oleg
 */
public class CustomAPI implements AnalysisAPI, ReportAPI
{
    private void checkString(String name)
    {
        Preconditions.checkArgument(name != null, "file name is null.");
        Preconditions.checkArgument(name.length() <=30, "Name lenght > 30");
        Preconditions.checkArgument(!name.isEmpty(), "Name isEmpy");
    }
    /**
     * вызов данной функции инициирует выполнение кода
     * отвечающего за поиск антипаттернов. Функция принимает
     * два аргумента. Первый аргумент это строка с именем проверяемого проекта.
     * Имя выступает в роли ключа для получения доступа к результатам проверки.
     * Имя должно быть от 1 до 30 символов и должно состоять из символов
     * латинского алфавита(верхнего или нижнего регистра) и чисел в десятичной системе счисления.
     * возвразает отчет
     */
    @Override
    public Report analysis(String fileName, Map<String, String> map)
    {
        // check arg
        checkString(fileName);
        Preconditions.checkArgument(map != null, "map is null.");
        Preconditions.checkArgument(!map.isEmpty(), "map is empty");

        ParseExecutor executor = new CustomParseExecutor();

        // сохраняем результаты
        Map<String, List<Result>> results = executor.execute(map);

        ReportManager reportManager = CustomReportManager.getInstance();
        reportManager.saveReport(results);

        // возращем отчет
        return reportManager.createReport(results);
    }

    /**
     * функция возвращает сформированный отчет о результатах
     *  поиска антипаттернов в исходных кодах с заданным именем проекта.
     *  Первый аргумент этой функции это строка с именем искомого проекта.
     */
    @Override
    public Report getLastReport(String name)
    {
        // check arg
        checkString(name);

        return CustomReportManager.getInstance().getLastReport(name);
    }
}
