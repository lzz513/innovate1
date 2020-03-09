package mju.lzz.enums;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-27 19:21
 **/
public enum MessageTypeEnum {

	INVITE_MESSAGE(1, "invite"),
	RESULT_MESSAGE(2, "result");

	private int id;
	private String desc;

	MessageTypeEnum(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
