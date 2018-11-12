package org.fkjava.menu.service;

import java.util.List;

import org.fkjava.common.data.domain.Result;
import org.fkjava.menu.domain.Menu;

public interface MenuService {

	void save(Menu menu);

	List<Menu> findTopMenus();

	Result move(String id, String targetId, String moveType);

	Result delete(String id);

}
