import java.util.*;
public class Parser {

    private Symtab symtab = new Symtab();

    // the first sets.
    // note: we cheat sometimes:
    // when there is only a single token in the set,
    // we generally just compare tkrep with the first token.
    TK f_declarations[] = {TK.VAR, TK.none};
    TK f_statement[] = {TK.ID, TK.PRINT, TK.SKIP, TK.STOP, TK.IF, TK.DO, TK.FA, TK.BREAK, TK.DUMP, TK.EXCNT, TK.none};
    TK f_print[] = {TK.PRINT, TK.none};
    TK f_assignment[] = {TK.ID, TK.none};
    TK f_skip[] = {TK.SKIP, TK.none};
    TK f_stop[] = {TK.STOP, TK.none};
    TK f_if[] = {TK.IF, TK.none};
    TK f_do[] = {TK.DO, TK.none};
    TK f_fa[] = {TK.FA, TK.none};
    TK f_break[] = {TK.BREAK, TK.none};
    TK f_dump[] = {TK.DUMP, TK.none};
    TK f_excnt[] = {TK.EXCNT, TK.none};
    TK f_expression[] = {TK.ID, TK.NUM, TK.LPAREN, TK.MODULO, TK.none};
    TK f_predef[] = {TK.MODULO, TK.MAX, TK.none};
    int maxLevel = 0; // Nesting Level of max
    int loopLevel = 0; // Nesting level of for/while loop (for break)
    int labelCountdown = 0; // Countdown to the label placement
    int exCount = 0; // ID/Count of the number of EXCNT occurances
    Stack<String> labelStack = new Stack<String>(); // Stack to place Unique Label values
    ArrayList<Entry> variableList = new ArrayList<>(); // List to store all Variable values for dump
    String labelID = "z"; // Base point for unique label naming

    // tok is global to all these parsing methods;
    // scan just calls the scanner's scan method and saves the result in tok.
    private Token tok; // the current token
    private void scan() {
        tok = scanner.scan();
    }
    
    private int getLine() { // Retrieves line number from scanner
        return scanner.getLine();
    } // Helper Function

    private Scan scanner;
    Parser(Scan scanner) {
        this.scanner = scanner;
        scan();
        program();
        if( tok.kind != TK.EOF )
            parse_error("junk after logical end of program");
        symtab.reportVariables();
    }

    // print something in the generated code
    private void gcprint(String str) {
        System.out.println(str);
    }
    // print identifier in the generated code
    // it prefixes x_ in case id conflicts with C keyword.
    private void gcprintid(String str) {
        System.out.println("x_"+str);
    }

    private void program() {
        // generate the E math functions:
        gcprint("int esquare(int x){ return x*x;}");
        gcprint("#include <math.h>");
        gcprint("#include <stdlib.h>");
        gcprint("#include <stdbool.h>");
        gcprint("#include <stdio.h>");
        gcprint("int esqrt(int x){ double y; if (x < 0) return 0; y = sqrt((double)x); return (int)y;}");
        // A simple zero checker
        gcprint("bool isZero(int x){if(x == 0)return true; return false;}");
        // Mod function with error check and message
        gcprint("int mod(int x, int y){if(isZero(y)){printf(\"\\nmod(a,b) with b=0\\n\"); exit(1);} return (((x % y) + y) % y);}");
        // Max Macro
        gcprint("#define max(x,y) (x)>(y)?(x):(y)");
        // EXCNT Tracker Struct
        gcprint("struct EXCNT{int count; int lineNumber;};");
        gcprint("struct EXCNT EXCNTarray[100];");
        
        gcprint("int main() {");
       	gcprint("for(int i = 0; i < 100;i++){");  // Initialize EXCNTarray to 0
       	gcprint("EXCNTarray[i].count=0;");
       	gcprint("}");
        block();
        if(exCount>0) exTable();  // If EXCNT was called at least once in e, print the table.
        gcprint("return 0; }");
    }

    private void block() {
        symtab.begin_st_block();
	      gcprint("{");
        if( first(f_declarations) ) {
            declarations();
        }
        statement_list();
        symtab.end_st_block();
	      gcprint("}");
    }

    private void declarations() {
        mustbe(TK.VAR);
        while( is(TK.ID) ) {
            // Takes the declared variable's name, line number, and nest level and stores it in variableList
            variableList.add(new Entry(tok.string, tok.lineNumber, this.symtab.get_level())); 
            if( symtab.add_var_entry(tok.string,tok.lineNumber) ) { // Saves the variable data as an Entry
                gcprint("int");
                gcprintid(tok.string);
                gcprint("= -12345;");
            }
            scan();
        }
        mustbe(TK.RAV);
    }

    private void statement_list(){
        while( first(f_statement) ) {
            statement();
        }
    }

    private void statement(){
        if( first(f_assignment) )
            assignment();
        else if( first(f_print) )
            print();
	      else if ( first(f_skip) ) // Skip check
	          skip();
	      else if ( first(f_stop) ) // Stop check
	          stop();
        else if( first(f_if) )
            ifproc();
        else if( first(f_do) )
            doproc();
        else if( first(f_fa) )
            fa();
        else if( first(f_break) ) // Break check
            breakproc();
        else if( first(f_dump) ) // Dump check 
            dump();
        else if( first(f_excnt) ) // EXCNT check
        	  excnt(); 
        else
            parse_error("statement");
    }

    private void assignment(){
        String id = tok.string;
        int lno = tok.lineNumber; // save it too before mustbe!
        mustbe(TK.ID);
        referenced_id(id, true, lno);
        gcprintid(id);
        mustbe(TK.ASSIGN);
        gcprint("=");
        expression();
        gcprint(";");
    }

    private void print(){
        mustbe(TK.PRINT);
        gcprint("printf(\"%d\\n\", ");
        expression();
        gcprint(");");
    }

    private void skip(){ // Skip Function
        mustbe(TK.SKIP);
    } // Skip Function

    private void stop(){ // Stop Function
	  mustbe(TK.STOP);
    // Place jump to print EXCMT
    // NOTE: If table printing is called here, incorrect exCount values may be
    //       given to the output if EXCNT is called after this stop is parsed.
	  if(exCount > 0) gcprint("goto a;");
	  gcprint("exit(0);");
	  if( first(f_statement) ) // Checks for anything following stop
	      System.err.println( "warning: on line " 
		    + tok.lineNumber + " statement(s) follows stop statement");
    } // Stop Function

    private void ifproc(){
        mustbe(TK.IF);
        guarded_commands(TK.IF);
        mustbe(TK.FI);
    }

    private void doproc(){
        mustbe(TK.DO);
        
        loopLevel++; // Increments level of for/while loops
        createLabel(); // Create Label for this instance
        
        gcprint("while(1){");
        guarded_commands(TK.DO);
        gcprint("}");
        mustbe(TK.OD);
        
        labelCountdown--; // Decrements label countdown
        if (labelCountdown == 0){ // Found correct place to put label 
                String endLabel = labelStack.pop(); // Remove Unique Label from stack and place
                gcprint(endLabel+":");
        }
        loopLevel--; // Decrements (backs out) level of for/while loops
    }

    private void fa(){
        mustbe(TK.FA);
        gcprint("for(");
        
        loopLevel++;  // Increments level of for/while loops
        createLabel(); // Create Label for this instance
        
        String id = tok.string;
        int lno = tok.lineNumber; // save it too before mustbe!
        mustbe(TK.ID);
        referenced_id(id, true, lno);
        gcprintid(id);
        mustbe(TK.ASSIGN);
        gcprint("=");
        expression();
        gcprint(";");
        mustbe(TK.TO);
        gcprintid(id);
        gcprint("<=");
        expression();
        gcprint(";");
        gcprintid(id);
        gcprint("++)");
        if( is(TK.ST) ) {
            gcprint("if( ");
            scan();
            expression();
            gcprint(")");
        }
        commands();
        mustbe(TK.AF);
        
        labelCountdown--; // Decrements label countdown
        if (labelCountdown == 0 ){ // Found correct place to put label 
                String endLabel = labelStack.pop();  // Remove Unique Label from stack and place
                gcprint(endLabel+":");
        }
        loopLevel--; // Decrements (backs out) level of for/while loops
    }
    
    private void breakproc(){ // Break Function
        if(is(TK.BREAK)){
            if (loopLevel > 0){ // If inside a for/while loop
                scan();
                boolean ignored = false; // Allows us to get a warning line in case of extra sentences
                if (is(TK.NUM)){ // If the optional parameter exists
                    int num = Integer.parseInt(tok.string);
                    if(num <= loopLevel && num!=0){ // If num is a valid input
                        String label = labelStack.peek();
                        gcprint("goto "+ label + ";"); // Copy unique label from stack and print
                        labelCountdown = num; // Set countdown for label placement
                    }
                    // ~~ Error Messages ~~
                    if (num == 0){ // Ignore inputs equal to 0
                        System.err.println("warning: on line "+ tok.lineNumber + " break " + tok.string + " statement ignored" );
                        ignored = true;
                    } else if (loopLevel < num){ // Ignore inputs greater than number of nested loops
                        System.err.println("warning: on line " + tok.lineNumber + " break statement exceeding loop nesting ignored");
                        ignored = true;
                    }
                    scan(); // Continue
                }
                if (!ignored) { // Optional Parameter is not there, execute regular break
                    gcprint("break;");
                    if(!(is(TK.FA)) && !(is(TK.FI)) && !(is(TK.DO))){ // If next is not closing of for/while/if
                        System.err.println("warning: on line "+ tok.lineNumber + " statement(s) follows break statement" );
                        statement_list(); // Prints following statements
                    }
                }
            } else { // If outside a for/while loop
                System.err.println("warning: on line "+ tok.lineNumber + " break statement outside of loop ignored"); 
                scan(); // Continue
            }
        }
    } // Break Function
    
    private void dump(){ // Dump Function
        String msg;
        int dumpLineNumber = getLine(); // Retrieve dump line number
        scan();
        int level = this.symtab.get_level(); // Retrieve current nest level
        if(is(TK.NUM)){ // If optional level parameter exists
            int dumpLevel = Integer.parseInt(tok.string);
            if(dumpLevel > level){ // If requested level is out of bounds
                msg = String
                        .format("warning: on line %d dump statement level (%d) exceeds block nesting level (%d). using %d" ,
                                tok.lineNumber, dumpLevel, level, level);
                System.err.println(msg); // Print error message
                dumpLevel = level; // And treat as regular dump
            }
            msg = String.format("\"+++ dump on line %d of level %d begin +++\\n", tok.lineNumber, dumpLevel);
            gcprint("printf(" + msg + "\");");
            printVariables(Optional.of(dumpLevel)); // Print Variable details
            msg = String.format("\"--- dump on line %d of level %d end ---\\n", tok.lineNumber, dumpLevel);
            gcprint("printf(" + msg + "\");");
            scan(); // Continue Scanning
        }
        else{
            msg = String.format("\"+++ dump on line %d of all levels begin +++\\n", dumpLineNumber);
            gcprint("printf(" + msg + "\");");
            printVariables(Optional.empty()); // Print Variable details
            msg = String.format("\"--- dump on line %d of all levels end ---\\n", dumpLineNumber);
            gcprint("printf(" + msg + "\");");
        }
    } // Dump Function
    
    private void excnt(){  // EXCNT Function
        // Replace EXCNT with increment command to keep track of occurances
        gcprint("EXCNTarray[" + String.valueOf(exCount) + "].count++;"); 
        exCount++;  // Increment ID/# of EXCNT
        // If more than 100 EXCNT, return parse error
    	  if(exCount > 100) parse_error("too many EXCNT (more than 100)");
        scan();  // Continue Scanning
    }  // EXCNT Function

    private void exTable(){ // Print function for EXCNT
   	    gcprint("a:;"); // Stop goto target
    	  gcprint("printf(\"---- Execution Counts ----\\n\");");
    	  gcprint("printf(\"%4s%9s\\n\", \"num\", \"count\");");
    	  gcprint("for(int currentEXE = 0; currentEXE < " + String.valueOf(exCount)
    		    + "; currentEXE++) {");
    	  gcprint("printf(\"%4d%9d\\n\", currentEXE + 1, EXCNTarray[currentEXE].count);");
    	  gcprint("}");
    } // Print function for EXCNT


    private void createLabel(){ // Unique Label Generation
        String temp = Integer.toString(loopLevel);  
        labelID = labelID + "s"; // Adds another s to labelID for distinction
        temp = temp + labelID;     
        String label = "label" + temp; // Constructs unique label
        labelStack.push(label); // Places label onto stack to be used
    } // Unique Label Generation
    
    private void printVariables(Optional<Integer> dumpLevel) { // Print Function for Dump
        Set<String> isUsed = new HashSet<>();  // Initialize set of found variables for this Dump
        for(Entry variable : this.variableList){  // For every recorded variable
            // If optional level parameter exists and is not equal to the variable's nest level, skip
            if(dumpLevel.isPresent() && dumpLevel.get() != variable.getLevel()) continue;
            Entry entry = findEntry(isUsed, variable.getName(), dumpLevel); // Search for variable details
            if(entry != null){
                // Add variable to found variable list to avoid overlap
                isUsed.add(String.format("%s%s%s", entry.getName(), entry.getLevel(), entry.getLine()));
                String msg = "printf(\"%12d %3d %3d %s\\n\", " + "x_" + variable.getName() + "," + entry.getLine() +
                        "," + entry.getLevel() + "," + "\"" + variable.getName() + "\"" + ");";
                gcprint(msg); // Print variable details
            }
        }
    } // Print Function for Dump

    private Entry findEntry(Set<String> isUsed, String variable, Optional<Integer> startLevel) { // Search function for Dump
        Entry entry = null;
        if(startLevel.isPresent()){ // If optional level parameter exists
            // Search within symtab for the variable on the specified level
            // Note: Symtab searches in reverse; To search the nth level, you input the difference between it
            //       and the current level rather than n outright.
            entry = this.symtab.search_level(this.symtab.get_level() - startLevel.get(), variable);
            return (entry != null
                    && !isUsed.contains(String.format("%s%s%s", entry.getName(), entry.getLevel(), entry.getLine()))) ? entry : null;
        }else{  // Else, if there is no optional level parameter
            for(int i = this.symtab.get_level(); i >= 0; i--){ // Starting from the current level, working down
                if(entry != null && !isUsed  // If we have found the variable and not yet recorded it before, break
                        .contains(String.format("%s%s%s", entry.getName(), entry.getLevel(), entry.getLine())))
                    break; // Break so we don't overwrite the variable with an earlier value in a lower level
                entry = this.symtab.search_level(i, variable);
            }
            return entry;
        }
    } // Search Function for Dump

    private void guarded_commands(TK which){
        guarded_command();
        while( is(TK.BOX) ) {
            scan();
            gcprint("else");
            guarded_command();
        }
        if( is(TK.ELSE) ) {
            gcprint("else");
            scan();
            commands();
        }
        else if( which == TK.DO )
            gcprint("else break;");
    }

    private void guarded_command(){
        gcprint("if(");
        expression();
        gcprint(")");
        commands();
    }

    private void commands(){
        mustbe(TK.ARROW);
        gcprint("{");/* note: generate {} to simplify, e.g., st in fa. */
        block();
        gcprint("}");
    }

    private void expression(){
        simple();
        while( is(TK.EQ) || is(TK.LT) || is(TK.GT) ||
               is(TK.NE) || is(TK.LE) || is(TK.GE)) {
            if( is(TK.EQ) ) gcprint("==");
            else if( is(TK.NE) ) gcprint("!=");
            else gcprint(tok.string);
            scan();
            simple();
        }
    }

    private void simple(){
        term();
        while( is(TK.PLUS) || is(TK.MINUS) ) {
            gcprint(tok.string);
            scan();
            term();
        }
    }

    private void term(){
        factor();
        while(  is(TK.TIMES) || is(TK.DIVIDE) || is(TK.MODULUS)) { // Added Remainder (MODULUS)
            gcprint(tok.string);
            scan();
            factor();
        }
    }

    private void factor(){
        if( is(TK.LPAREN) ) {
            gcprint("(");
            scan();
            expression();
            mustbe(TK.RPAREN);
            gcprint(")");
        }
        else if( is(TK.SQUARE) ) {
            gcprint("esquare(");
            scan();
            expression();
            gcprint(")");
        }
        else if( is(TK.SQRT) ) {
            gcprint("esqrt(");
            scan();
            expression();
            gcprint(")");
        }
        else if( is(TK.ID) ) {
            referenced_id(tok.string, false, tok.lineNumber);
            gcprintid(tok.string);
            scan();
        }
        else if( is(TK.NUM) ) {
            gcprint(tok.string);
            scan();
        }
        else if( is(TK.MODULO) ) { // MOD Function
            scan();
            mustbe(TK.LPAREN); // MOD is already read, check for '('
            gcprint("mod(");
            expression();
            mustbe(TK.COMMA); // Checks for comma
            gcprint(",");
            expression();
            mustbe(TK.RPAREN); // Encloses function
            gcprint(")");
        } // MOD Function
        else if( is(TK.MAX) ) {
            scan();
            mustbe(TK.LPAREN);
            maxLevel++; // Increases "max" nesting level by 1
            gcprint("max(");
            expression();
            mustbe(TK.COMMA); // Checks for comma 
            gcprint(",");
            expression();
            mustbe(TK.RPAREN);
            if(is(TK.RPAREN)){ // If there is another RParen afterwards indicating backing out of a nest
                if(maxLevel > 5){ // Check for > 5 Nesting Level
                    System.err.println("can't parse: line "+ tok.lineNumber + " max expressions nested too deeply (> 5 deep)");  // Error Message  
                    System.exit(1); // Exit
                }
            }
            gcprint(")");
            maxLevel--; // Decreases "max" nesting level by 1 (back out)
        }
        else
            parse_error("factor");
    }

    // be careful: use lno here, not tok.lineNumber
    // (which may have been advanced by now)
    private void referenced_id(String id, boolean assigned, int lno) {
        Entry e = symtab.search(id);
        if( e == null) {
            System.err.println("undeclared variable "+ id + " on line "
                               + lno);
            System.exit(1);
        }
        e.ref(assigned, lno);
    }

    // is current token what we want?
    private boolean is(TK tk) {
        return tk == tok.kind;
    }

    // ensure current token is tk and skip over it.
    private void mustbe(TK tk) {
        if( ! is(tk) ) {
            System.err.println( "mustbe: want " + tk + ", got " +
                                    tok);
            parse_error( "missing token (mustbe)" );
        }
        scan();
    }
    boolean first(TK [] set) {
        int k = 0;
        while(set[k] != TK.none && set[k] != tok.kind) {
            k++;
        }
        return set[k] != TK.none;
    }

    private void parse_error(String msg) {
        System.err.println( "can't parse: line "
                            + tok.lineNumber + " " + msg);
        System.exit(1);
    }
}
