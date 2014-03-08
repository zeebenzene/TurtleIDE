package Parser;

import GUI.CommandLine;
import GUI.Commands;

import java.util.HashMap;

import edu.hendrix.grambler.ParseException;
import edu.hendrix.grambler.Tree;

public class Evaluator {
	private HashMap<String, String> procToVar;
	private HashMap<String, Tree> procToExpression;
	private HashMap<String, Integer> varToValue;
	private Commands cmd;
	private CommandLine cmdLine;

	public Evaluator(Commands cmds, CommandLine cline){
		cmd = cmds;
		cmdLine = cline;
		procToVar = new HashMap<String, String>();
		procToExpression = new HashMap<String, Tree>();
		varToValue = new HashMap<String, Integer>();
	}

	public void eval(String input) {
		LogoGrammar p = new LogoGrammar();
		try {
			evalTree(p.parse(input));
		} catch (ParseException e) {
			cmdLine.clearText();
			cmdLine.setError("ERROR: Invalid Command. Recheck your spelling or visit the LOGO API");
		}
	}

	public void evalTree(Tree t){
		if(t.isNamed("lines")){
			evalLines(t);

		} else if(t.isNamed("line")){
			evalTree(t.getChild(0));

		} else if(t.isNamed("procedure")){
			if(t.getNumChildren() > 1){
				String procName = t.getNamedChild("var").toString();
				String procVar = t.getNamedChild("pVar").getNamedChild("var").toString();
				
				procToVar.put(procName, procVar);
				varToValue.put(procVar, 0);
				procToExpression.put(procName, t.getNamedChild("line"));
			}
			
			cmdLine.setConfirmation("CONFIRMATION: Stored Procedure");

		} else if(t.isNamed("pCall")){
			String procedure = t.getNamedChild("var").toString();
			int num = (int) evalNum(t.getNamedChild("num"));
			String procVar = procToVar.get(procedure);
			
			varToValue.put(procVar, num);
			
			Tree procExpr = procToExpression.get(procedure);
			evalTree(procExpr);
			
			cmdLine.setConfirmation("CONFIRMATION: Doing Procedure " + procedure);

		} else if(t.isNamed("repeat")){
			double num = evalNum(t.getNamedChild("num"));
			for(int i = 0; i < num; i++){
				evalTree(t.getNamedChild("line"));
			}
			cmdLine.setConfirmation("CONFIRMATION: Doing Repeat " + num  + " Times");
			
		} else if(t.isNamed("if")){
			evalIf(t.getChild(0));
			
			cmdLine.setConfirmation("CONFIRMATION: Evaluating conditional " + t.getChild(0).getName());

		} else if(t.isNamed("expr")){
			evalTree(t.getNamedChild("cmds"));

		} else if(t.isNamed("cmds")){
			if(t.getNumChildren() == 1){
				evalTree(t.getChild(0));
			}
			else {
				evalTree(t.getChild(0));
				evalTree(t.getChild(2));
			}

		} else if(t.isNamed("command")){
			evalCommandTree(t);
		}
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
		int num1 = (int) evalNum(t.getChild(2));
		int num2 = (int) evalNum(t.getChild(6));
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

	public void evalCommandTree(Tree t){
		String commandType = t.getChild(0).getName();
		if(commandType.equals("argCmd")){
			evalArgCommand(t);
		}
		else if(commandType.equals("emptCmd")){
			evalEmptCommand(t);
		}
		else if(t.hasNamed("pCall")){
			evalTree(t.getNamedChild("pCall"));
		}
	}

	public void evalArgCommand(Tree t){
		String command = t.getChild(0).toString();
		double num = evalNum(t.getNamedChild("num"));

		if(command.equals("fd") || command.equals("forward")){
			cmd.forward(num);
		} else if(command.equals("lt") || command.equals("left")){
			cmd.left(num);
		} else if(command.equals("rt") || command.equals("right")){
			cmd.right(num);
		} else if(command.equals("bk") || command.equals("back")){
			cmd.back(num);
		}
	}

	public void evalEmptCommand(Tree t){
		String command = t.getChild(0).toString();
		if(command.equals("pu") || command.equals("penup")){
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
	}

	public double evalNum(Tree t){
		if(t.isLeaf()){
			return Double.parseDouble(t.toString());
		}
		else {
			String procVar = t.getNamedChild("pVar").getNamedChild("var").toString();
			return varToValue.get(procVar);
		}
	}
}
