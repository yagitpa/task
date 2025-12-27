package org.example;

import java.util.Arrays;
import java.util.List;

public class BookFormatter {

    static class Book {
        private String title;
        private String authorFullName;

        public Book(String title, String authorFullName) {
            this.title = title;
            this.authorFullName = authorFullName;
        }

        private static String complexName(String[] nameParts) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < nameParts.length - 1; i++) {
                if (i > 0) {
                    sb.append(" ");
                }
                sb.append(nameParts[i]);
            }
            return sb + " " + nameParts[nameParts.length - 1];
        }

        public static void main(String[] args) {
            List<Book> books = Arrays.asList(
                    new Book("Война и мир", "Лев Николаевич Толстой"),
                    new Book("Преступление и наказание", "Фёдор Михайлович Достоевский"),
                    new Book("Мастер и Маргарита", "Михаил Афанасьевич Булгаков"),
                    new Book("1984", "Джордж Оруэлл"),
                    new Book("Мы", "Евгений Замятин"),
                    new Book("Гамлет", "Шекспир"),
                    new Book("Сказки", ""),
                    new Book("", "Николай Некрасов")
            );

            System.out.println("ФОРМАТИРОВАННЫЙ СПИСОК КНИГ");
            System.out.println("=".repeat(60));

            books.stream()
                 .map(book -> formattedBook(book.getTitle(), book.getAuthorFullName()))
                 .sorted()
                 .forEach(System.out::println);
        }

        private static String formattedBook(String title, String authorFullName) {
            title = title.trim();
            authorFullName = authorFullName.trim();

            String formattedAuthor = formattedAuthorName(authorFullName);

            return String.format("\"%s\" %s",
                    title.trim().isEmpty() ? "[Без названия]" : title,
                    formattedAuthor.trim().isEmpty() ? "[Автор неизвестен]" : formattedAuthor
            );
        }

        private static String formattedAuthorName(String fullName) {
            if (fullName == null || fullName.trim().isEmpty()) {
                return "";
            }

            String[] nameParts = fullName.trim().split("\\s+");

            return switch (nameParts.length) {
                case 1 -> nameParts[0];
                case 2 -> nameParts[0] + " " + nameParts[1];
                case 3 -> nameParts[0] + " " + nameParts[1] + " " + nameParts[2];
                default -> complexName(nameParts);
            };
        }

        public String getTitle() {
            return title;
        }

        public String getAuthorFullName() {
            return authorFullName;
        }
    }
}

