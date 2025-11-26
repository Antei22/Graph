import classes.Graph;
import java.util.*;

public class Main {
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("------------- GRAPH TYPE -------------");
        System.out.println("0. Directed graph");
        System.out.println("1. Undirected graph");
        System.out.print("Choose option: ");

        int type = -1;
        boolean vt = false;

        while(!vt) {
            try {
                type = scan.nextInt();
                scan.nextLine();
                if (type == 0 || type == 1) {
                    vt = true;
                } else {
                    System.out.println("Invalid option.Enter 0 or 1.");
                    System.out.print("Choose option: ");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
                System.out.print("Choose option: ");
            }
        }

        Graph graph = new Graph(type == 0);

        while (true) {
            try {
                System.out.println("\n------------- GRAPH MENU -------------");
                System.out.println("1. Add vertex");
                System.out.println("2. Remove vertex");
                System.out.println("3. Add edge");
                System.out.println("4. Remove edge");
                System.out.println("5. Show adjacent vertices");
                System.out.println("6. DFS");
                System.out.println("7. BFS");
                System.out.println("8. Exit program.");
                System.out.print("\nChoose option: ");

                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 1: {
                        boolean k = false;
                        int number = 0;

                        while(!k) {
                            try {
                                System.out.print("Enter vertex number to add(except -1): ");
                                number = scan.nextInt();
                                scan.nextLine();
                                k = true;
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scan.nextLine();
                            }
                        }

                        if(graph.addVertex(number))
                            System.out.println("Vertex created");
                        break;
                    }

                    case 2: {
                        boolean k = false;
                        int number = 0;

                        while(!k) {
                            try {
                                System.out.print("Enter vertex number to remove: ");
                                number = scan.nextInt();
                                scan.nextLine();
                                k = true;
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scan.nextLine();
                            }
                        }

                        if(graph.removeVertex(number))
                            System.out.println("Vertex removed");
                        break;
                    }

                    case 3: {
                        int from = 0;
                        int to = 0;
                        int weight = 0;
                        boolean vFrom = false;
                        boolean vTo = false;
                        boolean vW = false;

                        while(!vFrom) {
                            try {
                                System.out.print("Enter vertex FROM: ");
                                from = scan.nextInt();
                                scan.nextLine();
                                vFrom = true;
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scan.nextLine();
                            }
                        }

                        while (!vTo) {
                            try {
                                System.out.print("Enter vertex TO: ");
                                to = scan.nextInt();
                                scan.nextLine();
                                vTo = true;
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scan.nextLine();
                            }
                        }

                        while (!vW) {
                            try {
                                System.out.print("Enter edge weight: ");
                                weight = scan.nextInt();
                                scan.nextLine();
                                vW = true;
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scan.nextLine();
                            }
                        }

                        if(graph.addEdge(from, to, weight))
                            System.out.println("Edge created");
                        break;
                    }

                    case 4: {
                        int from = 0;
                        int to = 0;
                        boolean vFrom = false;
                        boolean vTo = false;

                        while (!vFrom) {
                            try {
                                System.out.print("Enter vertex FROM: ");
                                from = scan.nextInt();
                                scan.nextLine();
                                vFrom = true;
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scan.nextLine();
                            }
                        }

                        while (!vTo) {
                            try {
                                System.out.print("Enter vertex TO: ");
                                to = scan.nextInt();
                                scan.nextLine();
                                vTo = true;
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scan.nextLine();
                            }
                        }

                        if(graph.removeEdge(from, to))
                            System.out.println("Edge removed");
                        break;
                    }

                    case 5: {
                        boolean k = false;
                        int number = 0;

                        while (!k) {
                            try {
                                System.out.print("Enter vertex number: ");
                                number = scan.nextInt();
                                scan.nextLine();
                                k = true;
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scan.nextLine();
                            }
                        }

                        List<Integer> adj = graph.getAdjacent(number);
                        if (adj != null) {
                            if (adj.isEmpty()) {
                                System.out.println("No adjacent vertices.");
                            } else {
                                System.out.println("Adjacent vertices:");
                                for(int i = 0; i < adj.size(); i++) {
                                    int v = adj.get(i);
                                    System.out.println(v);
                                }
                            }
                        }
                        break;
                    }

                    case 6: {
                        boolean k = false;
                        int start = 0;

                        while (!k) {
                            try {
                                System.out.print("Enter start vertex for DFS: ");
                                start = scan.nextInt();
                                scan.nextLine();
                                k = true;
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scan.nextLine();
                            }
                        }

                        int[] res = graph.dfs(start);
                        if (res != null) {
                            System.out.print("DFS result: ");
                            printArr(res);
                        }
                        break;
                    }

                    case 7: {
                        boolean k = false;
                        int start = 0;

                        while (!k) {
                            try {
                                System.out.print("Enter start vertex for BFS: ");
                                start = scan.nextInt();
                                scan.nextLine();
                                k = true;
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please enter a number.");
                                scan.nextLine();
                            }
                        }

                        int[] res = graph.bfs(start);
                        if (res != null) {
                            System.out.print("BFS result: ");
                            printArr(res);
                        }
                        break;
                    }

                    case 8:
                        System.out.println("Exit program.");
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
            }
        }
    }

    private static void printArr(int[] res) {
        boolean first = true;
        for (int i = 0; i < res.length; i++) {
            int x = res[i];

            if(x == -1)
                continue;
            if(!first) 
                System.out.print(" ");
            System.out.print(x);
            first = false;
        }
        System.out.println();
    }

}
