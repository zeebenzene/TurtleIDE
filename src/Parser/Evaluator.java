package Parser;

import GUI.Canvas;
import GUI.Commands;

import java.util.HashMap;

import edu.hendrix.grambler.ParseException;
import edu.hendrix.grambler.Tree;

public class Evaluator {
	private HashMap<String, String> procToVar;
	private HashMap<String, Tree> procToExpression;
	private HashMap<String, Integer> procVars;
	private Commands cmd;
	
	public Evaluator(Canvas canvas){
//		commands = new ButtonPanel();
		cmd = new Commands(canvas);
	}
	
	public void eval(String input) {
		LogoGrammar p = new LogoGrammar();
		try {
			evalTree(p.parse(input));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String evalTree(Tree t){
		if(t.isNamed("lines")){
			evalLines(t);
			System.out.println();
		}
		else if(t.isNamed("line")){
			return evalTree(t.getChild(0));
		}
		else if(t.isNamed("procedure")){
			String procName = t.getNamedChild("var").toString();
			String procVar = t.getNamedChild("pVar").getNamedChild("var").toString();
			procToVar.put(procName, procVar);
			procVars.put(procVar, 0);
			procToExpression.put(procName, t.getNamedChild("line"));
		}
		else if(t.isNamed("pCall")){
			String proc = t.getNamedChild("var").toString();
			int num = Integer.parseInt(t.getNamedChild("num").toString());
			String procVar = procToVar.get(proc);
			procVars.put(procVar, num);
			
			Tree procExpr = procToExpression.get(proc);
			evalTree(procExpr);
		}
		
		else if(t.isNamed("repeat")){
			int num = Integer.parseInt(t.getNamedChild("num").toString());
			for(int i = 0; i < num; i++){
				evalTree(t.getNamedChild("line"));
			}
		}
		else if(t.isNamed("if")){
			evalIf(t.getChild(0));
		}
		else if(t.isNamed("expr")){
			return evalTree(t.getNamedChild("cmds"));
		}
		else if(t.isNamed("cmds")){
			for(int i = 0; i < t.getNumChildren(); i++){
				evalTree(t.getChild(i));
			}
		}
		else if(t.isNamed("command")){
//			System.out.print("doing command stuff " + evalCommandTree(t) + " ");
			return evalCommandTree(t);
		}
		return "";
	}

	public void evalLines(Tree t){
		if(t.getNumChildren() == 1){
			evalTree(t.getNamedChild("line"));	
		}
		else{
			evalTree(t.getNamedChild("lines"));
			evalTree(t.getNamedChild("line"));
		}
	}
	
	public void evalIf(Tree t){
		if(t.getName().equals("ifNorm")){		
			evalIfNorm(t);
		}
		else if(t.getName().equals("ifElse")){
			evalIfElse(t);
		}
		else if(t.getName().equals("ifBool")){
			evalIfBool(t);
		}
	}
	
	public void evalIfNorm(Tree t){
		int num1 = Integer.parseInt(t.getChild(2).toString());
		int num2 = Integer.parseInt(t.getChild(6).toString());
		String comp = t.getChild(4).toString();

		boolean result = evalComparison(comp, num1, num2);
		
		if(result == true){
			evalTree(t.getNamedChild("expr"));
		}		
	}
	public void evalIfElse(Tree t){
		int num1 = Integer.parseInt(t.getChild(2).toString());
		int num2 = Integer.parseInt(t.getChild(6).toString());
		String comp = t.getChild(4).toString();
		
		boolean result = evalComparison(comp, num1, num2);
		if(result == true){
			evalTree(t.getChild(8));
		} else { 
			evalTree(t.getChild(10)); 
		}		
	}
	public void evalIfBool(Tree t){
		String bool = t.getChild(4).toString();
		boolean pCond1 = evalPCondition(t.getChild(2));
		boolean pCond2 = evalPCondition(t.getChild(6));
		boolean result = false;
		
		if(bool.equals("or")){
			result = (pCond1 || pCond2); 
		} else if(bool.equals("and")){
			result = (pCond1 && pCond2);
		} else if(bool.equals("not")){
			result = (pCond1 && !pCond2);
		}
		
		if(result == true){
			evalTree(t.getNamedChild("expr"));
		}		
	}
	
	public boolean evalPCondition(Tree t){
		int num1 = Integer.parseInt(t.getChild(1).toString());
		int num2 = Integer.parseInt(t.getChild(5).toString());
		String comp = t.getChild(3).toString();
		
		return evalComparison(comp, num1, num2);
	}
	
	public boolean evalComparison(String comp, int num1, int num2){
		if(comp.equals("<")){
			if(num1 < num2){ return true; }
		} else if (comp.equals("<=")){
			if(num1 <= num2){ return true; }
		} else if (comp.equals(">")){
			if(num1 > num2){ return true; }
		} else if (comp.equals(">=")){
			if(num1 >= num2){ return true; }
		} else if (comp.equals("=")){
			if(num1 == num2){ return true; }
		} else { return false; }
		return false;
	}
	
	public String evalCommandTree(Tree t){
		String command = t.getChild(0).toString();
		double num = Double.parseDouble((t.getNamedChild("num").toString()));		
		if(command.equals("fd") || command.equals("forward")){
			cmd.forward(num);
		}
		else if(command.equals("lt") || command.equals("left")){
			cmd.left(num);
		}
		else if(command.equals("rt") || command.equals("right")){
			cmd.right(num);
		}
		else if(command.equals("bk") || command.equals("back")){
			cmd.back(num);
		} 
		else if(command.equals("pu") || command.equals("penup")){
			cmd.penup();
		} else if(command.equals("pd") || command.equals("pendown")){
			cmd.pendown();
		} else if(command.equals("home")){
			cmd.home();
		} else if(command.equals("cs") || command.equals("clearscreen")){
			cmd.clear();
		} else if(command.equals("hideturtle") || command.equals("showturtle")){
			cmd.hideturtle();
		}
		
		return command + " " + num;
	}
	
//	public static void main(String[] args) throws ParseException{
//		LogoGrammar p = new LogoGrammar();
//		Evaluator e = new Evaluator();
//		e.evalTree(p.parse("fd 50 rt 90"));
//	}	
}
/*
lines: lines cr if | if;
if: 'if' sp num sp pCond sp num sp repeat | ifElse;
ifElse: 'ifelse' sp num sp pCond sp num sp repeat | ifBool;
ifBool: 'if' sp pCond sp cond sp pCond sp repeat | repeat;

repeat: 'repeat' sp num sp line | line;
line: line sp brkLine | brkLine;
brkLine: brk line brk | forward;

forward: 'fd' sp num | back;
back: 'bk' sp num | left;
left: 'lt' sp num | right;
right: 'rt' sp num | penDown;

penDown: 'pd' sp num | penUp;
penUp: 'pu' | home;
home: 'home' | clearScreen;
clearScreen: 'cs' | showTurtle;
showTurtle: 'st' | hideTurtle;
hideTurtle: 'ht' | num;

num: "\d+";
sp: "\s*";
cr: '\r\n' | '\n';
brk: '[' | ']';
prn: '(' | ')';

pCond: prn num sp cond sp num prn | cond;
cond: cond | and;

and: 'and' | or;
or: 'or' | not;
not: 'not' | great;

great: '>' | less;
less: '<' | greatEq;
greatEq: '>=' | lessEq;
lessEq: '<=' | eq;
eq: '=';lines: lines cr line| line;

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
lines: lines cr line| line;
line: if | expr | repeat;

if: ifNorm | ifElse | ifBool;
ifNorm: 'if' x num x comp x num x expr;
ifElse: 'ifelse' x num x comp x num x expr x expr;
ifBool: 'if' x pCond x bool x pCond x expr;

expr: br cmds br | cmds;
repeat: 'repeat' x num x expr;

cmds: br cmds x cmds br  | command;
command: argCmd x num | emptCmd x num;
argCmd: 'fd' | 'forward' | 'bk' | 'back' | 'lt' |'left' | 'rt' | 'right';
emptCmd: 'pd' | 'pendown' | 'pu' | 'penup' | 'home' | 'cs' | 'clearscreen' | 'st' | 'showturtle' | 'ht' | 'hideturtle';

num: "\d+";
x: "\s*";
br: '[' | ']' | x;
var: "[A-Za-z]+";
cr: '\r\n' | '\n';

pCond: '(' num x comp x num ')';

bool: 'and' | 'or' | 'not';
comp: '>' | '<' | '>=' | '<=' | '=';

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
lines: lines cr line| line;
line: if | ifElse | ifBool | expr;
if: 'if' sp num sp pCond sp num sp expr;
ifElse: 'ifelse' sp num sp pCond sp num sp expr;
ifBool: 'if' sp pCond sp cond sp pCond sp expr;

expr: expr sp expr | brkExpr | repeat;
repeat: 'repeat' sp num sp expr;
brkExpr: '[' cmds ']' | cmds;

cmds: cmds sp cmds | command;
command: argCmd sp num | emptCmd sp num;
argCmd: 'fd' | 'bk' | 'lt' | 'rt';
emptCmd: 'pd' | 'pu' | 'home' | 'cs' | 'st' | 'ht';

num: "\d+";
sp: "\s*";
var: "[A-Za-z]+";
cr: '\r\n' | '\n';

pCond: '(' num sp cond sp num ')' | cond;
cond: bool | comp;

bool: 'and' | 'or' | 'not';
comp: '>' | '<' | '>=' | '<=' | '=';

*/