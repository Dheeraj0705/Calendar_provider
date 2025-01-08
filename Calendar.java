import java.util.Scanner;

public class Calendar {

    private int total_no_of_odd_days;

    public Calendar() {
        System.out.println("\n");
        System.out.println("                A Project ");
        System.out.println("                   ON ");
        System.out.println("                CALENDAR   ");
        System.out.println("---------------------------------------------------------------");
        System.out.println("                                   MADE BY: DHEERAJ KUMAR");
        System.out.println("                                URN: 2102150");
        System.out.println("                                  CGC-CEC    ");
        System.out.println("\nPlease enter the date in the correct format:");
        System.out.println("Enter date, month, and year of the calendar.");
        System.out.println("\nChoose an option:");
        System.out.println("1. Find the day of the week for a specific date.");
        System.out.println("2. Display the whole calendar for a year.");
        System.out.println("3. Display the calendar for a specific month.");
        System.out.println("\nPress Enter to continue...");
        new Scanner(System.in).nextLine();
        clearScreen();
    }

    private void calculation(int day, int month, int year) {
        int totalDays = 0;

        // Days in previous years
        int yearsCompleted = year - 1;
        totalDays += yearsCompleted * 365 + (yearsCompleted / 4) - (yearsCompleted / 100) + (yearsCompleted / 400);

        // Days in previous months of the same year
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(year)) daysInMonth[1] = 29; // February in leap year

        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonth[i];
        }

        // Days in the current month
        totalDays += day;

        total_no_of_odd_days = totalDays % 7; // Determine the day of the week
    }

    private boolean isLeapYear(int year) {
        return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
    }

    public void printDayOfWeek() {
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        System.out.println("The day of the week is: " + daysOfWeek[total_no_of_odd_days]);
    }

    public void displayMonth(int year, int month) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(year)) daysInMonth[1] = 29; // February in leap year

        System.out.printf("\n%s %d\n", months[month - 1], year);
        System.out.println("Sun  Mon  Tue  Wed  Thu  Fri  Sat");

        calculation(1, month, year);
        int startingDay = total_no_of_odd_days;

        // Print leading spaces
        for (int i = 0; i < startingDay; i++) {
            System.out.print("     ");
        }

        // Print the days of the month
        for (int day = 1; day <= daysInMonth[month - 1]; day++) {
            System.out.printf("%3d  ", day);
            if ((startingDay + day) % 7 == 0) System.out.println();
        }
        System.out.println();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calendar calendar = new Calendar();

        while (true) {
            System.out.println("Enter your choice (1, 2, or 3): ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter day: ");
                    int day = sc.nextInt();
                    System.out.println("Enter month in numerical form: ");
                    int month = sc.nextInt();
                    System.out.println("Enter year: ");
                    int year = sc.nextInt();

                    calendar.calculation(day, month, year);
                    calendar.printDayOfWeek();
                    break;

                case 2:
                    System.out.println("Enter year: ");
                    year = sc.nextInt();
                    for (month = 1; month <= 12; month++) {
                        calendar.displayMonth(year, month);
                    }
                    break;

                case 3:
                    System.out.println("Enter year: ");
                    year = sc.nextInt();
                    System.out.println("Enter month in numerical form: ");
                    month = sc.nextInt();

                    calendar.displayMonth(year, month);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
