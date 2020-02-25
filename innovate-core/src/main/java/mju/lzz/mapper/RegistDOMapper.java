package mju.lzz.mapper;

import mju.lzz.beans.Regist;

import java.util.List;

public interface RegistDOMapper {
	List<Regist> query(Regist query);
	int saveRegist(Regist query);
}
