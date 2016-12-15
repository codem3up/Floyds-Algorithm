/* Floyd Algorithm
 *
 * Andrew Becker
 */

import java.util.Scanner;

public class Floyd{

  static Cell array[][];
  static int numVertices = 0;

   public static void printMatrix(String name, Cell array[][]){
    System.out.println("\nMatrix " + name); 
    
    for(int i = 0; i < array.length; i++){
      for(int z = 0; z < array[i].length; z++){
        System.out.format("%5s", array[i][z]);  
      }
      System.out.println();
    }
  }

  public static void iterate(int value){
    for(int j = 0; j < numVertices; j++){
      for(int z = 0; z < numVertices; z++){
        if(j != value && z != value){
          if(array[j][value].weight != -1 && array[j][value].weight != 0 && array[value][z].weight != 0 && array[value][z].weight != -1){
            if((array[j][value].weight + array[value][z].weight < array[j][z].weight) || array[j][z].weight == -1 ){
              array[j][z] = new Cell(array[j][value].weight + array[value][z].weight, value);
            }
          }
        }
      }
    }
  }

  public static void main(String [] args){
   Scanner s = null;
   
    try{
      s = new Scanner(System.in);    
      System.out.println("Please Input number of vertices");
      numVertices = Integer.parseInt(s.nextLine());
      if(numVertices < 2){
        System.out.println("Invalid entry");
        System.exit(0);
      }
    }catch(Exception ex){
      System.out.println("Invalid entry");
      System.exit(0);
    }
    
    array = new Cell[numVertices][numVertices];


    for(int j = 0; j < numVertices; j++){
      for(int z = 0; z < numVertices; z++){
        if(z == j){
          array[j][z] = new Cell(0, 0); 
        }
        else{
          System.out.println("Enter Weight from Vertice " + j + " to " + z);
          array[j][z] = new Cell(Integer.parseInt(s.nextLine()), 0);
        }
      }
    }

    printMatrix("D0", array);

    for(int i = 0; i < numVertices; i++){
      iterate(i);
      printMatrix("D" + (i+1), array);
    }
  }
}

class Cell{
  int weight;
  int path;

  public Cell(int w, int p){
    this.weight = w;
    this.path = p;
  }

  @Override
  public String toString(){
    return "|" + (weight != -1 ? Integer.toString(weight) : "-") + "," + Integer.toString(path) + "|";
  }
}
