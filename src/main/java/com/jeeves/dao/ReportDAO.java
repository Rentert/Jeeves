package com.jeeves.dao;

import com.jeeves.shared.Report;

/**
 * @author Aleksandrov Oleg
 */
public class ReportDAO {
    private static ReportDAO ourInstance = new ReportDAO();

    public static ReportDAO getInstance() {
        return ourInstance;
    }

    private ReportDAO() {
    }

    Report getReport(String projectName)
    {
        return null;
    }

    void saveReport(String projectName, Report report)
    {

    }
}
