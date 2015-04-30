package net.crowdfunding.intf.beans;

import java.util.Date;

import net.crowdfunding.intf.model.Registration;

public interface IRegistration {
	public Registration get(String email, Date timestamp);

	public Registration get(String key);

	public void save(Registration reg);
}
