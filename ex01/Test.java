import java.util.*;

public class Test {      

    
    public static void main(String[] args) throws Exception {

	System.out.println("\nStringList Tester (version 20170106");
	System.out.println("type help for help");
	
	String commands = 
	    " add (+): add to the front of l1 a new string \n" +
	    " append (app): append to the end of list l1 the list l2 \n"+
	    " count (c): deliver number occurances of a given string in l1 \n"+
	    " empty (e): is l1 empty? \n"+
	    " equal (=): are l1 and l2 equal in content and order? \n" + 
	    " help: help \n"+
	    " insert (ins): insert a string in lex order into l1 \n" +
            " indexOf (ind): get the index of first occurrence \n" +
	    " last: deliver the last node in l1 \n"+
            " get: deliver the string in the get position (0 is first position) \n"+
	    " present (p): is a given string present in l1?\n"+
	    " quit (q): terminate Test \n" + 
	    " remove (rem/-): remove a given string from l1 \n"+
	    " replace (repl): replace occurrences of s1 with s2 \n"+
	    " reverse (rev): l1 becomes the reverse of l1 \n"+
            " show: shows l1 and then l2 \n"+
            " size: the size of l1 \n"+
	    " toggle (tog): swap l1 with l2";
	
	Scanner sc = new Scanner(System.in);
	
	System.out.print("> ");
	String command = sc.next();
	String s = "";
	int n = 0;
	StringList l1, l2, lTemp;
	l1 = new StringList();
	l2 = new StringList();
	
	while (!command.equals("quit") && !command.equals("q")){

	    if (command.equals("help")) System.out.println(commands);
	    
	    if (command.equals("size")) System.out.println(l1.size());
	    
	    if (command.equals("empty") || command.equals("e")) System.out.println(l1.isEmpty());
	    
	    if (command.equals("equal") || command.equals("=")) System.out.println(l1.equal(l2));
	    
	    if (command.equals("remove") || command.equals("rem") || command.equals("-")){
		System.out.print(">> ");
		s = sc.next();
		l1.remove(s);
		System.out.println(l1);
	    }

	    if (command.equals("replace") || command.equals("repl")){
		System.out.print("first string >> ");
		String s1 = sc.next();
		System.out.print("second string >> ");
		String s2 = sc.next();
		l1.replace(s1,s2);
		System.out.println(l1);
	    }
	    
	    if (command.equals("add") || command.equals("+")){
		System.out.print(">> ");
		s = sc.next();
		l1.addFront(s);
		System.out.println(l1);
	    }
	    
	    if (command.equals("insert") || command.equals("ins")){
		System.out.print(">> ");
		s = sc.next();
		l1.insert(s);
		System.out.println(l1);
	    }
	    
	    if (command.equals("present") || command.equals("p")){
		System.out.print(">> ");
		s = sc.next();
		System.out.println(l1.isPresent(s));
	    }

	    if (command.equals("count") || command.equals("c")){
		System.out.print(">> ");
		s = sc.next();
		System.out.println(l1.count(s));
	    }
	    
	    if (command.equals("reverse") || command.equals("rev")){
		l1 = l1.reverse();
		System.out.println(l1);
	    }

	    if (command.equals("get")){
		System.out.print(">> ");
		n = sc.nextInt();
		System.out.println(l1.get(n));
	    }

	    if (command.equals("indexOf") || command.equals("ind")){
		System.out.print(">> ");
		s = sc.next();
		System.out.println(l1.indexOf(s));
	    }

	    if (command.equals("last")) System.out.println(l1.last());
	    
	    if (command.equals("append") || command.equals("app")){
		l1.append(l2);
		System.out.println(l1);
	    }
	    
	    if (command.equals("show")){
		System.out.println("l1: "+ l1);
		System.out.println("l2: "+ l2);
	    }
	    
	    if (command.equals("toggle") || command.equals("tog")){
		lTemp = l1; l1 = l2; l2 = lTemp;
		System.out.println(l1);
	    }
	    
	    System.out.print("> ");
	    command = sc.next();
	}
    }
}
