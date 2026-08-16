import java.util.ArrayList;
import java.util.Scanner;

public class CafeQueueAdmin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> queueList = new ArrayList<>();
        ArrayList<String> timeList = new ArrayList<>();
        ArrayList<String> servedList = new ArrayList<>();
        ArrayList<String> servedTimeList = new ArrayList<>();

        final String ADMIN_PASSWORD = "Admin8987";
        final int MAX_QUEUE = 10;

        System.out.println("=== CafeQueueAdmin ===");
        System.out.print("Enter Password : ");
        String inputPassword = scanner.nextLine();

        if (!inputPassword.equals(ADMIN_PASSWORD)) {
            System.out.println("The password is incorrect! Unable to log in");
            return;
        }

        System.out.println("Login successful!");

        boolean running = true;

        while (running) {
            System.out.println("-------------------------------------");
            System.out.println("Queue Management Menu (Current Queue : "
                    + queueList.size() + "/" + MAX_QUEUE + ")");
            System.out.println("1.Add Customer To The Queue");
            System.out.println("2.Remove (Next Queue)");
            System.out.println("3.View All Customer");
            System.out.println("4.End Transaction");
            System.out.print("Select Option (1-4): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    if (queueList.size() >= MAX_QUEUE) {
                        System.out.println("[!] Queue capacity reached.");
                    } else {
                        System.out.print("Enter Customer Name : ");
                        String name = scanner.nextLine().trim();

                        System.out.print("Enter Service Time (HH:mm) : ");
                        String time = scanner.nextLine().trim();

                        if (!time.matches("([01][0-9]|2[0-3]):[0-5][0-9]")) {
                            System.out.println("[!] Invalid time format.");
                        } else {
                            int hour = Integer.parseInt(time.substring(0, 2));
                            int minute = Integer.parseInt(time.substring(3, 5));
                            int totalMinutes = hour * 60 + minute;

                            if (totalMinutes < 600 || totalMinutes > 1080) {
                                System.out.println("[!] Booking time must be between 10:00 and 18:00.");
                            } else if (timeList.contains(time)) {
                                System.out.println("[!] This time is already booked.");
                            } else if (name.isEmpty()) {
                                System.out.println("[!] Customer name cannot be empty.");
                            } else {
                                queueList.add(name);
                                timeList.add(time);
                                System.out.println("[✓] Added successfully.");
                            }
                        }

                        String[] t = time.split(":");
                        int hour = Integer.parseInt(t[0]);
                        int minute = Integer.parseInt(t[1]);

                        int totalMinutes = hour * 60 + minute;

                        if (totalMinutes < 600 || totalMinutes > 1080) {
                            System.out.println("[!] Booking time must be between 10:00-18:00.");
                        } else if (timeList.contains(time)) {
                            System.out.println("[!] This time is already booked.");
                        } else if (name.isEmpty()) {
                            System.out.println("[!] Customer name cannot be empty.");
                        } else {
                            queueList.add(name);
                            timeList.add(time);
                            System.out.println("[✓] Added successfully.");
                        }
                    }
                    break;

                case "2":
                    if (queueList.isEmpty()) {
                        System.out.println("[!] No customers in the queue.");
                    } else {
                        for (int i = 0; i < queueList.size(); i++) {
                            System.out.println((i + 1) + ". "
                                    + queueList.get(i) + " | " + timeList.get(i));
                        }

                        System.out.print("Enter Queue Number to remove: ");

                        try {
                            int num = Integer.parseInt(scanner.nextLine()) - 1;

                            if (num >= 0 && num < queueList.size()) {
                                servedList.add(queueList.remove(num));
                                servedTimeList.add(timeList.remove(num));
                                System.out.println("[✓] Removed successfully.");
                            } else {
                                System.out.println("[!] Invalid Queue Number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("[!] Please enter a valid number.");
                        }
                    }
                    break;

                case "3":
                    if (queueList.isEmpty()) {
                        System.out.println("--- No Queue Waiting ---");
                    } else {
                        for (int i = 0; i < queueList.size(); i++) {
                            System.out.println("Queue No. " + (i + 1) + ": "
                                    + queueList.get(i) + " | " + timeList.get(i));
                        }
                    }
                    break;

                case "4":
                    running = false;

                    System.out.println("=== DAILY QUEUE SUMMARY ===");

                    System.out.println("[ Served Customers ]");
                    for (int i = 0; i < servedList.size(); i++) {
                        System.out.println((i + 1) + ". "
                                + servedList.get(i) + " | "
                                + servedTimeList.get(i));
                    }

                    System.out.println("[ Remaining Customers ]");
                    for (int i = 0; i < queueList.size(); i++) {
                        System.out.println((i + 1) + ". "
                                + queueList.get(i) + " | "
                                + timeList.get(i));
                    }

                    System.out.println("Total Customers Today: "
                            + (servedList.size() + queueList.size()));

                    System.out.println("----- Queue System Closed -----");
                    break;

                default:
                    System.out.println("[!] Please select option 1-4 only");
            }
        }

        scanner.close();
    }
}