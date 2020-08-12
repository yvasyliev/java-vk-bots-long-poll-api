package bots.longpoll.sdk.model.other;

import bots.longpoll.sdk.model.update.UpdateObject;
import com.google.gson.annotations.SerializedName;

public class VkpayTransaction extends UpdateObject {
	@SerializedName("from_id")
	private Integer fromId;
	@SerializedName("amount")
	private Integer amount;
	@SerializedName("description")
	private String description;
	@SerializedName("date")
	private Integer date;

	public Integer getFromId() {
		return fromId;
	}

	public VkpayTransaction setFromId(Integer fromId) {
		this.fromId = fromId;
		return this;
	}

	public Integer getAmount() {
		return amount;
	}

	public VkpayTransaction setAmount(Integer amount) {
		this.amount = amount;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public VkpayTransaction setDescription(String description) {
		this.description = description;
		return this;
	}

	public Integer getDate() {
		return date;
	}

	public VkpayTransaction setDate(Integer date) {
		this.date = date;
		return this;
	}
}
