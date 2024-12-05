import java.util.*;

public class Roadmap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RoadmapGraph roadmap = new RoadmapGraph();

        while (true) {
            System.out.println("\nRoadmap Menu:");
            System.out.println("1. Add Location");
            System.out.println("2. Add Road");
            System.out.println("3. View Roadmap");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter location name: ");
                    String location = scanner.nextLine();
                    roadmap.addLocation(location);
                    System.out.println("Location added!");
                    break;

                case 2:
                    System.out.print("Enter starting location: ");
                    String from = scanner.nextLine();
                    System.out.print("Enter destination location: ");
                    String to = scanner.nextLine();
                    System.out.print("Enter distance (km): ");
                    int distance = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    roadmap.addRoad(from, to, distance);
                    System.out.println("Road added!");
                    break;

                case 3:
                    roadmap.printRoadmap();
                    break;

                case 4:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class RoadmapGraph {
    private Map<String, List<Road>> adjList;

    public RoadmapGraph() {
        adjList = new HashMap<>();
    }

    public void addLocation(String location) {
        if (!adjList.containsKey(location)) {
            adjList.put(location, new ArrayList<Road>());
        }
    }

    public void addRoad(String from, String to, int distance) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            System.out.println("Error: Both locations must exist.");
            return;
        }
        adjList.get(from).add(new Road(to, distance));
        adjList.get(to).add(new Road(from, distance)); // Undirected graph
    }

    public void printRoadmap() {
        if (adjList.isEmpty()) {
            System.out.println("The roadmap is empty. Add locations and roads first.");
            return;
        }
        System.out.println("Roadmap:");
        for (String location : adjList.keySet()) {
            System.out.print(location + " -> ");
            for (Road road : adjList.get(location)) {
                System.out.print(road.destination + " (" + road.distance + " km), ");
            }
            System.out.println();
        }
    }
}

class Road {
    String destination;
    int distance;

    public Road(String destination, int distance) {
        this.destination = destination;
        this.distance = distance;
    }
}
