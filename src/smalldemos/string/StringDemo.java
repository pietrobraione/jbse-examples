package smalldemos.string;

public class StringDemo {
    String third;
    
    public void entryPoint(String first, int second) {
        this.third = first + second;
    }
    /* two leaves:
       1- first != null (&& first.value != null && first.value.length >= 0) : 
          this.third.equals(first + "{V4}")
       2- first == null : this.third.equals("null{V4}")
       (and yes, it's quite ugly that the conversion of the symbolic value
       of second, {V4}, to string is "{V4}", but currently we do not have
       a better way.)
    */
}
