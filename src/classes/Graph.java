package classes;
import java.util.*;

public class Graph {

    private static class Vertex {

        private int number;
        private Edge connectedEdges;

        public Vertex(int number, Edge connectedEdges) {
            this.number = number;
            this.connectedEdges = connectedEdges;
        }

            public int getNumber() {
                return number;
            }
    
    }

    private static class Edge {
        private int toVertex;
        private int weight;
        private Edge next;

        public Edge(int toVertex, int weight, Edge next) {
            this.toVertex = toVertex;
            this.weight = weight;
            this.next = next;
        }
    }

    private enum graphType
    {
        directed,
        undirected
    }

    private graphType type;
    private Vertex vertices[];
    private int vcount;

    public Graph(boolean type) {
    if(type)
        this.type = graphType.directed;
    else
        this.type = graphType.undirected;
    this.vertices = new Vertex[4];
    this.vcount = 0;
}


    private void increaseCapacity() {
        if (vcount == vertices.length) {
            Vertex newVertices[] = new Vertex[vertices.length * 2];
            for (int i = 0; i < vertices.length; i++) {
                newVertices[i] = vertices[i];
            }
            vertices = newVertices;
        }
    }

    private int findVindex(int number) {
        for (int i = 0; i < vcount; i++) {
            if(vertices[i] != null && vertices[i].getNumber() == number)
                return i;
        }
        return -1;
    }

    private boolean DirectEdge(int fromInd, int to) {
    Edge i = vertices[fromInd].connectedEdges;
    while(i != null) {
        if(i.toVertex == to)
            return true; 
        i = i.next;
    }
    return false;
}


    private void removeDirectEdge(int fromInd, int to) {
        Vertex vfrom = vertices[fromInd]; 
        Edge prev = null;
        Edge current = vfrom.connectedEdges;

        while(current !=null) {
            if(current.toVertex == to) {
                if(prev==null)
                    vfrom.connectedEdges = current.next;
                else 
                    prev.next = current.next;
                break;
            }
            prev = current;
            current = current.next;
        }
    }

    private int dfs(int Vindex, boolean visited[], int res[], int s) {
        if(visited[Vindex])
            return s;

        visited[Vindex] = true;
        res[s] = vertices[Vindex].getNumber();
        s++;

        for(Edge i = vertices[Vindex].connectedEdges; i != null; i = i.next) {
            int neighbor = findVindex(i.toVertex);
            if(!visited[neighbor])
                s = dfs(neighbor, visited, res, s);
        }
        return s;
    }

    private boolean edge(int from, int to, int weight, int action) {
        int fromIndex = findVindex(from);
        int toIndex = findVindex(to);

        if(fromIndex < 0 && toIndex < 0) {
            System.out.println("Both vertices doesn't exist"); 
            return false;
        }
        else if(fromIndex < 0) {
            System.out.println("Vertex from doesn't exist"); 
            return false;
        }
        else if(toIndex < 0) {
            System.out.println("Vertex to doesn't exist"); 
            return false;
        }

        if(action == 0) {
            if(type == graphType.directed) {
                if(!DirectEdge(fromIndex, to)) {
                    System.out.println("Edge doesn't exist");
                    return false;
                }
            removeDirectEdge(fromIndex, to);
            }
            else if(type == graphType.undirected) {
                if(!DirectEdge(fromIndex, to) && !DirectEdge(toIndex, from)) {
                    System.out.println("Edge doesn't exist");
                    return false;
                }
                removeDirectEdge(fromIndex, to);
                removeDirectEdge(toIndex, from);
            }
            return true;
        }
        if(action == 1) {
            if (type == graphType.directed) {
                if (DirectEdge(fromIndex, to)) {
                    System.out.println("Edge already exists");
                    return false;
                }
            }
            else if(type == graphType.undirected) {
                if (DirectEdge(fromIndex, to) || DirectEdge(toIndex, from)) {
                    System.out.println("Edge already exists");
                    return false;
                }
            }

            Vertex vfrom = vertices[fromIndex]; 
            Vertex vto = vertices[toIndex]; 

            Edge newEdge = new Edge(to, weight, vfrom.connectedEdges);
            vfrom.connectedEdges = newEdge;

            if(type == graphType.undirected) {
                Edge reverseEdge = new Edge(from, weight, vto.connectedEdges);
                vto.connectedEdges = reverseEdge;
            }
            return true;
        }
        return false;
    }



    public boolean removeEdge(int from, int to) {
        return edge(from, to, 0, 0);
    }

    public boolean addEdge(int from, int to, int weight) {
        return edge(from, to, weight, 1);
    }


    public boolean addVertex(int number) {
        for (int i = 0; i < vcount; i++) {
            if(vertices[i] != null && vertices[i].getNumber() == number) {
                System.out.println("Vertex already exists"); 
                return false;
            }
        }

        increaseCapacity();

        vertices[vcount] = new Vertex(number, null);
        vcount++;
        return true;
    }

    public boolean removeVertex(int number) {
        int removeIndex = findVindex(number);
        if(removeIndex < 0) {
            System.out.println("Vertex doesn't exist"); 
            return false;
        }

        vertices[removeIndex].connectedEdges = null;
        for(int i = 0; i < vcount; i++) {
            if(vertices[i] != null && i != removeIndex)
                removeDirectEdge(i, number);
        } 
        vertices[removeIndex] = null;
        vcount--;
        return true;
    }
    
    public List<Integer> getAdjacent(int number) {
        int Index = findVindex(number);
        if(Index < 0) {
            System.out.println("Vertex doesn't exist"); 
            return null;
        }

        List<Integer> AdjList = new ArrayList<>();
        Edge i = vertices[Index].connectedEdges;
        while(i != null) {
            AdjList.add(i.toVertex);
            i= i.next;
        }
        return AdjList;
    }

    public int[] dfs(int number) {
        int Index = findVindex(number);
        if(Index < 0) {
            System.out.println("Vertex doesn't exist"); 
            return null;
        }
        boolean visited[] = new boolean[vcount];
        int res[] = new int[vcount];
        int s = 0;
        Arrays.fill(res, -1);
        dfs(Index, visited, res, s);
        return res;
    }

    public int[] bfs(int number) {
        int Index = findVindex(number);
        if(Index < 0) {
            System.out.println("Vertex doesn't exist"); 
            return null;
        }

    boolean[] visited = new boolean[vcount];
    int[] res = new int[vcount];     
    int[] queue = new int[vcount];   
    int head = 0; 
    int tail = 0; 
    int s = 0;    
    Arrays.fill(res, -1);

    visited[Index] = true;
    queue[tail] = Index;
    tail++;

    while(head < tail) { 
        int vIndex = queue[head]; 
        head++;

        res[s] = vertices[vIndex].getNumber();
        s++;

        for(Edge i = vertices[vIndex].connectedEdges; i != null; i = i.next) {
            int neighbor = findVindex(i.toVertex);
            if(!visited[neighbor]) {
                visited[neighbor] = true;
                queue[tail] = neighbor;
                tail++;
            }
        }
    }
    return res;
    }

}


