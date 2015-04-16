package net.crowdfunding.api.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
	public String validate(@PathParam("validationKey") String validationKey) {
		Integer result = registrationManagement.validate(validationKey);
		return result.toString();
	}

}
