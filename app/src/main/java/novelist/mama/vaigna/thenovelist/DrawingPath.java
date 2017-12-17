package novelist.mama.vaigna.thenovelist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abid Hasan on 17/12/17.
 */


public class DrawingPath {

    private int[][] targetMatrix ;

    public ArrayList<Node> coordintes = new ArrayList<>();

    public DrawingPath(int[][] targetMatrix) {
        this.targetMatrix = targetMatrix;
    }


    public void getPaths(){



        for(int row = 0 ; row<targetMatrix.length ; row ++ ){
            for (int column = 0 ; column < targetMatrix.length ;column++){

                Node current = new Node(row , column);

                if(targetMatrix[current.x][current.y] == 1) {
                    coordintes.add(current);
                    targetMatrix[current.x][current.y] = 0 ;
                }

                List<Node> neighbours = getNeighbours(targetMatrix , current);

                for(int i= 0 ; i<neighbours.size() ;i++){
                    Node targetNode = neighbours.get(i);
                    if(targetMatrix[targetNode.x][targetNode.y] == 1){
                        coordintes.add(targetNode);
                        targetMatrix[targetNode.x][targetNode.y] = 0 ;
                    }
                }

            }
        }



        printCoordinated();
    }


    boolean isValidPoint(int[][] matrix , int x , int y){
        return !(x<0 || x >= matrix.length  || y<0 || y>=matrix[0].length) && (matrix[x][y] != 0);
    }

    List<Node> getNeighbours(int[][] matrix , Node node){
        List<Node> neighbors = new ArrayList<>();

        if(isValidPoint(matrix , node.x -1 , node.y))
            neighbors.add(new Node(node.x -1 , node.y));

        if(isValidPoint(matrix , node.x , node.y+1))
            neighbors.add(new Node(node.x , node.y+1));

        if(isValidPoint(matrix , node.x+1 , node.y))
            neighbors.add(new Node(node.x+1 , node.y));

        if(isValidPoint(matrix , node.x , node.y - 1))
            neighbors.add(new Node(node.x , node.y - 1));


        return neighbors;
    }


    private void printCoordinated(){
        for (Node node: coordintes) {
            System.out.println("coordinates to visit (x,y) \n"+ node.toString());
        }
    }



    class Node{

        final int x ;
        final int y ;

        public Node(int x, int y ) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.valueOf(x)+", "+String.valueOf(y);
        }
    }
}
