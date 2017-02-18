import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	private static final String REQUEST_NAME = "What do you need to do?";
	private static final String REQUEST_DESCRIPTION = "Give a short description...";
	private static final String REMOVE_ITEM = "Select an item to remove:";
	private static final String MARK_COMPLETE = "Select an item to mark as complete:";
	private static final String MARK_INCOMPLETE = "Select an item to mark as to do:";
	private static final String EXIT_MESSAGE = "Goodbye (b^_^)b";
	private static final String INVALID_INT = "Try again.";
	private static final String MENU_OPTIONS = 
									"************************************\n"
								 + "          TO DO LIST APP\n\n"          
								 + "     Press a key to continue:\n\n"
								 + " 1. Add an item\n"
								 + " 2. Remove an item\n"
								 + " 3. Mark an item as complete\n"
								 + " 4. Mark a completed item as to do\n"
								 + " 5. Print to do list\n"
								 + " 0. Exit\n\n"
								 + "************************************";
	private static ArrayList<ToDo> myToDoList = new ArrayList<ToDo>();
	private static final String ITEM_DOES_NOT_EXIST = "Sorry the item you have selected does not exist.";

	public static void main(String[] args) {
		Scanner scanner =  new Scanner(System.in);
		int userInput;
		do {
			userInput = getUserSelection(scanner);
			
			switch (userInput) {
				case 0: exit();
					break;
				case 1: addToDoToList(scanner);
					break;
				case 2:
					removeToDoFromList(scanner);
					break;
				case 3:
					markToDoAsComplete(scanner);
					break;
				case 4:
					markToDoAsIncomplete(scanner);
					break;
				case 5:
					printToDoList();
					break;
				default: System.out.println(INVALID_INT);
					break;
			}
		} while (userInput != 0);
		
		exit();

	}
	
	private static void removeToDoFromList(Scanner sc) {
		actionToDoItem(sc, REMOVE_ITEM, Action.REMOVE);
	}
	
	private static void markToDoAsComplete(Scanner sc) {
		actionToDoItem(sc, MARK_COMPLETE, Action.COMPLETE);
	}
	
	private static void markToDoAsIncomplete(Scanner sc) {
		actionToDoItem(sc, MARK_INCOMPLETE, Action.INCOMPLETE);
	}

	private static void addToDoToList(Scanner sc) {
		System.out.println(REQUEST_NAME);
		String name = sc.nextLine();
		
		System.out.println(REQUEST_DESCRIPTION);
		String description = sc.nextLine();
		
		ToDo toDo = new ToDo(name, description);
		myToDoList.add(toDo);
		
	}
	
	private static void actionToDoItem(Scanner sc, String message, Action action) {
		System.out.println(message);
		

		printToDoListNames();
		
		int choice = sc.nextInt();
		
		if (choice == 0) {
			return;
		}
		
		int myToDoListSize = myToDoList.size();
		
		if (choice < 0 || choice > myToDoListSize) {
			System.out.println(ITEM_DOES_NOT_EXIST);
			return;
		}
		int index = choice -1;
		
		ToDo toDo = myToDoList.get(index);
		String name = toDo.getName();
		boolean completeAlready = toDo.isComplete();
		String response;
		
		switch(action) {
			case REMOVE: 
				myToDoList.remove(toDo);
				System.out.println("Removed '" + name + "' from your list.");
				break;
			case COMPLETE:
				toDo.setComplete(true);
				response = completeAlready ? "'" + name + "' is already marked as complete." : "Marked '" + name + "' as to do.";
				System.out.println(response);
				break;
			case INCOMPLETE:
				toDo.setComplete(false);
				response = completeAlready ? "Marked '" + name + "' as to do." : "'" + name + "' is already marked as incomplete." ; 
				System.out.println(response);
				break;
			default:
				return;
		}
				
	}
	
	private static void printToDoList() {
		printToDoListHeader();
		
		int myToDoListSize = myToDoList.size();
		
		if (myToDoListSize == 0) {
			System.out.println("You have no items on your todo list.");
		}
		else {
			for(int i = 0; i < myToDoListSize; i++) {
				System.out.println((i + 1) + ". " + myToDoList.get(i));
			}
		}
			
		printToDoListFooter();
	}
	
	private static void printToDoListNames() {
		printToDoListHeader();
		
		int myToDoListSize = myToDoList.size();
		
		if (myToDoListSize == 0) {
			System.out.println("You have no items on your todo list.");
		}
		else {
			for(int i = 0; i < myToDoListSize; i++) {
				System.out.println((i + 1) + ". " + myToDoList.get(i).getName());
			}
		}
			
		printToDoListFooter();
	}
	
	private static void printToDoListHeader() {
		System.out.println("************** TODOs ***************");
	}
	
	private static void printToDoListFooter() {
		System.out.println("************************************");
	}

	private static int getUserSelection(Scanner sc) {
		printMenuOptions();
		int selection = sc.nextInt();
		sc.nextLine(); // flush '\n' from scanner
		return selection;
	}
	
	private static void printMenuOptions() {
		System.out.println(MENU_OPTIONS);
	}
	
	private static void exit() {
		System.out.println(EXIT_MESSAGE);
		System.exit(0);
	}

}
