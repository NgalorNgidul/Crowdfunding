package net.crowdfunding.api.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import net.crowdfunding.api.intf.beans.MemberManagement;
import net.crowdfunding.api.intf.dto.MemberDto;
import net.crowdfunding.intf.model.Member;

@Path("/member")
public class MemberApi {

	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingApiEjb/MemberManagementImpl")
	MemberManagement memberManagement;

	@GET()
	@Path("/get/{memberId}")
	@Produces("application/json")
	public Member get(@PathParam("memberId") Long memberId) {
		return memberManagement.get(memberId);
	}

	@POST()
	@Path("/save")
	@Consumes("application/json")
	public String save(MemberDto member) {
		return memberManagement.save(member).toString();
	}

	@GET()
	@Path("/getBySession/{sessionName}")
	@Produces("application/json")
	public Member getBySession(@PathParam("sessionName") String sessionName) {
		return memberManagement.getBySession(sessionName);
	}

	@GET()
	@Path("/listAll/{sessionName}")
	@Produces("application/json")
	public List<Member> listAll(@PathParam("sessionName") String sessionName) {
		return memberManagement.listAll(sessionName);
	}

}
