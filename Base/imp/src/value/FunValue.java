package value;

import java.util.ArrayList;

public class FunValue extends Value {

    public final String name;
    public final ArrayList<String> parameters;
    public final Object comContext;
    public final Object expContex;

    public FunValue(String name, ArrayList<String> parameters, Object comContext, Object expContex) {
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

    public Object getComContext() {
        return comContext;
    }

    public Object getExpContex() {
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
