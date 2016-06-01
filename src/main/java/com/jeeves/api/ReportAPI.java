package com.jeeves.api;

import com.jeeves.shared.Report;

/**
 * @author Aleksandrov Oleg
 */
public interface ReportAPI
{
    Report getLastReport(String name);
}
