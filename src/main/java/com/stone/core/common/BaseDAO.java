package com.stone.core.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Spring 3.1.2.RELEASE
 * 
 * @author Peter
 *
 */
public class BaseDAO extends SqlMapClientDaoSupport {

	@Autowired
	@Qualifier("sqlMapClient")
	public void setSqlMapClientForAutowired(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

}
