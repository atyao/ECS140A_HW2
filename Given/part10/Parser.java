import java.util.*;

public class Parser {

    private Symtab symtab = new Symtab();

    // the first sets.
    // note: we cheat sometimes:
    // when there is only a single token in the set,
    // we generally just compare tkrep with the first token.
    TK f_declarations[] = {TK.VAR, TK.none};
    TK f_statement[] = {TK.ID, TK.PRINT, TK.SKIP,TK.STOP,TK.IF, TK.DO, TK.FA, TK.BREAK, TK.DUMP, TK.none};
    TK f_print[] = {TK.PRINT, TK.none};
    TK f_skip[] = {TK.SKIP, TK.none};
    TK f_stop[] = {TK.STOP, TK.none};
    TK f_assignment[] = {TK.ID, TK.none};
    TK f_if[] = {TK.IF, TK.none};
    TK f_do[] = {TK.DO, TK.none};
    TK f_fa[] = {TK.FA, TK.none};
    TK f_break[] = {TK.BREAK, TK.none};
    TK f_dump[] = {TK.DUMP, TK.none};
    TK f_expression[] = {TK.ID, TK.NUM, TK.LPAREN, TK.none};
    int nest = 0;
    int nestChecked = 0;
    int breakCheck = 0;
    int numCheck = 0;
    Stack<String> labelStack = new Stack<>();
    ArrayList<String> variableList = new ArrayList<>();
    String baffle;
    String labelUnique = "z";



    // tok is global to all these parsing methods;
    // scan just calls the scanner's scan method and saves the result in tok.
    private Token tok; // the current token
    private void scan() {
        tok = scanner.scan();
    }

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
        gcprint("#include <math.h>");
        gcprint("#include <stdlib.h>");
        gcprint("#include <stdio.h>");
        gcprint("#include <stdbool.h>");
        gcprint("#define max(x,y) (x)>(y)?(x):(y)");

        gcprint("int esquare(int x){ return x*x;}");
        gcprint("bool isZero(int x){if(x == 0)return true; return false;}");
        gcprint("int mod(int x, int y){if(isZero(y)){printf(\"\\nmod(a,b) with b=0\\n\"); exit(1);} return (((x % y) + y) % y);}");
        gcprint("int esqrt(int x){ double y; if (x < 0) return 0; y = sqrt((double)x); return (int)y;}");


        gcprint("int main() {");
        block();
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
            variableList.add(tok.string);
            if( symtab.add_var_entry(tok.string,tok.lineNumber) ) {
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
        else if( first(f_skip) )
            skipproc();
        else if( first(f_stop) )
            stopproc();
        else if( first(f_if) )
            ifproc();
        else if( first(f_do) )
            doproc();
        else if( first(f_fa) )
            fa();
        else if( first(f_break) )
            breakproc();
        else if(first(f_dump))
            dump();
        else
            parse_error("statement");
    }

    private void dump(){
        Set<String> isUsed = new HashSet<>();
        gcprint("printf(" + "\"+++ dump of all levels begin +++\\n" + "\");");
         for(String variable : this.variableList){
             Entry entry = null;


             for(int i = this.symtab.get_level(); i >= 0; i--){
                 if(entry != null && !isUsed
                         .contains(String.format("%s%s%s", entry.getName(), entry.getLevel(), entry.getLine())))
                     break;
                 entry = this.symtab.search_level(i, variable);
             }
             if(entry == null) continue;

             isUsed.add(String.format("%s%s%s", entry.getName(), entry.getLevel(), entry.getLine()));


             String msg = "printf(\"%12d %3d %3d %s\\n\", " + "x_" + variable + "," + entry.getLine() +
                     "," + entry.getLevel() + "," + "\"" + variable + "\"" + ");";

             gcprint(msg);
         }
        gcprint("printf(" + "\"--- dump of all levels end ---\\n" + "\");");
        scan();
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

    private void skipproc(){
        mustbe(TK.SKIP);
    }

    private void stopproc(){

        mustbe(TK.STOP);
        gcprint("exit(0);");
        if ( first(f_statement)){
            System.err.println("warning: on line "+ tok.lineNumber + " statement(s) follows stop statement" );
        }

    }

    private void print(){
        mustbe(TK.PRINT);
        gcprint("printf(\"%d\\n\", ");
        expression();
        gcprint(");");
    }

    private void ifproc(){
        mustbe(TK.IF);
        // breakIFCheck = 1;

        guarded_commands(TK.IF);
        mustbe(TK.FI);
        // if(breakCheck==1){ //Still in the FOR loop
        //     while (!is(TK.AF)){
        //         scan();
        //     }
        // }
        //  breakIFCheck = 0; //Out of IF loop

    }

    private void labelCreate(){
        String temp;
        temp = Integer.toString(breakCheck);
        labelUnique = labelUnique + "s";
        temp = temp + labelUnique;
        String label = "label" + temp;
        labelStack.push(label);
    }


    private void doproc(){
        mustbe(TK.DO);
        breakCheck++;
        labelCreate();
        gcprint("while(1){");
        guarded_commands(TK.DO);
        gcprint("}");
        mustbe(TK.OD);
        breakCheck--;
        numCheck--;
        if (numCheck == 0){ //Only if numCheck is valid we can place the label

            String endLabel = labelStack.pop();
            gcprint(endLabel+":");

        }
    }

    private void fa(){
        mustbe(TK.FA);
        breakCheck++;
        labelCreate();
        gcprint("for(");
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
        numCheck--;
        if (numCheck == 0 ){ //Only if numCheck is valid we can place the label
            String endLabel = labelStack.pop();
            gcprint(endLabel+":");

        }
        breakCheck--; //This means if we see a break it's outside of the loop
    }

    private void breakproc(){
        if(is(TK.BREAK)){
            if (breakCheck >0){ //This means it's inside the FOR loop
                int ignored = 0;

                scan(); //Scan next line for the number 
                if (is(TK.NUM)){
                    int num = Integer.parseInt(tok.string);
                    if(num <= breakCheck && num!=0){ //If it's valid
                        baffle = labelStack.peek();
                        gcprint("goto "+ baffle + ";");
                        numCheck = num; //ex. 2 

                    }
                    if (num == 0){ //always ignore 0's
                        System.err.println("warning: on line "+ tok.lineNumber + " break " + tok.string + " statement ignored" );
                        ignored = 1;
                    }

                    else{
                        if (breakCheck < num){
                            System.err.println("warning: on line " + tok.lineNumber + " break statement exceeding loop nesting ignored");
                            ignored = 1;
                        }

                    }
                    scan();
                }
                if(ignored == 0 ){
                    gcprint("break;");
                }
                if(!(is(TK.FA)) && !(is(TK.FI)) && !(is(TK.DO)) && ignored == 0){ //If it's neither then it's an expression so output warning
                    System.err.println("warning: on line "+ tok.lineNumber + " statement(s) follows break statement" );
                    statement_list();
                }
            }

            else if (breakCheck <1) { //Outside loop so just output warning
                System.err.println("warning: on line "+ tok.lineNumber + " break statement outside of loop ignored");
                scan();
            }
        }
    }

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
        while(  is(TK.TIMES) || is(TK.DIVIDE) || is (TK.REMAINDER)) {
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

        else if( is(TK.MODULO)){ // mod(a,b) ==>
            gcprint("mod(");
            scan();
            mustbe(TK.LPAREN);
            expression();
            mustbe(TK.COMMA);
            gcprint(",");
            expression();
            mustbe(TK.RPAREN);
            gcprint(")");

        }
        else if( is(TK.MAX)){
            gcprint("max(");
            scan();
            if(is(TK.LPAREN)){
                nest++;
                //   System.out.println("nest" + nest);
            }
            mustbe(TK.LPAREN);

            expression();
            mustbe(TK.COMMA);
            gcprint(",");

            expression();
            mustbe(TK.RPAREN);
            if(is(TK.RPAREN)){
                if(nest>5 && nestChecked == 0){
                    System.err.println("can't parse: line "+ tok.lineNumber + " max expressions nested too deeply (> 5 deep)");
                    System.exit(1);
                }
                nestChecked = 1;
            }
            gcprint(")");

        }
        else if( is(TK.SQRT) ) {
            gcprint("esqrt(");
            scan();
            expression();
            gcprint(")");
        }
        else if( is(TK.REMAINDER) ) {
            gcprint("eRemainder(");
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
                + tok.lineNumber + " " + msg );
        System.exit(1);
    }
}