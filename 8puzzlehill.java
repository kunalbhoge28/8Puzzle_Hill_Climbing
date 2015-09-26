import java.util.*;

//  By  Kunal Bhoge

class Main
{
	public static void main(String[] args) 
	{
		 List<Integer> initial = new ArrayList<Integer>();
	   	 initial.add(1);
	   	 initial.add(0);
	   	 initial.add(3);
	   	 initial.add(4);
	   	 initial.add(2);
	   	 initial.add(6);
	   	 initial.add(7);
	   	 initial.add(5);
	   	 initial.add(8);


	   	 List<Integer> goal = new ArrayList<Integer>();
	   	 goal.add(1);
	   	 goal.add(2);
	   	 goal.add(3);
	   	 goal.add(4);
	   	 goal.add(5);
	   	 goal.add(6);
	   	 goal.add(7);
	   	 goal.add(8);
	   	 goal.add(0);

	   	 int index;
	   	 List<Integer> compare_list = new ArrayList<Integer>();
	

	   	 Puzzle p=new Puzzle(initial,goal);

	   	System.out.println("INITIAL STATE : ");
		p.tempStack(initial);
		p.display(p.temp_stack);

	   	
	  
		while(p.finish_flag==0)
		{
			index=p.searchBlank();
			

			System.out.println("-----------------------------------------------");
			System.out.println();

			p.no_of_swaps(index);


			//System.out.println("STACK :"+p.stack);
			p.display(p.stack);

			int state_min=1000;
			int state_min_index=-1;

			System.out.println();
			System.out.println("MANHATAN DISTANCE FOR EACH ABOVE RESPECTIVELY");
			for(int i=0;i<p.stack.size();i++) //calculating dist for each state in stack
			{
				compare_list=p.stack.elementAt(i);
				int dist=p.calculateManhattan(compare_list,goal);
				System.out.println(dist);

				if(dist<state_min)  ///THIS LOGIC IS FOR FINDING BEST MIN DIST STATE INDEX IN STACK
				{
					state_min=dist;
					state_min_index=i;
				}
	   			
			}

			compare_list=p.stack.elementAt(state_min_index);
			p.temp_stack.push(compare_list);
			System.out.println("Minimum Distance is "+state_min+" Above At Position  "+(state_min_index+1));
			System.out.println();
			//System.out.println(state_min+""+state_min_index);
			
			System.out.println("Next State :");
			p.display(p.temp_stack);
			p.stack.clear();

		
		}
		if(p.finish_flag==1)
		{
			System.out.println("************GOAL STATE REACHED************");
		}

	}
}

class Puzzle
{
	int finish_flag=0;


	List<Integer> initial = new ArrayList<Integer>();
	List<Integer> goal = new ArrayList<Integer>();
	List<Integer> displayList = new ArrayList<Integer>();

	Stack<List> stack=new Stack<List>();
	Stack<List> temp_stack=new Stack<List>();


	Puzzle(List initial,List goal)
	{
		this.initial=initial;
		this.goal=goal;
	}

	//HEURISTICS BY KUNAL
	int calculateManhattan(List<Integer> initial,List<Integer> goal)  
	{
		int distance=0;
		for(int i=0;i<goal.size();i++)
		{
			if(initial.get(i)==goal.get(i) || initial.get(i)==0) //comparing elemt at each index
			{
				
			}
			else  //if elemt not equal 
			{
				int correct_index=getCorrectIndex(initial.get(i),goal); //passing inital elemt to get its correct index in goal list
				distance+=Math.abs(correct_index-i);
					
			}
		}
		return distance;
	}

	int getCorrectIndex(int value,List<Integer> goal)
	{
		int index=100;
		for(int i=0;i<goal.size();i++)
		{
			if(goal.get(i)==value)  //gets correct index of wrongly placed elemt
			{
				index=i;
				break;
			}
		}
		return index;
	}
	///HEURISTICS ABOVE

	int searchBlank()
	{
		int blank_index=99;

		List<Integer> loaded_list = new ArrayList<Integer>(temp_stack.peek());

		for(int i:loaded_list)
		{
			if(loaded_list.get(i)==0)
			{
				blank_index=i;
			}
		}
		return blank_index;
	}

	void swap(int index,int blank_index)
	{
		List<Integer> temp_initial = new ArrayList<Integer>(temp_stack.peek());
		//the above syntax copies content from old list to new list without harming or modifying the old one
		//--kunal

		int temp,temp2;

		temp=temp_initial.get(blank_index);
		temp2=temp_initial.get(index);

		temp_initial.set(index,temp);
		temp_initial.set(blank_index,temp2);
		

		if(!isGoal(temp_initial))
		{
			//DUMPING --kunal
			stack.push(temp_initial);

		}
		else
		{
			finish_flag=1;
			//System.out.println("************GOAL STATE REACHED************");
			//System.out.println();
			stack.push(temp_initial);
		}
		
	}

	void no_of_swaps(int blank_index)
	{
		int swaps=0;

		   	 if(blank_index==3)
   	 {
   		 
   		 swap(4,blank_index);
   		 swap(0,blank_index);
   		 swap(6,blank_index);
   	 }
   	 else if(blank_index==1)
   	 {
   		 swap(2,blank_index);
   		 swap(4,blank_index);
   		 swap(0,blank_index);
   	 }
   	 else if(blank_index==5)
   	 {
   		 swap(2,blank_index);
   		 swap(4,blank_index);
   		 swap(8,blank_index);
   	 }
   	 else if(blank_index==7)
   	 {
   		 swap(6,blank_index);
   		 swap(4,blank_index);
   		 swap(8,blank_index);
   	 }
   	 else if(blank_index==0)
   	 {
   		 swap(1,blank_index);
   		 swap(3,blank_index);
   		 
   	 }
   	 else if(blank_index==2)
   	 {
   		 swap(1,blank_index);
   		 swap(5,blank_index);
   		 
   	 }
   	 else if(blank_index==6)
   	 {
   		 swap(3,blank_index);
   		 swap(7,blank_index);
   		 
   	 }
   	 else if(blank_index==8)
   	 {
   		 swap(5,blank_index);
   		 swap(7,blank_index);
   		 
   	 }
   	 else if(blank_index==4)
   	 {
   		 swap(5,blank_index);
   		 swap(3,blank_index);
   		 swap(1,blank_index);
   		 swap(7,blank_index);	 
   	 }


	reload();
		

	}

	void reload()
	{
		temp_stack.pop();
	}

	void tempStack(List list)
	{
		temp_stack.push(list);
	}

	boolean isGoal(List list)
	{
		if(list.equals(goal))
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	 void display(Stack<List> display_stack)
    {
   	 for(int i=0;i<display_stack.size();i++)
   	 {
   		 displayList=display_stack.get(i);
   		 for(int j=0;j<displayList.size();j+=3)
   		 {
   			 for(int k=j;k<j+3;k++)
   			 {
   				 System.out.printf(displayList.get(k)+" ");
   			 }
   			 System.out.println();
   		 }    
   		 System.out.println();    
   	 }
    }
}
