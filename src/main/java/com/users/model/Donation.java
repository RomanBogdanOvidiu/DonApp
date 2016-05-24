package com.users.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "donations", catalog = "donation", uniqueConstraints = @UniqueConstraint(columnNames = { "donationsTitle",
		"username" }))
public class Donation {

	private int donationsId;
	private String donationsTitle;
	private String donationsDesc;
	private String city;
	private User user;
	
	private List<Image> img=new ArrayList<Image>();

	public Donation() {
	}

	public Donation(String donationsTitle, int donationsId) {
		this.donationsTitle = donationsTitle;
		this.donationsId = donationsId;
	}

	public Donation(User user, String donations_title) {
		this.user = user;
		this.donationsTitle = donations_title;
	}

	@Column(name = "City", nullable = false)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "donationsTitle", nullable = false)
	public String getDonationsTitle() {
		return donationsTitle;
	}

	public void setDonationsTitle(String donationsTitle) {
		this.donationsTitle = donationsTitle;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "donationsId", unique = true, nullable = false)
	public int getDonationsId() {
		return donationsId;
	}

	public void setDonationsId(int donationsId) {
		this.donationsId = donationsId;
	}

	@Column(name = "donations_description", nullable = false)
	public String getDonationsDesc() {
		return donationsDesc;
	}

	public void setDonationsDesc(String donationsDesc) {
		this.donationsDesc = donationsDesc;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "donation", cascade = CascadeType.REMOVE)
	public List<Image> getImg() {
		return img;
	}

	public void setImg(List<Image> img) {
		this.img = img;
	}

	

}
