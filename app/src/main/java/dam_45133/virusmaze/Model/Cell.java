package dam_45133.virusmaze.Model;

public class Cell {
    private boolean topWall,leftWall, bottomWall, rightWall, visited;
    private int col, row;

    public Cell(int col, int row) {
        this.col = col;
        this.row = row;
        topWall = true;
        leftWall=true;
        bottomWall=true;
        rightWall=true;
        visited = false;
    }
    public boolean isTopWall() {
        return topWall;
    }

    public void setTopWall(boolean topWall) {
        this.topWall = topWall;
    }

    public boolean isLeftWall() {
        return leftWall;
    }

    public void setLeftWall(boolean leftWall) {
        this.leftWall = leftWall;
    }

    public boolean isBottomWall() {
        return bottomWall;
    }

    public void setBottomWall(boolean bottomWall) {
        this.bottomWall = bottomWall;
    }

    public boolean isRightWall() {
        return rightWall;
    }

    public void setRightWall(boolean rightWall) {
        this.rightWall = rightWall;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

}
