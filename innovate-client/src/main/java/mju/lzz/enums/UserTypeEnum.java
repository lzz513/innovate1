package mju.lzz.enums;

public enum UserTypeEnum {
	STUDENT(1, "this is a student"),
	TEACHER(2, "this is a teacher");

	private int id;
	private String desc;

	UserTypeEnum(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}
}
