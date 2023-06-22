import java.util.List;

public class Node {
    int noOfBones;
    boolean isMaxPlayer;
    int score;
    List<Node> children;
    // setters and getters

    public Node(int noOfBones, boolean isMaxPlayer) {
        this.noOfBones = noOfBones;
        this.isMaxPlayer = isMaxPlayer;
    }

    public int getNoOfBones() {
        return noOfBones;
    }

    public void setNoOfBones(int noOfBones) {
        this.noOfBones = noOfBones;
    }

    public boolean isMaxPlayer() {
        return isMaxPlayer;
    }

    public void setMaxPlayer(boolean isMaxPlayer) {
        this.isMaxPlayer = isMaxPlayer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public void addChild(Node child) {
        children.add(child);
    }
}
