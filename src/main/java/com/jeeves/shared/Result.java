package com.jeeves.shared;

import com.google.common.base.Objects;

import java.util.List;
import java.util.Vector;

/**
 * Класс хранит в себе информации о результатах поиска антипаттернов
 * @author Aleksandrov Oleg
 */
public class Result
{
    // id антипаттерна
    private int antipatternID;
    // номера строк с ошибками
    private List<Integer> lines;
    // флаг были найдены антипаттерн или нет
    private boolean antipatternIdFound;

    public int getAntipatternID()
    {
        return antipatternID;
    }

    public List<Integer> getLines()
    {
        return lines;
    }

    public boolean isAntipatternFound()
    {
        return antipatternIdFound;
    }

    public Result(int antipatternID, int line, boolean antipatternIdFound)
    {
        this.antipatternID = antipatternID;

        lines = new Vector<>();
        lines.add(line);

        this.antipatternIdFound = antipatternIdFound;
    }

    public Result(int antipatternID, List<Integer> lines, boolean antipatternIdFound) {
        this.lines = lines;
        this.antipatternIdFound = antipatternIdFound;
        this.antipatternID = antipatternID;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("antipatternID", antipatternID)
                .add("lines", lines)
                .add("antipatternFound", antipatternIdFound)
                .toString();
    }
}
