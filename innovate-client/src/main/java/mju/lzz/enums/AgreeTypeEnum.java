package mju.lzz.enums;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-28 19:17
 **/
public enum  AgreeTypeEnum {

	AGREE(1, "同意邀请"),
	NOT_AGREE(2, "不同意"),
	NOT_DEAL(0, "未处理");

	private int id;
	private String desc;

	AgreeTypeEnum(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public static String getDescById(int id) {
		for (AgreeTypeEnum agreeTypeEnum:AgreeTypeEnum.values()) {
			if (id == agreeTypeEnum.getId()){
				return agreeTypeEnum.desc;
			}
		}
		return "";
	}
}
