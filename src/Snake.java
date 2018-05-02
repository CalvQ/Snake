
class Snake {
    enum Direction{
        N, S, E, W, none
    }
    private int length;
    private int x;
    private int y;
    private Snake.Direction dir;

    Snake(Snake.Direction sDir, int sX, int sY){
        this.dir = sDir;
        this.length = 5;
        this.x = sX;
        this.y = sY;
    }

    void changeDir(Snake.Direction sDir){

        this.dir = sDir;
    }

    int getLength(){
        return this.length;
    }
    int getX(){
        return this.x;
    }
    int getY(){
        return this.y;
    }

    void move(){
        switch (dir){
            case N:
                this.x--;
                break;
            case S:
                this.x++;
                break;
            case E:
                this.y++;
                break;
            case W:
                this.y--;
                break;
            case none:
                break;
            default:
                System.out.println("how");
        }
    }
}

