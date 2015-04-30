package net.crowdfunding.api.intf.beans;

import net.crowdfunding.api.intf.dto.RegistrationDto;

public interface RegistrationManagement {
	String register(RegistrationDto reg);

	RegistrationDto validate(String key);

	String registerMember(RegistrationDto reg);
}
