package com.splitmoney.utils;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.splitmoney.resources.GroupResource;

//@ApplicationPath("/")
public class SplitApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(GroupResource.class);
		
		return classes;
		
	}
}
