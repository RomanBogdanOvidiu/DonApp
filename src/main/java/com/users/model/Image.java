package com.users.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "image", catalog = "donation")
public class Image {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "imgId", unique = true, nullable = false)
	private int imgId;

	@Column(name = "ImageBlob", nullable = false)
	private Blob imgB;

	private Donation donation;

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public Blob getImgB() {
		return imgB;
	}

	public void setImgB(Blob imgB) {
		this.imgB = imgB;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "donationsId", nullable = false)
	public Donation getDonation() {
		return donation;
	}

	public void setDonation(Donation donation) {
		this.donation = donation;
	}

}
