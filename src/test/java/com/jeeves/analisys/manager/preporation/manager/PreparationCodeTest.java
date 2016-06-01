package com.jeeves.analisys.manager.preporation.manager;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.core.preparation.CustomCodePreparation;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aleksandrov Oleg
 */
public class PreparationCodeTest
{
    // инициализация используемых данных
    @Before
    public void init()
    {
    }


    @Test(expected = IllegalArgumentException.class)
    // тест с map в которой нет значений.
    public void testWithEmptyData()
    {
        Map<String, String> map = new HashMap<>();

        new CustomCodePreparation(map);
    }

    @Test(expected = IllegalArgumentException.class)
    // тест с map которая равна null
    public void testWithNullData()
    {
        new CustomCodePreparation(null);
    }

    @Test
    public void testCorrectCleanCode()
    {
        Map<String, String> map = new HashMap<>();
        String key = "123.java";
        String code = "public class CustomAPI {}";
        map.put(key, code);

        CodePreparation codePreparation = new CustomCodePreparation(map);

        Assert.assertEquals(codePreparation.getCodeOnString(key), code);

    }

    @Test
    // чек что мы действительно получии нужное AST
    public void testCorrectFoundFieldDeclaration()
    {
        Map<String, String> map = new HashMap<>();
        String key = "123.java";
        String code = "    private class PreparationHelper\n" +
                "    {\n" +
                "        private String code;\n" +
                "        private ASTParser parser;\n" +
                "\n" +
                "        public PreparationHelper(final String code, final ASTParser parser)\n" +
                "        {\n" +
                "            this.code = code;\n" +
                "            this.parser = parser;\n" +
                "        }\n" +
                "\n" +
                "        public String getCode()\n" +
                "        {\n" +
                "            return code;\n" +
                "        }\n" +
                "\n" +
                "        public ASTParser getParser()\n" +
                "        {\n" +
                "            return parser;\n" +
                "        }\n" +
                "    }";
        List<String> list = new ArrayList<>();
        list.add("code");
        list.add("parser");
        map.put(key, code);

        CodePreparation codePreparation = new CustomCodePreparation(map);

        ASTParser parser = codePreparation.getCodeOnAST(key);

        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        cu.accept(new ASTVisitor()
        {
            public boolean visit(VariableDeclarationFragment node)
            {
                Assert.assertTrue(list.contains(node.getName().toString()));

                return false;
            }
        });

    }
}
