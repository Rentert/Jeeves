package com.jeeves.core.preparation;

import com.google.common.base.Preconditions;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aleksandrov Oleg
 */
public final class CustomCodePreparation implements CodePreparation
{
    private Map<String, PreparationHelper> analysisValue = new HashMap<>();

    private final class PreparationHelper
    {
        private final String code;
        private final ASTParser parser;

        public PreparationHelper(final String code, final ASTParser parser)
        {
            this.code = code;
            this.parser = parser;
        }

        public String getCode()
        {
            return code;
        }

        public ASTParser getParser()
        {
            return parser;
        }
    }

    private void checkArg(final String name)
    {
        Preconditions.checkArgument(name != null, "Name is null.");
        Preconditions.checkArgument(name.length() > 0, "Name is empty.");
    }

    /**
     * функция возвращает AST
     */
    @Override
    public ASTParser getCodeOnAST(final String fileName)
    {
        checkArg(fileName);

        return analysisValue.get(fileName).getParser();
    }

    /**
     * функция возвращает исходный код в строке
     */
    @Override
    public String getCodeOnString(final String fileName)
    {
        checkArg(fileName);

        return analysisValue.get(fileName).getCode();
    }


    // Функция строит AST
    private ASTParser createAST(final String code)
    {
        ASTParser parser = ASTParser.newParser(AST.JLS8);

        parser.setSource((code).toCharArray());
        //parser.setSource("/*abc*/".toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        //ASTNode node = parser.createAST(null);

        return parser;
    }

    // TODO: create method
    private String clearCode(String code)
    {
        return code.replaceAll("\n", "");
    }

    public CustomCodePreparation(final Map<String, String> map)
    {
        // проверка входных аргументов
        Preconditions.checkArgument(map != null, "Map is null.");
        Preconditions.checkArgument(map.size() > 0, "Map is empty");

        // Построение AST для каждого из файлов и также удаление
        // из кода мусора
        map.forEach((key, value) ->
        {
            analysisValue.put(key, new PreparationHelper(clearCode(value), createAST(value)));
        });
    }
}
