package com.jeeves.core.search;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.shared.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksandrov Oleg
 */
public abstract class Parser {
    private final int ID;
    protected String analysisFileName;
    protected final CodePreparation codePreparation;


    protected Parser(final CodePreparation codePreparation, int id) {
        ID = id;
        this.codePreparation = codePreparation;
    }

    public final int getID()
    {
        return ID;
    }

    protected static final List<Integer> getEmptyErrorList ()
    {
        return new ArrayList<>();
    }

    public abstract Result execute(String fileName);
}
