package main.java.datastructureproject.init;

public class StringList {

    private String[] list;
    private Integer i;
    private Integer size;


    /**
     * My own data structure to replace arraylist and in a sense hashset as well. 
     * Methods are self explanatory so won't be going deeper in to those here.
     */
    public StringList() {
        this.size = 1000;
        this.list = new String[size];
        this.i = 0;
    }


    public void addUnique(String s) {
        if (contains(s) == -1) {
            add(s);
        }
    }

    public void add(String s) {
        if (this.i >= size) {
            String[] temp = new String[size * 2];
            for (int j = 0; j < this.size; j++) {
                temp[j] = this.list[j];
            }
            this.list = temp;
            this.size = this.size * 2;
        }
        this.list[i] = s;
        this.i++;
    }

    public String get(Integer j) {
        return this.list[j];
    }

    public int size() {
        return i;
    }

    public void addAll(StringList toBeAdded) {
        for (int j = 0; j < toBeAdded.size(); j++) {
            add(toBeAdded.get(j));
        }
    }

    public Integer contains(String s) {
        for (int j = 0; j < i; j++) {
            if (get(j).equals(s)) {
                return 1;
            }
        }
        return -1;
    }

    public String[] returnStringList() {
        return this.list;
    }
}
