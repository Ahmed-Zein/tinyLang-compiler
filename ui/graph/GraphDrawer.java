package ui.graph;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import parser.tree.Node;
import parser.tree.NonTerminalNode;

import java.util.Random;

public class GraphDrawer {
    public static final double DEFAULT_NODE_HEIGHT = 40;
    public static double DEFAULT_NODE_WIDTH = 90;

    public void drawGraph(Pane pane, Node node, double x, double y, int indent) {
        drawNodeShape(pane, node, x, y);
        if (node instanceof NonTerminalNode) {
            if (!((NonTerminalNode) node).children.isEmpty()) {
                double childX = x + (indent > 0 ? DEFAULT_NODE_WIDTH * 1.8 : 0);
                double childY = y;
                int tmp = 1;
                for (Node child : ((NonTerminalNode) node).children) {
                    drawChildLine(pane, x, y, childX, childY);
                    drawGraph(pane, child, childX, childY, indent + 1);
                    childY += DEFAULT_NODE_HEIGHT * tmp * 2.3  + new Random().nextInt(100);
                    tmp = (child instanceof NonTerminalNode) ? ((NonTerminalNode) (child)).children.size() : 1;
                }

            }
        }
    }

    private void drawNodeShape(Pane pane, Node node, double x, double y) {
        if (node instanceof NonTerminalNode) {
            drawNodeRectangle(pane, node, x, y);
        } else {
            drawNodeCircle(pane, node, x, y);
        }
    }

    private void drawNodeCircle(Pane pane, Node node, double x, double y) {
        StackPane circle = new GraphCircularNode().widget(node.toString());
        setPosition(circle, x, y - 10);
        pane.getChildren().add(circle);
    }

    private void drawNodeRectangle(Pane pane, Node node, double x, double y) {
        StackPane rectangle = new GraphRectangularNode().widget(node.toString());
        setPosition(rectangle, x, y);
        pane.getChildren().add(rectangle);
    }

    private void setPosition(StackPane rectangle, double x, double y) {
        rectangle.setLayoutX(x);
        rectangle.setLayoutY(y);
    }

//    private void drawChildrenLinesAndNodes(Pane pane, NonTerminalNode node, double x, double y) {
//    }

//    private void drawChildrenLinesAndNodes(Pane pane, NonTerminalNode nonTerminalNode, double x, double y) {
//        Random random = new Random();
//        if (!nonTerminalNode.children.isEmpty()) {
//            double childX = x - DEFAULT_NODE_WIDTH/ 1.2  + new Random().nextInt(40);
//            double childY = y + DEFAULT_NODE_HEIGHT + 40 + new Random().nextInt(60 );// Increase vertical spacing between nodes
//
//            for (Node child : nonTerminalNode.children) {
//                drawChildLine(pane, x, y, childX, childY);
//                drawGraph(pane, child, childX, childY);
//
//                // Adjust position for the next child
//                childX += DEFAULT_NODE_WIDTH + 30 +new Random().nextInt(100); // Adjust horizontal spacing
//            }
//        }
//    }

    private void drawChildLine(Pane pane, double parentX, double parentY, double childX, double childY) {
        Line line = new Line(parentX + DEFAULT_NODE_WIDTH / 2, parentY + DEFAULT_NODE_HEIGHT, childX + DEFAULT_NODE_WIDTH / 2, childY);
        pane.getChildren().add(line);
    }
}

class GraphNode {

}

class GraphRectangularNode extends GraphNode {

    public StackPane widget(String title) {
        StackPane stackPane = new StackPane();
        Rectangle rect = createNodeRectangle();
        stackPane.getChildren().add(rect);
        Label l = createNodeLabel(title);
        stackPane.getChildren().add(l);
        return stackPane;
    }

    private Rectangle createNodeRectangle() {
        Rectangle rectangle = new Rectangle(GraphDrawer.DEFAULT_NODE_WIDTH, GraphDrawer.DEFAULT_NODE_HEIGHT);
        rectangle.setFill(Color.SNOW);
        rectangle.setStroke(Color.BLACK);
        return rectangle;
    }

    private Label createNodeLabel(String node) {
        Label label = new Label(node);
        label.setMaxWidth(GraphDrawer.DEFAULT_NODE_WIDTH - 10); // Adjust maximum width to prevent overflow
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        return label;
    }

}

class GraphCircularNode extends GraphNode {

    public StackPane widget(String title) {
        StackPane stackPane = new StackPane();

        Circle circle = createCircle();
        stackPane.getChildren().add(circle);

        Label l = createNodeLabel(title);
        stackPane.getChildren().add(l);
        return stackPane;
    }

    private Circle createCircle() {
        Circle circle = new Circle(GraphDrawer.DEFAULT_NODE_WIDTH / 3);
//        circle.setFill(Color.SNOW);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        return circle;
    }

    private Label createNodeLabel(String node) {
        Label label = new Label(node);
        label.setMaxWidth(GraphDrawer.DEFAULT_NODE_WIDTH); // Adjust maximum width to prevent overflow
        label.setFont(Font.font(12));
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        return label;
    }

}
