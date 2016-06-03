package com.jeeves.analisys.manager.parsers;

import com.jeeves.core.search.AST.Parsers.CreationManyConstrictorForMandatoryFields;
import com.jeeves.shared.Result;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Aleksandrov Oleg
 */
public class CreationManyConstrictorForMandatoryFieldsTest extends ParserTest {
    private final String key = "123.java";

    private Result run(final String code)
    {
        CreationManyConstrictorForMandatoryFields parser = new CreationManyConstrictorForMandatoryFields(init(code));

        return parser.execute(key);
    }

    @Test
    // чек с кодом в котором есть антипаттерн
    public void testWithAntiPattern ()
    {

        String code = "class A\n" +
                "    {\n" +
                "        public A(int a){\n" +
                "            \n" +
                "        }\n" +
                "        \n" +
                "        public A(String e, int y) {\n" +
                "            return 1;\n" +
                "        }\n" +
                "\n" +
                "    }";

        Assert.assertTrue(run(code).isAntipatternFound());

    }

    @Test
    // чек с кодов в котором нет антиптатерно
    public void testWithOutAntiPattern ()
    {

        String code = "class A\n" +
                "    {\n" +
                "        public A(int a){\n" +
                "            \n" +
                "        }\n" +
                "        \n" +
                "        public A(String d) {\n" +
                "            return 1;\n" +
                "        }\n" +
                "    }";

        Assert.assertFalse(run(code).isAntipatternFound());

    }
}
