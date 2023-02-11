package comv2.aunwesha.lfutil;

import java.io.Serializable;


public class Quadruple <T1, T2, T3, T4> implements Serializable{

    private static final long serialVersionUID = 4286033139179995529L;
    T1 first;
    T2 second;
    T3 third;
    T4 fourth;
    
    public Quadruple() {
        this.first = null;
        this.second = null;
        this.third = null;
        this.fourth = null;
    }

    public Quadruple(final T1 first, final T2 second, final T3 third, final T4 fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    public T1 getFirst() {
        return this.first;
    }
    
    public void setFirst(final T1 first) {
        this.first = first;
    }
    
    public T2 getSecond() {
        return this.second;
    }
    
    public void setSecond(final T2 second) {
        this.second = second;
    }

    public T3 getThird() {
        return this.third;
    }
    
    public void setThird(final T3 third) {
        this.third = third;
    }

    public T4 getFourth() {
        return this.fourth;
    }
    
    public void setFourth(final T4 fourth) {
        this.fourth = fourth;
    }

}
