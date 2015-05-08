package net.crowdfunding.api.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

	@POST()
	@Path("/{session}/save")
	@Consumes("application/json")
	public String save(ProspectDto prospect) {
		Long id = prospectManagement.save(prospect);
		return id == null ? "0" : id.toString();
	}

	@GET()
	@Path("/{session}/listAll")
	@Produces("application/json")
	public List<ProspectDto> listAll(@PathParam("session") String session) {
		return prospectManagement.listAll(session);
	}

	@GET()
	@Path("/{session}/listPublishApproval")
	@Produces("application/json")
	public List<ProspectDto> listPublishApproval(
			@PathParam("session") String session) {
		return prospectManagement.listPublishApproval(session);
	}

	@GET()
	@Path("/{session}/listAllByOwner")
	@Produces("application/json")
	public List<ProspectDto> listAllByOwner(@PathParam("session") String session) {
		return prospectManagement.listAllByOwner(session);
	}

	@GET()
	@Path("/{session}/approve/{prospectId}")
	public String approve(@PathParam("session") String session,
			@PathParam("prospectId") Long prospectId) {
		prospectManagement.approve(session, prospectId);
		return "0";
	}

}
