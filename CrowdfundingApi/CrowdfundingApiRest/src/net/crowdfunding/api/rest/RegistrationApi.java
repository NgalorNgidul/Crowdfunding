package net.crowdfunding.api.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import net.crowdfunding.api.intf.beans.RegistrationManagement;
import net.crowdfunding.api.intf.dto.RegistrationDto;

@Path("/registration")
public class RegistrationApi {

	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingApiEjb/RegistrationManagementImpl")
	RegistrationManagement registrationManagement;

	@POST()
	@Path("/create")
	@Consumes("application/json")
	public String create(RegistrationDto dto) {
		registrationManagement.register(dto);
		return "";
	}

	@GET()
	@Path("/validate/{validationKey}")
	@Produces("application/json")
	public RegistrationDto validate(
			@PathParam("validationKey") String validationKey) {
		return registrationManagement.validate(validationKey);
	}

	@POST()
	@Path("/confirmmember")
	@Consumes("application/json")
	public String confirmMember(RegistrationDto dto) {
		registrationManagement.registerMember(dto);
		return "";
	}

}
