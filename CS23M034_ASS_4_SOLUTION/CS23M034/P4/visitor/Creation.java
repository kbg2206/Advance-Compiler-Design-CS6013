//
// Generated by JTB 1.3.2
//

package visitor;
import syntaxtree.*;
import java.util.*;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class Creation<R,A> implements GJVisitor<R,A> {
   //
   // Auto class visitors--probably don't need to be overridden.
   //
   public Map<String, Map<Integer, Vector<String>>> Graph = new HashMap<>();
   public Map<String, Map<String, Vector<String>>> Function_info = new HashMap<>();
   public Map<String, Map<String, Map<String, Vector<String>>>> Method_var = new HashMap<>();
   public Map<String, String> InfoInline = new HashMap<>();
   Map<String, String> InfoVariable = new HashMap<>();
   Set<String> st = new LinkedHashSet<>();
   Vector<String> argList = new Vector<>();
   public String ans = "";
   int ctr = 0;
   String ClassName = "";
   String MethodName = "";
   Map<String, String> VarInfo = new HashMap<>();
   public R visit(NodeList n, A argu) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeListOptional n, A argu) {
      if ( n.present() ) {
         R _ret=null;
         int _count=0;
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this,argu);
            _count++;
         }
         return _ret;
      }
      else
         return null;
   }

   public R visit(NodeOptional n, A argu) {
      if ( n.present() )
         return n.node.accept(this,argu);
      else
         return null;
   }

   public R visit(NodeSequence n, A argu) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeToken n, A argu) { return null; }

   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> MainClass()
    * f1 -> ( TypeDeclaration() )*
    * f2 -> <EOF>
    */
   public R visit(Goal n, A argu) {
      R _ret = null;
      Test Il = (Test) (argu);
      Graph = Il.Graph;
      Function_info = Il.Function_info;
      InfoInline = Il.InfoInline;
      Method_var = Il.Method_var;
      InfoVariable = Il.InfoVariable;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "{"
    * f3 -> "public"
    * f4 -> "static"
    * f5 -> "void"
    * f6 -> "main"
    * f7 -> "("
    * f8 -> "String"
    * f9 -> "["
    * f10 -> "]"
    * f11 -> Identifier()
    * f12 -> ")"
    * f13 -> "{"
    * f14 -> ( VarDeclaration() )*
    * f15 -> ( Statement() )*
    * f16 -> "}"
    * f17 -> "}"
    */
   public R visit(MainClass n, A argu) {
      R _ret = null;
      ans += "class " + n.f1.f0.toString() + "\n{\n "+"    "+"public static void main (String[] " + n.f11.f0.toString()
            + ")\n{\n";
      ClassName = n.f1.f0.toString();
      MethodName = "main";
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
      n.f8.accept(this, argu);
      n.f9.accept(this, argu);
      n.f10.accept(this, argu);
      n.f11.accept(this, argu);
      n.f12.accept(this, argu);
      n.f13.accept(this, argu);
      n.f14.accept(this, argu);

      
      
      
      String node = ClassName + "_" + MethodName;
      if (Graph.containsKey(node))
      {
         for(Integer c : Graph.get(node).keySet())
         {
            Vector<String> x = Graph.get(node).get(c);
            if (!(x.size() > 1 || InfoInline.containsKey(x.get(0)) == false)) {
               st.add(x.get(0));
            }
         }
         for (String key : st)
         {
            ans += ""+InfoVariable.get(key);
         }

         for(String key:st)
         {
            String[] x = key.split("_");
            Vector<String> dummy = Function_info.get(x[0]).get(x[1]);
            for(int i=1;i<dummy.size();++i)
            {
               ans += ""+Method_var.get(x[0]).get(x[1]).get(dummy.get(i)).get(0) + " "
                     + Method_var.get(x[0]).get(x[1]).get(dummy.get(i)).get(1) + " ;\n";
            }

            ans += "    "+x[0] + " this" + x[0] + " ;\n";
         }
      }
      
      n.f15.accept(this, argu);
      n.f16.accept(this, argu);
      n.f17.accept(this, argu);
      VarInfo.clear();
      ans += "\n}\n}\n";
      return _ret;
   }

   /**
    * f0 -> ClassDeclaration()
    *       | ClassExtendsDeclaration()
    */
   public R visit(TypeDeclaration n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "{"
    * f3 -> ( VarDeclaration() )*
    * f4 -> ( MethodDeclaration() )*
    * f5 -> "}"
    */
   public R visit(ClassDeclaration n, A argu) {
      R _ret = null;
      ans += "\nclass " + n.f1.f0.toString() + "\n{\n";
      ClassName = n.f1.f0.toString();
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      ans += "\n}\n";
      return _ret;
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "extends"
    * f3 -> Identifier()
    * f4 -> "{"
    * f5 -> ( VarDeclaration() )*
    * f6 -> ( MethodDeclaration() )*
    * f7 -> "}"
    */
   public R visit(ClassExtendsDeclaration n, A argu) {
      R _ret = null;
      ans += "class " + n.f1.f0.toString() + " extends " + n.f3.f0.toString() + "\n{\n";
      ClassName = n.f1.f0.toString();
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);



      
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
      ans+="\n}\n";
     return _ret;
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    * f2 -> ";"
    */
   public R visit(VarDeclaration n, A argu) {
      R _ret=null;
      _ret = n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      ans += "    "+_ret.toString() + " " + n.f1.f0.toString() + " ;\n";

      return _ret;
   }

   /**
    * f0 -> "public"
    * f1 -> Type()
    * f2 -> Identifier()
    * f3 -> "("
    * f4 -> ( FormalParameterList() )?
    * f5 -> ")"
    * f6 -> "{"
    * f7 -> ( VarDeclaration() )*
    * f8 -> ( Statement() )*
    * f9 -> "return"
    * f10 -> Identifier()
    * f11 -> ";"
    * f12 -> "}"
    */
   public R visit(MethodDeclaration n, A argu) {
      R _ret = null;
      ans += "    public ";
      ctr = 0;
      MethodName = n.f2.f0.toString();
      n.f0.accept(this, argu);
      _ret = n.f1.accept(this, argu);
      ans += _ret.toString()+" "+n.f2.f0.toString()+" (";
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      ans += " )\n{\n";
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
      
      
      
      String node = ClassName + "_" + MethodName;
      if (Graph.containsKey(node))
      {
         for(Integer c : Graph.get(node).keySet())
         {
            Vector<String> x = Graph.get(node).get(c);
            if (!(x.size() > 1 || InfoInline.containsKey(x.get(0)) == false)) {
               st.add(x.get(0));
            }
         }
         for (String key : st)
         {
            ans += "    "+InfoVariable.get(key);
         }

         for(String key:st)
         {
            String[] x = key.split("_");
            Vector<String> dummy = Function_info.get(x[0]).get(x[1]);
            for(int i=1;i<dummy.size();++i)
            {
               ans += "    "+Method_var.get(x[0]).get(x[1]).get(dummy.get(i)).get(0) + " "
                     + Method_var.get(x[0]).get(x[1]).get(dummy.get(i)).get(1) + " ;\n";
            }

            ans += "    "+x[0] + " this" + x[0] + " ;\n";
         }
      }

      
      
      
      

      
      n.f8.accept(this, argu);
      n.f9.accept(this, argu);
      n.f10.accept(this, argu);
      n.f11.accept(this, argu);
      n.f12.accept(this, argu);
      ans += "    return " + n.f10.f0.toString() + " ;\n";
      ans += "\n}\n";
      ctr = 0;
      st.clear();
      return _ret;
   }

   /**
    * f0 -> FormalParameter()
    * f1 -> ( FormalParameterRest() )*
    */
   public R visit(FormalParameterList n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    */
   public R visit(FormalParameter n, A argu) {
      R _ret=null;
      _ret = n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      ans+=_ret.toString()+" "+n.f1.f0.toString();
      
      return _ret;
   }

   /**
    * f0 -> ","
    * f1 -> FormalParameter()
    */
   public R visit(FormalParameterRest n, A argu) {
      R _ret = null;
      ans += ",";
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> ArrayType()
    *       | BooleanType()
    *       | IntegerType()
    *       | Identifier()
    */
   public R visit(Type n, A argu) {
      R _ret=null;
      _ret = n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "int"
    * f1 -> "["
    * f2 -> "]"
    */
   public R visit(ArrayType n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      _ret = (R) ("int[]");
      return _ret;
   }

   /**
    * f0 -> "boolean"
    */
   public R visit(BooleanType n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      _ret = (R) ("boolean");
      return _ret;
   }

   /**
    * f0 -> "int"
    */
   public R visit(IntegerType n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      _ret = (R)("int");
      return _ret;
   }

   /**
    * f0 -> Block()
    *       | AssignmentStatement()
    *       | ArrayAssignmentStatement()
    *       | FieldAssignmentStatement()
    *       | IfStatement()
    *       | WhileStatement()
    *       | ForStatement()
    *       | PrintStatement()
    *       | MessageSendStatement()
    */
   public R visit(Statement n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "{"
    * f1 -> ( Statement() )*
    * f2 -> "}"
    */
   public R visit(Block n, A argu) {
      R _ret = null;
      ans += "{\n";
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      ans += "}\n";
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "="
    * f2 -> RhsExpression()
    * f3 -> ";"
    */
   public R visit(AssignmentStatement n, A argu) {
      R _ret = null;
      ctr++;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      _ret = n.f2.accept(this, argu);
      n.f3.accept(this, argu);

      ans += "    "+n.f0.f0.toString() + " = " + _ret.toString() + " ;\n";
      return _ret;
   }

   /**
    * f0 -> DotExpression()
    *       | Expression()
    */
   public R visit(RhsExpression n, A argu) {
      R _ret=null;
      _ret = n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "["
    * f2 -> Identifier()
    * f3 -> "]"
    * f4 -> "="
    * f5 -> Identifier()
    * f6 -> ";"
    */
   public R visit(ArrayAssignmentStatement n, A argu) {
      R _ret = null;
       ctr++;
      ans += "    "+n.f0.f0.toString() + " [" + n.f2.f0.toString() + "] = " + n.f5.toString() + " ;\n";
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "."
    * f2 -> Identifier()
    * f3 -> "="
    * f4 -> Identifier()
    * f5 -> ";"
    */
   public R visit(FieldAssignmentStatement n, A argu) {
      R _ret = null;
       ctr++;
      ans += "    "+n.f0.f0.toString() + "." + n.f2.f0.toString() + " = " + n.f4.f0.toString() + " ;\n";
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "if"
    * f1 -> "("
    * f2 -> Identifier()
    * f3 -> ")"
    * f4 -> Statement()
    * f5 -> "else"
    * f6 -> Statement()
    */
   public R visit(IfStatement n, A argu) {
      R _ret = null;
       ctr++;
      ans += "    "+"if(" + n.f2.f0.toString() + ")\n";
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      ans += "    "+"else\n";
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "while"
    * f1 -> "("
    * f2 -> Identifier()
    * f3 -> ")"
    * f4 -> Statement()
    */
   public R visit(WhileStatement n, A argu) {
      R _ret = null;
       ctr++;
      ans +="    "+ "while(" + n.f2.f0.toString() + ")\n";
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "for"
    * f1 -> "("
    * f2 -> Identifier()
    * f3 -> "="
    * f4 -> Expression()
    * f5 -> ";"
    * f6 -> Expression()
    * f7 -> ";"
    * f8 -> Identifier()
    * f9 -> "="
    * f10 -> Expression()
    * f11 -> ")"
    * f12 -> Statement()
    */
   public R visit(ForStatement n, A argu) {
      R _ret = null;
       ctr++;
      R r1, r2, r3;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      r1 = n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      r2 = n.f6.accept(this, argu);
      n.f7.accept(this, argu);
      n.f8.accept(this, argu);
      n.f9.accept(this, argu);
      r3 = n.f10.accept(this, argu);
      n.f11.accept(this, argu);
      n.f12.accept(this, argu);

      ans += "    "+"for(" + n.f2.f0.toString() + " = " + r1.toString() + " ; " + r2.toString() + " ; "+n.f8.f0.toString()+" = "+r3.toString()+")\n";
      return _ret;
   }

   /**
    * f0 -> "System.out.println"
    * f1 -> "("
    * f2 -> Identifier()
    * f3 -> ")"
    * f4 -> ";"
    */
   public R visit(PrintStatement n, A argu) {
      R _ret = null;
       ctr++;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      ans += "    "+"System.out.println(" + n.f2.f0.toString() + ") ;\n";
      return _ret;
   }

   /**
    * f0 -> ( InlineAnn() )?
    * f1 -> ( VoidMessageSendStmt() | RetMessageSendStmt() )
    */
   public R visit(MessageSendStatement n, A argu) {
      R _ret = null;
       ctr++;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> MessageSend()
    * f1 -> ";"
    */
   public R visit(VoidMessageSendStmt n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "="
    * f2 -> MessageSend()
    * f3 -> ";"
    */
   public R visit(RetMessageSendStmt n, A argu) {
      R _ret = null;
      
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      _ret = n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      ans += "    "+n.f0.f0.toString() + " = " + _ret.toString() + " ;\n";

      return _ret;
   }

   /**
    * f0 -> <SCOMMENT1>
    * f1 -> <INLINE>
    * f2 -> <SCOMMENT2>
    */
   public R visit(InlineAnn n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> AndExpression()
    *       | CompareExpression()
    *       | PlusExpression()
    *       | MinusExpression()
    *       | TimesExpression()
    *       | ArrayLookup()
    *       | ArrayLength()
    *       | PrimaryExpression()
    */
   public R visit(Expression n, A argu) {
      R _ret=null;
      _ret = n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "&"
    * f2 -> Identifier()
    */
   public R visit(AndExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      String s = n.f0.f0.toString() + "&" + n.f2.f0.toString();
      _ret = (R) (s);
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "<"
    * f2 -> Identifier()
    */
   public R visit(CompareExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      String s = n.f0.f0.toString() + "<" + n.f2.f0.toString();
      _ret = (R) (s);
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "+"
    * f2 -> Identifier()
    */
   public R visit(PlusExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      String s = n.f0.f0.toString() + "+" + n.f2.f0.toString();
      _ret = (R) (s);
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "-"
    * f2 -> Identifier()
    */
   public R visit(MinusExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      String s = n.f0.f0.toString() + "-" + n.f2.f0.toString();
      _ret = (R) (s);
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "*"
    * f2 -> Identifier()
    */
   public R visit(TimesExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      String s = n.f0.f0.toString() + "*" + n.f2.f0.toString();
      _ret = (R) (s);
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "["
    * f2 -> Identifier()
    * f3 -> "]"
    */
   public R visit(ArrayLookup n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      String s = n.f0.f0.toString() + "[" + n.f2.f0.toString()+"]";
      _ret = (R) (s);
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "."
    * f2 -> "length"
    */
   public R visit(ArrayLength n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      String s = n.f0.f0.toString() + ".length" ;
      _ret = (R) (s);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "."
    * f2 -> Identifier()
    * f3 -> "("
    * f4 -> ( ArgList() )?
    * f5 -> ")"
    */
   public R visit(MessageSend n, A argu) {
      R _ret = null,r1;

      String node = ClassName + "_" + MethodName;
      Vector<String> dummy = Graph.get(node).get(ctr);
      if (dummy.size() > 1 || InfoInline.containsKey(dummy.get(0)) == false)
      {
         r1 = n.f0.accept(this, argu);
         n.f1.accept(this, argu);
         n.f2.accept(this, argu);
         n.f3.accept(this, argu);
         n.f4.accept(this, argu);
         String s = r1.toString() + "." + n.f2.f0.toString() + "(" ;
         for (int i = 0; i < argList.size() - 1; ++i) {
            s += argList.get(0) + ",";
         }
         if (argList.size() >= 1)
            s += argList.get(argList.size() - 1);
         n.f5.accept(this, argu);
         s+= ")";
         _ret = (R) (s);

      }
      else
      {
         n.f4.accept(this, argu);
         String[] x = dummy.get(0).split("_");
         Vector<String> y = Function_info.get(x[0]).get(x[1]);
         //System.out.println(y.size() + " " + argList.size() + " " + x[0] + " " + x[1]);
         for (int i = 1; i < y.size(); ++i) {
            ans += ""+Method_var.get(x[0]).get(x[1]).get(y.get(i)).get(1) + " = " + argList.get(i-1) + " ;\n";
         }
         ans += InfoInline.get(dummy.get(0));
         _ret = (R)"ans";
         
      }
      argList.clear();



      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> ( ArgRest() )*
    */
   public R visit(ArgList n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      argList.add(n.f0.f0.toString());
      return _ret;
   }

   /**
    * f0 -> ","
    * f1 -> Identifier()
    */
   public R visit(ArgRest n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      argList.add(n.f1.f0.toString());
      return _ret;
   }

   /**
    * f0 -> IntegerLiteral()
    *       | TrueLiteral()
    *       | FalseLiteral()
    *       | Identifier()
    *       | ThisExpression()
    *       | ArrayAllocationExpression()
    *       | AllocationExpression()
    *       | NotExpression()
    */
   public R visit(PrimaryExpression n, A argu) {
      R _ret=null;
      _ret = n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> <INTEGER_LITERAL>
    */
   public R visit(IntegerLiteral n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      _ret = (R) (n.f0.toString());
      return _ret;
   }

   /**
    * f0 -> "true"
    */
   public R visit(TrueLiteral n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      _ret = (R) (n.f0.toString());
      return _ret;
   }

   /**
    * f0 -> "false"
    */
   public R visit(FalseLiteral n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      _ret = (R) (n.f0.toString());
      return _ret;
   }

   /**
    * f0 -> <IDENTIFIER>
    */
   public R visit(Identifier n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      _ret = (R) (n.f0.toString());
      return _ret;
   }

   /**
    * f0 -> "this"
    */
   public R visit(ThisExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      _ret = (R) (n.f0.toString());
      return _ret;
   }

   /**
    * f0 -> "new"
    * f1 -> "int"
    * f2 -> "["
    * f3 -> Identifier()
    * f4 -> "]"
    */
   public R visit(ArrayAllocationExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      String s = "new int [" + n.f3.f0.toString() + "]";
      _ret = (R)(s);
      return _ret;
   }

   /**
    * f0 -> "new"
    * f1 -> Identifier()
    * f2 -> "("
    * f3 -> ")"
    */
   public R visit(AllocationExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      String s = "new " + n.f1.f0.toString() + "()";
      _ret = (R)(s);
      return _ret;
   }

   /**
    * f0 -> "!"
    * f1 -> Identifier()
    */
   public R visit(NotExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      _ret = (R) ("!" + n.f1.f0.toString());
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "."
    * f2 -> Identifier()
    */
   public R visit(DotExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      _ret = (R) (n.f0.f0.toString() + "." + n.f2.f0.toString());

      return _ret;
   }

}
