package com.okex.trande.enums;

public enum OkexEnum {
	OKEXTRADE("48c568ca-cddf-4403-aa40-b064a6885271","455DB684D0302CE19AB99D3C2CA269A4","交易api"),
	OKEXQUERY("ca481c37-aa5f-48f4-864d-3728411b5a7c","3D913940E284DB838365567C7D04230B","交易api"),
	OKEXTRADESHU("4a2e9cc3-cbc5-4af0-8270-b1a35b9b54f8","C4E5D9908B6948BE7EBC0AC766B6250E","山狗的API");
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
