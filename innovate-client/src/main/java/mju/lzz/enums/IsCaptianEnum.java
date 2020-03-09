package mju.lzz.enums;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-03-05 13:11
 **/
public enum  IsCaptianEnum {

	CAPTIAN(1, "yes"),
	NOT_CAPTIAN(0, "no");

	private int id;
	private String desc;

	public int getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	IsCaptianEnum(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}
}
