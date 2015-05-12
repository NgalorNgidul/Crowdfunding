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
	@Path("/{session}/listAllPlanByOwner")
	@Produces("application/json")
	public List<InvestDto> listAllPlanByOwner(
			@PathParam("session") String session) {
		return investManagement.listAllPlanByOwner(session);
	}

	@GET()
	@Path("/{session}/listAllPlan")
	@Produces("application/json")
	public List<InvestDto> listAllPlan(@PathParam("session") String session) {
		return investManagement.listAllPlan(session);
	}

}
