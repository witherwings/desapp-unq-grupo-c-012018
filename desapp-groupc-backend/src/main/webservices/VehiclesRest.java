package main.webservices;

import main.model.Vehicle;
import main.model.dtos.VehicleDto;
import main.services.VehicleService;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.cxf.rs.security.cors.LocalPreflight;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/vehicles")
@CrossOriginResourceSharing(allowAllOrigins = true)
public class VehiclesRest {

  private VehicleService vehicleService;

  public void setVehicleService(final VehicleService vehicleService) {
    this.vehicleService = vehicleService;
  }

  @GET
  @Path("/{id}")
  @Produces("application/json")
  public Response getVehicle(@PathParam("id") String id) {
    return Response.ok(new VehicleDto(vehicleService.findById(Integer.valueOf(id)))).build();
  }

  @GET
  @Path("/type/{type}")
  @Produces("application/json")
  public Response filterVehicles(@PathParam("type") String type) {
    return Response.ok(vehicleService.filterByType(type)).build();
  }

  @GET
  @Path("/from/user/{email}")
  @Produces("application/json")
  @CrossOriginResourceSharing(allowAllOrigins = true)
  public Response getVehiclesFromUser(@PathParam("email") String email) {
    List<VehicleDto> vehiclesDto = new ArrayList<>();
//      vehicleService.getVehiclesFrom(Integer.valueOf(id))
//      .stream()
//      .map(elt -> new VehicleDto(elt))
//      .collect(Collectors.toList());

    for (Vehicle vehicle: vehicleService.getVehiclesFromEmail(email)) {
      vehiclesDto.add(new VehicleDto(vehicle));
    }

    return Response.ok(vehiclesDto).build();
  }

  @POST
  @Consumes("application/json")
  @Produces("application/json")
  public Response createVehicle(VehicleDto vDto) {
    return Response.ok(vehicleService.createVehicleFromDto(vDto)).build();
  }

  @PUT
  @Consumes("application/json")
  public Response modifyVehicle(VehicleDto vDto) {
    if (vehicleService.updateVehicleFromDto(vDto))
      return Response.ok().build();
    else
      return Response.notModified().build();
  }

  @DELETE
  @Path("/{id}")
  public Response deleteVehicle(@PathParam("id") String id) {
    if (vehicleService.disableVehicle(Integer.valueOf(id)))
      return Response.ok("deleted").build();
    else
      return Response.notModified("not deleted").build();
  }

}
