package za.ac.cput.carrental.domain;

/* Car.java
     Car Class
     Author: Malwandla Blessing Mahori (230962963)
     Date: 21 June 2026 */

import jakarta.persistence.*;


import java.util.Objects;


@Entity
@Table(name = "cars")
public class Car {

    @Id
    @Column(name = "car_id", nullable = false, updatable = false)
    private String carId;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int year;

    @Column(name = "daily_rate", nullable = false)
    private double dailyRate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarStatus status;

    @Column(nullable = false)
    private int mileage;


    protected Car() {
    }


    private Car(Builder b) {
        this.carId = b.carId;
        this.make = b.make;
        this.model = b.model;
        this.year = b.year;
        this.dailyRate = b.dailyRate;
        this.status = b.status;
        this.mileage = b.mileage;
    }

    public static Builder builder() {
        return new Builder();
    }

    // ---------- Getters ----------

    public String getCarId() {
        return carId;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public CarStatus getStatus() {
        return status;
    }

    public int getMileage() {
        return mileage;
    }

    // Behavior


    public void setStatus(CarStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("status cannot be null");
        }
        this.status = newStatus;
    }


    public void markForMaintenance() {
        if (this.status == CarStatus.RENTED) {
            throw new IllegalStateException(
                    "Cannot send car " + carId + " to maintenance while it is currently rented");
        }
        this.status = CarStatus.UNDER_MAINTENANCE;
    }


    public void updateMileage(int newMileage) {
        if (newMileage < this.mileage) {
            throw new InvalidMileageException(
                    "New mileage (" + newMileage + ") cannot be less than current mileage ("
                            + this.mileage + ")");
        }
        this.mileage = newMileage;
    }

    public boolean isAvailable() {
        return this.status == CarStatus.AVAILABLE;
    }

    // Identity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId='" + carId + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", dailyRate=" + dailyRate +
                ", status=" + status +
                ", mileage=" + mileage +
                '}';
    }

    // Nested: CarStatus


    public enum CarStatus {
        AVAILABLE,
        RENTED,
        UNDER_MAINTENANCE,
        OUT_OF_SERVICE
    }

    // Nested: InvalidMileageException


    public static class InvalidMileageException extends RuntimeException {
        public InvalidMileageException(String message) {
            super(message);
        }
    }

    //  Nested: Builder
    public static class Builder {
        private String carId;
        private String make;
        private String model;
        private int year;
        private double dailyRate;
        private CarStatus status = CarStatus.AVAILABLE;
        private int mileage = 0;

        private Builder() {
        }

        public Builder carId(String carId) {
            this.carId = carId;
            return this;
        }

        public Builder make(String make) {
            this.make = make;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder dailyRate(double dailyRate) {
            this.dailyRate = dailyRate;
            return this;
        }

        public Builder status(CarStatus status) {
            this.status = status;
            return this;
        }

        public Builder mileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Car build() {
            if (carId == null || carId.isBlank()) {
                throw new IllegalArgumentException("carId is required");
            }
            if (make == null || make.isBlank()) {
                throw new IllegalArgumentException("make is required");
            }
            if (model == null || model.isBlank()) {
                throw new IllegalArgumentException("model is required");
            }
            if (dailyRate < 0) {
                throw new IllegalArgumentException("dailyRate must be zero or positive");
            }
            if (status == null) {
                throw new IllegalArgumentException("status cannot be null");
            }
            if (mileage < 0) {
                throw new IllegalArgumentException("mileage cannot be negative");
            }
            return new Car(this);
        }
    }
}

