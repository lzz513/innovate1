package mju.lzz.enums;

public enum RegistStatusEnum {

	WAIT(0, "等待队友确认"),
	SUCCESS(1, "报名成功"),
	FAIL(2, "报名失败");

	private int id;
	private String desc;

	public int getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	RegistStatusEnum(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}
}
