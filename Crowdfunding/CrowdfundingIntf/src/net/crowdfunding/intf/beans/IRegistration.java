package net.crowdfunding.intf.beans;

import net.crowdfunding.intf.model.Registration;

public interface IRegistration {
	public Registration get(String key);

	public void save(Registration reg);
}
