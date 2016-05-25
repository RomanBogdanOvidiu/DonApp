package com.users.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "imagine", catalog = "donation", uniqueConstraints = @UniqueConstraint(columnNames = { "imgId",
		"donationsId" }))
public class Imagine implements Serializable{

	private int imgId;

	private byte[] imgB;

	private Donation donation;

	public Imagine() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "imgId", unique = true, nullable = false)
	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	@Lob
	@Column(name = "ImageBlob", nullable = false)
	public byte[] getImgB() {
		return imgB;
	}

	public void setImgB(byte[] imgB) {
		this.imgB = imgB;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "donationsId", nullable = false)
	public Donation getDonation() {
		return donation;
	}

	public void setDonation(Donation donation) {
		this.donation = donation;
	}

}
