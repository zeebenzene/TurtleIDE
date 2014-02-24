package Parser;

public class Evaluator {

	public Evaluator(){
		
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
eq: '=';

*/