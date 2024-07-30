package com.EZ.spring.notice.Store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.EZ.spring.notice.domain.NoticeVO;

public interface NoticeStore {
/**
 * 공지사항 등록 Store
 * @param notice
 * @return int
 */
public int insertNotice(SqlSession session,NoticeVO notice);

/**
 * 공지사항 목록 조회
 * @param session 
 * @return List<NoticeVo>
 */
public List<NoticeVO> selectList(SqlSession session,int currentPage);

/**
 * 공지사항 페이징 
 * @param session
 * @return int
 */
public int getTotalCount(SqlSession session);

/**
 * 공지사항 목록 삭제 Store
 * @param session
 * @param noticeNo
 * @return
 */
public NoticeVO selectOneByNo(SqlSession session, int noticeNo);

/**
 * 공지사항 목록 삭제 Store
 * @param noticeNo
 * @param session
 * @return
 */
public int deleteNotice(Integer noticeNo, SqlSession session);
/**
 * 
 * @param notice
 * @param session
 * @return
 */
public int updateNotice(NoticeVO notice, SqlSession session);

/**
 * 공지사항 검색 Store
 * @param session
 * @param paramMap
 * @return
 */
public List<NoticeVO> selectSearchList(SqlSession session, Map<String, String> paramMap);
	
}
