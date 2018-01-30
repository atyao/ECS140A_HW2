class AU {
    private int linenumber;     // line on which referenced occurred
    private int count;          // number of times referenced on linenumber
    public AU(int linenumber) {
        this.linenumber = linenumber;
        this.count = 1;
    }
    public boolean update(int linenumber) {
        if (this.linenumber == linenumber) {
            this.count++;
            return true;
        }
        return false;
    }
    public String toString() {
        return linenumber + ((count > 1)?"("+count+")":"");
    }
}
