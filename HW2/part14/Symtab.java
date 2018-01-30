import java.util.*;

// Symtab is a Stack of ArrayLists of Entry

public class Symtab {

    // the symbol table:
    private Stack<ArrayList<Entry>> st;
    // all blocks, in order, that were ever pushed on the symbol table:
    // (unlike st, never pop from stBlocks
    private Stack<ArrayList<Entry>> stBlocks;
    // level of current symtab block
    private int level;

    public Symtab(){
        st = new Stack<ArrayList<Entry>>();
        stBlocks = new Stack<ArrayList<Entry>>();
        level = -1;
    };

    // start of symtab block
    public void begin_st_block() { // push block
        ArrayList<Entry> newBlock = new ArrayList<Entry>();
        st.push(newBlock);
        stBlocks.push(newBlock);
        level++;
    }

    // end of symtab block
    public void end_st_block() { // pop block
        st.pop();
        level--;
    }

    public int get_level() {
        return level;
    }

    // add variable entry to current block
    public boolean add_var_entry(String myid, int myline) {
        if (search_this_block(myid) != null) {
                System.err.println("variable " + myid +
                                   " is redeclared on line "+
                                   myline);
                return false;
        }

        Entry p = new Entry(myid, myline, level);
        st.peek().add(p);
	return true;
    }

    // these search routines search individual blocks backwards.
    // that's not necessary -- either order works.
    // the *stack* needs to be searched backwards.

    // also, can't (easily) use .contains to search
    // within block of symbol table because entries contain more
    // than just a name and want to search based only on name.
    // (actually here Entry contains only name, so would work.
    //  but in more realistic Symtabs wouldn't work.)

    public Entry search(String myid) {
        // for scoping, search list backwards.
        // check for identifer in this block first, outward to global block.
        ListIterator<ArrayList<Entry>> blocks = st.listIterator(st.size());
        // now can go backwards.
        while( blocks.hasPrevious() ) {
            ArrayList<Entry> block = blocks.previous();
            // OK to search block in either order
            // since Entry unique w/i block.
            for( Entry p : block) {
                if( p.getName().equals(myid) ){
                    return p;
                }
            }
        }
        return null;
    }

    private Entry search_this_block(String myid) {
        ArrayList<Entry> block = st.peek();
        for( Entry p : block) {
            if( p.getName().equals(myid) ) {
                return p;
            }
        }
        return null;
    }

    // some of these search methods could be combined ...
    public Entry search_level(int slevel, String myid) {
        if (slevel > level) {
            return null;
        }
        if (slevel < 0) { // be defensive
            System.err.println("oops slevel<0 " + slevel);
            System.exit(1);
        }
        // go directly to right block:
        ArrayList<Entry> block = st.elementAt(level-slevel);
        // now search the right block:
        for( Entry p : block) {
            if( p.getName().equals(myid) ){
                return p;
            }
        }
        return null;
    }

    public void reportVariables() {
	for( ArrayList<Entry> block: stBlocks) {
	    for (Entry p: block) {
                p.print();
            }
        }
    }
}
