import java.util.*;

class Entry {
    private String name;        // name of symbol.
    private int linenumber;     // line on which declared
    private int level;          // nesting depth
    private ArrayList<AU> assigned;
    private ArrayList<AU> used;
    public Entry(String name, int linenumber, int level) {
        this.name = name;
        this.linenumber = linenumber;
        this.level = level;
        used = new ArrayList<AU>();
        assigned = new ArrayList<AU>();
    }
    String getName() {
        return name;
    }
    public void ref(boolean wasassigned, int linenumber) { // referenced
        ArrayList<AU> which;
        if (wasassigned) {
            which = assigned;
        }
        else {
            which = used;
        }
        boolean found = false;
        for( AU a: which ) {
            if (a.update(linenumber)) {
                found = true;
                break;
            }
        }
        if (!found) {
            which.add(new AU(linenumber));
        }
    }
    public void print() {
        System.err.println(name);
        System.err.println("  declared on line " + linenumber +
			   " at nesting depth " + level);
        printAU(assigned, "  assigned to on:", "  never assigned");
        printAU(used,     "  used on:",        "  never used");
    }
    public void printAU(ArrayList<AU> w, String s1, String s2) {
        if (w.size() == 0) {
            System.err.println(s2);
        }
        else {
            System.err.print(s1);
	    for( AU a: w) {
                System.err.print(" "+a);
            }
            System.err.println();
        }
    }
}
