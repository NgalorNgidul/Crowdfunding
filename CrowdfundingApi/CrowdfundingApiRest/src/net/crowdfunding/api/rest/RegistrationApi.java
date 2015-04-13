package net.crowdfunding.api.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

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

}
