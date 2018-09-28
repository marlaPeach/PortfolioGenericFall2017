/*
 * This program will help the user make their prep list for the apocalypse.
 */
package apocalypseprep;
    
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.PrintWriter;

public class ApocalypsePrep 
{
    static String userName;
    static String userChoice;
    static File zombielist = new File(".\\zombielist.txt");
    static Scanner classIn = new Scanner(System.in);
    static ArrayList<String> itemList = new ArrayList<String>();
    static ArrayList<String> itemsToAdd = new ArrayList<String>();
    
    //Display menu.
    public static void displayMenu()
    {
        System.out.println("________________________________________________________________________________________");
        System.out.println("Welcome to your Apocalypse List, " + userName + ".\n");
        System.out.println("a. Display List          b. Change name");
        System.out.println("c. Add Item              d. Save List");
        System.out.println("e. Delete Item           f. Swap items");
        System.out.println("                q. Quit");
        System.out.println("_________________________________________________________________________________________");
        System.out.print("                    Please make a selection: ");
    }
    
    //Load file.
    public static void loadFile() throws FileNotFoundException
    {
        try
        {
            Scanner dataIn = new Scanner(zombielist);
            while(dataIn.hasNext())
            {
                itemList.add(dataIn.nextLine());  
            }
            for(int k = 0; k < itemList.size(); k++)
            {
                System.out.println(itemList.get(k));
            }
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println("OMG. No file.");
        }
    }
    
    //Display list.
    public static void displayList()
    {
        try
        {
            if(itemList.isEmpty()) //To try to avoid the redundancy, this checks to be sure itemList array is empty before loading.
            {
                loadFile();
            }
            else
            {
                for(int k = 0; k < itemList.size(); k++) //Display items in ArrayList
                {
                    System.out.println(itemList.get(k));
                }
            }
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println("OMG. No file.");
        }
        
    }
    
    //Add item.
    public static void addItem(String itemName)
    {
        itemsToAdd.add(itemName);
    }
    public static void addItem()
    {
        int count = 0;
        while(!itemsToAdd.isEmpty())
        {
            itemList.add(itemsToAdd.get(count));
            count++;
        }
        itemsToAdd.clear();
    }
    
    //Remove item.
    public static void removeItem(String itemName)
    {
        itemList.remove(itemName);
        
    }
    
    //Change name.
    public static void changeName(String input)
    {
        userName = input;
    }
    
    //Save list.
    public static void saveList()
    {
        try 
        {
            PrintWriter writeIn = new java.io.PrintWriter("zombielist.txt");
            writeIn.println(userName + "'s List of Apocalypse Tools.");
            for(int i = 0; i < itemList.size(); i++)
            {
                writeIn.println(itemList.get(i));
            }
            writeIn.close();
        } catch (FileNotFoundException fnfe) 
        {
            System.out.println("File Not Found");
        }
    }
    
    //Swap items.
    public static void swap(String itemOne, String itemTwo)
    {
        int indexOne = itemList.indexOf(itemOne);
        int indexTwo = itemList.indexOf(itemTwo);
        itemList.set(indexTwo, itemOne);
        itemList.set(indexOne, itemTwo);
    }

    public static void main(String[] args)
    {
        boolean addAgain = true;
        boolean continueList = true;
        System.out.print("Enter your name: ");
        Scanner mainIn = new Scanner(System.in);
        changeName(mainIn.nextLine());
        /*try 
        {
            loadFile();
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println("File Not Found");
        }*/

        while(continueList == true)
        {
            displayMenu();
            String userAction = mainIn.nextLine();
        
            switch (userAction)
            {
                case "a": displayList();
                          break;
                case "b": System.out.println("What is your name?");
                          changeName(mainIn.nextLine());
                          System.out.println("Thank you, " + userName + ". Your name change is saved.");
                          break;
                case "c": do
                          {
                            System.out.println("What item to add?");
                            addItem(mainIn.nextLine());
                            System.out.println("Item Added. Add another?");
                            if(mainIn.nextLine().equals("N"))addAgain = false;
                          }
                          while (addAgain == true);
                          break;
                case "d": saveList();
                          System.out.println("List saved successfully.");
                          break;
                case "e": System.out.println("What item would you like to remove?");
                          removeItem(mainIn.nextLine());
                          break;
                case "f": System.out.println("First item to swap?");
                          String firstItem = mainIn.nextLine();
                          System.out.println("Second swap item?");
                          String secondItem = mainIn.nextLine();
                          swap(firstItem, secondItem);
                          break;
                case "q": System.out.println("Goodbye, " + userName);
                          continueList = false;
                          break;
            }
        }

    }
}
