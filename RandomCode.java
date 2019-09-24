import java.util.Scanner;
import java.util.ArrayList;

public class RandomCode {
    public static void menu(ArrayList<ArrayList<String>> diction){
        Scanner in = new Scanner(System.in);
        System.out.println("Dictionary Options:\n\t1. Add Entry\n\t2. Remove Entry\n\t3. Modify Entry\n\t4. Search\n\t5. Print Dictionary\n\t6. Exit Dictionary Program\nEnter the corresponding number to execute an option.");
        int choice = 0;
        while(in.hasNextInt()){
           choice = in.nextInt(); 
           break;
        }
        switch (choice){
            case 1: addEnt(diction);
            case 2: remEnt(diction);
            case 3: modEnt(diction);
            case 4: search(diction);
            case 5: printResults(diction);
            case 6: exit(diction);
            default: System.out.println("Invalid Input!");
                     menu(diction);
        }
    }
    public static void addEnt(ArrayList<ArrayList<String>> addDict){
        Scanner in = new Scanner (System.in);
        ArrayList<String> newEnt = new ArrayList<String>();
        System.out.println("Enter a word: ");
        String newWord = in.nextLine().toLowerCase();
        newEnt.add(newWord);
        System.out.println("Enter the definition: ");
        String newDef = in.nextLine();
        newEnt.add(newDef);
        addDict.add(newEnt);
        menu(addDict);
    }
    public static void remEnt(ArrayList<ArrayList<String>> remDict){
        if(remDict.isEmpty()){
            System.out.println("There is nothing inside of this dictionary, enter 1 to add an entry.\n");
            menu(remDict);
        }
        Scanner in = new Scanner (System.in);
        System.out.println("Your Dictionary:");      //prints dictionary so they can see what to delete
        for(int i = 0; i<remDict.size();i++){
            System.out.print("\t");
            System.out.println(i+1+"- "+remDict.get(i).get(0)+": "+remDict.get(i).get(1));   //i is added by one so the numbers of all the entries starts at 1 and not 0
        }
        System.out.println("Which entry do you wish to remove (enter corresponding number)?");
        int userIn = in.nextInt();
        in.nextLine();
        while(userIn>remDict.size()||userIn<1){
            System.out.println("This entry does not exist, enter a number for an existing entry.");
            userIn = in.nextInt();
            in.nextLine();
        }
        remDict.remove(remDict.get(userIn-1));  //input is subtracted for index (to prevent user getting confused seeing a 0 for row 1)
        menu(remDict);
    }
    public static void modEnt(ArrayList<ArrayList<String>> modDict){
        if(modDict.isEmpty()){
            System.out.println("There is nothing inside of this dictionary, enter 1 to add an entry.\n");
            menu(modDict);
        }
        Scanner in = new Scanner (System.in);
        System.out.println("Your Dictionary:");     //prints dictionary so they can see what to modify
        for(int i = 0; i<modDict.size();i++){
            System.out.print("\t");
            System.out.println(i+1+"- "+modDict.get(i).get(0)+": "+modDict.get(i).get(1));
        }
        System.out.println("Which entry do you wish to modify (enter corresponding number)?");
        int userIn = in.nextInt();
        in.nextLine();
        while(userIn>modDict.size()||userIn<1){ //handles error that would happen if they put in a value that is not a number shown next to the entries
            System.out.println("This entry does not exist, enter a number for an existing entry.");
            userIn = in.nextInt();
            in.nextLine();
        }
        System.out.println("Enter the new word: ");
        String modWord = in.nextLine().toLowerCase();
        System.out.println("Enter the new definition: ");
        String modDef = in.nextLine();
        modDict.get(userIn-1).set(0,modWord);  //userIn-1 so we get the index
        modDict.get(userIn-1).set(1,modDef);
        menu(modDict);
    }
    public static void search(ArrayList<ArrayList<String>> serDict){
        if(serDict.isEmpty()){
            System.out.println("There is nothing inside of this dictionary, enter 1 to add an entry.\n");
            menu(serDict);
        }
        Scanner in = new Scanner (System.in);
        System.out.println("Enter the word you are looking for: ");
        String findWord = in.nextLine().toLowerCase();
        int fcount = 0; //false count
        for(int i = 0; i<serDict.size();i++){
            if(serDict.get(i).get(0).equals(findWord)){
                System.out.println("Entry Found:\n\t"+serDict.get(i).get(0)+": "+serDict.get(i).get(1));
            }else{
                fcount++; //increment everytime word is not found in a single entry (arraylist)
            }
        }
        if(fcount==serDict.size()){   //if word is never found in the whole dictionary (2darraylist) then fcount will equal the size of finDict
            System.out.println("Word doesn't exist in the dictionary.");
        }
        menu(serDict);
    }
    public static void printResults(ArrayList<ArrayList<String>> prinDict){
        if(prinDict.isEmpty()){
            System.out.println("There is nothing inside of this dictionary, enter 1 to add an entry.\n");
            menu(prinDict);
        }
        System.out.println("Your Dictionary:");
        for(int i = 0; i<prinDict.size();i++){
            System.out.print("\t");
            System.out.println(i+1+"- "+prinDict.get(i).get(0)+": "+prinDict.get(i).get(1));
        }
        menu(prinDict);  
    }
    public static void exit(ArrayList<ArrayList<String>> exiDict){
        Scanner in = new Scanner (System.in);
        System.out.println("Are you sure you want to exit? (Y/N): ");
        String answer = in.nextLine().toLowerCase();
        if(answer.equals("y")){
            System.exit(0);
        }else{
            menu(exiDict);
        }
    }
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> finDict = new ArrayList<ArrayList<String>>(); //this has to be declared in main so every method can use this as the final dictionary and constantly update it
        menu(finDict);
    } 
}
