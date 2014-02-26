package Parser;

import java.util.HashMap;

import edu.hendrix.grambler.ParseException;
import edu.hendrix.grambler.Tree;

public class Evaluator {
	private HashMap<String, Integer> procedures;
	
	
	public Evaluator(){
		
	}
	
	public Tree eval(String input) throws ParseException {
		LogoGrammar p = new LogoGrammar();
		return null;
	}
	
	public String evalTree(Tree t){
		if(t.isNamed("lines")){
			System.out.println(t.toTextTree());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			evalLinesTree(t);
		}
		else if(t.isNamed("line")){
			return evalTree(t.getNamedChild("expr"));
		}
		else if(t.isNamed("expr")){
			 return evalTree(t.getNamedChild("brkExpr"));
		}
		else if(t.isNamed("brkExpr")){
			return evalTree(t.getNamedChild("cmds"));
		}
		else if(t.isNamed("cmds")){
			for(int i = 0; i < t.getNumChildren(); i++){
				evalTree(t.getChild(i));
			}
		}
		else if(t.isNamed("command")){
			System.out.println(evalCommandTree(t));
			return evalCommandTree(t);
		}
		return "";
	}

	public void evalLinesTree(Tree t){
		if(t.getNumChildren() == 1){
			evalTree(t.getNamedChild("line"));	
		}
		else{
			evalTree(t.getNamedChild("lines"));
			evalTree(t.getNamedChild("line"));
		}
	}
	
	public String evalCommandTree(Tree t){
		String command = t.getChild(0).toString();
		String num = t.getNamedChild("num").toString();
		return command + " " + num;
	}
	
	public static void main(String[] args) throws ParseException{
		LogoGrammar p = new LogoGrammar();
		Evaluator e = new Evaluator();
		e.evalTree(p.parse("[bk 30 fd 60 fd 10]"));
	}	
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




line: if | ifElse;
if: 'if' sp num sp pCond sp num sp repeat | ifElse;
ifElse: 'ifelse' sp num sp pCond sp num sp repeat | procedure;

procedure: 'to' sp var sp ':' num;

repeat: 'repeat' sp num sp expr | expr;
expr: expr sp expr | brkExpr;
brkExpr: '[' sp expr sp ']' | command;

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