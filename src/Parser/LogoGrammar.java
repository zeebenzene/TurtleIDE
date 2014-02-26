package Parser;

public class LogoGrammar extends edu.hendrix.grambler.Grammar {
    public LogoGrammar() {
        super();
        addProduction("lines", new String[]{"lines", "cr", "line"}, new String[]{"line"});
        addProduction("line", new String[]{"if"}, new String[]{"ifElse"}, new String[]{"ifBool"}, new String[]{"expr"});
        addProduction("if", new String[]{"'if'", "sp", "num", "sp", "pCond", "sp", "num", "sp", "expr"});
        addProduction("ifElse", new String[]{"'ifelse'", "sp", "num", "sp", "pCond", "sp", "num", "sp", "expr"});
        addProduction("ifBool", new String[]{"'if'", "sp", "pCond", "sp", "cond", "sp", "pCond", "sp", "expr"});
        addProduction("expr", new String[]{"expr", "sp", "expr"}, new String[]{"brkExpr"}, new String[]{"repeat"});
        addProduction("repeat", new String[]{"'repeat'", "sp", "num", "sp", "expr"});
        addProduction("brkExpr", new String[]{"'['", "cmds", "']'"}, new String[]{"cmds"});
        addProduction("cmds", new String[]{"cmds", "sp", "cmds"}, new String[]{"command"});
        addProduction("command", new String[]{"argCmd", "sp", "num"}, new String[]{"emptCmd", "sp", "num"});
        addProduction("argCmd", new String[]{"'fd'"}, new String[]{"'bk'"}, new String[]{"'lt'"}, new String[]{"'rt'"});
        addProduction("emptCmd", new String[]{"'pd'"}, new String[]{"'pu'"}, new String[]{"'home'"}, new String[]{"'cs'"}, new String[]{"'st'"}, new String[]{"'ht'"});
        addProduction("num", new String[]{"\"\\d+\""});
        addProduction("sp", new String[]{"\"\\s*\""});
        addProduction("var", new String[]{"\"[A-Za-z]+\""});
        addProduction("cr", new String[]{"'\r\n'"}, new String[]{"'\n'"});
        addProduction("pCond", new String[]{"'('", "num", "sp", "cond", "sp", "num", "')'"}, new String[]{"cond"});
        addProduction("cond", new String[]{"bool"}, new String[]{"comp"});
        addProduction("bool", new String[]{"'and'"}, new String[]{"'or'"}, new String[]{"'not'"});
        addProduction("comp", new String[]{"'>'"}, new String[]{"'<'"}, new String[]{"'>='"}, new String[]{"'<='"}, new String[]{"'='"});
    }
}

