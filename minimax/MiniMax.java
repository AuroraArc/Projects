import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MiniMax {
    static Tree tree;

    public static void constructTree(int noOfBones) {
        tree = new Tree();
        Node root = new Node(noOfBones, true);
        tree.setRoot(root);
        constructTree(root);
    }

    private static void constructTree(Node parentNode) {
        List<Integer> listOfPossibleHeaps = GameOfBones.getPossibleStates(parentNode.getNoOfBones());
        boolean isChildMaxPlayer = !parentNode.isMaxPlayer();
        listOfPossibleHeaps.forEach(n -> {
            Node newNode = new Node(n, isChildMaxPlayer);
            parentNode.addChild(newNode);
            if (newNode.getNoOfBones() > 0) {
                constructTree(newNode);
            }
        });
    }

    public static boolean checkWin() {
        Node root = tree.getRoot();
        checkWin(root);
        return root.getScore() == 1;
    }

    public static void checkWin(Node node) {
        List<Node> children = node.getChildren();
        boolean isMaxPlayer = node.isMaxPlayer();
        children.forEach(child -> {
            if (child.getNoOfBones() == 0) {
                child.setScore(isMaxPlayer ? 1 : -1);
            } else {
                checkWin(child);
            }
        });
        Node bestChild = findBestChild(isMaxPlayer, children);
        node.setScore(bestChild.getScore());
    }

    private static Node findBestChild(boolean isMaxPlayer, List<Node> children) {
        Comparator<Node> byScoreComparator = Comparator.comparing(Node::getScore);
        return children.stream()
                .max(isMaxPlayer ? byScoreComparator : byScoreComparator.reversed())
                .orElseThrow(NoSuchElementException::new);
    }

    @Test
    public void givenMax_whenCheckWin_thenComputeOptimal() {
        MiniMax.constructTree(6);
        boolean result = MiniMax.checkWin();

        assertTrue(result);

        MiniMax.constructTree(8);
        result = MiniMax.checkWin();

        assertFalse(result);
    }
}
