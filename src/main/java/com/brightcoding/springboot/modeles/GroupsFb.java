package com.brightcoding.springboot.modeles;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity(name = "groups_fb")
public class GroupsFb implements Serializable{

	private static final long serialVersionUID = -8944801824466609933L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(length = 50, nullable = false)
	@NotBlank
	private String groupeId;
	
	@Column(length = 30, nullable = false)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "gourps_users", joinColumns = {@JoinColumn(name ="group_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
	private Set<Users> users = new HashSet<>();
}
