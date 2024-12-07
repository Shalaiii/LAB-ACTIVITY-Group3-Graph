import java.util.*;

public class Roadmap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RoadmapGraph roadmap = new RoadmapGraph();

        // Infinite loop for the interactive menu
        while (true) {
            // Displaying the menu options
            System.out.println("\nRoadmap Menu:");
            System.out.println("1. Add Location");
            System.out.println("2. Add Road");
            System.out.println("3. View Roadmap");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt(); // Reading user's choice
            scanner.nextLine(); // Consume the newline character

            // Handling user's choice using a switch-case
            switch (choice) {
                case 1:
                    System.out.print("Enter location name: "); // Prompt to enter a location name
                    String location = scanner.nextLine();
                    roadmap.addLocation(location); // Add the location to the graph
                    System.out.println("Location added!");
                    break;

                case 2:
                    System.out.print("Enter starting location: "); // Prompt for starting location
                    String from = scanner.nextLine();
                    System.out.print("Enter destination location: "); // Prompt for destination location
                    String to = scanner.nextLine();
                    System.out.print("Enter distance (km): "); // Prompt for the distance between locations
                    int distance = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    roadmap.addRoad(from, to, distance); // Add the road to the graph
                    System.out.println("Road added!");
                    break;

                case 3:
                    roadmap.printRoadmap(); // Display the current roadmap
                    break;

                case 4:
                    System.out.println("Exiting... Goodbye!"); // Exit message
                    scanner.close(); // Close the scanner
                    return; // Exit the program

                default:
                    System.out.println("Invalid choice. Please try again."); // Handle invalid input
            }
        }
    }
}

class RoadmapGraph {
    // Adjacency list to store locations and their connected roads
    private Map<String, List<Road>> adjList;

    // Constructor to initialize the adjacency list
    public RoadmapGraph() {
        adjList = new HashMap<>();
    }

    // Method to add a location (vertex) to the graph
    public void addLocation(String location) {
        if (!adjList.containsKey(location)) { // Only add if the location doesn't already exist
            adjList.put(location, new ArrayList<Road>()); // Initialize the adjacency list for the location
        }
    }

    // Method to add a road (edge) between two locations
    public void addRoad(String from, String to, int distance) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) { // Check if both locations exist
            System.out.println("Error: Both locations must exist."); // Error message for missing locations
            return;
        }
        // Add the road in both directions to make the graph undirected
        adjList.get(from).add(new Road(to, distance));
        adjList.get(to).add(new Road(from, distance));
    }

    // Method to print the entire roadmap (graph)
    public void printRoadmap() {
        if (adjList.isEmpty()) { // Check if the graph is empty
            System.out.println("The roadmap is empty. Add locations and roads first.");
            return;
        }
        System.out.println("Roadmap:");
        for (String location : adjList.keySet()) { // Iterate through all locations
            System.out.print(location + " -> "); // Print the location
            for (Road road : adjList.get(location)) { // Iterate through connected roads
                System.out.print(road.destination + " (" + road.distance + " km), "); // Print each road
            }
            System.out.println(); // Move to the next line
        }
    }
}

// Class to represent a road (edge) between two locations
class Road {
    String destination; // Destination location
    int distance; // Distance to the destination

    // Constructor to initialize road properties
    public Road(String destination, int distance) {
        this.destination = destination;
        this.distance = distance;
    }
}
