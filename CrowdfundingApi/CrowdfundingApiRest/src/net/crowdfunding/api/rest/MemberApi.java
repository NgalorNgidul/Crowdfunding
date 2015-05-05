package net.crowdfunding.api.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import net.crowdfunding.api.intf.beans.MemberManagement;
import net.crowdfunding.intf.model.Member;

@Path("/member")
public class MemberApi {

	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingApiEjb/MemberManagementImpl")
	MemberManagement memberManagement;

	@GET()
	@Path("/getBySession/{sessionName}")
	@Produces("application/json")
	public Member getBySession(@PathParam("sessionName") String sessionName) {
		return memberManagement.getBySession(sessionName);
	}

}
