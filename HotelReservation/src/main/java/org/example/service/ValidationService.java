package org.example.service;

import org.example.HotelApplication;
import org.example.enumeration.RoomType;
import org.example.model.Customer;
import org.example.model.IRoom;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ValidationService {
    private static ValidationService singleInstance = null;
    private static Scanner scanner = new Scanner(System.in);
    private final String EMAIL_REGEX = "\\A[0-9A-Za-z][\\+\\-./0-9A-Za-z_^\\?]*\\@([0-9A-Za-z\\-]+\\.)+[A-Za-z]{2,}\\Z";
    private final String NAME_REGEX = "[a-zA-Z ]+";

    private ValidationService() {
    }

    public static ValidationService getInstance() {
        if (singleInstance == null) {
            singleInstance = new ValidationService();
        }
        return singleInstance;
    }

    public int validateInputMenu() {
        int integer = 0;
        do {
            try {
                integer = Integer.parseInt(scanner.nextLine());
                return integer;
            } catch (Exception ex) {
                System.err.println("Invalid integer input");
            }
        } while (integer >= 1 && integer <= 5);
        return integer;
    }

    public int validateInputInteger() {
        while (true) {
            try {
                int integer = Integer.parseInt(scanner.nextLine());
                return integer;
            } catch (Exception ex) {
                System.err.println("Invalid integer input");
            }
        }
    }

    public double validateInputPrice() {
        while (true) {
            try {
                double price = Double.parseDouble(scanner.nextLine());
                if (price > 0) {
                    return price;
                } else {
                    System.out.println("Price must be > 0");
                }
            } catch (Exception ex) {
                System.err.println("Invalid double input");
            }
        }
    }

    public String validateInputEmail() {
        while (true) {
            try {
                String email = scanner.nextLine();
                if (email.matches(EMAIL_REGEX)) {
                    return email;
                } else {
                    System.err.println("Invalid email input");
                }
            } catch (Exception ex) {
                System.err.println("Invalid string input");
            }
        }
    }

    public Date validateInputDate() {
        while (true) {
            try {
                String date = scanner.nextLine();
                return new SimpleDateFormat("MM/dd/yyyy").parse(date);
            } catch (Exception ex) {
                System.err.println("Invalid date input");
            }
        }
    }

    public String validateInputName() {
        while (true) {
            try {
                String name = scanner.nextLine();
                if (name.matches(NAME_REGEX)) {
                    return name;
                } else {
                    System.err.println("Invalid name input");
                }
            } catch (Exception ex) {
                System.err.println("Invalid string input");
            }
        }
    }

    public RoomType validateInputRoomType() {
        while (true) {
            try {
                int integer = Integer.parseInt(scanner.nextLine());
                if (integer == 1) {
                    return RoomType.SINGLE;
                } else if (integer == 2) {
                    return RoomType.DOUBLE;
                } else {
                    System.err.println("Please choose 1 or 2");
                }
            } catch (Exception ex) {
                System.err.println("Invalid integer input");
            }
        }
    }

    public String validateInputYesNo() {
        while (true) {
            String yesNo = scanner.nextLine();
            if (yesNo.equalsIgnoreCase("y") || yesNo.equalsIgnoreCase("n")) {
                return yesNo;
            } else {
                System.err.println("Invalid y/n input");
            }
        }
    }

    public String validateInputRoomNumber() {
        while (true) {
            String roomNumber = scanner.nextLine();
            if (!isRoomNumberExisted(roomNumber)) {
                return roomNumber;
            } else {
                System.out.println("Room number existed, please enter another one");
            }

        }
    }

    public boolean isRoomNumberExisted(String roomNumber) {
        boolean check = false;
        for (IRoom room : HotelApplication.roomList) {
            if (room.getRoomNumber().equalsIgnoreCase(roomNumber)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public boolean isEmailExisted(String email) {
        boolean check = false;
        for (Customer customer : HotelApplication.customerList) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                check = true;
                break;
            }
        }
        return check;
    }
}