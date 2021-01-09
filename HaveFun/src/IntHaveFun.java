import org.antlr.v4.runtime.tree.ParseTree;
import value.*;

import java.util.ArrayList;

public class IntHaveFun extends HaveFunBaseVisitor<Value> {

    private final FunMap funMap;
    private Conf conf;

    public IntHaveFun(Conf conf) {
        this.conf = conf;
        this.funMap = new FunMap();
    }

    @Override
    public ComValue visitProg(HaveFunParser.ProgContext ctx) {

        // visit all functions
        for (ParseTree fun : ctx.fun()) {
            visit(fun);
        }

        // then visit com
        return visitCom(ctx.com());
    }

    public Value visitFunDef(HaveFunParser.FunDefContext ctx) {

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

    public ExpValue<?> visitFunCall(HaveFunParser.FunCallContext ctx) {

        String funName = ctx.ID().getText();

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
        
        // restore memory
        conf = temp;

        return retVal;
    }

    private ComValue visitCom(HaveFunParser.ComContext ctx) {
        return (ComValue) visit(ctx);
    }

    private ExpValue<?> visitExp(HaveFunParser.ExpContext ctx) {
        return (ExpValue<?>) visit(ctx);
    }

    private int visitNatExp(HaveFunParser.ExpContext ctx) {
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

    private boolean visitBoolExp(HaveFunParser.ExpContext ctx) {
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
    public ComValue visitIf(HaveFunParser.IfContext ctx) {
        return visitBoolExp(ctx.exp())
                ? visitCom(ctx.com(0))
                : visitCom(ctx.com(1));
    }

    @Override
    public ComValue visitAssign(HaveFunParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        ExpValue<?> v = visitExp(ctx.exp());

        conf.update(id, v);
        //System.out.println("Assign conf : " +  conf.toString());


        return ComValue.INSTANCE;
    }

    @Override
    public ComValue visitSkip(HaveFunParser.SkipContext ctx) {
        return ComValue.INSTANCE;
    }

    @Override
    public ComValue visitSeq(HaveFunParser.SeqContext ctx) {
        visitCom(ctx.com(0));
        return visitCom(ctx.com(1));
    }

    @Override
    public ComValue visitWhile(HaveFunParser.WhileContext ctx) {
        if (!visitBoolExp(ctx.exp()))
            return ComValue.INSTANCE;

        visitCom(ctx.com());

        return visitWhile(ctx);
    }

    @Override
    public ComValue visitOut(HaveFunParser.OutContext ctx) {
        System.out.println(visitExp(ctx.exp()));
        return ComValue.INSTANCE;
    }

    @Override
    public NatValue visitNat(HaveFunParser.NatContext ctx) {
        return new NatValue(Integer.parseInt(ctx.NAT().getText()));
    }

    @Override
    public BoolValue visitBool(HaveFunParser.BoolContext ctx) {
        return new BoolValue(Boolean.parseBoolean(ctx.BOOL().getText()));
    }

    @Override
    public ExpValue<?> visitParExp(HaveFunParser.ParExpContext ctx) {
        return visitExp(ctx.exp());
    }

    @Override
    public NatValue visitPow(HaveFunParser.PowContext ctx) {
        int base = visitNatExp(ctx.exp(0));
        int exp = visitNatExp(ctx.exp(1));

        return new NatValue((int) Math.pow(base, exp));
    }

    @Override
    public BoolValue visitNot(HaveFunParser.NotContext ctx) {
        return new BoolValue(!visitBoolExp(ctx.exp()));
    }

    @Override
    public NatValue visitDivMulMod(HaveFunParser.DivMulModContext ctx) {
        int left = visitNatExp(ctx.exp(0));
        int right = visitNatExp(ctx.exp(1));

        return switch (ctx.op.getType()) {
            case HaveFunParser.DIV -> new NatValue(left / right);
            case HaveFunParser.MUL -> new NatValue(left * right);
            case HaveFunParser.MOD -> new NatValue(left % right);
            default -> null;
        };
    }

    @Override
    public NatValue visitPlusMinus(HaveFunParser.PlusMinusContext ctx) {
        int left = visitNatExp(ctx.exp(0));
        int right = visitNatExp(ctx.exp(1));

        return switch (ctx.op.getType()) {
            case HaveFunParser.PLUS -> new NatValue(left + right);
            case HaveFunParser.MINUS -> new NatValue(Math.max(left - right, 0));
            default -> null;
        };
    }

    @Override
    public BoolValue visitEqExp(HaveFunParser.EqExpContext ctx) {
        ExpValue<?> left = visitExp(ctx.exp(0));
        ExpValue<?> right = visitExp(ctx.exp(1));

        return switch (ctx.op.getType()) {
            case HaveFunParser.EQQ -> new BoolValue(left.equals(right));
            case HaveFunParser.NEQ -> new BoolValue(!left.equals(right));
            default -> null; // unreachable code
        };
    }

    @Override
    public ExpValue<?> visitId(HaveFunParser.IdContext ctx) {
        String id = ctx.ID().getText();

        if (!conf.contains(id)) {
            System.err.println("Variable " + id + " used but never instantiated");
            System.err.println("@" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine());

            System.exit(1);
        }

        return conf.get(id);
    }

    @Override
    public BoolValue visitCmpExp(HaveFunParser.CmpExpContext ctx) {
        int left = visitNatExp(ctx.exp(0));
        int right = visitNatExp(ctx.exp(1));

        return switch (ctx.op.getType()) {
            case HaveFunParser.GEQ -> new BoolValue(left >= right);
            case HaveFunParser.LEQ -> new BoolValue(left <= right);
            case HaveFunParser.LT -> new BoolValue(left < right);
            case HaveFunParser.GT -> new BoolValue(left > right);
            default -> null;
        };
    }

    @Override
    public BoolValue visitLogicExp(HaveFunParser.LogicExpContext ctx) {
        boolean left = visitBoolExp(ctx.exp(0));
        boolean right = visitBoolExp(ctx.exp(1));

        return switch (ctx.op.getType()) {
            case HaveFunParser.AND -> new BoolValue(left && right);
            case HaveFunParser.OR -> new BoolValue(left || right);
            default -> null;
        };
    }
}
