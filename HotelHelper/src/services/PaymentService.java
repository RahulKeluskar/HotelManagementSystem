package services;

import db.FileHandler;
import entities.Payment;
import entities.ReservedRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PaymentService {
    FileHandler fileHandler;
    UserService userService;
    RoomService roomService;
    ReservationService reservationService;

    public PaymentService() {
        this.fileHandler = new FileHandler();
        this.userService = new UserService();
        this.roomService = new RoomService();
        this.reservationService = new ReservationService();
    }

    public boolean getPaymentStatus(){
        return false;
    }

    public void completePayment(String reservationId, float amount, String userId) throws IOException {
        System.out.println("Completing payment for reservation id: "+ reservationId + " for Rs. "+ Float.toString(amount));
        ReservedRoom reservation = reservationService.findReservationById(reservationId);
        System.out.println("This is the bill generated for User with id: " + reservation.getUserId());
        System.out.println(reservation.getBill());


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Payment payment = new Payment();
        System.out.println("Please enter the upi id");
        payment.setUpiAddress(br.readLine());
        payment.setUserId(userId);
        payment.setAmount(amount);
        (new FileHandler()).insertPayment(payment);







    }
}
