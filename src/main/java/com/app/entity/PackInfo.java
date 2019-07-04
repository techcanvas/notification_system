package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="pack_info")
public class PackInfo {

	public PackInfo(){
		
	}
	
	@Id
	private Long packid;

	private String packName;

	private String packInfo;

	public Long getId() {
		return packid;
	}

	public void setId(Long packid) {
		this.packid = packid;
	}

	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	public String getPackInfo() {
		return packInfo;
	}

	public void setPackInfo(String packInfo) {
		this.packInfo = packInfo;
	}



}