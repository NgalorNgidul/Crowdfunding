package net.crowdfunding.api.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import net.crowdfunding.api.intf.beans.InvestManagement;
import net.crowdfunding.api.intf.dto.InvestDto;

@Path("/invest")
public class InvestApi {

	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingApiEjb/InvestManagementImpl")
	InvestManagement investManagement;

	@POST()
	@Path("/save")
	@Consumes("application/json")
	public String savePlan(InvestDto dto) {
		return investManagement.savePlan(dto).toString();
	}

	@GET()
	@Path("/{session}/listAllPlanByOwner/{status}")
	@Produces("application/json")
	public List<InvestDto> listAllPlanByOwnerStatus(
			@PathParam("session") String session,
			@PathParam("status") int status) {
		return investManagement.listAllPlanByOwnerStatus(session, status);
	}

	@GET()
	@Path("/{session}/listAllPlan/{status}")
	@Produces("application/json")
	public List<InvestDto> listAllPlanByStatus(
			@PathParam("session") String session,
			@PathParam("status") int status) {
		return investManagement.listAllPlanByStatus(session, status);
	}

	@GET()
	@Path("/{session}/verify/{planid}")
	public String verify(@PathParam("session") String sessionName,
			@PathParam("planid") Long planId) {
		investManagement.verifyPlan(sessionName, planId);
		return "0";
	}

}
