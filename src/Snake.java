
class Snake {
    enum Direction{
        N, S, E, W
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
        if(this.dir == Direction.N && sDir!=Direction.S) {
            this.dir = sDir;
        }else if(this.dir == Direction.S && sDir!=Direction.N){
            this.dir = sDir;
        }else if(this.dir==Direction.E && sDir!=Direction.W){
            this.dir = sDir;
        }else if(this.dir==Direction.W && sDir!=Direction.E){
            this.dir = sDir;
        }
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
    void incLength(){
        this.length+= 1;
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
            default:
                System.out.println("how");
        }
    }
}

