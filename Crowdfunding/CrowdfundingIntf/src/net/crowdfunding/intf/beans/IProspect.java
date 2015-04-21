package net.crowdfunding.intf.beans;

import java.util.List;

import net.crowdfunding.intf.model.Prospect;

public interface IProspect {
	Prospect get(long id);

	List<Prospect> listAll();

	List<Prospect> find(String textKey);
}
