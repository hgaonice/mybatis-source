package org.apache.ibatis.test;

import java.util.List;

/**
 * @author gaoh
 * @version 1.0
 * @date 2020/1/14 15:11
 */
public interface BlogsMapper {

  List<Blogs> selectAll();

}
