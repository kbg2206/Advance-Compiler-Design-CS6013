import syntaxtree.*;
import visitor.*;

public class P4 {
   public static void main(String [] args) {
      try {
         new FunkyTacoJavaParser(System.in);
         Node root = FunkyTacoJavaParser.Goal();
         //System.out.println("Program parsed successfully");
         Information x = new Information<>();
         root.accept(x, ""); // Your assignment part is invoked here.
         CallGraph y = new CallGraph<>();
         root.accept(y, x);
         Test t = new Test<>();
         root.accept(t, y);
         //Inline z = new Inline<>();
         //root.accept(z, y);
         Creat w = new Creat<>();
         root.accept(w, t);
         System.out.println(w.ans);
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
} 



