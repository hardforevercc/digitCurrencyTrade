package com.okex.trande.enums;

public enum OkexEnum {
	OKEXTRADE("8d8f9938-3e93-40b7-bb42-cedcbf49235a","D5E9619FE8646D4B3BDB90CC83B62CE7","查询api"),
	OKEXQUERY("ca481c37-aa5f-48f4-864d-3728411b5a7c","3D913940E284DB838365567C7D04230B","交易api");
	
	private String api_key;
	private String secret_key;
	private String desc;
	
	private OkexEnum(String api_key, String secret_key, String desc) {
		this.api_key = api_key;
		this.secret_key = secret_key;
		this.desc = desc;
	}
	public String getApi_key() {
		return api_key;
	}
	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	public String getSecret_key() {
		return secret_key;
	}
	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
