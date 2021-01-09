import org.antlr.v4.runtime.tree.ParseTree;
import value.*;

import java.util.ArrayList;

public class IntImp extends ImpBaseVisitor<Value> {

    private final FunMap funMap;
    private Conf conf;

    public IntImp(Conf conf) {
        this.conf = conf;
        this.funMap = new FunMap();
    }

    @Override
    public ComValue visitProg(ImpParser.ProgContext ctx) {

        // visit all functions
        for (ParseTree fun : ctx.fun()) {
            visit(fun);
        }

        // then visit com
        return visitCom(ctx.com());
    }

    public Value visitFunDef(ImpParser.FunDefContext ctx) {

        String funName = ctx.ID(0).getText();

        // check if function is already defined
        if (funMap.contains(funName)) {
            System.err.println("Function " + funName + " already defined");
            System.err.println("@" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine());

            System.exit(1);
        }

        // saving parameters
        ArrayList<String> parameters = new ArrayList<String>();

        // skip first parameter, which is function name
        for (int i = 1; i < ctx.ID().size(); i++) {

            String par = ctx.ID(i).getText();

            // check if parameter name is already used
            if (parameters.contains(par)) {
                System.err.println("Parameter " + par + " already utilized");
                System.err.println("@" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine());

                System.exit(1);
            } else {
                parameters.add(par);
            }

        }

        // create funValue
        FunValue fun = new FunValue(funName, parameters, ctx.com(), ctx.exp());

        // store function
        funMap.update(funName, fun);

        return null;
    }

    public ExpValue<?> visitFunCall(ImpParser.FunCallContext ctx) {

        String funName = ctx.ID().getText();
        //System.out.println("Fun call");

        // check if function is not defined
        if (!funMap.contains(funName)) {
            System.err.println("Function " + funName + " is not defined");
            System.err.println("@" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine());

            System.exit(1);
        }

        FunValue fun = funMap.get(funName);
        int parNum = ctx.exp().size();

        // check the number of parameters
        if (fun.getParameters().size() != parNum) {
            System.err.println("Function " + funName + " needs " + parNum + " parameters");
            System.err.println("@" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine());

            System.exit(1);
        }


        // calculating arguments
        ArrayList<ExpValue> args = new ArrayList<ExpValue>();

        for (int i = 0; i < ctx.exp().size(); i++) {
            ExpValue arg = (ExpValue) visit(ctx.exp(i));
            args.add(arg);
            //System.out.println("Val: " + arg.toJavaValue().toString());
        }



        // setting up temp memory for body evaluation
        Conf temp = new Conf();
        temp.getMap().putAll(conf.getMap());
        conf.clear();
        //System.out.println("temp conf : " +  temp.toString());


        // connecting parameter with arguments
        ArrayList<String> parameters = fun.getParameters();
        for (int i = 0; i < parameters.size(); i++) {
            conf.update(parameters.get(i), args.get(i));
        }

        // evaluate function body if exists
        if (fun.getComContext() != null) {
            visit(fun.getComContext());
        }

        // evaluate return expression
        ExpValue<?> retVal = (ExpValue<?>) visit(fun.getExpContex());

        //System.out.println("conf : " +  conf.toString());
        //System.out.println("temp : " +  temp.toString());
        // restore memory
        conf = temp;

        return retVal;
    }

    private ComValue visitCom(ImpParser.ComContext ctx) {
        return (ComValue) visit(ctx);
    }

    private ExpValue<?> visitExp(ImpParser.ExpContext ctx) {
        return (ExpValue<?>) visit(ctx);
    }

    private int visitNatExp(ImpParser.ExpContext ctx) {
        try {
            return ((NatValue) visitExp(ctx)).toJavaValue();
        } catch (ClassCastException e) {
            System.err.println("Type mismatch exception!");
            System.err.println("@" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine());
            System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>");
            System.err.println(ctx.getText());
            System.err.println("<<<<<<<<<<<<<<<<<<<<<<<<");
            System.err.println("> Natural expression expected.");
            System.exit(1);
        }

        return 0; // unreachable code
    }

    private boolean visitBoolExp(ImpParser.ExpContext ctx) {
        try {
            return ((BoolValue) visitExp(ctx)).toJavaValue();
        } catch (ClassCastException e) {
            System.err.println("Type mismatch exception!");
            System.err.println("@" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine());
            System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>");
            System.err.println(ctx.getText());
            System.err.println("<<<<<<<<<<<<<<<<<<<<<<<<");
            System.err.println("> Boolean expression expected.");
            System.exit(1);
        }

        return false; // unreachable code
    }

    @Override
    public ComValue visitIf(ImpParser.IfContext ctx) {
        return visitBoolExp(ctx.exp())
                ? visitCom(ctx.com(0))
                : visitCom(ctx.com(1));
    }

    @Override
    public ComValue visitAssign(ImpParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        ExpValue<?> v = visitExp(ctx.exp());

        conf.update(id, v);
        //System.out.println("Assign conf : " +  conf.toString());


        return ComValue.INSTANCE;
    }

    @Override
    public ComValue visitSkip(ImpParser.SkipContext ctx) {
        return ComValue.INSTANCE;
    }

    @Override
    public ComValue visitSeq(ImpParser.SeqContext ctx) {
        visitCom(ctx.com(0));
        return visitCom(ctx.com(1));
    }

    @Override
    public ComValue visitWhile(ImpParser.WhileContext ctx) {
        if (!visitBoolExp(ctx.exp()))
            return ComValue.INSTANCE;

        visitCom(ctx.com());

        return visitWhile(ctx);
    }

    @Override
    public ComValue visitOut(ImpParser.OutContext ctx) {
        System.out.println(visitExp(ctx.exp()));
        return ComValue.INSTANCE;
    }

    @Override
    public NatValue visitNat(ImpParser.NatContext ctx) {
        return new NatValue(Integer.parseInt(ctx.NAT().getText()));
    }

    @Override
    public BoolValue visitBool(ImpParser.BoolContext ctx) {
        return new BoolValue(Boolean.parseBoolean(ctx.BOOL().getText()));
    }

    @Override
    public ExpValue<?> visitParExp(ImpParser.ParExpContext ctx) {
        return visitExp(ctx.exp());
    }

    @Override
    public NatValue visitPow(ImpParser.PowContext ctx) {
        int base = visitNatExp(ctx.exp(0));
        int exp = visitNatExp(ctx.exp(1));

        return new NatValue((int) Math.pow(base, exp));
    }

    @Override
    public BoolValue visitNot(ImpParser.NotContext ctx) {
        return new BoolValue(!visitBoolExp(ctx.exp()));
    }

    @Override
    public NatValue visitDivMulMod(ImpParser.DivMulModContext ctx) {
        int left = visitNatExp(ctx.exp(0));
        int right = visitNatExp(ctx.exp(1));

        return switch (ctx.op.getType()) {
            case ImpParser.DIV -> new NatValue(left / right);
            case ImpParser.MUL -> new NatValue(left * right);
            case ImpParser.MOD -> new NatValue(left % right);
            default -> null;
        };
    }

    @Override
    public NatValue visitPlusMinus(ImpParser.PlusMinusContext ctx) {
        int left = visitNatExp(ctx.exp(0));
        int right = visitNatExp(ctx.exp(1));

        return switch (ctx.op.getType()) {
            case ImpParser.PLUS -> new NatValue(left + right);
            case ImpParser.MINUS -> new NatValue(Math.max(left - right, 0));
            default -> null;
        };
    }

    @Override
    public BoolValue visitEqExp(ImpParser.EqExpContext ctx) {
        ExpValue<?> left = visitExp(ctx.exp(0));
        ExpValue<?> right = visitExp(ctx.exp(1));

        return switch (ctx.op.getType()) {
            case ImpParser.EQQ -> new BoolValue(left.equals(right));
            case ImpParser.NEQ -> new BoolValue(!left.equals(right));
            default -> null; // unreachable code
        };
    }

    @Override
    public ExpValue<?> visitId(ImpParser.IdContext ctx) {
        String id = ctx.ID().getText();

        if (!conf.contains(id)) {
            System.err.println("Variable " + id + " used but never instantiated");
            System.err.println("@" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine());

            System.exit(1);
        }

        return conf.get(id);
    }

    @Override
    public BoolValue visitCmpExp(ImpParser.CmpExpContext ctx) {
        int left = visitNatExp(ctx.exp(0));
        int right = visitNatExp(ctx.exp(1));

        return switch (ctx.op.getType()) {
            case ImpParser.GEQ -> new BoolValue(left >= right);
            case ImpParser.LEQ -> new BoolValue(left <= right);
            case ImpParser.LT -> new BoolValue(left < right);
            case ImpParser.GT -> new BoolValue(left > right);
            default -> null;
        };
    }

    @Override
    public BoolValue visitLogicExp(ImpParser.LogicExpContext ctx) {
        boolean left = visitBoolExp(ctx.exp(0));
        boolean right = visitBoolExp(ctx.exp(1));

        return switch (ctx.op.getType()) {
            case ImpParser.AND -> new BoolValue(left && right);
            case ImpParser.OR -> new BoolValue(left || right);
            default -> null;
        };
    }
}
