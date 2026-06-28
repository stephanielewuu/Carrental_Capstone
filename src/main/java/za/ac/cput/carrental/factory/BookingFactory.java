/*Author: Tanatswa Mabhodha
Student No: 220637482
Date: 27/06/2026

 */

package za.ac.cput.carrental.factory;


import za.ac.cput.carrental.domain.Booking;
import za.ac.cput.carrental.util.Helper;

public class BookingFactory {
    public static Booking createBooking(String bookingId,
                                        String memberId,
                                        String carId,
                                        String startDate,
                                        String endDate,
                                        double totalCost){
        return new Booking.Builder()
                .setBookingId(Helper.generateId())
                .setMemberId(memberId)
                .setCarId(carId)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setTotalCost(totalCost)
                .build();
    }

}
