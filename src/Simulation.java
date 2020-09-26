public class Simulation {
    int width;
    int height;
    int[][] board;



    public static void main(String[] args) throws InterruptedException {

        Simulation simulation = new Simulation(20,20);

        simulationCounter(simulation,15);



    }
    public static void simulationCounter(Simulation current, int times) throws InterruptedException {
        Simulation simulation = new Simulation(20,20);
        //blinker
        simulation.setAlive(2,2);
        simulation.setAlive(3,2);
        simulation.setAlive(4,2);

        //blinker2
        simulation.setAlive(7,2);
        simulation.setAlive(8,2);
        simulation.setAlive(9,2);


        //Beacon
        simulation.setAlive(11,12);
        simulation.setAlive(11,13);
        simulation.setAlive(12,12);
        simulation.setAlive(12,13);
        simulation.setAlive(13,14);
        simulation.setAlive(13,15);
        simulation.setAlive(14,14);
        simulation.setAlive(14,15);
        for (int i = 0; i < times; i++) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            simulation.printBoard();
            simulation.step();
            Thread.sleep(300);
        }
    }

    public Simulation(int width, int height){
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }
    public void printBoard(){
        for (int y = 0; y < height; y++) {
            String line = "";
            for (int x = 0; x < width; x++) {
                if(this.board[x][y] == 0){
                    line += " ";
                }else{
                    line += "*";
                }
            }
            System.out.println(line);

        }
    }

    public void setAlive(int x, int y){
        this.board[x][y] = 1;
    }
    public void setDead(int x, int y){
        this.board[x][y] = 0;
    }

    public int countAliveNeighbours(int x, int y){
        int count = 0;
        count += getState(x - 1, y - 1);
        count += getState(x,y - 1);
        count += getState(x + 1, y - 1);

        count += getState(x - 1, y);
        count += getState(x + 1, y);

        count += getState(x - 1,y + 1);
        count += getState(x,y + 1);
        count += getState(x + 1,y + 1);

        return count;
    }
    public int getState(int x, int y){
        if(x < 0 || x >= width){
            return 0;
        }
        if(y < 0 || y >= height){
            return 0;
        }
        return this.board[x][y];
    }


    public void step(){
        int[][] newBoard = new int[width][height];

        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++) {
                int aliveNeighbours = countAliveNeighbours(x,y);

                if(getState(x,y) == 1){
                    if(aliveNeighbours < 2){
                        newBoard[x][y] = 0;
                    }else if (aliveNeighbours == 2 || aliveNeighbours == 3){
                        newBoard[x][y] = 1;
                    }
                    else if (aliveNeighbours > 3){
                        newBoard[x][y] = 0;
                    }
                } else{
                    if(aliveNeighbours == 3){
                        newBoard[x][y] = 1;
                    }
                }
            }
        }
        this.board = newBoard;
    }

}
