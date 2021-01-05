// Generated from /home/lore/Repos/ProgettoLinguaggi/Base/imp/src/Imp.g4 by ANTLR 4.9
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ImpParser}.
 */
public interface ImpListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ImpParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(ImpParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(ImpParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImpParser#fun}.
	 * @param ctx the parse tree
	 */
	void enterFun(ImpParser.FunContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImpParser#fun}.
	 * @param ctx the parse tree
	 */
	void exitFun(ImpParser.FunContext ctx);
	/**
	 * Enter a parse tree produced by the {@code skip}
	 * labeled alternative in {@link ImpParser#com}.
	 * @param ctx the parse tree
	 */
	void enterSkip(ImpParser.SkipContext ctx);
	/**
	 * Exit a parse tree produced by the {@code skip}
	 * labeled alternative in {@link ImpParser#com}.
	 * @param ctx the parse tree
	 */
	void exitSkip(ImpParser.SkipContext ctx);
	/**
	 * Enter a parse tree produced by the {@code while}
	 * labeled alternative in {@link ImpParser#com}.
	 * @param ctx the parse tree
	 */
	void enterWhile(ImpParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code while}
	 * labeled alternative in {@link ImpParser#com}.
	 * @param ctx the parse tree
	 */
	void exitWhile(ImpParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if}
	 * labeled alternative in {@link ImpParser#com}.
	 * @param ctx the parse tree
	 */
	void enterIf(ImpParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if}
	 * labeled alternative in {@link ImpParser#com}.
	 * @param ctx the parse tree
	 */
	void exitIf(ImpParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code seq}
	 * labeled alternative in {@link ImpParser#com}.
	 * @param ctx the parse tree
	 */
	void enterSeq(ImpParser.SeqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code seq}
	 * labeled alternative in {@link ImpParser#com}.
	 * @param ctx the parse tree
	 */
	void exitSeq(ImpParser.SeqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link ImpParser#com}.
	 * @param ctx the parse tree
	 */
	void enterAssign(ImpParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link ImpParser#com}.
	 * @param ctx the parse tree
	 */
	void exitAssign(ImpParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code out}
	 * labeled alternative in {@link ImpParser#com}.
	 * @param ctx the parse tree
	 */
	void enterOut(ImpParser.OutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code out}
	 * labeled alternative in {@link ImpParser#com}.
	 * @param ctx the parse tree
	 */
	void exitOut(ImpParser.OutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nat}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterNat(ImpParser.NatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nat}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitNat(ImpParser.NatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code divMulMod}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterDivMulMod(ImpParser.DivMulModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code divMulMod}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitDivMulMod(ImpParser.DivMulModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterNot(ImpParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitNot(ImpParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusMinus}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterPlusMinus(ImpParser.PlusMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusMinus}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitPlusMinus(ImpParser.PlusMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eqExp}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterEqExp(ImpParser.EqExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eqExp}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitEqExp(ImpParser.EqExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bool}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterBool(ImpParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitBool(ImpParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cmpExp}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterCmpExp(ImpParser.CmpExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cmpExp}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitCmpExp(ImpParser.CmpExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicExp}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterLogicExp(ImpParser.LogicExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicExp}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitLogicExp(ImpParser.LogicExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parExp}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterParExp(ImpParser.ParExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parExp}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitParExp(ImpParser.ParExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pow}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterPow(ImpParser.PowContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pow}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitPow(ImpParser.PowContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterId(ImpParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitId(ImpParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funCall}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterFunCall(ImpParser.FunCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funCall}
	 * labeled alternative in {@link ImpParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitFunCall(ImpParser.FunCallContext ctx);
}