package visitor;
import syntaxtree.*;

import java.util.*;



public class Test_depth implements Visitor{

   public boolean ans=false; 
   private Stack<Map<String, Boolean>> scopeStack = new Stack<>();
   private Stack<Map<String,Boolean>> elseStack = new Stack<>();
   private Boolean methodFlag=false;
   private String[] currectBlockAccess =new String[1000];
   private int ctr=0; 
   private Stack<String> prev = new Stack<>();
   
   private String currentBlockType="";

   public void visit(NodeList n) {
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); )
         e.nextElement().accept(this);
   }

   public void visit(NodeListOptional n) {
      if ( n.present() )
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); )
            e.nextElement().accept(this);
   }

   public void visit(NodeOptional n) {
      if ( n.present() )
         n.node.accept(this);
   }

   public void visit(NodeSequence n) {
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); )
         e.nextElement().accept(this);
   }

   public void visit(NodeToken n) { }

   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> MainClass()
    * f1 -> ( TypeDeclaration() )*
    * f2 -> <EOF>
    */
   public void visit(Goal n) {
      //System.out.println("Goal");
      // prev.push("");
      // prev.push("");
      // prev.push("");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
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
    * f14 -> PrintStatement()
    * f15 -> "}"
    * f16 -> "}"
    */
   public void visit(MainClass n) {
      //System.out.println("Mainclass");
      scopeStack.push(new HashMap<>());
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      n.f5.accept(this);
      n.f6.accept(this);
      n.f7.accept(this);
      n.f8.accept(this);
      n.f9.accept(this);
      n.f10.accept(this);
      n.f11.accept(this);
      n.f12.accept(this);
      n.f13.accept(this);
      n.f14.accept(this);
      n.f15.accept(this);
      n.f16.accept(this);
      scopeStack.pop();
   }

   /**
    * f0 -> ClassDeclaration()
    *       | ClassExtendsDeclaration()
    */
   public void visit(TypeDeclaration n) {
      //System.out.println("TypeDeclaration");
      scopeStack.push(new HashMap<>());
      n.f0.accept(this);
      scopeStack.pop();
      //System.out.println();

   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "{"
    * f3 -> ( VarDeclaration() )*
    * f4 -> ( MethodDeclaration() )*
    * f5 -> "}"
    */
   public void visit(ClassDeclaration n) {
      //System.out.println("classdeclaration");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      // System.out.println("Class");
      // for(String key :scopeStack.peek().keySet())
      // {
      //    System.out.println(key + " : " + scopeStack.peek().get(key));
      // }
      n.f4.accept(this);
      n.f5.accept(this);
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
   public void visit(ClassExtendsDeclaration n) {
      //System.out.println("classextenddeclaration");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      n.f5.accept(this);
      n.f6.accept(this);
      n.f7.accept(this);
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    * f2 -> ";"
    */
   public void visit(VarDeclaration n) {
     // System.out.println("varDeclaration");
      n.f0.accept(this);

      String varIdentifier=n.f1.f0.toString();
      if(methodFlag==false)
      {
         scopeStack.peek().put(varIdentifier,true);
      }
      else
      {
         scopeStack.peek().put(varIdentifier,false);
      }
      
      
      n.f1.accept(this);
      n.f2.accept(this);
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
    * f10 -> Expression()
    * f11 -> ";"
    * f12 -> "}"
    */
   public void visit(MethodDeclaration n) {
      //System.out.println("MethodDeclaration");
      methodFlag=true;
      Map<String,Boolean> methodScope = new HashMap<>();
      
      for(String key :scopeStack.peek().keySet())
      {
         methodScope.put(key,scopeStack.peek().get(key));
      }
      scopeStack.push(methodScope);
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      n.f5.accept(this);
      n.f6.accept(this);
      n.f7.accept(this);
      n.f8.accept(this);
      n.f9.accept(this);
      n.f10.accept(this);
      // System.out.println("method");
      // for(String key :scopeStack.peek().keySet())
      // {
      //    System.out.println(key + " : " + scopeStack.peek().get(key));
      // }
      n.f11.accept(this);
      n.f12.accept(this);
      methodFlag=false;
      
      scopeStack.pop();
      //System.out.println();
   }

   /**
    * f0 -> FormalParameter()
    * f1 -> ( FormalParameterRest() )*
    */
   public void visit(FormalParameterList n) {
      //System.out.println("FormalParameterList");

      n.f0.accept(this);
      n.f1.accept(this);
   

   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    */
   public void visit(FormalParameter n) {
      //System.out.println("FormalParameter");
      n.f0.accept(this);
      String parIdentifier=n.f1.f0.toString();
      Map<String,Boolean> parScope = new HashMap<>();
      
      for(String key :scopeStack.peek().keySet())
      {
         parScope.put(key,scopeStack.peek().get(key));
      }
      scopeStack.pop();
      parScope.put(parIdentifier,true);
      scopeStack.push(parScope);
      n.f1.accept(this);
   }

   /**
    * f0 -> ","
    * f1 -> FormalParameter()
    */
   public void visit(FormalParameterRest n) {
      //System.out.println("formalparameterREst");
      n.f0.accept(this);
      n.f1.accept(this);
   }

   /**
    * f0 -> ArrayType()
    *       | BooleanType()
    *       | IntegerType()
    *       | Identifier()
    */
   public void visit(Type n) {
      //System.out.println("type");
      n.f0.accept(this);
   }

   /**
    * f0 -> "int"
    * f1 -> "["
    * f2 -> "]"
    */
   public void visit(ArrayType n) {
      //System.out.println("arraytype");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
   }

   /**
    * f0 -> "boolean"
    */
   public void visit(BooleanType n) {
      //System.out.println("BooleanType");
      n.f0.accept(this);
   }

   /**
    * f0 -> "int"
    */
   public void visit(IntegerType n) {
      //System.out.println("IntegerType");
      n.f0.accept(this);
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
    */
   public void visit(Statement n) {
      //System.out.println("statement");
      n.f0.accept(this);
   }

   /**
    * f0 -> "{"
    * f1 -> ( Statement() )*
    * f2 -> "}"
    */
   

   public void visit(Block n) {
      //System.out.println("Block");
    


      //System.out.println(scopeStack.size());

      if(currentBlockType!="if")
      {
         Map<String,Boolean> blockScope = new HashMap<>();
         for(String key : scopeStack.peek().keySet())
         {
            blockScope.put(key,scopeStack.peek().get(key));
         }
         scopeStack.push(blockScope);
         n.f0.accept(this);
         n.f1.accept(this);
         n.f2.accept(this);
         if(currentBlockType=="")
         {
            Map<String,Boolean> temp = new HashMap<>();
            Map<String,Boolean> first =scopeStack.peek();
            scopeStack.pop();
            for(String key:scopeStack.peek().keySet())
            {
               if(first.containsKey(key))
               {
                  temp.put(key,first.get(key));
               }
            }
            scopeStack.pop();
            scopeStack.push(temp);
         }
         else{
            scopeStack.pop();
         }
         
      }
      else
      {
         n.f0.accept(this);
         n.f1.accept(this);
         n.f2.accept(this);
      }
      


      
   }


   
   
   /**
    * f0 -> Identifier()
    * f1 -> "="
    * f2 -> Expression()
    * f3 -> ";"
    */
   
   public void visit(AssignmentStatement n) {
      

      
      

     // System.out.println("Assignment statement");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      

      
      String assIdentifier=n.f0.f0.toString();
      //System.out.println(assIdentifier);
      if(ctr==0 || currectBlockAccess[ctr-1]=="if")
      {
         Map<String,Boolean> assignmentScope = new HashMap<>();

         for(String key : scopeStack.peek().keySet())
         {
            assignmentScope.put(key,scopeStack.peek().get(key));
         }
         
         assignmentScope.put(assIdentifier,true);
         //System.out.println(assignmentScope.get(assIdentifier));
         scopeStack.pop();
         scopeStack.push(assignmentScope);
         // for(Map<String,Boolean> m:scopeStack)
         // {
         //    for(String key:m.keySet())
         //    {
         //       System.out.println(key+" : "+m.get(key));
         //    }
         //    System.out.println();
         // }
      }
      else
      {
         //System.out.println("go");
         Map<String,Boolean> assignmentScope = new HashMap<>();

         for(String key : elseStack.peek().keySet())
         {
            assignmentScope.put(key,elseStack.peek().get(key));
         }
         
         assignmentScope.put(assIdentifier,true);
         //System.out.println(assignmentScope.get(assIdentifier));
         elseStack.pop();
         elseStack.push(assignmentScope);
         // for(Map<String,Boolean> m:elseStack)
         // {
         //    for(String key:m.keySet())
         //    {
         //       System.out.println(key+" : "+m.get(key));
         //    }
         //    System.out.println();
         // }
      }

      

  }

  

   /**
    * f0 -> Identifier()
    * f1 -> "["
    * f2 -> Expression()
    * f3 -> "]"
    * f4 -> "="
    * f5 -> Expression()
    * f6 -> ";"
    */
   public void visit(ArrayAssignmentStatement n) {
      //System.out.println("ArrayAssignmentstatement ");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      n.f5.accept(this);
      n.f6.accept(this);
   }

   /**
    * f0 -> Expression()
    * f1 -> "."
    * f2 -> Identifier()
    * f3 -> "="
    * f4 -> Expression()
    * f5 -> ";"
    */
   public void visit(FieldAssignmentStatement n) {
      //System.out.println("FieldAssignmentStatement");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      n.f5.accept(this);
   }

   /**
    * f0 -> "if"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    * f5 -> "else"
    * f6 -> Statement()
    */
   public void visit(IfStatement n) {
      //System.out.println("IfStatement");
      
      currectBlockAccess[ctr]="if";
      ctr++;

      prev.push(currentBlockType);
      currentBlockType="if";
      if(ctr<2 || currectBlockAccess[ctr-2]=="if")
      {
         Map<String,Boolean> temp = new HashMap<>();

         for(String key : scopeStack.peek().keySet())
         {
            temp.put(key,scopeStack.peek().get(key));
         }
         scopeStack.push(temp);
      }
      else
      {
         Map<String,Boolean> temp = new HashMap<>();

         for(String key : elseStack.peek().keySet())
         {
            temp.put(key,elseStack.peek().get(key));
         }
         scopeStack.push(temp);
      }
      
    
      
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);

      // for(String key:scopeStack.peek().keySet())
      // {
      //    System.out.println(key + " "+ scopeStack.peek().get(key));
      // }
      // System.out.println();

      currectBlockAccess[ctr]="else";
      ctr++;

      if(ctr<3 || currectBlockAccess[ctr-3]=="if")
      {
         Map<String,Boolean> temp = new HashMap<>();

         for(String key : scopeStack.get(scopeStack.size()-2).keySet())
         {
            temp.put(key,scopeStack.get(scopeStack.size()-2).get(key));
         }
         elseStack.push(temp);
      }
      else
      {
         Map<String,Boolean> temp = new HashMap<>();

         for(String key : elseStack.peek().keySet())
         {
            temp.put(key,elseStack.peek().get(key));
         }
         elseStack.push(temp);
      }

      n.f5.accept(this);
      n.f6.accept(this);

      Map<String,Boolean> first=scopeStack.peek();
      scopeStack.pop();
      ctr--;
      Map<String,Boolean> second=elseStack.peek();
      elseStack.pop();
      ctr--;


      // for(String key:second.keySet())
      // {
      //    System.out.println(key+ " " + second.get(key));
      // }

      //System.out.println(scopeStack.size());
      //System.out.println(elseStack.size());
      if(ctr==0 || currectBlockAccess[ctr-1]=="if")
      {
         //System.out.println(scopeStack.size());
         for(String key:first.keySet())
         {
            if(second.containsKey(key))
            scopeStack.peek().put(key,(first.get(key) & second.get(key)));
            //System.out.println("if " +key +" "+(first.get(key) & second.get(key)));
         }
      }
      else
      {
         for(String key:first.keySet())
         {
            if(second.containsKey(key))
            elseStack.peek().put(key,(first.get(key) & second.get(key)));
            //System.out.println("else "+key +" "+(first.get(key) & second.get(key)));
         }
      }

      
      currentBlockType=prev.peek();
      prev.pop();
   }

   /**
    * f0 -> "while"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    */
   public void visit(WhileStatement n) {
      //System.out.println("WhileStatement");
      prev.push(currentBlockType);
      currentBlockType="while";
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      currentBlockType=prev.peek();
      prev.pop();
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
   public void visit(ForStatement n) {
      //System.out.println("ForStatement");
      prev.push(currentBlockType);
      currentBlockType="for";
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      String assIdentifier=n.f2.f0.toString();
      if(ctr==0 || currectBlockAccess[ctr-1]=="if")
      {
         Map<String,Boolean> assignmentScope = new HashMap<>();

         for(String key : scopeStack.peek().keySet())
         {
            assignmentScope.put(key,scopeStack.peek().get(key));
         }
         
         assignmentScope.put(assIdentifier,true);
         //System.out.println(assignmentScope.get(assIdentifier));
         scopeStack.pop();
         scopeStack.push(assignmentScope);
      }
      else
      {
         //System.out.println("go");
         Map<String,Boolean> assignmentScope = new HashMap<>();

         for(String key : elseStack.peek().keySet())
         {
            assignmentScope.put(key,elseStack.peek().get(key));
         }
         
         assignmentScope.put(assIdentifier,true);
         //System.out.println(assignmentScope.get(assIdentifier));
         elseStack.pop();
         elseStack.push(assignmentScope);
      }

      n.f5.accept(this);
      n.f6.accept(this);
      n.f7.accept(this);
      n.f8.accept(this);
      n.f9.accept(this);
      n.f10.accept(this);
      n.f11.accept(this);
      n.f12.accept(this);
      currentBlockType=prev.peek();
      prev.pop();
   }

   /**
    * f0 -> "System.out.println"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> ";"
    */
   public void visit(PrintStatement n) {
      //System.out.println("PrintStatement");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
   }

   /**
    * f0 -> AndExpression()
    *       | CompareExpression()
    *       | PlusExpression()
    *       | MinusExpression()
    *       | TimesExpression()
    *       | ArrayLookup()
    *       | ArrayLength()
    *       | MessageSend()
    *       | PrimaryExpression()
    */
   public void visit(Expression n) {
      //System.out.println("Expression");
      n.f0.accept(this);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "&"
    * f2 -> PrimaryExpression()
    */
   public void visit(AndExpression n) {
      //System.out.println("AndExpression");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "<"
    * f2 -> PrimaryExpression()
    */
   public void visit(CompareExpression n) {
      //System.out.println("CompareExpression");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "+"
    * f2 -> PrimaryExpression()
    */
   public void visit(PlusExpression n) {
      //System.out.println("PlusExpression");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "-"
    * f2 -> PrimaryExpression()
    */
   public void visit(MinusExpression n) {
      //System.out.println("MinusExpression");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "*"
    * f2 -> PrimaryExpression()
    */
   public void visit(TimesExpression n) {
      //System.out.println("TimesExpression");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "["
    * f2 -> PrimaryExpression()
    * f3 -> "]"
    */
   public void visit(ArrayLookup n) {
      //System.out.println("ArrayLookup");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "."
    * f2 -> "length"
    */
   public void visit(ArrayLength n) {
      //System.out.println("ArrayLength");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "."
    * f2 -> Identifier()
    * f3 -> "("
    * f4 -> ( ExpressionList() )?
    * f5 -> ")"
    */
   public void visit(MessageSend n) {
      //System.out.println("MessageSend");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
      n.f5.accept(this);
   }

   /**
    * f0 -> Expression()
    * f1 -> ( ExpressionRest() )*
    */
   public void visit(ExpressionList n) {
      //System.out.println("ExpressionList");
      n.f0.accept(this);
      n.f1.accept(this);
   }

   /**
    * f0 -> ","
    * f1 -> Expression()
    */
   public void visit(ExpressionRest n) {
      //System.out.println("ExpressionRest");
      n.f0.accept(this);
      n.f1.accept(this);
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
    *       | BracketExpression()
    */
   public void visit(PrimaryExpression n) {
      //System.out.println("PrimaryExpression");
      if (n.f0.choice instanceof Identifier) {
         // If f0 is an Identifier, extract and print its value
         String identifierName = ((Identifier) n.f0.choice).f0.toString();
         //System.out.println(identifierName + "-----------------");

         if(ctr==0 || currectBlockAccess[ctr-1]=="if")
         {
            if(scopeStack.peek().containsKey(identifierName))
            {

                Boolean value = scopeStack.peek().get(identifierName);
                if(value==false)
                {
                  ans=true;
                  //System.out.println(identifierName + " is uninitialised <----------------------------- ");
                }
            }
         }
         else
         {
            if(elseStack.peek().containsKey(identifierName))
            {
                Boolean value = elseStack.peek().get(identifierName);
                if(value==false)
                {
               
                  ans=true;
                  //System.out.println(identifierName + " is uninitialised <----------------------------- ");
                }
            }
         }
        
         
     }
      n.f0.accept(this);
   }

   /*
    * f0 -> <INTEGER_LITERAL>
    */
   public void visit(IntegerLiteral n) {
      //System.out.println("IntegerLiteral");
      n.f0.accept(this);
   }

   /**
    * f0 -> "true"
    */
   public void visit(TrueLiteral n) {
      //System.out.println("TrueLiteral");
      n.f0.accept(this);
   }

   /**
    * f0 -> "false"
    */
   public void visit(FalseLiteral n) {
      //System.out.println("FalseLiteral");
      n.f0.accept(this);
   }

   /**
    * f0 -> <IDENTIFIER>
    */
   public void visit(Identifier n) {
      //System.out.println("Identifier");
      n.f0.accept(this);
   }

   /**
    * f0 -> "this"
    */
   public void visit(ThisExpression n) {
      //System.out.println("ThisExpression");
      n.f0.accept(this);
   }

   /**
    * f0 -> "new"
    * f1 -> "int"
    * f2 -> "["
    * f3 -> Expression()
    * f4 -> "]"
    */
   public void visit(ArrayAllocationExpression n) {
      //System.out.println("ArrayAllocationExpression");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      n.f4.accept(this);
   }

   /**
    * f0 -> "new"
    * f1 -> Identifier()
    * f2 -> "("
    * f3 -> ")"
    */
   public void visit(AllocationExpression n) {
      //System.out.println("AllocationExpression");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
   }

   /**
    * f0 -> "!"
    * f1 -> ( MessageSend() | PrimaryExpression() )
    */
   public void visit(NotExpression n) {
      //System.out.println("NotExpression");
      n.f0.accept(this);
      n.f1.accept(this);
   }

   /**
    * f0 -> "("
    * f1 -> Expression()
    * f2 -> ")"
    */
   public void visit(BracketExpression n) {
      //System.out.println("BracketExpression");
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
   }

   



}
