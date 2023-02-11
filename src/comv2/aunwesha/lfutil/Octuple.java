package comv2.aunwesha.lfutil;

import java.io.Serializable;

public class Octuple <T1, T2, T3, T4, T5, T6, T7, T8> implements Serializable {
	private static final long serialVersionUID = 4286033139179995529L;
    T1 first;
    T2 second;
    T3 third;
    T4 fourth;
    T5 fifth;
    T6 sixth;
    T7 seventh;
    T8 eighth;
    
    public Octuple() {
        this.first = null;
        this.second = null;
        this.third = null;
        this.fourth = null;
        this.fifth = null;
        this.sixth = null;
        this.seventh = null;
        this.eighth = null;
    }
    
    public Octuple(final T1 first, final T2 second, final T3 third, final T4 fourth, final T5 fifth, final T6 sixth, final T7 seventh, final T8 eighth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
        this.seventh = seventh;
        this.eighth = eighth;
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
    
    public T5 getFifth() {
        return this.fifth;
    }
    
    public void setFifth(final T5 fifth) {
        this.fifth = fifth;
    }
    
    public T6 getSixth() {
        return this.sixth;
    }
    
    public void setSixth(final T6 sixth) {
        this.sixth = sixth;
    }
    
    public T7 getSeventh() {
        return this.seventh;
    }
    
    public void setSeventh(final T7 seventh) {
        this.seventh = seventh;
    }
    
    public T8 getEighth() {
        return this.eighth;
    }
    
    public void setEighth(final T8 eighth) {
        this.eighth = eighth;
    }

}
