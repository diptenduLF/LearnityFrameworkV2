package comv2.aunwesha.lfutil;

import java.io.Serializable;

public class Triple <T1, T2, T3> implements Serializable{

    private static final long serialVersionUID = 4271023139179995529L;
    T1 first;
    T2 second;
    T3 third;
    
    public Triple() {
        this.first = null;
        this.second = null;
        this.third = null;
    }

    public Triple(final T1 first, final T2 second, final T3 third) {
        this.first = first;
        this.second = second;
        this.third = third;
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

}
