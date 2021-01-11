 
/* 
Edoardo Di Paolo

JML Exercise: 
   
   This class implements a MultiSet of integers, using an array.
 
   Add JML specs to this class, and if necessary modify the code, to stop openJML from complaining.
 
 
   The only JML keywords needed are
      requires
      invariant 
      ensures 
  
   If you want, you can also use
      non_null
   as abbreviation.
 
 
   While developing your specs, it may be useful to use the keyword
      assert
   to add additional assertions in source code, to find out what 
   JML can - or cannot - prove at a given program point.
  
*/
import java.lang.Math;
 
class MultiSet {
 
    
    //public int[] elements = new int[Integer.MAX_VALUE]; //@ invariant elements != null; 
    int[] elements;
    int n;
    
    /*@ 
      requires input != null; 
    @*/
    MultiSet(int[] input) {
      if(input != null) {
        n = input.length;
        elements = new int[n];
        arraycopy(input, 0, elements, 0, n);
      }
    }
 
    MultiSet() {
      n = 0;
      elements = new int[0];
    }
 
    void removeOnce(int elt) {
      if(elements != null && n <= elements.length && n > 0) {
        /*@ loop_invariant i <= elements.length && n <= elements.length && i >= 0; @*/
        for(int i = 0; i < n; i++) {
          if(elements[i] == elt) {
            n--;
            elements[i] = elements[n]; 
            return;
          }
        }
      }
    }
 
 
    void removeAll(int elt)  {
      if(elements != null && n <= elements.length && n > 0) {
        /*@ loop_invariant i <= elements.length && n <= elements.length && i >= 0; @*/
        for(int i = 0; i < n; i++) {
          if(elements[i] == elt) {
            n--;
            elements[i] = elements[n]; 
          }
        }
      }
      return;
    }
 
 
    int getCount(int elt) {
      int count = 0;
      
      if(elements != null && n <= elements.length && n > 0) {
        /*@ loop_invariant i <= elements.length && n <= elements.length && i >= 0; @*/
        for(int i = 0; i < n; i++) {
          if(elements[i] == elt && count < Integer.MAX_VALUE) {
            count++;
          }
        }
      }
 
      return count;
    }
 
    //@ requires Integer.MAX_VALUE > 2 * n + 1;
    void add(int elt) {
        if(elements != null && n == elements.length) {
          int[] new_elements = new int[2 * n + 1];
          arraycopy(elements, 0, new_elements, 0, n);
          elements = new_elements;
        }
 
        if(n >= 0 && n < elements.length) {
          elements[n] = elt;
          n++;
        }
    }
 
  
    void add(MultiSet b) {
      if(b == null)
        return;
 
      int b_length = b.n;
      int[] b_elements = b.elements;
      if(b_elements == null || elements == null)
        return;
 
      if(n < 0 || b_length < 0 || n > elements.length || b_length > b_elements.length)
        return;
 
      if(n == Integer.MAX_VALUE || b_length == Integer.MAX_VALUE)
        return;
 
      int size = Math.addExact(n, b_length);
      if(size > -1) {
        int[] new_elements = new int[size];
        if(new_elements.length > Integer.MAX_VALUE || new_elements.length < 0)
          return;
 
        arraycopy(elements, 0, new_elements, 0, n);
        arraycopy(b.elements, 0, new_elements, Math.addExact(n, 1), b.n);
        elements = new_elements;
      }
    }
 
 
    void add(int[] a) {
      if(a != null) {
        this.add(new MultiSet(a));
      }
    }
 
    MultiSet(MultiSet b) {
      if(b != null)
        this.add(b);
    }
 
    
 
    private void arraycopy(int[] src, int srcOff, int[] dest, int destOff, int length) {
      int srclength = Math.addExact(srcOff, length);
      int destofflength = Math.addExact(destOff, length);
      if(src != null && dest != null && srcOff > 0 && destOff > 0 && srclength > -1 && srclength <= src.length && destofflength > -1 && destofflength<= dest.length) {
        for(int i = 0; i < length; i++) {
          int ck = Math.addExact(destOff, i);
          int sk = Math.addExact(srcOff, i);
          if(ck < 0 || sk < 0 || ck >= dest.length || sk >= src.length)
            return;
          dest[ck] = src[sk];
        }
      }
    }
}