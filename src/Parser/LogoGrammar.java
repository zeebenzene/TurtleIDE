package Parser;

public class LogoGrammar extends edu.hendrix.grambler.Grammar {
    public LogoGrammar() {
        super();
        addProduction("lines", new String[]{"lines", "cr", "line"}, new String[]{"line"}, new String[]{"x"});
        addProduction("line", new String[]{"if"}, new String[]{"expr"}, new String[]{"repeat"}, new String[]{"x"}, new String[]{"procedure"});
        addProduction("procedure", new String[]{"'to'", "x", "var", "x", "pVar", "x", "br", "line", "br"});
        addProduction("pCall", new String[]{"var", "x", "num"});
        addProduction("if", new String[]{"ifNorm"}, new String[]{"ifElse"}, new String[]{"ifBool"});
        addProduction("ifNorm", new String[]{"'if'", "x", "num", "x", "comp", "x", "num", "x", "expr"});
        addProduction("ifElse", new String[]{"'ifelse'", "x", "num", "x", "comp", "x", "num", "x", "expr", "x", "expr"});
        addProduction("ifBool", new String[]{"'if'", "x", "pCond", "x", "bool", "x", "pCond", "x", "expr"});
        addProduction("expr", new String[]{"br", "cmds", "br"}, new String[]{"cmds"});
        addProduction("repeat", new String[]{"'repeat'", "x", "num", "x", "br", "line", "br"});
        addProduction("cmds", new String[]{"cmds", "x", "cmds"}, new String[]{"command"});
        addProduction("command", new String[]{"argCmd", "x", "num"}, new String[]{"emptCmd"}, new String[]{"pCall"});
        addProduction("argCmd", new String[]{"'fd'"}, new String[]{"'forward'"}, new String[]{"'bk'"}, new String[]{"'back'"}, new String[]{"'lt'"}, new String[]{"'left'"}, new String[]{"'rt'"}, new String[]{"'right'"});
        addProduction("emptCmd", new String[]{"'pd'"}, new String[]{"'pendown'"}, new String[]{"'pu'"}, new String[]{"'penup'"}, new String[]{"'home'"}, new String[]{"'cs'"}, new String[]{"'clearscreen'"}, new String[]{"'st'"}, new String[]{"'showturtle'"}, new String[]{"'ht'"}, new String[]{"'hideturtle'"});
        addProduction("num", new String[]{"\"\\d+\""}, new String[]{"pVar"});
        addProduction("x", new String[]{"\"\\s*\""});
        addProduction("br", new String[]{"'['"}, new String[]{"']'"}, new String[]{"x"});
        addProduction("pVar", new String[]{"':'", "var"});
        addProduction("var", new String[]{"\"(?!repeat|fd|forward|bk|back|lt|left|rt|right|pd|pendown|pu|penup|home|cs |clearscreen|st|showturtle|ht)[A-Za-z]+\""});
        addProduction("cr", new String[]{"'\r\n'"}, new String[]{"'\n'"});
        addProduction("pCond", new String[]{"'('", "num", "x", "comp", "x", "num", "')'"});
        addProduction("bool", new String[]{"'and'"}, new String[]{"'or'"}, new String[]{"'not'"});
        addProduction("comp", new String[]{"'>'"}, new String[]{"'<'"}, new String[]{"'>='"}, new String[]{"'<='"}, new String[]{"'='"});
    }
}

