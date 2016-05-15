package calcuroid;
import java.util.*;
import java.math.*;

public class Equation {	
// 'r' means root , 'u' means unary root , 'b' means binary root	

Stack operands= new Stack();
Stack operators=new Stack();
StringBuilder eq;
String temp=""; //carry string of value double
int i=0; //iterate eq string
Equation(String x){
	x=x.replaceAll("sin","s");
	x=x.replaceAll("log","l");
	x=x.replaceAll("cos","c");
	x=x.replaceAll("tan","t");
	x=x.replaceAll("PI","(22.0/7)");
	x=x.replaceAll("rootOf","r");
	x=x.replaceAll("e",String.valueOf(Math.E));
	eq=new StringBuilder(x);
}

//health of equation
boolean healthOfEq(){
	int openBracket=0,closeBracket=0;
	char checked=eq.charAt(0);
	if(eq.charAt(0)=='(')openBracket++;
	if(eq.charAt(0)==')')return false;
	for(int j=1;j<eq.length();j++){
	if(eq.charAt(j)=='(') openBracket++;
	else if(eq.charAt(j)==')') closeBracket++;
	else {
		String temp=String.valueOf(checked)+String.valueOf(eq.charAt(j));
		if(temp.equals("-+")||temp.equals("-*")||temp.equals("-%")||temp.equals("-^")||
				temp.equals("-/")||temp.equals("+/")||temp.equals("+%")||temp.equals("+^")||
				temp.equals("+*")||temp.equals("*/")||temp.equals("/*")||temp.equals("/%")||
				temp.equals("*%")||temp.equals("*+")||temp.equals("*^")||temp.equals("/^")||
				temp.equals("/+")||temp.equals("%*")||temp.equals("%/")||temp.equals("%^")||
				temp.equals("%+")||temp.equals("^%")||temp.equals("^/")||temp.equals("^*")||
				temp.equals("^+")||temp.equals("r*")||temp.equals("r/")||temp.equals("r%")||
				temp.equals("r+")||temp.equals("r^")||temp.equals("++")||temp.equals("**")||
				temp.equals("//")||temp.equals("^^")||temp.equals("%%")) return false;
		 checked=eq.charAt(j);
	}
	}
	if(openBracket!=closeBracket) return false;
	return true;
}

//return true if 'r' or '-' is unary operator
boolean isUniary(int j){
	
	for(;j>=0;j--)
		if(eq.charAt(j)=='('||eq.charAt(j)==')') continue;
		else if(precedence(eq.charAt(j))==-1){return false;}
		else break;
	return true;
}

//return precedence of operator or 5 if it's not an operator
int precedence(char ch){
	if(ch=='-'||ch=='+') return 2;
	else if(ch=='*'||ch=='/'||ch=='%') return 3;
	else if(ch=='r'||ch=='u'||ch=='b'||ch=='^')return 4;
	else if(ch=='s'||ch=='c'||ch=='t'||ch=='l')return 5;
	else if(ch=='(')return 0;
	else if(ch==')') return 1;
//if not operator
	else return -1;
}

//push any operator can be repeated after each other rr(), (()), (--3)
void push_repeated_operators(){
	boolean negativeno=false;
	while(eq.charAt(i)=='('||eq.charAt(i)=='r'||eq.charAt(i)=='-'||eq.charAt(i)=='s'||eq.charAt(i)=='c'||eq.charAt(i)=='t'||eq.charAt(i)=='l'){
		if(eq.charAt(i)=='-')negativeno=!negativeno;
		else if(eq.charAt(i)=='r')
			if(isUniary(i-1))operators.push('u');
			else operators.push('b');
		else
		operators.push(eq.charAt(i));
		i++; 
		}

	while(i<eq.length()&&precedence(eq.charAt(i))==-1)
	{temp+=eq.charAt(i);
			i++;}
	if(negativeno) operands.push(-1*Double.parseDouble(temp));
	else operands.push(Double.parseDouble(temp));
	temp="";
}

// the first step for initiating operator and operands stacks
void initiateStacks(){

			while(eq.charAt(i)=='-'){operators.push(eq.charAt(i)); i++;}
			 push_repeated_operators();
	}

//solve small equation of at most two operands
double minisolve(double operand1,char operator,double operand2){
	double tempresult=0;
	switch(operator){
	case '+':
		tempresult=operand1+operand2; break;
	case '-':
		tempresult=operand1-operand2; break;
	case '*':
		tempresult=operand1*operand2; break;
	case '/':
		tempresult=operand1/operand2; break;
	case '%':
		tempresult=operand1%operand2; break;
	case 'r':
		tempresult=Math.pow(operand2,(1.0/operand1)); break;
	case '^':
		tempresult=Math.pow(operand1, operand2); break;
	case 's':
		tempresult=Math.sin(Math.toRadians(operand2)); break;
	case 'c':
		tempresult=Math.cos(Math.toRadians(operand2)); break;
	case 't':
		tempresult=Math.tan(Math.toRadians(operand2)); break;
	case 'l':
		tempresult=Math.log10(operand2); break;

}
	return tempresult;
}

//finish the rest of the equation
//if operators stack isn't empty
//if operands stack isn't empty having negative no
void finishing(){
	double operand1=0,operand2=0; 
	
	while(!operators.isEmpty()){
		char operator=(char) operators.pop();
		operand2=(double) operands.pop();
		 if(operator=='b') operand1=(double) operands.pop();
		 else if(operator=='u'||operator=='s'||operator=='c'||operator=='t'||operator=='l')  operand1=2;
		else if(operands.empty()) operand1=0;
		else
		operand1=(double) operands.pop();
		 
	operands.push(minisolve(operand1,(operator=='u'||operator=='b')?'r':operator,operand2));
	}
	
	while(operands.size()>1&&operators.empty()){
		operand2=(double) operands.pop();	
		operand1=(double) operands.pop();
		operands.push(minisolve(operand1,'+',operand2));
	}	
}


String solve() throws Exception{
	if(!healthOfEq())
		throw (new Exception());
	
	double operand1=0,operand2=0; 

	initiateStacks();

	while(i<eq.length()){
		char currentoperator=eq.charAt(i);
		char lastoperator= operators.empty()?'w':(char) operators.peek();
		
		if((precedence(currentoperator)>precedence(lastoperator)||operators.empty())&&currentoperator!=')'){
			if(currentoperator=='r')
				if(isUniary(i-1))operators.push('u');
				else operators.push('b');
			else
			operators.push(currentoperator);
			i++;
			 push_repeated_operators();
		}
		else{
			while(precedence(currentoperator)<=precedence(lastoperator)&&!operators.empty()&&currentoperator!='('){
				operators.pop();
				if(lastoperator=='u'||lastoperator=='s'||lastoperator=='c'||lastoperator=='t'||lastoperator=='l'){
					operand2=(double) operands.pop();
					operand1=2;
					
					operands.push(minisolve(operand1,'r',operand2));	
				}else if(lastoperator=='b'){
					operand2=(double) operands.pop();
					operand1=(double) operands.pop();
					
					operands.push(minisolve(operand1,'b',operand2));	
				}
				else if(lastoperator=='-'){
					operand2=(double) operands.pop();
					int j=i-1;
					while(eq.charAt(j)!=lastoperator)j--;
					if(isUniary(j-1)){operand1=0;}
					else{operand1=(double) operands.pop();}
					operands.push(minisolve(operand1,lastoperator,operand2));
				}
				else{
					operand2=(double) operands.pop();
					if(operands.empty()) operand1=0;
					else
						operand1=(double) operands.pop();
					operands.push(minisolve(operand1,lastoperator,operand2));
				}
			lastoperator= operators.empty()?'w':(char) operators.peek();
					}		
			
			if (currentoperator==')') {operators.pop(); i++; continue;}
			if(currentoperator=='r')
				if(isUniary(i-1))operators.push('u');
				else operators.push('b');
			else
			operators.push(currentoperator);
			i++;
			 push_repeated_operators();
			}
	
	}
	finishing();
	return String.valueOf((double) operands.peek()); 
}

public static void main(String [] args){
	Equation y=new Equation("sin(45)");
	try{
	System.out.println(y.solve());
	}catch(Exception ex){
		System.out.println("My Exception");
	}
	
}
	
}
