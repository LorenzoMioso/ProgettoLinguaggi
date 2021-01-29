package value;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class FunValue extends Value {

    private final String name;
    private final ArrayList<String> parameters;
    private final ParseTree comContext;
    private final ParseTree expContex;

    public FunValue(String name, ArrayList<String> parameters, ParseTree comContext, ParseTree expContex) {
        this.name = name;
        this.parameters = parameters;
        this.comContext = comContext;
        this.expContex = expContex;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public ParseTree getComContext() {
        return comContext;
    }

    public ParseTree getExpContex() {
        return expContex;
    }



    @Override
    public boolean equals(Object obj) {
        return obj instanceof FunValue;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
