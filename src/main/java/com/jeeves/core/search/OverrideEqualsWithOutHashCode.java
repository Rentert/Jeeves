package com.jeeves.core.search;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.core.preparation.CustomCodePreparation;
import com.jeeves.shared.Result;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aleksandrov Oleg
 */
public class OverrideEqualsWithOutHashCode extends Parser
{
    private final static Logger logger = LoggerFactory.getLogger(OverrideEqualsWithOutHashCode.class);

    private final String EQUALS = "equals";
    private final String HASH_CODE = "hashCode";

    private boolean hasEquals = false;
    private boolean hasHashCode = false;

    private String analysisFileName = "";

    private void reInit (String analysisFileName)
    {
        hasEquals = hasHashCode = false;
        this.analysisFileName = analysisFileName;
    }

    /**
     * прегружаем функция для обхода дерева из класса ASTVisitor
     */
    private ASTVisitor visitor = new ASTVisitor()
    {
        @Override
        public boolean visit(MethodDeclaration node)
        {
            // получаем имя функции
            String name = node.getName().getIdentifier();

            if (name.equals(EQUALS))
            {
                hasEquals = true;

                logger.debug("In file {} equals found, line {}", analysisFileName, getLineNumber(node.getStartPosition()));
            }
            else if (name.equals(HASH_CODE))
            {
                hasHashCode = true;

                logger.debug("In file {} hashCode found, line {}", analysisFileName, getLineNumber(node.getStartPosition()));
            }

            return true;
        }
    };

    public OverrideEqualsWithOutHashCode(CodePreparation codePreparation)
    {
        super(codePreparation, 6);
    }

    @Override
    public Result execute(String fileName)
    {
        reInit(fileName);

        // на тот случай если переменные успели изменить свое состояние
        ASTParser parser = codePreparation.getCodeOnAST(fileName);
        compilationUnit = (CompilationUnit) parser.createAST(null);

        compilationUnit.accept(visitor);

        logger.debug("Antipattern is found={}", hasEquals && !hasHashCode);

        return new Result(getID(), getEmptyErrorList(), hasEquals && !hasHashCode);
    }

    public static void main(String[] args) {
        String key = "123.java";
        Map<String, String> map = new HashMap<>();
        String code = " public class A {\n" +
                "        public int a = 10;\n" +
                "        public String str;\n" +
                "\n" +
                "        @Override\n" +
                "        public boolean equals() {\n" +
                "            return super.equals(obj);\n" +
                "        }\n" +
                "        \n" +
                "        public A (int a, String str)\n" +
                "        {\n" +
                "            this.a = a;\n" +
                "            this.str = str;\n" +
                "        }\n" +
                "\n" +
                "        public A (String str, int a)\n" +
                "        {\n" +
                "            this.a = a;\n" +
                "            this.str = str + String.valueOf(a);\n" +
                "        }\n" +
                "    }";

        map.put(key, code);

        Parser parser = new OverrideEqualsWithOutHashCode(new CustomCodePreparation(map));

        logger.debug(parser.execute(key).toString());

    }
}
