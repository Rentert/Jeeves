package com.jeeves.core.preparation;

import org.eclipse.jdt.core.dom.ASTParser;

/**
 * @author Aleksandrov Oleg
 */
public interface CodePreparation {
    /**
     * функция возвращает AST
     */
    ASTParser getCodeOnAST(String fileName);

    /**
     * функция возвращает исходный код в строке
     */
    String getCodeOnString(String fileName);
}
