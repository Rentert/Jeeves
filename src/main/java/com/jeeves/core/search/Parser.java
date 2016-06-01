package com.jeeves.core.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksandrov Oleg
 */
public abstract class Parser
{
    private final int ID;

    public Parser(int ID)
    {
        this.ID = ID;
    }

    public final List<Integer> getEmptyErrorList ()
    {
        List<Integer> list = new ArrayList<>();
        list.add(-1);

        return list;
    }

    public final int getID()
    {
        return ID;
    }
}
