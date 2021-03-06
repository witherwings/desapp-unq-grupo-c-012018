package main.model.builders;

import main.model.*;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class PublicationBuilder {

  private int publicationId;
  private Vehicle vehicle;
  private User owner;
  private Availability availability = new Availability(DateTime.now(), DateTime.now().plusDays(1));
  private List<Reservation> reservations = new ArrayList<>();
  private List<Reservation> acceptedReservations = new ArrayList<>();
  private float rentFeeHour;
  private float rentFeeDay;

  public PublicationBuilder withVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
    return this;
  }

  public Publication build() {
    Publication publication = new Publication();

    publication.setPublicationId(publicationId);
    publication.setVehicleOffered(vehicle);
    publication.setOwner(owner);
    publication.setAvailability(availability);
    publication.setReservations(reservations);
    publication.setAcceptedReservations(acceptedReservations);
    publication.setRentFeeHour(rentFeeHour);
    publication.setRentFeeDay(rentFeeDay);

    return publication;
  }

  public PublicationBuilder withOwner(User owner) {
    this.owner = owner;
    return this;
  }

  public PublicationBuilder withAvailability(Availability availability) {
    this.availability = availability;
    return this;
  }

  public PublicationBuilder withPublicationId(int publicationId) {
    this.publicationId = publicationId;
    return this;
  }

  public PublicationBuilder withRentFeeHour(float rentFeeHour) {
    this.rentFeeHour = rentFeeHour;
    return this;
  }

  public PublicationBuilder withRentFeeDay(float rentFeeDay) {
    this.rentFeeDay = rentFeeDay;
    return this;
  }
}
