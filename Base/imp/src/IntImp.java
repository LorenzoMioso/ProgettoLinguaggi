import value.*;

import java.util.ArrayList;
import java.util.Iterator;

public class IntImp extends ImpBaseVisitor<Value> {

    private final Conf conf;
    private final FunMap funMap;

    public IntImp(Conf conf) {
        this.conf = conf;
        this.funMap = new FunMap();
    }

    public Value visitFunDef(ImpParser.FunDefContext ctx) {
        System.out.println("visit fun");

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
        funMap.update(funName,fun);

        // visit next function
        visit(ctx.fun());

        return null;
    }


    public Value visitFunNil(ImpParser.FunNilContext ctx) {
        return null;
    }


    public ExpValue<?> visitFunCall(ImpParser.FunCallContext ctx) {

        String funName = ctx.ID().getText();

        // check if function is not defined
        if (funMap.contains(funName)) {
            System.err.println("Function " + funName + " is not defined");
            System.err.println("@" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine());

            System.exit(1);
        }

        FunValue fun = funMap.get(funName);
        int parNum = ctx.exp().size();

        // check the number of parameters
        if (fun.parameters.size() != parNum){
            System.err.println("Function " + funName + " needs" + parNum + "parameters");
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



        System.out.println("visit fun call");
        ExpValue<?> v = null;
        return v;
    }

    private ComValue visitCom(ImpParser.ComContext ctx) {
        //System.out.println("Visit com: " + ctx.getText());
        return (ComValue) visit(ctx);
    }

    private ExpValue<?> visitExp(ImpParser.ExpContext ctx) {

        System.out.println("visit exp");
        return (ExpValue<?>) visit(ctx);
    }

    private int visitNatExp(ImpParser.ExpContext ctx) {
        System.out.println("visit nat exp");
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
        System.out.println("visit bool exp");
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
    public ComValue visitProg(ImpParser.ProgContext ctx) {
        System.out.println("visit prog");
        //System.out.println(ctx.fun().getText());

        //System.out.println(ctx.children.);

        visit(ctx.fun());
        return visitCom(ctx.com());
    }

    @Override
    public ComValue visitIf(ImpParser.IfContext ctx) {
        System.out.println("visit if");
        return visitBoolExp(ctx.exp())
                ? visitCom(ctx.com(0))
                : visitCom(ctx.com(1));
    }

    @Override
    public ComValue visitAssign(ImpParser.AssignContext ctx) {
        System.out.println("visit assign");
        String id = ctx.ID().getText();
        ExpValue<?> v = visitExp(ctx.exp());

        conf.update(id, v);

        return ComValue.INSTANCE;
    }

    @Override
    public ComValue visitSkip(ImpParser.SkipContext ctx) {
        System.out.println("visit skip");
        return ComValue.INSTANCE;
    }

    @Override
    public ComValue visitSeq(ImpParser.SeqContext ctx) {
        System.out.println("visit seq");
        visitCom(ctx.com(0));
        return visitCom(ctx.com(1));
    }

    @Override
    public ComValue visitWhile(ImpParser.WhileContext ctx) {
        System.out.println("visit while");
        if (!visitBoolExp(ctx.exp()))
            return ComValue.INSTANCE;

        visitCom(ctx.com());

        return visitWhile(ctx);
    }

    @Override
    public ComValue visitOut(ImpParser.OutContext ctx) {
        System.out.println("visit out");
        System.out.println(visitExp(ctx.exp()));
        return ComValue.INSTANCE;
    }

    @Override
    public NatValue visitNat(ImpParser.NatContext ctx) {
        System.out.println("visit nat");
        return new NatValue(Integer.parseInt(ctx.NAT().getText()));
    }

    @Override
    public BoolValue visitBool(ImpParser.BoolContext ctx) {
        System.out.println("visit bool");
        return new BoolValue(Boolean.parseBoolean(ctx.BOOL().getText()));
    }

    @Override
    public ExpValue<?> visitParExp(ImpParser.ParExpContext ctx) {
        System.out.println("visit per exp");
        return visitExp(ctx.exp());
    }

    @Override
    public NatValue visitPow(ImpParser.PowContext ctx) {
        System.out.println("visit pow");
        int base = visitNatExp(ctx.exp(0));
        int exp = visitNatExp(ctx.exp(1));

        return new NatValue((int) Math.pow(base, exp));
    }

    @Override
    public BoolValue visitNot(ImpParser.NotContext ctx) {
        System.out.println("visit not");
        return new BoolValue(!visitBoolExp(ctx.exp()));
    }

    @Override
    public NatValue visitDivMulMod(ImpParser.DivMulModContext ctx) {
        System.out.println("visit div mul mod");
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
        System.out.println("visit plus minus");
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
        System.out.println("visit eq exp");
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
        System.out.println("visit id");
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
        System.out.println("visit cmp exp");
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
        System.out.println("visit logic exp");
        boolean left = visitBoolExp(ctx.exp(0));
        boolean right = visitBoolExp(ctx.exp(1));

        return switch (ctx.op.getType()) {
            case ImpParser.AND -> new BoolValue(left && right);
            case ImpParser.OR -> new BoolValue(left || right);
            default -> null;
        };
    }
}
