import java.io.*;
import java.util.Scanner;

public class Main implements Serializable {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryNode<String> root;

        // enters infinite loop which prompts user to select tree they want to play game with
        //Continues the game until a leaf node is reached in the binary tree structure.

        while (true) {
            root = chooseTreeCreationMethod(scanner);

            BinaryNode<String> currentNode = root;

            while (!currentNode.isLeaf()) {
                processNodeGuess(scanner, currentNode, root);
            }
        }
    }


    private static BinaryNode<String> chooseTreeCreationMethod(Scanner scanner) {
        // prompts user if they want to use their own binary tree -
        // if yes it calls loadTree function and returns the tree received else calls createTree and returns the root node
        System.out.println("Do you want to use your own binary tree? (y/n)");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("y")) {
            System.out.println("Enter the filename to load the tree from");
            String filename = scanner.nextLine();
            return loadTree(filename);
        } else {
            return createTree();
        }
    }

    private static void processNodeGuess(Scanner scanner, BinaryNode<String> currentNode, BinaryNode<String> root) {

/*        Processes the user's guess based on the current node in the binary tree.
        Asks the user a question based on the current node's data.
       If the current node is not a leaf, prompts the user with a question and navigates to the next node based on the response - either right or left ( left for yes / right for no).
       If the current node is a leaf, asks the user to confirm the guess and handles the correct or incorrect guess accordingly.
*/
        if (currentNode != null) {
            if (!currentNode.isLeaf()) {
                System.out.println("Is it a " + currentNode.getData() + "? (y/n)");
                boolean isYes = scanner.nextLine().equalsIgnoreCase("y");

                currentNode = isYes ? (BinaryNode<String>) currentNode.getLeftChild() : (BinaryNode<String>) currentNode.getRightChild();
                processNodeGuess(scanner, currentNode, root);
            } else {
                System.out.println("Is it " + currentNode.getData() + "? (y/n)");
                boolean isGuessCorrect = scanner.nextLine().equalsIgnoreCase("y");

                if (isGuessCorrect) {
                    System.out.println("I guessed it correctly!");
                    handleCorrectGuess(scanner, root);
                } else {
                    handleIncorrectGuess(scanner, currentNode, root);
                }
            }
        } else {
            System.out.println("Current node is null. Unable to process the guess.");
        }
    }

    private static void handleCorrectGuess(Scanner scanner, BinaryNode<String> root) {

        //  handles the actions to be taken when the user's guess is correct.
        //  gives options to save tree / quit game loop / print tree
        System.out.println("Options:");
        System.out.println("1. Store the tree?");
        System.out.println("2. Quit?");
        System.out.println("3. Print tree?");
        int option = scanner.nextInt(); // gets user input and passed into switch case to execute the functionality accordingly
        scanner.nextLine();

        switch (option) { // each case statement calls the corresponding function and exits
            case 1:
                saveTree(root, scanner);
                System.exit(0);
            case 2:
                System.out.println("Exiting the game. Goodbye!");
                System.exit(0);
            case 3:
                System.out.println("Printing the tree");
                printTree(root);
                System.exit(0);
            default:
                System.out.println("Invalid option. Exiting the game.");
                System.exit(0);
        }
    }

    private static void handleIncorrectGuess(Scanner scanner, BinaryNode<String> currentNode, BinaryNode<String> root) {

        System.out.println("What were you thinking of?");
        String newAnswer = scanner.nextLine(); // prompts user for the answer user thought of
        System.out.println("What is the noun in the question? For example, 'Is it a sports car?' the noun is 'sports car'");
        String newQuestionNoun = scanner.nextLine(); // node value
        System.out.println("Enter 'y' for the correct answer and 'n' for the incorrect answer:");
        boolean correctAns = scanner.nextLine().equalsIgnoreCase("y"); // decides if node will be positioned left or right of parent node

        BinaryNode<String> newQuestionNode = new BinaryNode<>(newQuestionNoun);
        BinaryNode<String> newAnswerNode = new BinaryNode<>(newAnswer);

        // creates 2 nodes one for the question and one for the answer

        // if given answer was true to the question answer was added as a left node else it was added as a right node
        if (correctAns) {
            newQuestionNode.setLeftChild(newAnswerNode);
            newQuestionNode.setRightChild(currentNode);
        } else {
            newQuestionNode.setLeftChild(currentNode);
            newQuestionNode.setRightChild(newAnswerNode);
        }

        // Update the parent node of the current node to point to the new question node
        if (root != currentNode) {
            BinaryNode<String> parent = findParentNode(root, currentNode);
            //If a valid parent is found, updates the left or right child of the parent to the new question node based on the current node's position.
            if (parent != null) {
                if (parent.getLeftChild() == currentNode) {
                    parent.setLeftChild(newQuestionNode);
                } else {
                    parent.setRightChild(newQuestionNode);
                }
            }
        } else {
            root = newQuestionNode; // Update the root if currentNode is the root
        }
        //     Finally, continues the game by calling handleCorrectGuess with the updated root.
        handleCorrectGuess(scanner, root);
    }

    private static BinaryNode<String> findParentNode(BinaryNode<String> root, BinaryNode<String> target) {
        return findParentNodeHelper(root, target, null);
    }

    private static BinaryNode<String> findParentNodeHelper(BinaryNode<String> currentNode, BinaryNode<String> target, BinaryNode<String> parent) {
        if (currentNode == null) {
            return null; // Base case: Reached the end of the tree without finding the target
        }

        if (currentNode == target) {
            return parent; // Found the target node, return its parent
        }

        // Recursively search in the left and right subtrees
        BinaryNode<String> leftResult = findParentNodeHelper((BinaryNode<String>) currentNode.getLeftChild(), target, currentNode);
        if (leftResult != null) {
            return leftResult; // If found in the left subtree, return the result
        }

        return findParentNodeHelper((BinaryNode<String>) currentNode.getRightChild(), target, currentNode); // Search in the right subtree
    }


    private static BinaryNode<String> createTree() {

/*       Creates a predefined binary tree structure with nodes representing vehicles and their types
      Initializes the root node as "vehicle" and creates nodes for various vehicle types
       Establishes the relationships between the nodes to form a binary tree structure.

 */

        BinaryNode<String> rootNode = new BinaryNode<>("vehicle");
        BinaryNode<String> n2 = new BinaryNode<>("car");
        BinaryNode<String> n3 = new BinaryNode<>("bike");
        BinaryNode<String> n4 = new BinaryNode<>("sedan");
        BinaryNode<String> n5 = new BinaryNode<>("sports car");
        BinaryNode<String> n6 = new BinaryNode<>("two-wheeler");
        BinaryNode<String> n7 = new BinaryNode<>("four-wheeler");
        BinaryNode<String> n8 = new BinaryNode<>("Toyota");
        BinaryNode<String> n9 = new BinaryNode<>("Ford");
        BinaryNode<String> n10 = new BinaryNode<>("Ferrari");
        BinaryNode<String> n11 = new BinaryNode<>("Lamborghini");
        BinaryNode<String> n12 = new BinaryNode<>("Harley Davidson");
        BinaryNode<String> n13 = new BinaryNode<>("Kawasaki");
        BinaryNode<String> n14 = new BinaryNode<>("Honda");

        rootNode.setLeftChild(n2);
        rootNode.setRightChild(n3);
        n2.setLeftChild(n4);
        n2.setRightChild(n5);
        n3.setLeftChild(n6);
        n3.setRightChild(n7);
        n4.setLeftChild(n8);
        n4.setRightChild(n9);
        n5.setLeftChild(n10);
        n5.setRightChild(n11);
        n6.setLeftChild(n12);
        n6.setRightChild(n13);
        n7.setLeftChild(n14);

        return rootNode;


    }


    private static void saveTree(BinaryNode<String> root, Scanner scanner) {
/*
        Saves the binary tree structure to a file using serialization.
        Prompts the user to enter the filename to save the tree to.
         writes the root BinaryNode<String> to the file.
*/
        System.out.println("Enter the filename to save the tree to");
        String filename = scanner.nextLine();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(root);
            System.out.println("Tree saved successfully to " + filename);
        } catch (FileNotFoundException e) {
            handleFileError("File not found", filename);
        } catch (IOException e) {
            handleFileError("Error saving the tree to file", e.getMessage());
        }
    }

    // util function to print err msg
    private static void handleFileError(String message, String filename) {
        System.err.println(message + ": " + filename);
    }

    // deserialises the loaded binary tree and casts it back to BinaryNode object
    private static BinaryNode<String> loadTree(String filename) {
        BinaryNode<String> root = null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = in.readObject();
            if (obj instanceof BinaryNode) {
                root = (BinaryNode<String>) obj;
                System.out.println("Tree loaded successfully from " + filename);
            } else {
                System.err.println("Error: Object read is not of type BinaryNode<String>");
            }
        } catch (FileNotFoundException e) {
            handleFileError("File not found", filename);
        } catch (IOException | ClassNotFoundException e) {
            handleFileError("Error loading the tree from file", e.getMessage());
        }

        return root;
    }

    /* Traverses the binary tree in pre-order fashion to build a visual representation of the tree structure.
      Appends the node data to a StringBuilder with appropriate padding and pointers.
         Recursively calls itself for the left and right children of the current node.
     */
// used https://www.baeldung.com/java-print-binary-tree-diagram
    private static void traversePreOrder(StringBuilder sb, BinaryNode<String> node, String padding, String pointer) {
        if (node != null) {
            sb.append(padding);
            sb.append(pointer);

            sb.append(node.getData()).append("\n");

            String paddingForBoth = padding + "│  ";
            String pointerForRight = "└──";
            String pointerForLeft = (node.getRightChild() != null) ? "├──" : "└──";

            traversePreOrder(sb, (BinaryNode<String>) node.getLeftChild(), paddingForBoth, pointerForLeft);
            traversePreOrder(sb, (BinaryNode<String>) node.getRightChild(), paddingForBoth, pointerForRight);
        }
    }
 //prints the visual representation of the binary tree by calling the recursive function above.
    private static void printTree(BinaryNode<String> root) {
        StringBuilder sb = new StringBuilder();
        traversePreOrder(sb, root, "", "");
        System.out.print(sb);
    }
}