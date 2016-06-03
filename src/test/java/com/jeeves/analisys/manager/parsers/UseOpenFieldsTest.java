package com.jeeves.analisys.manager.parsers;

import com.jeeves.core.search.AST.Parsers.UseOpenFields;
import com.jeeves.shared.Result;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Aleksandrov Oleg
 */
public class UseOpenFieldsTest extends ParserTest {
    private final String key = "123.java";

    private Result run (final String code)
    {
        UseOpenFields e = new UseOpenFields(init(code));

        return e.execute(key);
    }

    @Test
    public void testWithTwoAntipattern()
    {
        String code = "    private class PreparationHelper\n" +
                "    {\n" +
                "        public String code;\n" +
                "        protected String code1;\n" +
                "        public ASTParser parser;\n" +
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

        Assert.assertEquals(run(code).getLines().size(), 2);
    }

    @Test
    public void testWithOneAntipattern()
    {
        String code = "    private class PreparationHelper\n" +
                "    {\n" +
                "        private String code;\n" +
                "        protected String code1;\n" +
                "        public ASTParser parser;\n" +
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

        Assert.assertEquals(run(code).getLines().size(), 1);
    }

    @Test
    public void testWithOutAntipattern()
    {
        String code = "    private class PreparationHelper\n" +
                "    {\n" +
                "        private String code;\n" +
                "        protected String code1;\n" +
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

        Assert.assertTrue(run(code).getLines().isEmpty());
    }

}
