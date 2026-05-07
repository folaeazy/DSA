package arrays;

public class Array {

    public static void main(String[] args) {
        int[][] matrix = new int[4][];

        matrix[0] = new int[1];
        matrix[1] = new int[2];
        matrix[2] = new int[3];
        matrix[3] = new int[4];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++){
                System.out.printf(matrix[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
}
