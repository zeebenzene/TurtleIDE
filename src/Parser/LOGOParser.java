package Parser;

public class LOGOParser extends edu.hendrix.grambler.Grammar {
    public LOGOParser() {
        super();
        addProduction("lines", new String[]{"lines", "cr", "if"}, new String[]{"if"});
        addProduction("if", new String[]{"'if'", "sp", "num", "sp", "pCond", "sp", "num", "sp", "repeat"}, new String[]{"ifElse"});
        addProduction("ifElse", new String[]{"'ifelse'", "sp", "num", "sp", "pCond", "sp", "num", "sp", "repeat"}, new String[]{"ifBool"});
        addProduction("ifBool", new String[]{"'if'", "sp", "pCond", "sp", "cond", "sp", "pCond", "sp", "repeat"}, new String[]{"repeat"});
        addProduction("repeat", new String[]{"'repeat'", "sp", "num", "sp", "line"}, new String[]{"line"});
        addProduction("line", new String[]{"line", "sp", "brkLine"}, new String[]{"brkLine"});
        addProduction("brkLine", new String[]{"brk", "line", "brk"}, new String[]{"forward"});
        addProduction("forward", new String[]{"'fd'", "sp", "num"}, new String[]{"back"});
        addProduction("back", new String[]{"'bk'", "sp", "num"}, new String[]{"left"});
        addProduction("left", new String[]{"'lt'", "sp", "num"}, new String[]{"right"});
        addProduction("right", new String[]{"'rt'", "sp", "num"}, new String[]{"penDown"});
        addProduction("penDown", new String[]{"'pd'", "sp", "num"}, new String[]{"penUp"});
        addProduction("penUp", new String[]{"'pu'"}, new String[]{"home"});
        addProduction("home", new String[]{"'home'"}, new String[]{"clearScreen"});
        addProduction("clearScreen", new String[]{"'cs'"}, new String[]{"showTurtle"});
        addProduction("showTurtle", new String[]{"'st'"}, new String[]{"hideTurtle"});
        addProduction("hideTurtle", new String[]{"'ht'"}, new String[]{"num"});
        addProduction("num", new String[]{"\"\\d+\""});
        addProduction("sp", new String[]{"\"\\s*\""});
        addProduction("cr", new String[]{"'\r\n'"}, new String[]{"'\n'"});
        addProduction("brk", new String[]{"'['"}, new String[]{"']'"});
        addProduction("prn", new String[]{"'('"}, new String[]{"')'"});
        addProduction("pCond", new String[]{"prn", "num", "sp", "cond", "sp", "num", "prn"}, new String[]{"cond"});
        addProduction("cond", new String[]{"cond"}, new String[]{"and"});
        addProduction("and", new String[]{"'and'"}, new String[]{"or"});
        addProduction("or", new String[]{"'or'"}, new String[]{"not"});
        addProduction("not", new String[]{"'not'"}, new String[]{"great"});
        addProduction("great", new String[]{"'>'"}, new String[]{"less"});
        addProduction("less", new String[]{"'<'"}, new String[]{"greatEq"});
        addProduction("greatEq", new String[]{"'>='"}, new String[]{"lessEq"});
        addProduction("lessEq", new String[]{"'<='"}, new String[]{"eq"});
        addProduction("eq", new String[]{"'='"});
    }
}

