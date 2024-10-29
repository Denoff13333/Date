import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        // Задача 1
        System.out.println("Задача 1: Текущие дата и время");
        printCurrentDateTime();

        // Задача 2
        System.out.println("\nЗадача 2: Сравнение двух дат");
        System.out.println(compareDates(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 2)));

        // Задача 3
        System.out.println("\nЗадача 3: Количество дней до Нового года");
        System.out.println("Дней до Нового года: " + daysUntilNewYear());

        // Задача 4
        System.out.println("\nЗадача 4: Проверка високосного года");
        System.out.println("Год 2024 високосный? " + isLeapYear(2024));

        // Задача 5
        System.out.println("\nЗадача 5: Количество выходных в месяце");
        System.out.println("Выходных в феврале 2024 года: " + countWeekends(2, 2024));

        // Задача 6
        System.out.println("\nЗадача 6: Время выполнения метода");
        measureExecutionTime(() -> {
            for (int i = 0; i < 1_000_000; i++);
        });

        // Задача 7
        System.out.println("\nЗадача 7: Форматирование и парсинг даты");
        System.out.println("Новая дата: " + parseAndFormatDate("25-10-2024"));

        // Задача 8
        System.out.println("\nЗадача 8: Конвертация времени в московский часовой пояс");
        System.out.println("Время в Москве: " + convertToMoscowTime(LocalDateTime.now()));

        // Задача 9
        System.out.println("\nЗадача 9: Вычисление возраста");
        System.out.println("Возраст: " + calculateAge(LocalDate.of(1990, 5, 15)));

        // Задача 10
        System.out.println("\nЗадача 10: Календарь на месяц с рабочими днями и выходными");
        printMonthlyCalendar(10, 2024);

        // Задача 11
        System.out.println("\nЗадача 11: Случайная дата в диапазоне");
        System.out.println("Случайная дата: " + generateRandomDate(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31)));

        // Задача 12
        System.out.println("\nЗадача 12: Время до события");
        System.out.println("Время до события: " + timeUntilEvent(LocalDateTime.of(2024, 12, 31, 23, 59)));

        // Задача 13
        System.out.println("\nЗадача 13: Количество рабочих часов в диапазоне");
        System.out.println("Рабочих часов: " + calculateWorkingHours(LocalDateTime.of(2024, 10, 1, 9, 0), LocalDateTime.of(2024, 10, 31, 18, 0)));

        // Задача 14
        System.out.println("\nЗадача 14: Форматирование даты с учетом локали");
        System.out.println("Дата с локалью: " + formatDateWithLocale(LocalDate.now(), new Locale("ru")));

        // Задача 15
        System.out.println("\nЗадача 15: День недели на русском");
        System.out.println("День недели: " + getDayOfWeekInRussian(LocalDate.now()));
    }

    // Задача 1
    public static void printCurrentDateTime() {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        System.out.println("Дата: " + currentDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.println("Время: " + currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    // Задача 2
    public static String compareDates(LocalDate date1, LocalDate date2) {
        if (date1.isAfter(date2)) {
            return "Первая дата больше";
        } else if (date1.isBefore(date2)) {
            return "Первая дата меньше";
        } else {
            return "Даты равны";
        }
    }

    // Задача 3
    public static long daysUntilNewYear() {
        LocalDate today = LocalDate.now();
        LocalDate newYear = LocalDate.of(today.getYear() + 1, 1, 1);
        return ChronoUnit.DAYS.between(today, newYear);
    }

    // Задача 4
    public static boolean isLeapYear(int year) {
        return LocalDate.of(year, 1, 1).isLeapYear();
    }

    // Задача 5
    public static int countWeekends(int month, int year) {
        YearMonth yearMonth = YearMonth.of(year, month);
        int weekends = 0;

        for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
            LocalDate date = yearMonth.atDay(day);
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                weekends++;
            }
        }

        return weekends;
    }

    // Задача 6
    public static void measureExecutionTime(Runnable task) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();

        System.out.println("Время выполнения: " + (end - start) / 1_000_000 + " миллисекунд");
    }

    // Задача 7
    public static String parseAndFormatDate(String dateStr) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        LocalDate date = LocalDate.parse(dateStr, inputFormatter);
        LocalDate newDate = date.plusDays(10);

        return newDate.format(outputFormatter);
    }

    // Задача 8
    public static ZonedDateTime convertToMoscowTime(LocalDateTime dateTime) {
        ZonedDateTime utcDateTime = dateTime.atZone(ZoneId.of("UTC"));
        return utcDateTime.withZoneSameInstant(ZoneId.of("Europe/Moscow"));
    }

    // Задача 9
    public static int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    // Задача 10
    public static void printMonthlyCalendar(int month, int year) {
        YearMonth yearMonth = YearMonth.of(year, month);

        for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
            LocalDate date = yearMonth.atDay(day);
            String dayType = (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) ? "Выходной" : "Рабочий день";
            System.out.println(date + " - " + dayType);
        }
    }

    // Задача 11
    public static LocalDate generateRandomDate(LocalDate startDate, LocalDate endDate) {
        long start = startDate.toEpochDay();
        long end = endDate.toEpochDay();

        long randomEpochDay = ThreadLocalRandom.current().nextLong(start, end + 1);
        return LocalDate.ofEpochDay(randomEpochDay);
    }

    // Задача 12
    public static String timeUntilEvent(LocalDateTime eventDateTime) {
        Duration duration = Duration.between(LocalDateTime.now(), eventDateTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return hours + " часов, " + minutes + " минут, " + seconds + " секунд";
    }

    // Задача 13
    public static long calculateWorkingHours(LocalDateTime start, LocalDateTime end) {
        long totalHours = 0;

        for (LocalDateTime dateTime = start; dateTime.isBefore(end); dateTime = dateTime.plusDays(1)) {
            if (!(dateTime.toLocalDate().getDayOfWeek() == DayOfWeek.SATURDAY || dateTime.toLocalDate().getDayOfWeek() == DayOfWeek.SUNDAY)) {
                totalHours += ChronoUnit.HOURS.between(dateTime, dateTime.plusDays(1).withHour(9));
            }
        }

        return totalHours;
    }

    // Задача 14
    public static String formatDateWithLocale(LocalDate date, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", locale);
        return date.format(formatter);
    }

    // Задача 15
    public static String getDayOfWeekInRussian(LocalDate date) {
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("ru"));
    }
}
