package net.crowdfunding.api.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import net.crowdfunding.api.intf.beans.ProspectManagement;
import net.crowdfunding.api.intf.dto.ProspectDto;

@Path("/prospect")
public class ProspectApi {

	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingApiEjb/ProspectManagementImpl")
	ProspectManagement prospectManagement;

	@GET()
	@Path("/popular")
	@Produces("application/json")
	public List<ProspectDto> listPopuler() {
		return prospectManagement.listPopular();
	}

	@GET()
	@Path("/find/{textKey}")
	@Produces("application/json")
	public List<ProspectDto> find(@PathParam("textKey") String textKey) {
		return prospectManagement.find(textKey);
	}

	@GET()
	@Path("/{prospectId}")
	@Produces("application/json")
	public ProspectDto get(@PathParam("prospectId") Long prospectId) {
		return prospectManagement.get(prospectId);
	}

	@GET()
	@Path("/{session}/listAll")
	@Produces("application/json")
	public List<ProspectDto> listAll(@PathParam("session") String session) {
		return prospectManagement.listAll(session);
	}

	@GET()
	@Path("/{session}/listAllByOwner")
	@Produces("application/json")
	public List<ProspectDto> listAllByOwner(@PathParam("session") String session) {
		return prospectManagement.listAllByOwner(session);
	}


}
