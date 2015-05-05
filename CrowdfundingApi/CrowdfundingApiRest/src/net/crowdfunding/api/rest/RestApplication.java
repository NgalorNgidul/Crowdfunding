package net.crowdfunding.api.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

@ApplicationPath("/")
public class RestApplication extends Application {
	private Set<Object> singletons = null;
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public RestApplication() {
		classes.add(ProspectApi.class);
		classes.add(RegistrationApi.class);
		classes.add(MemberApi.class);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		if (singletons == null) {
			CorsFilter corsFilter = new CorsFilter();
			corsFilter.getAllowedOrigins().add("*");
			singletons = new HashSet<>();
			singletons.add(corsFilter);
		}
		return singletons;
	}

}
