package com.EZ.spring.notice.Service;

import java.util.List;
import java.util.Map;

import com.EZ.spring.notice.domain.NoticeVO;

public interface NoticeService {
/**
 * 공지사항 등록 Service
 * @param notice
 * @return int
 */
	int insertNotice(NoticeVO notice);
/**
 * 공지사항 목록 조회 Service
 * @return List<NoticeVO>
 */

	List<NoticeVO> selectNoticeList(int currentpage);
/**
 * 공지사항 페이징 Service
 * @return int
 */
	int getTotalCount();


/**
 * 	
 * @param noticeNo
 * @return
 */
	NoticeVO selectOneByNo(int noticeNo);

/**
 * 	
 * @param noticeNo
 * @return
 */
	int deleteNotice(Integer noticeNo);

/**
 * 	
 * @param notice
 * @return
 */
	int updateNotice(NoticeVO notice);
	
	/**
	 * 
	 * @param paramMap
	 * @return
	 */
List<NoticeVO> searchNoticeByKeyword(Map<String, String> paramMap);
}
