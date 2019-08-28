package carshop;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        CarDAO carDAO;
        try {
            carDAO = new CarDAO();
        } catch (SQLException e) {
            System.err.println("Car dao cannot be created. Mysql error");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return;
        } catch (IOException e) {
            System.err.println("Configuration file error");
            System.err.println("Error: " + e.getMessage());
            return;
        }

        do {
            System.out.println("co chcesz zrobić? \n" +
                    "dodać samochód (d) :: listuj (l) :: usuń po id (uid) :: usuń po nr rejestracyjnym (urej) :: " +
                    "wypisz po rejestracji (lrej) :: wypisz po nazwisku właściwciela (lname) :: listuj po marce (lmarka) :: quit (q)");
            command = scanner.nextLine();
            try {
                switch (command) {
                    case "d":
                        System.out.println("podaj numer rejestracyjny");
                        String registrationNumber = scanner.nextLine();

                        System.out.println("podaj przebieg");
                        int milage = Integer.parseInt(scanner.nextLine());

                        System.out.println("podaj markę i model");
                        String carBrandAndModel = scanner.nextLine();

                        System.out.println("podaj rok produkcji");
                        int yearOfProduction = Integer.parseInt(scanner.nextLine());

                        System.out.println("podaj typ (KOMBI) (SEDAN) (HATCHBACK) (CABRIO) (SUV)");
                        CarType carType = CarType.valueOf(scanner.nextLine().toUpperCase());

                        System.out.println("podaj nazwisko własciciela");
                        String ownerName = scanner.nextLine();

                        Car car = new Car(null, registrationNumber, milage, carBrandAndModel, yearOfProduction, carType, ownerName);

                        carDAO.insertCar(car);
                        break;
                    case "uid":
                        System.out.println("podaj id samochodu do usunięcia");
                        int idToDelete = Integer.parseInt(scanner.nextLine());
                        System.out.println(carDAO.deleteCarById(idToDelete));
                        break;
                    case "urej":
                        System.out.println("podaj nr rejestracyjny samochodu do usunięcia");
                        String rejestrationNubmberToDelete = scanner.nextLine();
                        System.out.println(carDAO.deleteCarByRegistrationNumber(rejestrationNubmberToDelete));
                        break;
                    case "l":
                        carDAO.listAllCars().forEach(System.out::println);
                        break;
                    case "lrej":
                        System.out.println("podaj nr rejestracyjny samochodu do wypisania");
                        String rejestrationNubmberToSelect = scanner.nextLine();
                        System.out.println(carDAO.getByRegistrationNumber(rejestrationNubmberToSelect));
                        break;
                    case "lname":
                        System.out.println("podaj nazwisko właściciela do wypisania");
                        String searchedName = scanner.nextLine();
                        System.out.println(carDAO.getByOwnerName(searchedName));
                        break;
                    case "lmarka":
                        System.out.println("podaj numer rejestracyjny do wypisania");
                        String searchedNumber = scanner.nextLine();
                        System.out.println(carDAO.getByRegistrationNumber(searchedNumber));
                        break;
                    case "q":
                        break;
                    default:
                        System.err.println("podałeś złą komendę, spórbuj jeszcze raz");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } while (!command.equalsIgnoreCase("q"));
    }


}
