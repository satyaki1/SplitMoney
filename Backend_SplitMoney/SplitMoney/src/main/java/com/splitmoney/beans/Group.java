package com.splitmoney.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group {

	private String groupId;
	private String groupName;
	private Set<User> users;
	private List<Link> links = new ArrayList<Link>();
	
	public Group() {
		super();
	}
	
	public Group(String groupId, String groupName) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
	}

	
	public Group(String groupId, String groupName, Set<User> users) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.users = users;
	}

	public String getGroupId() {
		return groupId;
	}

	@XmlElement
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	@XmlElement
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Set<User> getUsers() {
		return users;
	}

	@XmlElement
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public void addUser(User user){
		this.users.add(user);
	}
	
	public void addLink(String uri, String ref){
		links.add(new Link(uri, ref));
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
}
