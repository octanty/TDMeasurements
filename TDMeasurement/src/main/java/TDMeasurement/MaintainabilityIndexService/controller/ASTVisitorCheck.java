package TDMeasurement.MaintainabilityIndexService.controller;


import java.util.HashMap;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

//refactored from https://github.com/aametwally/Halstead-Complexity-Measures
public class ASTVisitorCheck extends ASTVisitor{
    public HashMap<String, Integer> operand = new HashMap<String, Integer>();
    public HashMap<String, Integer> op = new HashMap<String, Integer>();
    public HashMap<String, Integer> declaration = new HashMap<String, Integer>();
    CompilationUnit compilationUnit=null;


    public boolean visit(InfixExpression line)
    {
        if (!this.op.containsKey(line.getOperator().toString()))
        {
            this.op.put(line.getOperator().toString(), 1);
        }

        else
        {
            this.op.put(line.getOperator().toString(), this.op.get(line.getOperator().toString())+1);
        }
        return true;
    }



    public boolean visit(PostfixExpression line)
    {
        if (!this.op.containsKey(line.getOperator().toString()))
        {
            this.op.put(line.getOperator().toString(), 1);
        }
        else
        {
            this.op.put(line.getOperator().toString(), this.op.get(line.getOperator().toString())+1);
        }
        return true;
    }


    public boolean visit(PrefixExpression line)
    {
        if (!this.op.containsKey(line.getOperator().toString()))
        {
            this.op.put(line.getOperator().toString(), 1);
        }
        else
        {
            this.op.put(line.getOperator().toString(), this.op.get(line.getOperator().toString())+1);
        }

        return true;
    }



    public boolean visit(Assignment line)
    {
        if (!this.op.containsKey(line.getOperator().toString()))
        {
            this.op.put(line.getOperator().toString(), 1);
        }
        else
        {
            this.op.put(line.getOperator().toString(), this.op.get(line.getOperator().toString())+1);
        }

        return true;
    }

    public boolean visit(SingleVariableDeclaration line) {
        if(line.getInitializer()!=null)
        {
            if (!this.op.containsKey("="))
            {
                this.op.put("=", 1);
            }
            else
            {
                this.op.put("=", this.op.get("=")+1);
            }
        }

        return true;
    }


    public boolean visit(VariableDeclarationFragment line) {

        if(line.getInitializer()!=null)
        {
            if (!this.op.containsKey("="))
            {
                this.op.put("=", 1);
            }
            else
            {
                this.op.put("=", this.op.get("=")+1);
            }
        }

        return true;
    }

    public boolean visit(SimpleName line) {
        if (!this.operand.containsKey(line.getIdentifier()))
        {
            this.operand.put(line.getIdentifier(),1);
        }
        else
        {
            this.operand.put(line.getIdentifier(), this.operand.get(line.getIdentifier())+1);
        }
        return true;
    }


    public boolean visit(NullLiteral line) {
        if (!this.operand.containsKey("null"))
        {
            this.operand.put("null", 1);
        }
        else
        {
            this.operand.put("null", this.operand.get("null")+1);
        }

        return true;
    }


    public boolean visit(StringLiteral line) {

        if (!this.operand.containsKey(line.getLiteralValue()))
        {
            this.operand.put(line.getLiteralValue(),1);
        }
        else
        {
            this.operand.put(line.getLiteralValue(), this.operand.get(line.getLiteralValue())+1);
        }
        return true;
    }


    public boolean visit(CharacterLiteral line) {

        if (!this.operand.containsKey(Character.toString(line.charValue())))
        {
            this.operand.put(Character.toString(line.charValue()),1);
        }
        else
        {
            this.operand.put(Character.toString(line.charValue()), this.operand.get(Character.toString(line.charValue()))+1);
        }

        return true;
    }


    public boolean visit(BooleanLiteral line) {

        if (!this.operand.containsKey(Boolean.toString(line.booleanValue())))
        {
            this.operand.put(Boolean.toString(line.booleanValue()),1);
        }
        else
        {
            this.operand.put(Boolean.toString(line.booleanValue()), this.operand.get(Boolean.toString(line.booleanValue()))+1);
        }


        return true;
    }


    public boolean visit(NumberLiteral line) {
        if (!this.operand.containsKey(line.getToken()))
        {
            this.operand.put(line.getToken(),1);
        }
        else
        {
            this.operand.put(line.getToken(), this.operand.get(line.getToken())+1);
        }

        return true;
    }


    public boolean visit(CompilationUnit compUnit)	{
        compilationUnit=compUnit;
        return true;
    }
}