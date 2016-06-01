package com.jeeves.analisys.manager.parsers;

import com.google.common.base.Preconditions;
import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.shared.Result;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksandrov Oleg
 */
public class ConstructorArgDiffOnlyOrderParser extends ASTVisitor implements ParserInterface
{

    private CodePreparation codePreparation;
    private CompilationUnit cu;
    private final int ID = 0;

    List<List> constructorsArgs = new ArrayList<>();


    /**
     * прегружаем функция для обхода дерева из класса ASTVisitor
     */
    @Override
    public boolean visit (MethodDeclaration node)
    {
        if (node.isConstructor())
        {
            constructorsArgs.add(node.parameters());
        }

        return true;
    }

    /**
     * Функция возвращает объект класса Result,
     * в котором содержится информация найден ли был искомый антипаттерн или нет
     * Функция принимает аргумент имя файла, который необходимо проанализировать на ошибки
     */
    @Override
    public Result execute(String fileName) {
        ASTParser parser = codePreparation.getCodeOnAST(fileName);

        cu = (CompilationUnit) parser.createAST(null);

        cu.accept(this);

        //constructorsArgs.forEach(value -> System.out.println(value.toString()));

        // возвращаем результат проерки
        // hasEquals && !hasHashCode проверям есть ли антипаттерн или нет
        return new Result(ID, -1, true);
    }

    public ConstructorArgDiffOnlyOrderParser(CodePreparation codePreparation)
    {
        Preconditions.checkArgument(codePreparation != null, "arg is empty.");

        this.codePreparation = codePreparation;
    }

    public static void main(String[] args) {

    }
}
