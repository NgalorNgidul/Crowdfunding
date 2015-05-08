package net.crowdfunding.intf.beans;

import java.util.List;

import net.crowdfunding.intf.model.Prospect;

public interface IProspect {
	long save(Prospect prospect);

	Prospect get(long id);

	List<Prospect> listAll();

	List<Prospect> listAllByOwner(long ownerId);
	
	List<Prospect> listByVerified(int status);

	List<Prospect> find(String textKey);

}
