// Generated from /home/lore/Documents/HaveFun/src/HaveFun.g4 by ANTLR 4.9
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HaveFunParser}.
 */
public interface HaveFunListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HaveFunParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(HaveFunParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link HaveFunParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(HaveFunParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funDef}
	 * labeled alternative in {@link HaveFunParser#fun}.
	 * @param ctx the parse tree
	 */
	void enterFunDef(HaveFunParser.FunDefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funDef}
	 * labeled alternative in {@link HaveFunParser#fun}.
	 * @param ctx the parse tree
	 */
	void exitFunDef(HaveFunParser.FunDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code skip}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 */
	void enterSkip(HaveFunParser.SkipContext ctx);
	/**
	 * Exit a parse tree produced by the {@code skip}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 */
	void exitSkip(HaveFunParser.SkipContext ctx);
	/**
	 * Enter a parse tree produced by the {@code while}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 */
	void enterWhile(HaveFunParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code while}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 */
	void exitWhile(HaveFunParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 */
	void enterIf(HaveFunParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 */
	void exitIf(HaveFunParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code seq}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 */
	void enterSeq(HaveFunParser.SeqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code seq}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 */
	void exitSeq(HaveFunParser.SeqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 */
	void enterAssign(HaveFunParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 */
	void exitAssign(HaveFunParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code out}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 */
	void enterOut(HaveFunParser.OutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code out}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 */
	void exitOut(HaveFunParser.OutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nat}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterNat(HaveFunParser.NatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nat}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitNat(HaveFunParser.NatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code divMulMod}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterDivMulMod(HaveFunParser.DivMulModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code divMulMod}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitDivMulMod(HaveFunParser.DivMulModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterNot(HaveFunParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitNot(HaveFunParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusMinus}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterPlusMinus(HaveFunParser.PlusMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusMinus}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitPlusMinus(HaveFunParser.PlusMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eqExp}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterEqExp(HaveFunParser.EqExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eqExp}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitEqExp(HaveFunParser.EqExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bool}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterBool(HaveFunParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitBool(HaveFunParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cmpExp}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterCmpExp(HaveFunParser.CmpExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cmpExp}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitCmpExp(HaveFunParser.CmpExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicExp}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterLogicExp(HaveFunParser.LogicExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicExp}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitLogicExp(HaveFunParser.LogicExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parExp}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterParExp(HaveFunParser.ParExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parExp}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitParExp(HaveFunParser.ParExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pow}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterPow(HaveFunParser.PowContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pow}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitPow(HaveFunParser.PowContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterId(HaveFunParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitId(HaveFunParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funCall}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterFunCall(HaveFunParser.FunCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funCall}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitFunCall(HaveFunParser.FunCallContext ctx);
}